package com.jinchi.java.base.algorithm;

import java.util.Arrays;

class Solution_1619 {
    public double trimMean(int[] arr) {
        Arrays.sort(arr);
        int i = arr.length * 10 / 100 / 2;

        double sum = 0D;
        for (int j = i; j < arr.length - i; j++) {
            sum += arr[j];
        }

        int trim = arr.length - i - i;
        double result = sum / trim;
        return result;
    }
}