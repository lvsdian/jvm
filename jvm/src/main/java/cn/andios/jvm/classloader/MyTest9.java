package cn.andios.jvm.classloader;

/**
 * @description:
 * @author:LSD
 * @when:2020/01/02/11:54
 */
public class MyTest9 {
    static {
        System.out.println("myTest9 static block");
    }

    public static void main(String[] args) {
        /**
         * result:
         *      myTest9 static block
         *      parent static block
         *      child static block
         *      4
         * 加载顺序：MyTest9     MyParent9   MyChild9
         */
        System.out.println(MyChild9.b);
    }
}

class  MyParent9{
    static int a = 3;
    static {
        System.out.println("parent static block");
    }
}
class MyChild9 extends MyParent9{
    static int b = 4;
    static {
        System.out.println("child static block");
    }
}