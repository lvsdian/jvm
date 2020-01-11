package cn.andios.jvm.classloader;

/**
 * @description:类卸载，加jvm参数：-XX:+TraceClassUnloading
 * @author:LSD
 * @when:2020/01/11/11:24
 */
public class MyTest17 {
    public static void main(String[] args) throws Exception {
        /**
         * result:
         *      ====================
         *      load cn.andios.jvm.classloader.MyTest1 findClass invoked & classLoaderName：loader1
         *      [Unloading class cn.andios.jvm.classloader.MyTest1 0x0000000100061028]
         *
         * 查看类卸载的虚拟机参数：-XX:+TraceClassUnloading
         *
         * 第一句是MyTest16中findClass方法打印的(如果打印了这句话，
         *  表示由我们自己的类加载器加载，如果没打印，表示由jvm的类加载器加载)，
         * 第二句即表示类卸载，
         * test1方法中的MyTest17由jvm的类加载器加载，所以不会发生类卸载
         * test2中用我们自定义的MyTest16类加载器，所以会发生类卸载
         */
        //发生类卸载
        test1();
        System.out.println("====================");
        //不会发生类卸载
        test2();
    }
    private static void test1() throws Exception {
        MyTest17 test1 = new MyTest17();
        test1 = null;
        System.gc();
    }
    private static void test2() throws Exception {
        MyTest16 myLoader = new MyTest16("myLoader");
        /**
         * 删掉target下的MyTest.class文件才会使用我们自己的类加载器，
         * 否则会使用jvm的类加载器，删除target下的MyTest.class后，
         * 需要制定myLoader.setPath，否则报FileNotFoundException
         */
        myLoader.setPath("G:\\");
        Class<?> clazz = myLoader.loadClass("cn.andios.jvm.classloader.MyTest1");

        myLoader = null;
        clazz = null;

        System.gc();
    }
}
