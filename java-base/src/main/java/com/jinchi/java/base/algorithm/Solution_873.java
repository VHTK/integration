package com.jinchi.java.base.algorithm;

import java.util.HashMap;
import java.util.Map;

class Solution_873 {

    public static void main(String[] args) {
        int[] arr = new int[]{2,4,7,8,9,10,14,15,18,23,32,50};
        lenLongestFibSubseq(arr);
    }

    public static  int lenLongestFibSubseq(int[] arr) {
        int longest = Integer.MIN_VALUE;
        if (null == arr || arr.length < 3) {
            return longest;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            map.put(arr[i], i);
        }
        for (int i = 0; i < arr.length - 1; i++) {
            int first = i;
            int curLong = 2;
            for (int j = i + 1; j < arr.length; j++) {
                int second = j;
                while (null != map.get(arr[first] + arr[second])) {
                    int third = map.get(arr[first] + arr[second]);
                    curLong++;
                    first = second;
                    second = third;
                }
                longest = Integer.max(longest, curLong);
                first = i;
                curLong = 2;
            }
        }
        return longest == 2 ? 0 : longest;
    }
}