package com.jinchi.java.base.designPatterns.proxy.cglib;


import com.jinchi.java.base.designPatterns.proxy.UserServiceImpl;

import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {
        CGLibProxy cgLibProxy = new CGLibProxy();
        UserServiceImpl userService = (UserServiceImpl) cgLibProxy.bind(new UserServiceImpl());
        userService.addUser();
        userService.updateUser();
        System.in.read();
    }
}