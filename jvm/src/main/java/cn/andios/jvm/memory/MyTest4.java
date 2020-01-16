package cn.andios.jvm.memory;

import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

/**
 * @description:
 * @author:LSD
 * @when:2020/01/16/17:38
 */
public class MyTest4 {
    /**
     * 方法区产生内存溢出
     * 借助cglib完成类的动态创建，它可以在程序运行期动态生成类，这些生成类的元数据
     * 就会在元空间中，元空间随着动态生成类的增加，元信息数量也会增加。
     * 在jdk8中，元空间默认大小是21M，如果数据>=21M，会进行垃圾回收，如果回收后还不够，就会
     * 进行扩容。上限就是物理内存大小，当达到上限后，就会产生溢出。
     * 这里可以固定元空间大小，让它不进行扩容：-XX:MaxMetaspaceSize=10M
     */
    public static void main(String[] args) {
        for (;;){
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(MyTest4.class);
            enhancer.setUseCache(false);
            enhancer.setCallback((MethodInterceptor)(obj, method, argsl, proxy)->
                        proxy.invokeSuper(obj,argsl));
            System.out.println("hello");
            //不断创建MyTest4的子类
            enhancer.create();
        }
    }
}
