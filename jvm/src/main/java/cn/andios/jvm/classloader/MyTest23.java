package cn.andios.jvm.classloader;

/**
 * @description:
 * @author:LSD
 * @when:2020/01/13/16:38
 */
public class MyTest23 {
    static{
        System.out.println("MyTest23 initialized");
    }

    public static void main(String[] args) {
        /**
         * result:
         *      MyTest23 initialized
         *      sun.misc.Launcher$AppClassLoader@73d16e93
         *      sun.misc.Launcher$AppClassLoader@73d16e93
         *
         *  MyTest23.class类在类路径下，所以由AppClassLoader加载
         * 如果在当前目录的target下执行：
         *  java -Djava.ext.dirs=./ cn.andios.jvm.classloader.MyTest23
         *  即把扩展类加载的目录指定到当前的类路径，结果还是
         *  和上面一样，
         *  原因：扩展类加载的.class文件需以jar包形式存在，
         *      这里类路径下的.class文件并不是以jar包形式存在
         *  在target的classes目录下执行：
         *  jar cvf test.jar cn/andios/jvm/classloader/MyTest1.class
         *  即将MyTest1.class打成test.jar放在classes目录下，与cn目录平级
         *  此时再执行：
         *  java -Djava.ext.dirs=./ cn.andios.jvm.classloader.MyTest23
         *  result：
         *      MyTest23 initialized
         *      sun.misc.Launcher$AppClassLoader@2a139a55
         *      sun.misc.Launcher$ExtClassLoader@3d4eac69
         *   可以发现，MyTest1.class由扩展类加载器去加载了
         */
        System.out.println(MyTest23.class.getClassLoader());
        System.out.println(MyTest1.class.getClassLoader());
    }
}
