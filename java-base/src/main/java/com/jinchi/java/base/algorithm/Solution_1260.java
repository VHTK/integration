package com.jinchi.java.base.algorithm;

import java.util.ArrayList;
import java.util.List;

class Solution_1260 {
    public static void main(String[] args) {
        //int[][] grid = new int[][]{{3, 8, 1, 9}, {19, 7, 2, 6}, {4, 6, 11, 10}, {12, 0, 21, 13}};
        int[][] grid = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
        shiftGrid(grid, 1);
    }

    public static List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int[][] shiftGrid = new int[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                int ii = i;
                int jj = j + k;
                if (jj > grid[i].length - 1) {
                    ii = ii + jj / grid[i].length;
                    jj = jj % grid[i].length;
                }
                if (ii > grid.length - 1) {
                    ii = ii % grid.length;
                }
                shiftGrid[ii][jj] = grid[i][j];
            }
        }
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < shiftGrid.length; i++) {
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j < shiftGrid[i].length; j++) {
                list.add(shiftGrid[i][j]);
            }
            res.add(list);
        }
        return res;
    }
}