package com.jinchi.java.base.spi;

public class YourMsg implements IMsg {

    static {
        MsgManage.register("your", YourMsg.class);
    }

    public void send(String msg) {
        System.out.println("[通过YourMsg实现加载]" + msg);
    }
}