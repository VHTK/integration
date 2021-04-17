package com.jinchi.java.base.classloader;

/**
 * @Author: vhtk
 * @Description:
 * @Date: 2020/6/22
 */
public class ExtClassLoaderTest {

    public static void main(String[] args){
        ClassLoader classLoader = sun.security.ec.SunEC.class.getClassLoader();
        System.out.println(classLoader);
        System.out.println(classLoader.getParent());
    }
}
