package com.jinchi.java.base.algorithm;

class Solution_1413 {
    public int minStartValue(int[] nums) {
        int min = 0;
        int add = 0;
        for (int i = 0; i < nums.length; i++) {
            add = add + nums[i];
            if(add < min){
                min = add;
            }
        }
        return 1 - min;
    }
}