package cn.andios.jvm.classloader;

/**
 * @description:
 * @author:LSD
 * @when:2020/01/11/15:44
 */
public class MyTest19_2 {
    public static void main(String[] args) throws Exception {
        //
        MyTest16 loader1 = new MyTest16("loader1");
        loader1.setPath("G:\\");

        Class<?> clazz = loader1.loadClass("cn.andios.jvm.classloader.MyTest1");

        System.out.println("clazz："+clazz.hashCode());
        System.out.println("clazz loader："+clazz.getClassLoader());
    }
}
