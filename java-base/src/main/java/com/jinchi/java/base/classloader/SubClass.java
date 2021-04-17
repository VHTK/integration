package com.jinchi.java.base.classloader;

/**
 * @Author: vhtk
 * @Description: 通过子类引用父类的静态字段，不会导致子类初始化
 * @Date: 2020/6/21
 */
public class SubClass extends SupperClass{

    static {
        System.out.println("SubClass init!");
    }
}
