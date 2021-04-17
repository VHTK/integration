package com.jinchi.java.base.designPatterns.proxy.statics;


import com.jinchi.java.base.designPatterns.proxy.UserService;

public class UserStaticProxy implements UserService {
    private UserService userService;
    public UserStaticProxy(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void addUser() {
        userService.addUser();
        System.out.println("拿个小本本记录一下新增");
    }

    @Override
    public void updateUser() {
        userService.updateUser();
        System.out.println("拿个小本本记录一下更新");
    }
}