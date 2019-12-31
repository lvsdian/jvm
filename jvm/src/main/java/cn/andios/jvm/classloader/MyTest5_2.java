package cn.andios.jvm.classloader;

/**
 * @description:
 * @author:LSD
 * @when:2019/12/31/13:51
 */
public class MyTest5_2 {
    public static void main(String[] args) {
        /**
         * result:
         *      hello_static
         *      hello_no_static
         *      TestBlock...
         *      hello_no_static
         *      TestBlock...
         * 这里两次调用TestBlock的构造方法
         * 结果：TestBlock中静态代码块会被调用一次，非静态代码块会被调用两次，
         *      并且非静态代码块在构造方法之前调用
         */
        new TestBlock();
        new TestBlock();
    }
}

class TestBlock{
    /**
     * 静态代码块
     */
    static {
        System.out.println("hello_static");
    }

    /**
     * 菲静态代码块
     */
    {
        System.out.println("hello_no_static");
    }

    public TestBlock(){
        System.out.println("TestBlock...");
    }
}