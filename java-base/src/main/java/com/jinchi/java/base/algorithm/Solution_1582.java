package com.jinchi.java.base.algorithm;


import java.util.ArrayList;
import java.util.List;

class Solution_1582 {
    public int numSpecial(int[][] mat) {
        int cnt = 0;
        for (int i = 0; i < mat.length; i++) {
            List<Integer> mark = new ArrayList<>();
            for (int j = 0; j < mat[i].length; j++) {
                if (mat[i][j] == 1) {
                    mark.add(j);
                }
            }
            if (mark.size() == 1) {
                int col = mark.get(0);
                int sum = 0;
                for (int k = 0; k < mat.length; k++) {
                    sum += mat[k][col];
                }
                if (sum == 1) {
                    cnt++;
                }
            }
        }
        return cnt;
    }
}