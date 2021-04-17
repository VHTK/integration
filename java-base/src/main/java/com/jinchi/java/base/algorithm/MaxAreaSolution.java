package com.jinchi.java.base.algorithm;

class MaxAreaSolution {

    public static void main(String[] args) {
        int[] height = new int[]{1, 4, 3, 2};
        System.out.println(maxArea(height));
    }

    public static int maxArea(int[] height) {
        if (null == height || 0 == height.length) {
            return 0;
        }
        int begin = 0;
        int end = height.length - 1;
        int max = 0;
        while (begin < end) {
            max = Math.max(max, (end - begin) * Math.min(height[begin], height[end]));
            if (height[begin] < height[end]) {
                begin++;
            } else {
                end--;
            }
        }
        return max;
    }
}