package com.jinchi.java.base.algorithm;

class Solution_1374 {
    public String generateTheString(int n) {
        StringBuffer sb = new StringBuffer();
        if (n == 1) {
            return "a";
        }
        if (n % 2 == 0) {
            if (n == 2) {
                sb.append("a");
                sb.append("b");
            } else {
                int t = n / 2;
                if (t % 2 != 0) {
                    int a = t;
                    int b = t;
                    for (int i = 0; i < a; i++) {
                        sb.append("a");
                    }
                    for (int i = 0; i < b; i++) {
                        sb.append("b");
                    }
                } else {
                    int a = n / 2 + 1;
                    int b = n / 2 - 1;
                    for (int i = 0; i < a; i++) {
                        sb.append("a");
                    }
                    for (int i = 0; i < b; i++) {
                        sb.append("b");
                    }
                }
            }
        } else {
            for (int i = 0; i < n; i++) {
                sb.append("a");
            }
        }
        return sb.toString();
    }
}

