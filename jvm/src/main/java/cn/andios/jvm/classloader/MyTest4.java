package cn.andios.jvm.classloader;

import java.util.UUID;

/**
 * @Author: lsd
 * @Description:
 * @Date: 2019/12/27 下午7:30
 *
 * 对于数组实例来说,其类型是由jvm在运行期动态生成的,
 * 动态生成的类型,其父类型就是Object
 *
 * 对于数组来说,javadoc经常将构成数组的元素成为component,实际上就是将数组降低一个维度后的类型
 *
 * 通过 javap-c xxx.class 反编译,可以看到两个助记符
 *      anewarray:表示创建一个引用类型(类\接口\数组)的数组,并将其值压入栈顶
 *      newarray:表示创建一个指定的原始类型(int\float\char)的数组,并将其值压入栈顶
 */
public class MyTest4 {
    public static void main(String[] args) {
        //MyParent4 myParent4 = new MyParent4();
//        result:
//          MyParent4  static block
//        reason:
//          表示对类MyParent4的主动使用

        MyParent4 [] myParents1 = new MyParent4[5];
//        result:
//          (没有输出)
//        reason:
//          不表示对MyParent4的主动使用,
        System.out.println(myParents1.getClass());//class [Lcn.andios.jvm.classloader.MyParent4;
        System.out.println(myParents1.getClass().getSuperclass());//class java.lang.Object

        MyParent4 [][] myParents2 = new MyParent4[5][5];
//        result:
//          (没有输出)
//        reason:
//          不表示对MyParent4的主动使用,
        System.out.println(myParents2.getClass());//class [[Lcn.andios.jvm.classloader.MyParent4;
        System.out.println(myParents2.getClass().getSuperclass());//class java.lang.Object

        int [] arr = new int[5];
        System.out.println(arr.getClass());//class [I
        System.out.println(arr.getClass().getSuperclass());//class java.lang.Object
    }
}
class MyParent4{
    static {
        System.out.println("MyParent4  static block");
    }
}