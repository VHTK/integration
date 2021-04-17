package com.jinchi.java.base.algorithm;

public class ReverseIntSolution {

    public static void main(String[] args) {
        System.out.println(Math.pow(2, 31));
        System.out.println(reverse(-123));
    }

    public static int reverse(int x) {
        long res = 0L;
        while (x != 0) {
            int tmp = x % 10;
            res = res * 10 + tmp;
            x = (x - tmp) / 10;
        }
        if (res > Integer.MAX_VALUE || res < Integer.MIN_VALUE) {
            return 0;
        }
        return (int) res;
    }
}