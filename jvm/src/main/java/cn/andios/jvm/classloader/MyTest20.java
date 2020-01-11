package cn.andios.jvm.classloader;

import com.sun.crypto.provider.AESKeyGenerator;

/**
 * @description:
 * @author:LSD
 * @when:2020/01/11/16:07
 */
public class MyTest20 {
    public static void main(String[] args) {
        AESKeyGenerator aesKeyGenerator = new AESKeyGenerator();
        /**
         * result:
         *      sun.misc.Launcher$ExtClassLoader@7ea987ac
         *      sun.misc.Launcher$AppClassLoader@18b4aac2
         *
         *  AESKeyGenerator这个类默认是由扩展类加载器加载的
         *
         *  如果在当前项目的类路径下(即target/classes下)执行：
         *  java -Djava.ext.dirs=./ cn.andios.jvm.classloader.MyTest20
         *  就会报错：
         *  java.lang.ClassNotFoundException: com.sun.crypto.provider.AESKeyGenerator
         *  这个命令意思是：改变扩展类加载器加载的目录，改到当前目录，
         *  而此时当前目录中并没有AESKeyGenerator.class，所以这里会
         *  加载失败
         *
         */
        System.out.println(aesKeyGenerator.getClass().getClassLoader());
        System.out.println(MyTest20.class.getClassLoader());
    }
}
