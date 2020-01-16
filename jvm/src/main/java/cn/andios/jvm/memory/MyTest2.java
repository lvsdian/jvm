package cn.andios.jvm.memory;

/**
 * @description:
 * @author:LSD
 * @when:2020/01/16/15:53
 */
public class MyTest2 {
    public static void main(String[] args) {
        //演示虚拟机溢出
        MyTest2 test = new MyTest2();
        try {
            test.test();
        } catch (Throwable t) {
            System.out.println(test.getLength());
            t.printStackTrace();
        }
    }
    private int length;
    public int getLength(){
        return length;
    }
    public void test(){
        length ++;
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        test();
    }
}
