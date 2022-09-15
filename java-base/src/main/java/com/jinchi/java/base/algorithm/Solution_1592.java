package com.jinchi.java.base.algorithm;

import java.util.ArrayList;
import java.util.List;

class Solution_1592 {
    public static void main(String[] args) {
        System.out.println(reorderSpaces(" practice   makes   perfect"));
    }

    public static String reorderSpaces(String text) {
        String[] words = text.split(" ");
        List<String> wordss = new ArrayList<>();
        for (String word : words) {
            if (!"".equals(word)) {
                wordss.add(word);
            }
        }
        int spaceCnt = 0;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == ' ') {
                spaceCnt++;
            }
        }
        int num, less;
        if (wordss.size() == 1) {
            num = 0;
            less = spaceCnt;
        } else {
            num = spaceCnt / (wordss.size() - 1);
            less = spaceCnt - (wordss.size() - 1) * num;
        }
        String result = "";
        for (int i = 0; i < wordss.size(); i++) {
            result = result + wordss.get(i);
            if (i != wordss.size() - 1) {
                for (int j = 0; j < num; j++) {
                    result = result + " ";
                }
            }
        }
        if (less > 0) {
            for (int j = 0; j < less; j++) {
                result = result + " ";
            }
        }
        return result;
    }
}