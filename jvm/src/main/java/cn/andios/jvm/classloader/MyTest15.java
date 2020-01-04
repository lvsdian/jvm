package cn.andios.jvm.classloader;

/**
 * @description:
 * @author:LSD
 * @when:2020/01/04/19:14
 */
public class MyTest15 {
    public static void main(String[] args) {
        String []  str = new String[]{"aa","bb"};
        /**
         * result：
         *      class [Ljava.lang.String;
         *      null
         * 这里null表示由启动类加载
         */
        System.out.println(str.getClass());
        System.out.println(str.getClass().getClassLoader());

        Integer [] arr = new Integer[]{1,2};
        /**
         * result:
         *      class [Ljava.lang.Integer;
         *      null
         * 这里null并不是表示启动类加载，而是确实没有类加载器，因为它是原生类型
         */
        System.out.println(arr.getClass());
        System.out.println(arr.getClass().getClassLoader());

        MyTest15 [] test = new MyTest15[]{new MyTest15(),new MyTest15()};
        /**
         * result:
         *      class [Lcn.andios.jvm.classloader.MyTest15;
         *      sun.misc.Launcher$AppClassLoader@18b4aac2
         */
        System.out.println(test.getClass());
        System.out.println(test.getClass().getClassLoader());
    }
}
