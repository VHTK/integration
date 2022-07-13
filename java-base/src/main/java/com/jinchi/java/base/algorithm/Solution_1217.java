package com.jinchi.java.base.algorithm;

public class Solution_1217 {

    public int minCostToMoveChips(int[] position) {
        if (null == position || position.length == 0) {
            return 0;
        }
        int num1 = 0;
        int num2 = 0;
        for (int i : position) {
            if (i % 2 == 0) {
                num1++;
            } else {
                num2++;
            }
        }
        return Math.min(num1, num2);
    }
}