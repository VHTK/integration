package com.jinchi.java.base.algorithm;

class Solution_1470 {
    public int[] shuffle(int[] nums, int n) {
        int[] result = new int[n * 2];
        int j = 0;
        for (int i = 0; i < n; i++) {
            result[j] = nums[i];
            j++;
            result[j] = nums[i + n];
            j++;
        }
        return result;
    }
}