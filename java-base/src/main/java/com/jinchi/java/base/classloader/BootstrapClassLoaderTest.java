package com.jinchi.java.base.classloader;

import java.net.URL;

/**
 * @Author: vhtk
 * @Description:
 * @Date: 2020/6/22
 */
public class BootstrapClassLoaderTest {

    public static void main(String[] args) {
        URL[] urls = sun.misc.Launcher.getBootstrapClassPath().getURLs();
        for (URL url : urls) {
            System.out.println(url.toExternalForm());
        }
    }
}