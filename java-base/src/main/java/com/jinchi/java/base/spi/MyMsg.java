package com.jinchi.java.base.spi;

public class MyMsg implements IMsg {

    static {
        MsgManage.register("my", MyMsg.class);
    }

    public void send(String msg) {
        System.out.println("[通过MyMsg实现加载]" + msg);
    }
}