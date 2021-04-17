package com.jinchi.java.base.classloader;

/**
 * @Author: vhtk
 * @Description: 被动引用
 * @Date: 2020/6/21
 */
public class InitClass {

    public static void main(String[] args) {
        // 通过子类引用父类的静态字段，不会导致子类初始化
        System.out.println(SubClass.value);
        // 通过数组来引用类，不会触发该类的初始化
        SupperClass[] supperClasses = new SupperClass[3];
        // 常量在编译阶段会存入常量池，本质上并没有直接引用到类定义常量的类，因此不会触发定义常量类的初始化
        System.out.println(SubClass.HELLO_WORLD);
    }

    static class SupperClass {

        static {
            System.out.println("SupperClass init!");
        }

        public static int value = 123;
    }

    static class SubClass extends SupperClass {

        static {
            System.out.println("SubClass init!");
        }

        public static final String HELLO_WORLD = "hello world";
    }
}
