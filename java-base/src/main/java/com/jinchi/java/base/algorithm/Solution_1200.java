package com.jinchi.java.base.algorithm;

import java.util.*;

/**
 * @Author: vhtk
 * @Description:
 * @Date: 2020/11/10
 */
public class Solution_1200 {

    public static void main(String[] args) {

        int[] arr = new int[]{4, 2, 1, 3};

        System.out.println(minimumAbsDifference(arr));
    }

    public static List<List<Integer>> minimumAbsDifference(int[] arr) {
        List<List<Integer>> result = new ArrayList<>();
        if (null == arr || arr.length == 0 || arr.length == 1) {
            return result;
        }
        Arrays.sort(arr);
        int minDiff = arr[1] - arr[0];
        for (int i = 0; i < arr.length - 1; i++) {
            int diff = arr[i + 1] - arr[i];
            if (diff < minDiff) {
                List<Integer> list = new ArrayList<>(2);
                list.add(arr[i]);
                list.add(arr[i + 1]);
                result.clear();
                result.add(list);
                minDiff = diff;
            } else if (diff == minDiff) {
                List<Integer> list = new ArrayList<>(2);
                list.add(arr[i]);
                list.add(arr[i + 1]);
                result.add(list);
            }
        }
        return result;
    }
}
