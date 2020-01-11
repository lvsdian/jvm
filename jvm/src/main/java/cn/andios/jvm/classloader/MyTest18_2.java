package cn.andios.jvm.classloader;

/**
 * @description:命名空间
 * @author:LSD
 * @when:2020/01/11/14:01
 */
public class MyTest18_2 {
    public static void main(String[] args) throws Exception {
        MyTest16 loader1 = new MyTest16("loader1");
        //拷贝target下面的MySample.class和MyCat.class到G盘如下目录
        //删掉target下面的MySample.class和MyCat.class
        //指定loader的path为G盘
        //所以这里加载MySample和MyCat是用的我们自定义的类加载器MyTest16

        //如果只删掉target下面的MyCat.class文件，不删除MySample.class，执行时
        //MySample被AppClassLoader正常加载，但MyCat会报错ClassNotFoundException

        //如果只删掉target下面的MySample.class，保留MyCat.class文件，执行时
        //MySample会由自定义类加载器MyTest16加载，MyCat由AppClassLoader加载
        //这种情况下，
        //      如果MyCat中有对MySample的引用(如MyCat构造器中打印MySample.class，
        //  此时MySample中没有打印MyCat.class，只有new MyCat() )
        //  就会报错MySample ClassNotFoundException,原因：MyCat由AppClassLoader加载，
        //  MySample由MyTest16加载，AppClassLoader是MyTest16的父类，所以由
        //  AppClassLoader所加载的类当中就看不到子类MyTest16所加载的类.
        //      如果MySample中有对MyCat的引用(如MySample构造器中打印MyCat.class)
        //  执行时并不会报错，MySample中会正常打印MyCat.class.即在子类加载器加载的类中
        //  访问父类加载器加载的类

        /**
         * 以上总结：
         *      子类加载器所加载的类可以访问父类加载器所加载的类
         *      父类加载器所加载的类不能访问子类加载器所加载的类
         */
        loader1.setPath("G:\\");
        Class<?> clazz = loader1.loadClass("cn.andios.jvm.classloader.MySample");
        System.out.println("class:"+ clazz.hashCode());

        //如果注释这一行，那么并不会对MySample实例化，即MySample构造方法不会被调用，
        //因此不会实例化MyCat对象，即没有对MyCat进行主动使用，这里就不会加载MyCat
        Object obj = clazz.newInstance();
    }
}
