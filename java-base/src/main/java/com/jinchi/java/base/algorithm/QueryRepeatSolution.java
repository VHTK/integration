package com.jinchi.java.base.algorithm;

/**
 * 查找数组中的重复元素
 * Created by ZHANGTAO269 on 2019-8-28.
 */
public class QueryRepeatSolution {

    public static void main(String[] args) {
        int nums[] = {2, 3, 1, 0, 2, 5, 1};
        int dup[] = new int[1];
        duplicate(nums, 6, dup);
        for (int i = 0; i < dup.length; i++) {
            System.out.println(dup[i]);
        }
    }

    public static boolean duplicate(int[] nums, int length, int[] duplication) {
        if (nums == null || length <= 0)
            return false;
        for (int i = 0; i < length; i++) {
            while (nums[i] != i) {
                if (nums[i] == nums[nums[i]]) {
                    duplication[0] = nums[i];
                    return true;
                }
                swap(nums, i, nums[i]);
            }
        }
        return false;
    }

    private static void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}
