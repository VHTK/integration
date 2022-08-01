package com.jinchi.java.base.algorithm;

import java.util.*;

class Solution_1331 {
    public int[] arrayRankTransform(int[] arr) {
        List<Integer> source = new ArrayList<>();
        for (int i : arr) {
            source.add(i);
        }
        Arrays.sort(arr);
        HashMap<Integer, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (i == 0) {
                indexMap.put(arr[0], 1);
            } else {
                Integer index = indexMap.get(arr[i]);
                if (null != index) {
                    indexMap.put(arr[i], index);
                } else {
                    indexMap.put(arr[i], indexMap.get(arr[i - 1]) + 1);
                }
            }
        }
        int[] result = new int[source.size()];
        for (int i = 0; i < source.size(); i++) {
            result[i] = indexMap.get(source.get(i));
        }
        return result;
    }
}