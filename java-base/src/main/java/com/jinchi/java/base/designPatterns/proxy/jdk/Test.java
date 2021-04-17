package com.jinchi.java.base.designPatterns.proxy.jdk;

import com.jinchi.java.base.designPatterns.proxy.UserService;
import com.jinchi.java.base.designPatterns.proxy.UserServiceImpl;

public class Test {
    public static void main(String[] args) {
        DynamicProxy dynamicProxy = new DynamicProxy();
        UserService userService = (UserService)dynamicProxy.bind(new UserServiceImpl());
        userService.addUser();
        userService.updateUser();
    }
}