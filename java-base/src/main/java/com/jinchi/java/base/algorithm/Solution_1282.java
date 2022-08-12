package com.jinchi.java.base.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution_1282 {
    public List<List<Integer>> groupThePeople(int[] groupSizes) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < groupSizes.length; i++) {
            List<Integer> list = map.get(groupSizes[i]);
            if (list == null || list.size() == 0) {
                List<Integer> group = new ArrayList<>();
                group.add(i);
                map.put(groupSizes[i], group);
            } else {
                list.add(i);
            }
        }
        List<List<Integer>> list = new ArrayList<>();
        map.forEach((k, v) -> {
            if (v.size() > k) {
                List<Integer> group = new ArrayList<>(k);
                for (int i = 0; i < v.size(); i++) {
                    group.add(v.get(i));
                    if ((i + 1) % k == 0) {
                        list.add(group);
                        group = new ArrayList<>(k);
                    }
                }
            } else {
                list.add(v);
            }
        });
        return list;
    }
}