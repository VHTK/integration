package com.jinchi.java.base.algorithm;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeSet;

class Solution_757 {
    public static void main(String[] args) {
        int[][] intervals = new int[][]{{1,2},{2,3},{2,4},{4,5}};
        System.out.println(intersectionSizeTwo(intervals));
    }

    public static int intersectionSizeTwo(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[1]));
        TreeSet<Integer> set = new TreeSet<>();
        for (int[] ints : intervals) {
            int a = ints[0];
            int b = ints[1];
            Integer ceil = set.ceiling(a);
            Integer floor = set.floor(b);
            if (ceil == null || floor == null || ceil >= floor){
                set.add(b);
                if (floor == null || floor < a || floor == b){
                    set.add(b - 1);
                }
            }
        }
        return set.size();
    }
}