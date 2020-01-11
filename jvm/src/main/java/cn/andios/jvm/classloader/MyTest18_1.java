package cn.andios.jvm.classloader;

/**
 * @description:
 * @author:LSD
 * @when:2020/01/11/13:33
 */
public class MyTest18_1 {
    public static void main(String[] args) throws Exception {
        MyTest16 loader1 = new MyTest16("loader1");
        //这里target下的MySample.class文件没有删，所以使用的jvm的类加载器
        Class<?> clazz = loader1.loadClass("cn.andios.jvm.classloader.MySample");
        System.out.println("class:"+ clazz.hashCode());

        //如果注释这一行，那么并不会对MySample实例化，即MySample构造方法不会被调用，
        //因此不会实例化MyCat对象，即没有对MyCat进行主动使用，这里就不会加载MyCat
        Object obj = clazz.newInstance();
    }
}
