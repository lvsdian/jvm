package cn.andios.jvm.memory;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author:LSD-
 * @when:2020/01/16/11:46
 */

/**
 * 栈帧(Stack Frame)：存储线程操作相关的具体数据或数据结构
 * 程序计数器(Program Counter)：描述执行顺序，线程私有
 * 本地方法栈：处理本地方法
 * 虚拟机栈：处理java方法，线程私有
 * 堆(Heap)：jvm管理的最大一块内存空间，几乎所有的垃圾收集器都是采用的分代收集算法，所以堆空间也基于这一点进行相应的划分：新生代与老年代
 *      再具体一点：form survivor,to survivor,eden
 * 方法区(method area)：存储元信息，
 * 永久代(Permanent Generation):从jdk1.8开始，彻底废弃永久代，采用元空间(meta space)
 * 运行时常量池：方法区的一部分
 * 直接内存(direct memory)：与NIO密切相关，jvm通过directByteBuffer来操作直接内存
 *
 *  new关键字创建对象的步骤：
 *      1.在堆内存中创建出对象的实例
 *      2.为对象的实例成员变量赋初值
 *      3.将对象的引用返回
 *
 *   指针碰撞(前提是堆中的空间通过一个指针进行分割，一侧是已经被占用的空间，另一侧是未被占用的空间)
 *   空闲列表(前提是堆内存空间中已经被使用与未被使用的空间是交织在一起的，这时，虚拟机就需要通过一个空闲列表来记录那些空间是可以使用的，
 *   哪些空间是已被使用的，接下来找出可以容纳下新创建对象的且未被使用的空间，在此空间存放该对象且修改表记录)
 *
 *   对象在内存中的布局：
 *   1.对象头
 *   2.实例数据(即我们在一个类中所声明的各项信息)
 *   3.对齐填充
 *
 *   引用访问对象的方式：
 *   1.使用句柄
 *   2.使用直接指针
 *
 */
public class MyTest1 {
    public static void main(String[] args) {
        //演示堆溢出
        List<MyTest1> list = new ArrayList<>();
        for (;;){
            list.add(new MyTest1());
        }
    }
}
