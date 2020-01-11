package cn.andios.jvm.classloader;

import java.io.*;

/**
 * @description:自定义类加载器
 * @author:LSD
 * @when:2020/01/09/19:41
 */
public class MyTest16 extends ClassLoader{
    private String classLoaderName;
    /** 文件扩展名 */
    private final String fileExtension = ".class";
    /** 文件路径 */
    private String path;

    /**
     * 默认父加载器为SystemClassLoader的构造方法
     * @param classLoaderName
     */
    public MyTest16(String classLoaderName){
        super();
        this.classLoaderName = classLoaderName;
    }

    /**
     * 父加载器为指定的parent的构造方法
     * @param parent
     * @param classLoaderName
     */
    public MyTest16(ClassLoader parent,String classLoaderName){
        super(parent);
    }

    public void setPath(String path) {
        this.path = path;
    }

    /**
     * 重写父类的findClass方法
     * @param className
     * @return
     * @throws ClassNotFoundException
     */
    @Override
    protected Class<?> findClass(String className) throws ClassNotFoundException {
        //如果这句话打印了，表明这个方法调用了，即使用了这个自定义的类加载器，而不是jdk的类加载器
        System.out.println("load "+className+" findClass invoked & classLoaderName："+this.classLoaderName);
        //调用loadClassData方法加载类信息
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
        //包名转为路径名
        name = name.replace(".","\\");
        try {
            //字节流读取指定的字节码文件
            is = new FileInputStream(new File(this.path+name+this.fileExtension));
            baos = new ByteArrayOutputStream();
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
            try {
                assert is != null;
                is.close();
                assert baos != null;
                baos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //返回byte []
        return  data;
    }

    public static void main(String[] args) throws Exception {
        //创建一个自定义类加载器，名称是loader1,它的父类加载器是AppClassLoader
        MyTest16 loader1 = new MyTest16("loader1");
        //设置路径
        //loader1.setPath("C:\\Users\\LSD\\Desktop\\jvm\\jvm\\jvm\\target\\classes\\");
        loader1.setPath("G:\\");

        Class<?> clazz1 = loader1.loadClass("cn.andios.jvm.classloader.MyTest1");
        System.out.println("clazz1:"+clazz1.hashCode());


        System.out.println("============================");


        //创建一个自定义类加载器，名称是loader2,它的父类加载器是AppClassLoader
        MyTest16 loader2 = new MyTest16("loader2");
        //设置路径
        //loader2.setPath("C:\\Users\\LSD\\Desktop\\jvm\\jvm\\jvm\\target\\classes\\");
        loader2.setPath("G:\\");

        Class<?> clazz2 = loader2.loadClass("cn.andios.jvm.classloader.MyTest1");
        System.out.println("clazz2:"+clazz2.hashCode());


        System.out.println("****************************");


        //创建一个自定义类加载器，名称是loader3,它的父类加载器是指定的loader1
        //loader1和loader3都是MyTest16的实例，但是loader1可以成为loader3的父加载器，
        //源于类加载器之间不是继承关系，而是一种包含关系
        MyTest16 loader3 = new MyTest16(loader1,"loader3");
        //设置路径
        //loader3.setPath("C:\\Users\\LSD\\Desktop\\jvm\\jvm\\jvm\\target\\classes\\");
        loader3.setPath("G:\\");

        Class<?> clazz3 = loader3.loadClass("cn.andios.jvm.classloader.MyTest1");
        System.out.println("clazz3:"+clazz3.hashCode());
        /**
         * result:
         *  把target下MyTest1.class文件拷贝一份到G盘如上目录，
         *  如果类路径中存在MyTest1.class文件，即项目的target下存在
         *  cn.andios.jvm.classloader.MyTest1.class文件，那么不管
         *  loader1.setPath用的C盘文件还是G盘文件，MyTest1这个类都由
         *  jdk的AppClassLoader来加载，一次运行的结果：
         *     clazz1:1163157884
         *      ============================
         *      clazz2:1163157884
         *      *********************************
         *      clazz3:1163157884
         *   3个Class的hashcode()一样，说明加载的类一样
         *
         *   因为当classpath下存在MyTest1.class文件时，loader1的父加载器AppClassLoader
         *   试图从classpath下加载这个文件成功了，所以轮不到我们自定义的类加载器来加载，
         *   当loader2的父加载器(也是这个AppClassLoader)尝试加载时，发现之前加载过了，
         *   所以直接拿过来，不会再加载，loader3的父加载器是loader1，loader1的父加载器又是
         *   AppClassLoader,所以这3个hashCode()值一样，是同一个对象.
         *
         *  如果把target下MyTest1.class文件删掉，loader1.setPath使用的C盘路径，
         *  就会报错(因为确实没有这个文件，文件被删掉了),如果loader1.setPath
         *  使用的G盘路径，一次运行结果：
         *      load cn.andios.jvm.classloader.MyTest1 findClass invoked & classLoaderName：loader1
         *      clazz1:356573597
         *      ============================
         *      load cn.andios.jvm.classloader.MyTest1 findClass invoked & classLoaderName：loader2
         *      clazz2:2133927002
         *      *********************************
         *      clazz3:356573597
         *   当loader1的父加载器AppClassLoader尝试从classpath下加载时，发现文件不存在，
         *   无法加载，所以由我们自定义的loader1加载，loader2同理，所以由loader1和loader2
         *   分别加载得到的clazz1和clazz2不是同一个对象，对于loader3，它的父类加载器是loader1，
         *   所以它会委托给父类加载器loader1去加载，所以得到的对象的hashCode()与loader1加载的一样
         */

    }
}
