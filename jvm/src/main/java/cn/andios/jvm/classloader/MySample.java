package cn.andios.jvm.classloader;

/**
 * @description:
 * @author:LSD
 * @when:2020/01/11/13:28
 */
public class MySample {
    public MySample(){
        System.out.println("MySample is loaded by:"+ this.getClass().getClassLoader());
        new MyCat();
        System.out.println("form MySample:"+MyCat.class);
    }
}
