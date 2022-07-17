package com.jinchi.java.base.algorithm;

import java.util.HashMap;
import java.util.Map;

class Solution_565 {

    public static void main(String[] args) {
        int[] nums = new int[]{};
        arrayNesting(nums);
    }

    public static int arrayNesting(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(i,nums[i]);
        }
        int size = 1;
        int maxSize = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++){
            if(nums[i] != -1) {
                int cur = nums[i];
                int next = map.get(cur);
                while (next != cur) {
                    size++;
                    nums[next] = -1;
                    next = map.get(next);
                }
                if (size > maxSize) {
                    maxSize = size;
                }
                size = 1;
            }
        }
        return maxSize;
    }
}