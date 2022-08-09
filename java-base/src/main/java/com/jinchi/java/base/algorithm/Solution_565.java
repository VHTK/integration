package com.jinchi.java.base.algorithm;


class Solution_565 {

    public static void main(String[] args) {
        int[] nums = new int[]{};
        arrayNesting(nums);
    }

    public static int arrayNesting(int[] nums) {
        int size = 1;
        int maxSize = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != -1) {
                int cur = nums[i];
                int next = nums[cur];
                while (next != cur) {
                    size++;
                    int temp = nums[next];
                    nums[next] = -1;
                    next = temp;
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