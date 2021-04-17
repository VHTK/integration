package com.jinchi.java.base.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 二进制排序
 * 插入区间
 * 泰山数组
 * 删除指定元素
 * 接雨水
 * 删除重复元素
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(bitCount(6));
        System.out.println(bitCount(7));
    }

    public int[] sortByBits(int[] arr) {
        if (arr == null || arr.length == 0) {
            return arr;
        }

        process(arr, 0, arr.length - 1);

        return arr;
    }

    public static void process(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        swap(arr, L + (int) (Math.random() * (R - L + 1)), R);
        int[] equalArea = netherlandsFlag(arr, L, R);
        process(arr, L, equalArea[0] - 1);
        process(arr, equalArea[1] + 1, R);
    }

    // arr[L...R] 玩荷兰国旗问题的划分，以arr[R]做划分值
    //  <arr[R]  ==arr[R]  > arr[R]
    public static int[] netherlandsFlag(int[] arr, int L, int R) {
        if (L > R) {
            return new int[]{-1, -1};
        }
        if (L == R) {
            return new int[]{L, R};
        }
        int less = L - 1; // < 区 右边界
        int more = R;     // > 区 左边界
        int index = L;
        while (index < more) {
            if (compare(arr[index], arr[R]) == 0) {
                index++;
            } else if (compare(arr[index], arr[R]) > 0) {
                swap(arr, index++, ++less);
            } else { // >
                swap(arr, index, --more);
            }
        }
        swap(arr, more, R);
        return new int[]{less + 1, more};
    }

    public static int compare(int a, int b) {
        int c1 = bitCount(a);
        int c2 = bitCount(b);
        if (c1 < c2) {
            return 1;
        } else if (c1 > c2) {
            return -1;
        } else {
            if (a < b) {
                return 1;
            } else if (a > b) {
                return -1;
            } else {
                return 0;
            }
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static int bitCount(int n) {
        int count = 0;
        while (n != 0) {
            n = n & (n - 1);
            count++;
        }
        return count;
    }

    public static int[][] insert(int[][] intervals, int[] newInterval) {
        if (newInterval == null || newInterval.length == 0) {
            return intervals;
        }

        if (intervals == null || intervals.length == 0) {
            return new int[][]{newInterval};
        }

        int begin = newInterval[0];
        int end = newInterval[1];
        int head = 0;
        int tail = 0;
        int mid = 0;

        int firstInsert = -1;
        int secondInsert = -1;

        for (int i = 0; i < intervals.length; i++) {
            if (i == 0) {
                if (intervals[i][0] > end) {
                    head = 1;
                }
                if (end >= intervals[i][0]) {
                    if (intervals[i][0] > begin) {
                        intervals[i][0] = begin;
                    }
                }
                if (begin >= intervals[i][0] && begin <= intervals[i][1]) {
                    firstInsert = i;
                }
            }

            if (i > 0) {
                if (begin > intervals[i - 1][1] && end < intervals[i][0]) {
                    mid = i;
                }
                if (begin >= intervals[i][0] && begin <= intervals[i][1]) {
                    firstInsert = i;
                }
                if (begin >= intervals[i - 1][1] && begin <= intervals[i][0] && end >= intervals[i][0]) {
                    intervals[i][0] = begin;
                    firstInsert = i;
                }
                if (end >= intervals[i][0] && end <= intervals[i][1]) {
                    secondInsert = i;
                }
                if (end >= intervals[i - 1][1] && end <= intervals[i][0] && begin <= intervals[i - 1][1]) {
                    intervals[i - 1][1] = end;
                    secondInsert = i - 1;
                }
            }


            if (i == intervals.length - 1) {
                if (intervals[i][1] < begin) {
                    tail = 1;
                }
                if (intervals[i][1] >= begin) {
                    if (intervals[i][1] < end) {
                        intervals[i][1] = end;
                    }
                }
                if (end >= intervals[i][0] && end <= intervals[i][1]) {
                    secondInsert = i;
                }
            }
        }

        List<int[]> res = new ArrayList<>();
        if (head == 1) {
            res.add(newInterval);
        }
        if (firstInsert != -1 && secondInsert != -1) {
            if (firstInsert != 0 && intervals[firstInsert][0] == intervals[firstInsert - 1][1]) {
                firstInsert--;
            }

            if (secondInsert != intervals.length - 1 && intervals[secondInsert][1] == intervals[secondInsert + 1][0]) {
                secondInsert++;
            }

            for (int i = 0; i < firstInsert; i++) {
                res.add(new int[]{intervals[i][0], intervals[i][1]});
            }

            res.add(new int[]{intervals[firstInsert][0], intervals[secondInsert][1]});

            for (int i = secondInsert + 1; i < intervals.length; i++) {
                res.add(new int[]{intervals[i][0], intervals[i][1]});
            }
        } else {
            for (int i = 0; i < intervals.length; i++) {
                if (mid == i && mid != 0) {
                    res.add(newInterval);
                }
                res.add(new int[]{intervals[i][0], intervals[i][1]});
            }
        }

        if (tail == 1) {
            res.add(newInterval);
        }

        return res.toArray(new int[][]{});
    }


    public static boolean validMountainArray(int[] A) {
        if (A == null || A.length < 3) {
            return false;
        }
        int max = 0;
        for (int i = 1; i < A.length; i++) {
            if (A[i] > A[max]) {
                max = i;
            }
        }

        for (int i = 0; i < max; i++) {
            if (A[i] > A[i + 1] || A[i] >= A[max]) {
                return false;
            }
        }

        for (int i = max + 1; i < A.length; i++) {
            if (A[i] >= A[max] || A[i - 1] < A[i]) {
                return false;
            }
        }

        return true;
    }

    public int strStr(String haystack, String needle) {
        return haystack.indexOf(needle);
    }

    /**
     * [0,1,2,2,3,0,4,2]   2
     *
     * @param nums
     * @param val
     * @return
     */
    public int removeElement(int[] nums, int val) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int i = 0;
        int j = nums.length - 1;
        int count = 0;
        while (i <= j) {
            if (nums[i] == val) {
                if (nums[j] != val) {
                    count++;
                    int tmp = nums[j];
                    nums[j] = nums[i];
                    nums[i] = tmp;
                    i++;
                    j--;
                } else {
                    count++;
                    j--;
                }
            } else {
                i++;
            }
        }
        return nums.length - count;
    }

    public static int trap(int[] height) {
        int n = height.length;
        int result = 0;
        if (n == 0 || n == 1) {
            return result;
        }
        int left = 0;
        //variable left represents the left border of the area where can contain water
        while (left < n - 1 && height[left + 1] >= height[left]) {
            left++;
        }
        int right = n - 1;
        //variable right represents the right border of the area where can contain water
        while (right >= 1 && height[right - 1] >= height[right]) {
            right--;
        }
        while (left < right) {
            int leftHeight = height[left];
            int rightHeight = height[right];
            if (leftHeight <= rightHeight) {
                while (left < right) {
                    left++;
                    if (height[left] < leftHeight) {
                        result += leftHeight - height[left];
                    } else {
                        break;
                    }
                }
            } else {
                while (left < right) {
                    right--;
                    if (height[right] < rightHeight) {
                        result += rightHeight - height[right];
                    } else {
                        break;
                    }
                }
            }
        }
        return result;

    }

    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        if (nums.length == 1) {
            return 1;
        }

        int first = 0;
        int second = 0;

        int sum = 1;
        for (int i = 0; i < nums.length; i++) {
            while (true) {
                if (second > nums.length - 1) {
                    break;
                }

                if (nums[second] != nums[first]) {
                    nums[sum] = nums[second];
                    sum++;
                    first = second;
                    break;
                }
                second++;
            }
        }

        return sum;
    }

    public boolean isValid(String s) {
        if (s == null || "".equals(s)) {
            return false;
        }
        Stack<String> stacks = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            String c = String.valueOf(s.charAt(i));
            if (stacks.empty()) {
                stacks.push(c);
            } else {
                String p = stacks.peek();
                String m = p.concat(c);
                if ("()".equals(m) || "[]".equals(m) || "{}".equals(m)) {
                    stacks.pop();
                } else {
                    stacks.push(c);
                }
            }
        }
        return stacks.empty();
    }
}

