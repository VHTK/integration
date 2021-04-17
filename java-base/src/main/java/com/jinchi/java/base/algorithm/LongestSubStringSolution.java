package com.jinchi.java.base.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * 求不重复的最大子序列
 */
public class LongestSubStringSolution {
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("pwwkew"));
    }

    public static int lengthOfLongestSubstring(String s) {
        if (null == s) {
            return 0;
        }
        int maxLen = 0;
        Map<String, Integer> sub = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            String ch = String.valueOf(s.charAt(i));
            if (sub.get(ch) != null) {
                if (sub.size() > maxLen) {
                    maxLen = sub.size();
                }
                i = sub.get(ch);
                sub.clear();
            } else {
                sub.put(ch, i);
            }
        }
        if (sub.size() > maxLen) {
            maxLen = sub.size();
        }
        return maxLen;
    }
}