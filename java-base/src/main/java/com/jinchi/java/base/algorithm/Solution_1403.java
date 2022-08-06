package com.jinchi.java.base.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution_1403 {
    public List<Integer> minSubsequence(int[] nums) {
        List<Integer> list = new ArrayList<>();
        Arrays.sort(nums);
        Integer sum = Arrays.stream(nums).sum();
        Integer seqSum = 0;
        for (int i = nums.length - 1; i >= 0; i--) {
            list.add(nums[i]);
            seqSum += nums[i];
            int subSeqSum = sum - seqSum;
            if(seqSum > subSeqSum){
                return list;
            }
        }
        return list;
    }
}