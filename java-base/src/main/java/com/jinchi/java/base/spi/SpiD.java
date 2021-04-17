package com.jinchi.java.base.spi;


public class SpiD {

    public void test() throws ClassNotFoundException {
        // spi可插拔式编程
        Class.forName("com.example.code.spi.MyMsg");
        Class.forName("com.example.code.spi.YourMsg");
        IMsg my = new MsgManage().getMsgConnection("my");
        IMsg your = new MsgManage().getMsgConnection("your");
        my.send("你好，世界");
        your.send("你好，世界");
    }
}