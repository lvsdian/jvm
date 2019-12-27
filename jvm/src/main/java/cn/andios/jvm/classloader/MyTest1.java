package cn.andios.jvm.classloader;

/**
 * @Author: lsd
 * @Description:
 * @Date: 2019/12/27 上午8:39
 *
 *  -XX:+ TraceClassLoading:用于追踪类的加载信息并打印出来
 *
 *  -XX:+<option>表示开启option
 *  -XX:-<option>表示关闭option
 *  -xx:<option>=<value>表示将option选项值设置为value
 */
public class MyTest1 {
    public static void main(String[] args) {
        System.out.println(MyChild1.str);
//        result:
//          MyParent1 static block
//          hello world
//        reason:
//          对于静态字段来说,只有直接定义了该字段的类才会被初始化,这里只是主动使用MyParent1,并没有主动使用MyChild1,
//        虽说是通过子类的名字来引用str,但str是父类定义的.所以不会初始化MyChild1

        //System.out.println(MyChild1.str2);
//        result:
//          MyParent1 static block
//          MyChild1 static block
//          hello
//        reason:
//          str2在子类中定义,相当于对子类的主动使用,所以会先初始化父类,再初始化子类

    }
}

class MyParent1{
    public static String str = "hello world";
    static {
        System.out.println("MyParent1 static block");
    }
}

class MyChild1 extends MyParent1{
    public static  String str2 = "hello";
    static {
        System.out.println("MyChild1 static block");
    }
}