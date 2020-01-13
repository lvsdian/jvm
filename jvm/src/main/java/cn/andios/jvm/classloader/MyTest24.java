package cn.andios.jvm.classloader;

/**
 * @description:
 * @author:LSD
 * @when:2020/01/13/17:24
 */

/**
 * 在运行期，一个java类是由该类的完全限定名(binary name)和用于加载该类的定义类加载器(defining classloader)所共同决定的
 * 如果同样名字(相同限定名)的类是由两个不同的类加载器所加载，那么这些了就是不同的，即使.class文件完全一样，并且从同一位置加载亦如此
 */
public class MyTest24 {
    public static void main(String[] args) {
        /**
         * result:
         *      ...
         * 如果在target的classes目录下执行：
         * java cn/andios/jvm/classloader/MyTest24
         * 可以发现执行结果与上面的result不同
         *  reason:
         *      idea会自己往classpath里面追加一些内容
         * 如果在target的classes下执行:
         * java -Dsun.boot.class.path=./ cn.andios/jvm.classloader.MyTest24
         * 即把启动类加载器的加载目录改到当前目录，
         * result：
         *      Error occurred during initialization of VM
         *      java/lang/NoClassDefFoundError: java/lang/Object
         * 因为Object类是父类，加载时肯定会先加载Object,但此目录下并没有Object
         */
        //Bootstrap ClassLoader
        System.out.println(System.getProperty("sun.boot.class.path"));

        //Extension ClassLoader
        System.out.println(System.getProperty("java.ext.dirs"));

        //App ClassLoader
        System.out.println(System.getProperty("java.class.path"));

        /**
         * 如果在idea中运行，result:
         *      sun.misc.Launcher$AppClassLoader@18b4aac2
         * 因为没有指定 java.system.class.loader
         * 如果在target下的classes目录执行：
         *      java -Djava.system.class.loader=cn.andios.jvm.classloader.MyTest16  cn.andios.jvm.classloader.MyTest24
         * 即指定java.system.class.loader(注意：MyTest16中要定义公共的接收ClassLoader对象为唯一参数的构造器)，那么result：
         *      cn.andios.jvm.classloader.MyTest16@6d06d69c
         *
         */
        System.out.println(ClassLoader.getSystemClassLoader());
    }
}
