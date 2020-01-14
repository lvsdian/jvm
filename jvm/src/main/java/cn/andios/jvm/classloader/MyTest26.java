package cn.andios.jvm.classloader;

/**
 * @description:
 * @author:LSD
 * @when:2020/01/14/11:54
 */
public class MyTest26 implements Runnable{

    private Thread thread;

    public MyTest26() {
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        ClassLoader classLoader =   this.thread.getContextClassLoader();
        this.thread.setContextClassLoader(classLoader);
        /**
         * result:
         *      Class：class sun.misc.Launcher$AppClassLoader
         *      parent：class sun.misc.Launcher$ExtClassLoader
         *
         * 在Launcher类中的57行
         *  Thread.currentThread().setContextClassLoader(this.loader)
         * 设置当前线程上下文类加载器为系统类加载器，所以这里打印出来是AppClassLoader
         */
        System.out.println("Class："+classLoader.getClass());
        System.out.println("parent："+classLoader.getParent().getClass());

    }

    public static void main(String[] args) {
        new MyTest26();
    }
}
