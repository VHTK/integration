package com.jinchi.java.base.algorithm;

/**
 * 替换空格
 * Created by ZHANGTAO269 on 2019-8-28.
 */
public class ReplaceBlankSolution {

    public static void main(String[] args){
        StringBuffer str = new StringBuffer("A B g s  fds  sdf");
        System.out.println(replaceSpace(str));
    }

    public static String replaceSpace(StringBuffer str) {
        int P1 = str.length() - 1;
        for (int i = 0; i <= P1; i++)
            if (str.charAt(i) == ' ')
                str.append("  ");

        int P2 = str.length() - 1;
        while (P1 >= 0 && P2 > P1) {
            char c = str.charAt(P1--);
            if (c == ' ') {
                str.setCharAt(P2--, '0');
                str.setCharAt(P2--, '2');
                str.setCharAt(P2--, '%');
            } else {
                str.setCharAt(P2--, c);
            }
        }
        return str.toString();
    }
}
