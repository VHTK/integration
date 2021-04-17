package com.jinchi.java.base.algorithm;

import java.util.Map;
import java.util.Stack;
import java.util.TreeMap;

class IntToRomanSolution {

    public static void main(String[] args) {
        System.out.println(intToRoman(2000));
    }

    public static String intToRoman(int s) {
        Map<Integer, String> map = new TreeMap() {
            {
                put(1000, "M");

                put(900, "CM");
                put(800, "DCCC");
                put(700, "DCC");
                put(600, "DC");
                put(500, "D");
                put(400, "CD");
                put(300, "CCC");
                put(200, "CC");
                put(100, "C");

                put(90, "XC");
                put(80, "LXXX");
                put(70, "LXX");
                put(60, "LX");
                put(50, "L");
                put(40, "XL");
                put(30, "XXX");
                put(20, "XX");
                put(10, "X");

                put(9, "IX");
                put(8, "VIII");
                put(7, "VII");
                put(6, "VI");
                put(5, "V");
                put(4, "IV");
                put(3, "III");
                put(2, "II");
                put(1, "I");
            }
        };

        Stack<String> stack = new Stack<>();
        int i = 10;
        while (s != 0) {
            int ys = s % 10;

            int t = ys * i / 10;

            if (t < 1000) {
                if (map.get(t) != null) {
                    stack.push(map.get(ys * i / 10));
                }
            } else {
                for (int j = 0; j < t / 1000; j++) {
                    stack.push("M");
                }
            }

            s = s / 10;
            i *= 10;
        }

        StringBuffer sb = new StringBuffer();
        while (!stack.empty()) {
            sb.append(stack.pop());
        }

        return sb.toString();
    }
}