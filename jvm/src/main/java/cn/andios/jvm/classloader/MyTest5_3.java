package cn.andios.jvm.classloader;

import java.util.Random;

/**
 * @description:
 * @author:LSD
 * @when:2019/12/31/13:58
 */
public class MyTest5_3 {
    public static void main(String[] args) {
        /**
         * case:接口继承接口，变量值在编译期生成
         *
         * 加载：  MyTest5_3
         * 初始化：无
         * 编译后删除MyParent5_3_Interface，MyChild5_3_Interface_1两个class文件依旧可以运行
         *
         * reason：a的值在编译期确定，直接放到了MyTest5_3的常量池中，
         *      所以MyChild5_3_Interface_1不需要被加载，
         *      MyParent5_3_Interface更不需要被加载
         */
        //System.out.println(MyChild5_3_Interface_1.a);


        /**
         * case:接口继承接口，变量值在运行期生成
         *
         * 加载：MyTest5_3  MyParent5_3_Interface
         *      MyChild5_3_Interface_2   MyParent5_3_Interface$1
         *
         * 初始化：MyChild5_3_Interface_2
         */
        //System.out.println(MyChild5_3_Interface_2.a);


        /**
         * case:类实现接口，变量值在编译期生成 ,有final
         *
         * 加载：MyTest5_3
         *
         * 初始化：无
         */
        //System.out.println(MyChild5_3_Class_Impl_1.a);

        /**
         * case:类实现接口，变量值在运行期生成，有final
         *
         * 加载：MyTest5_3    MyParent5_3_Interface
         *      MyChild5_3_Class_Impl_2   MyParent5_3_Interface$1
         *
         * 初始化：MyChild5_3_Class_Impl_2
         */
        //System.out.println(MyChild5_3_Class_Impl_2.a);

        /**
         * case:类实现接口，变量值在编译期生成，无final
         *
         * 加载：MyTest5_3    MyParent5_3_Interface
         *      MyChild5_3_Class_Impl_3    MyParent5_3_Interface$1
         *
         * 初始化：MyChild5_3_Class_Impl_3
         *
         */
        //System.out.println(MyChild5_3_Class_Impl_3.a);


        /**
         * case:类实现接口，变量值在运行期生成，无final
         *
         * 加载：MyTest5_3  MyParent5_3_Interface
         *      MyChild5_3_Class_Impl_4  MyParent5_3_Interface$1
         *
         * 初始化：MyChild5_3_Class_Impl_4
         */
        //System.out.println(MyChild5_3_Class_Impl_4.a);

        /**
         * case:类继承父类，变量值在编译期生成，有final
         *
         * 加载：MyTest5_3
         *
         * 初始化：无
         */
        //System.out.println(MyChild5_3_Class_Extends_1.a);

        /**
         * case:类继承父类，变量值在运行期生成，有final
         *
         * 加载：MyTest5_3   MyParent5_3_Class
         *      MyChild5_3_Class_Extends_2  MyParent5_3_Class$1
         *
         * 初始化：MyParent5_3_Class   MyChild5_3_Class_Extends_2
         */
        //System.out.println(MyChild5_3_Class_Extends_2.a);

        /**
         * case:类继承父类，变量值在编译期生成，无final
         *
         * 加载：MyTest5_3    MyParent5_3_Class
         *      MyChild5_3_Class_Extends_3   MyParent5_3_Class$1
         * 初始化： MyParent5_3_Class   MyChild5_3_Class_Extends_3
         */
        //System.out.println(MyChild5_3_Class_Extends_3.a);


        /**
         * case:类继承父类，变量值在运行期生成，无final
         * 加载：MyTest5_3  MyParent5_3_Class
         *      MyChild5_3_Class_Extends_4  MyParent5_3_Class$1
         *
         * 初始化：MyParent5_3_Class  MyChild5_3_Class_Extends_4
         */
        System.out.println(MyChild5_3_Class_Extends_4.a);
    }
}
/**
 * 判断是否初始化：代码块是否执行
 * 判断是否被加载：-XX:+TraceClassLoading
 */
interface MyParent5_3_Interface{
    public static Thread thread = new Thread(){
        {
            System.out.println("MyParent5_3_Interface invoked...");
        }
    };
}
/**
 * 判断是否初始化：代码块是否执行
 * 判断是否被加载：-XX:+TraceClassLoading
 */
class MyParent5_3_Class{
    public static Thread thread = new Thread(){
        {
            System.out.println("MyParent5_3_Class invoked...");
        }
    };
}
/** 接口继承接口，变量值在编译期生成 */
interface MyChild5_3_Interface_1 extends MyParent5_3_Interface{
    int a = 5;
    public static Thread thread = new Thread(){
        {
            System.out.println("MyChild5_3_Interface_1 invoked...");
        }
    };
}
/** 接口继承接口，变量值在运行期生成 */
interface MyChild5_3_Interface_2 extends MyParent5_3_Interface{
    int a = new Random().nextInt(1);
    public static Thread thread = new Thread(){
        {
            System.out.println("MyChild5_3_Interface_2 invoked...");
        }
    };
}

/** 类实现接口，变量值在编译期生成 ,有final*/
class MyChild5_3_Class_Impl_1 implements MyParent5_3_Interface{
    public static final int a = 5;
    public static Thread thread = new Thread(){
        {
            System.out.println("MyChild5_3_Class_Impl_1 invoked...");
        }
    };
}
/** 类实现接口，变量值在运行期生成，有final*/
class MyChild5_3_Class_Impl_2 implements MyParent5_3_Interface{
    public static final int a = new Random().nextInt(1);
    public static Thread thread = new Thread(){
        {
            System.out.println("MyChild5_3_Class_Impl_2 invoked...");
        }
    };
}
/** 类实现接口，变量值在编译期生成，无final */
class MyChild5_3_Class_Impl_3 implements MyParent5_3_Interface{
    public static  int a = 5;
    public static Thread thread = new Thread(){
        {
            System.out.println("MyChild5_3_Class_Impl_3 invoked...");
        }
    };
}
/** 类实现接口，变量值在运行期生成，无final*/
class MyChild5_3_Class_Impl_4 implements MyParent5_3_Interface{
    public static  int a = new Random().nextInt(1);
    public static Thread thread = new Thread(){
        {
            System.out.println("MyChild5_3_Class_Impl_4 invoked...");
        }
    };
}

/** 类继承父类，变量值在编译期生成，有final */
class MyChild5_3_Class_Extends_1 extends MyParent5_3_Class{
    public static final int a = 5;
    public static Thread thread = new Thread(){
        {
            System.out.println("MyChild5_3_Class_Extends_1 invoked...");
        }
    };
}
/** 类继承父类，变量值在运行期生成，有final */
class MyChild5_3_Class_Extends_2 extends MyParent5_3_Class{
    public static final int a = new Random().nextInt(1);
    public static Thread thread = new Thread(){
        {
            System.out.println("MyChild5_3_Class_Extends_2 invoked...");
        }
    };
}
/** 类继承父类，变量值在编译期生成，无final */
class MyChild5_3_Class_Extends_3 extends MyParent5_3_Class{
    public static  int a = 5;
    public static Thread thread = new Thread(){
        {
            System.out.println("MyChild5_3_Class_Extends_3 invoked...");
        }
    };
}
/** 类继承父类，变量值在运行期生成，无final */
class MyChild5_3_Class_Extends_4 extends MyParent5_3_Class{
    public static  int a = new Random().nextInt(1);
    public static Thread thread = new Thread(){
        {
            System.out.println("MyChild5_3_Class_Extends_4 invoked...");
        }
    };
}
