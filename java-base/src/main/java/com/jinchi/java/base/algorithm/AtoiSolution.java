package com.jinchi.java.base.algorithm;

public class AtoiSolution {

    public static void main(String[] args) {
        System.out.println(myAtoi(  "    0000000000000   "));
    }

    public static int myAtoi(String str) {
        if (null == str) {
            return 0;
        }
        str = str.trim();
        if ("".equals(str)) {
            return 0;
        }
        // + - 0 9
        if (str.charAt(0) != 43 && str.charAt(0) != 45 && (str.charAt(0) < 48 || str.charAt(0) > 57)) {
            return 0;
        }

        if (str.charAt(0) == 43 || str.charAt(0) == 45) {
            char pre = str.charAt(0);

            str = getString(str.substring(1, str.length()));

            if (str.length() == 0) {
                return 0;
            }

            if (pre == 43) {
                if (str.length() > 10) {
                    return Integer.MAX_VALUE;
                }
            }
            if (pre == 45) {
                if (str.length() > 10) {
                    return Integer.MIN_VALUE;
                }
            }

            str = String.valueOf(pre).concat(str);

        } else {
            str = getString(str);
            if(str.length() == 0){
                return 0;
            }
            if (str.length() > 10) {
                return Integer.MAX_VALUE;
            }
        }
        long res = Long.valueOf(str);
        if (res > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }
        if (res < Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        }
        return (int) res;
    }

    private static String getString(String str) {
        int begin = 0;
        int end = str.length();

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == 48) {
                if (begin == i) {
                    begin++;
                }
            }

            if (str.charAt(i) < 48 || str.charAt(i) > 57) {
                end = i;
                break;
            }
        }
        str = str.substring(begin, end);
        return str;
    }
}