package com.jinchi.java.base.juc;

import org.openjdk.jol.info.ClassLayout;

/**
 * 测试对象头
 */
public class T14_Object_Header {
    public static void main(String[] args) {
        Object o = new Object();
        //o.hashCode();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
    }
}