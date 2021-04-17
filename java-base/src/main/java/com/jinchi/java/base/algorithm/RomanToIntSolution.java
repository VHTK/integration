package com.jinchi.java.base.algorithm;

import java.util.HashMap;
import java.util.Map;

class RomanToIntSolution {

    public static void main(String[] args) {
        System.out.println(romanToInt("MMLXMMLXXIXXDCCC"));
    }

    public static int romanToInt(String s) {
        Map<String, Integer> map = new HashMap<String, Integer>() {
            {
                put("I", 1);
                put("IV", 4);
                put("V", 5);
                put("IX", 9);
                put("X", 10);
                put("XL", 40);
                put("L", 50);
                put("XC", 90);
                put("C", 100);
                put("CD", 400);
                put("D", 500);
                put("CM", 900);
                put("M", 1000);
            }
        };
        int i = 0;
        int res = 0;
        while (i < s.length()) {
            String a = String.valueOf(s.charAt(i));
            if (i + 1 < s.length()) {
                String b = String.valueOf(s.charAt(i + 1));
                String tmp = a.concat(b);
                if (map.get(tmp) != null) {
                    res = res + map.get(tmp);
                    i += 2;
                } else {
                    res = res + map.get(a);
                    i++;
                }
            } else {
                res = res + map.get(a);
                i++;
            }
        }
        return res;
    }
}