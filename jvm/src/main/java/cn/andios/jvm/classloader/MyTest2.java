package cn.andios.jvm.classloader;
/**
 * @Author: lsd
 * @Description:
 * @Date: 2019/12/27 上午10:01
 *
 * 常量在编译阶段会存入到调用这个常量的方法所在的类的常量池中,
 * 本质上,调用类并没有直接引用到定义常量的类,因此并不会触发定义常量的类初始化
 *
 * 注意:这里指的是将常量存入到MyTest2的常量池中,之后MyTest2与MyParent2就没有
 *      任何关系了,甚至,可以将MyParent2的.class文件删除
 *
 *  助记符(javap -c  xxx.class反编译命令后的结果)
 *      ldc:表示将int,float或者是String类型的常量值从常量池中推送至栈顶
 *      bipush:表示将单字节(-128-127)的常量值推送到栈顶
 *      sipush:表示将一个短整型常量值(-32768-32767)推送至栈顶
 *      iconst_1:表示将int类型1推送到栈顶(iconst_m1-iconst_5分别表示将int类型-1到5推送到栈顶,超过5就是bipush,低于-1就是bipush)
 */
public class MyTest2 {
    public static void main(String[] args) {
//        System.out.println(MyParent2.str);
//        result:
//          MyParent2 static block
//          hello world
        System.out.println(MyParent2.str2);
//        result:
//          hello
//        reason:
//          常量在编译阶段会存入到调用这个常量的方法所在的类的常量池中,本质上,调用类并没有直接引用到定义常量的类,因此并不会触发定义常量的类初始化
//        即str2在编译阶段会存入到MyTest2的常量池中,MyTest2并没有直接引用到MyParent2这个类,所以MyParent2类不会被初始化
//

//        System.out.println(MyParent2.s1);
//        System.out.println(MyParent2.s2);
//        System.out.println(MyParent2.s3);
    }
}

class MyParent2{
//    public static String str = "hello world";
    public static final String str2 = "hello ";
//    public static final short s1 = 127;
//    public static final short s2 = 128;
//    public static final short s3 = 1;
    static {
        System.out.println("MyParent2 static block");
    }
}