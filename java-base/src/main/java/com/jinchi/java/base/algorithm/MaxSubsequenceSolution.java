package com.jinchi.java.base.algorithm;

import java.util.Scanner;

/**
 * 计算和最大的子序列
 */
public class MaxSubsequenceSolution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for (int i = 1; i <= t; i++) {
            int n = sc.nextInt();
            int maxsum = sc.nextInt();
            int maxleft = 0;
            int maxright = 0;
            int thisleft = 0;
            int thissum = maxsum;
            if (thissum < 0) {
                thissum = 0;
                thisleft = 1;
            }
            for (int j = 1; j < n; j++) {
                thissum += sc.nextInt();
                if (thissum > maxsum) {
                    maxsum = thissum;
                    maxleft = thisleft;
                    maxright = j;
                }
                if (thissum < 0) {
                    thisleft = j + 1;
                    thissum = 0;
                }
            }
            System.out.println("Case " + i + ":");
            System.out.println(maxsum + " " + (maxleft + 1) + " " + (maxright + 1));
            if (i != t) {
                System.out.println();
            }
        }
    }
}