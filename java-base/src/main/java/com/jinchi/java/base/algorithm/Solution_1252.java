package com.jinchi.java.base.algorithm;

import java.util.ArrayList;
import java.util.List;

class Solution_1252 {

    public int oddCells(int m, int n, int[][] indices) {
       int[][] cells = new int[m][n];
        for (int i = 0; i < indices.length; i++) {
            int r = indices[i][0];
            int c = indices[i][1];
            for(int j = 0 ; j < m; j++){
                cells[r][j]++;
            }
            for(int j = 0 ; j < n; j++){
                cells[j][c]++;
            }
        }
        int result = 0;
        for (int i = 0; i < cells.length; i++) {
            for(int j = 0 ; j < cells[i].length; j++){
                if(cells[i][j] % 2 == 1){
                    result ++;
                }
            }
        }
        return result;
    }
}