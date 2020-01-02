package cn.andios.jvm.classloader;

/**
 * @description:
 * @author:LSD
 * @when:2020/01/02/12:04
 */
public class MyTest10 {
    static {
        System.out.println("MyTest10 static block");
    }
    public static void main(String[] args) {
        /**
         * result:
         *      MyTest10 static block
         *      ---------------
         *      MyParent10 static block
         *      ***************
         *      3
         *      +++++++++++++++
         *      MyChild10 static block
         *      4
         */
        MyParent10 myParent10;
        System.out.println("---------------");
        myParent10 = new MyParent10();
        System.out.println("***************");
        System.out.println("+++++++++++++++");
        System.out.println(MyChild10.b);

        /**
         * 这里通过对象访问成员变量，编译器报错，但可以运行
         */
        int f = new MyParent10().a;
        System.out.println("f:"+f);
    }
}
class  MyParent10{
    static int a = 3;
    static {
        System.out.println("MyParent10 static block");
    }
}
class MyChild10 extends MyParent10{
    static int b = 4;
    static {
        System.out.println("MyChild10 static block");
    }
}