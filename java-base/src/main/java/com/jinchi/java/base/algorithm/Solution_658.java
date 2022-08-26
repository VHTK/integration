package com.jinchi.java.base.algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution_658 {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int maxGap = Integer.MAX_VALUE;
        int near = 0;
        for (int i = 0; i < arr.length; i++) {
            int currGap = Math.abs(arr[i] - x);
            if (currGap < maxGap) {
                near = i;
                maxGap = currGap;
            }
        }
        List<Integer> result = new ArrayList<>();
        result.add(arr[near]);
        int i = near - 1;
        int j = near + 1;
        while (result.size() < k) {
            if (i < 0) {
                result.add(arr[j]);
                j++;
            } else if (j > arr.length - 1) {
                result.add(arr[i]);
                i--;
            } else {
                if (Math.abs(arr[j] - x) < Math.abs(arr[i] - x)) {
                    result.add(arr[j]);
                    j++;
                } else {
                    result.add(arr[i]);
                    i--;
                }
            }
        }
        Collections.sort(result);
        return result;
    }
}