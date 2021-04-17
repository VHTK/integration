package com.jinchi.java.base.spi;

import java.util.HashMap;
import java.util.Map;

public class MsgManage {

        private static final Map<String, Class<? extends IMsg>> map = new HashMap<String, Class<? extends IMsg>>();

        public static void register(String protocol, Class<? extends IMsg> cls) {
            map.put(protocol, cls);
        }

        public IMsg getMsgConnection(String protocol) {
            try {
                return map.get(protocol).newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            return null;
        }
    }