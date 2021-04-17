package com.jinchi.java.base.designPatterns.proxy.statics;


import com.jinchi.java.base.designPatterns.proxy.UserServiceImpl;

public class Test {
    public static void main(String[] args) {
        UserStaticProxy userStaticProxy = new UserStaticProxy(new UserServiceImpl());
        userStaticProxy.addUser();
    }
}