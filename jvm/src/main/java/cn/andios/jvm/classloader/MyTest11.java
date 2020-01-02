package cn.andios.jvm.classloader;

import javax.swing.text.AsyncBoxView;

/**
 * @description:
 * @author:LSD
 * @when:2020/01/02/12:46
 */
public class MyTest11 {
    public static void main(String[] args){
        //是对MyParent11的主动使用，不是对MyChild11的主动使用
        /**
         * result:
         *      MyParent11 static block
         *      3
         *      ---------------
         *      do something
         */
        System.out.println(MyChild11.a);
        System.out.println("---------------");
        MyChild11.doSomething();
    }
}

class  MyParent11{
    static int a = 3;
    static {
        System.out.println("MyParent11 static block");
    }
    static  void doSomething(){
        System.out.println("do something");
    }
}
class MyChild11 extends MyParent11{
    static {
        System.out.println("MyChild11 static block");
    }
}