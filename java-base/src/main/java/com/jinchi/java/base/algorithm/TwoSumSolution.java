package com.jinchi.java.base.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * 数组中满足两数相加等于目标值的元素下标
 */
public class TwoSumSolution {
    public static void main(String[] args) {
        int[] nums = new int[]{2, 7, 11, 15};
        int[] result = twoSum(nums, 9);
        for (int s : result) {
            System.out.println(s);
        }
    }

    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            Integer tmp = target - nums[i];
            if(map.get(tmp) != null){
                return new int[]{i, map.get(tmp)};
            }else{
                map.put(nums[i],i);
            }
        }
        return new int[]{};
    }
}