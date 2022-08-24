package com.jinchi.java.base.algorithm;

import java.util.HashMap;
import java.util.Map;

class Solution_1460 {
    public static void main(String[] args) {
        int[] target = new int[]{1, 2, 2, 3};
        int[] arr = new int[]{1, 1, 2, 3};
        System.out.println(canBeEqual(target, arr));
    }

    public static boolean canBeEqual(int[] target, int[] arr) {
        Map<Integer, Integer> targetMap = new HashMap<>();
        for (int i = 0; i < target.length; i++) {
            Integer value = targetMap.get(target[i]);
            if (null == value) {
                targetMap.put(target[i], 1);
            } else {
                value = value + 1;
                targetMap.put(target[i], value);
            }
        }

        for (int i = 0; i < arr.length; i++) {
            Integer value = targetMap.get(arr[i]);
            if (null == value || value <= 0) {
                return false;
            } else {
                value = value - 1;
                targetMap.put(arr[i], value);
            }
        }
        return true;
    }
}