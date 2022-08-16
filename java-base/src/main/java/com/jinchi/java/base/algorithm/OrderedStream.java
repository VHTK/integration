package com.jinchi.java.base.algorithm;

import java.util.ArrayList;
import java.util.List;

class OrderedStream {
    private String[] data;
    private Integer ptr;


    public OrderedStream(int n) {
        data = new String[n + 1];
        ptr = 1;
    }

    public List<String> insert(int idKey, String value) {
        data[idKey] = value;
        List<String> result = new ArrayList<>();
        while (ptr < data.length && data[ptr] != null) {
            result.add(data[ptr]);
            ptr = ptr + 1;
        }
        return result;
    }
}