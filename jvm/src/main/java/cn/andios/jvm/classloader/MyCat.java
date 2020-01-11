package cn.andios.jvm.classloader;

/**
 * @description:
 * @author:LSD
 * @when:2020/01/11/13:27
 */
public class MyCat {
    public MyCat(){
        System.out.println("MyCat is loaded by:"+this.getClass().getClassLoader());
        //System.out.println("form MyCat:"+MySample.class);
    }
}
