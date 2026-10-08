package Object;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * java.lang.Object类
 * 类Object是类层次结构的根类。梅格雷都是用Object作为超类。所有对象（包括数组）都实现这个类的方法。
 */
public class ToStringDemo {
    public static void main(String[] args) {
        /**
         * Person类默认继承Object类，所以可以使用Object类中的toString方法
         */
        Person p = new Person("John", 18);
        String str = p.toString();
        System.out.println(str);        // Object.Person@3b918364
        // 直接打印对象的名字，其实就是调用对象的toString方法 p = p.toString();
        System.out.println(p);      // Object.Person@3b918364

        // 看一个类是否重写了toString方法，直接打印这个雷对应对象的名字即可
        // 如果没有重写toString方法，那么打印的就是对象的地址值（默认）
        // 如果重写了toString方法，那么就按照重写的方式打印
        Random r = new Random();
        System.out.println(r);      // 没有重写

        Scanner sc = new Scanner(System.in);
        System.out.println(sc);     // 重写了

        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        System.out.println(list);       // [1,2] 重写了
    }
}
