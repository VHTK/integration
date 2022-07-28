package com.jinchi.java.base.algorithm;


import java.util.ArrayList;
import java.util.List;

class Solution_592 {

    public static void main(String[] args) {
        String expression1 = "10/2+10/3+7/9";
        String expression2 = "-5/2+10/3+7/9";
        System.out.println(fractionAddition(expression2));
    }

    public static String fractionAddition(String expression) {
        List<Integer> fenmu = new ArrayList<>();
        List<Integer> fenzi = new ArrayList<>();
        Integer tongfen = 1;
        for (int i = 0; i < expression.length(); i++) {
            if (expression.charAt(i) == '/') {
                Integer f = expression.charAt(i + 1) - '0';
                if (f == 1) {
                    if (i + 2 < expression.length() && '0' == expression.charAt(i + 2)) {
                        f = f * 10;
                    }
                }
                tongfen *= f;
                fenmu.add(f);

                int g = expression.charAt(i - 1) - '0';
                if(g == 0){
                    if (i == 2) {
                        fenzi.add(10);
                    }else{
                        fenzi.add(Integer.parseInt(expression.substring(i - 3, i)));
                    }
                }else{
                    if (i == 1) {
                        fenzi.add(expression.charAt(i - 1) - '0');
                    } else {
                        fenzi.add(Integer.parseInt(expression.substring(i - 2, i)));
                    }
                }
            }
        }

        Integer sumFenzi = 0;
        for (int i = 0; i < fenzi.size(); i++) {
            Integer fm = fenmu.get(i);
            Integer fz = fenzi.get(i);

            sumFenzi = sumFenzi + fz * tongfen / fm;
        }

        if (sumFenzi == 0) {
            tongfen = 1;
            return sumFenzi.toString() + '/' + tongfen;
        }

        int gongyueshu = gongyueshu(tongfen, sumFenzi);

        sumFenzi = sumFenzi / gongyueshu;
        tongfen = tongfen / gongyueshu;

        return sumFenzi.toString() + '/' + tongfen;
    }

    public static Integer gongyueshu(int m, int n) {
        m = Math.abs(m);
        n = Math.abs(n);
        int g = 0;
        while (m % n != 0) {
            g = m % n;
            m = n;
            n = g;
        }
        return n;
    }
}