package cn.andios.jvm.classloader;

import java.io.*;

/**
 * @description:自定义类加载器
 * @author:LSD
 * @when:2020/01/09/19:41
 */
public class MyTest16 extends ClassLoader{
    private String classLoaderName;
    private final String fileExtension = ".class";

    /**
     * 默认父加载器为SystemClassLoader
     * @param classLoaderName
     */
    public MyTest16(String classLoaderName){
        super();
        this.classLoaderName = classLoaderName;
    }

    /**
     * 父加载器为指定的parent
     * @param parent
     * @param classLoaderName
     */
    public MyTest16(ClassLoader parent,String classLoaderName){
        super(parent);
    }

    @Override
    protected Class<?> findClass(String className) throws ClassNotFoundException {
        byte[] data = loadClassData(className);
        return this.defineClass(className,data,0,data.length);
    }

    /**
     * 加载类的信息以byte []形式返回
     * @param name
     * @return
     */
    private byte[] loadClassData(String name){
        //声明三个对象
        InputStream is = null;
        byte [] data = null;
        ByteArrayOutputStream baos = null;
        try {
            //将路径中的.替换为\\
            this.classLoaderName = this.classLoaderName.replace(".","\\");
            //字节流读取文件
            is = new FileInputStream(new File(name+this.fileExtension));
            int ch = 0;
            while ((ch = is.read())!= -1){
                baos.write(ch);
            }
            //将读取到的字节流转为字节数组
            assert baos != null;
            data = baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            //关闭资源
            try {
                assert is != null;
                is.close();
                assert baos != null;
                baos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return  data;
    }

    public static void main(String[] args) throws Exception {
        MyTest16 loader1 = new MyTest16("loader1");
        test(loader1);
    }
    public static void test(ClassLoader classLoader) throws Exception {
        Class<?> clazz = classLoader.loadClass("cn.andios.jvm.classloader.MyTest1");
        Object obj = clazz.newInstance();
        System.out.println(obj);
        System.out.println(obj.getClass());
        System.out.println(obj.getClass().getClassLoader());

    }

}
