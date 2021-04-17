package com.jinchi.java.base.algorithm;

/**
 * 求两个数组的中位数
 */
class TwoArrayMidSolution {

    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 2};
        int[] nums2 = new int[]{3, 4};
        System.out.println(findMedianSortedArrays(nums1, nums2));
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] merge = getMerge(nums1, nums2);
        double res = 0.0d;
        if (merge.length % 2 == 0) {
            int mid = merge.length / 2;
            res = (merge[mid - 1] + merge[mid]) / 2.0;
        } else {
            int mid = (merge.length - 1) / 2;
            res = merge[mid];
        }
        return res;
    }

    private static int[] getMerge(int[] nums1, int[] nums2) {
        if (nums1 == null) {
            return nums2;
        }
        if (nums2 == null) {
            return nums1;
        }

        int[] merge = new int[nums1.length + nums2.length];

        int i = 0;
        int j = 0;
        int k = 0;
        while (i < nums1.length || j < nums2.length) {
            if(i == nums1.length){
                merge[k] = nums2[j];
                j++;
                k++;
                continue;
            }
            if(j == nums2.length){
                merge[k] = nums1[i];
                i++;
                k++;
                continue;
            }
            if (nums1[i] < nums2[j]) {
                merge[k] = nums1[i];
                i++;
            } else {
                merge[k] = nums2[j];
                j++;
            }
            k++;
        }
        return merge;
    }
}