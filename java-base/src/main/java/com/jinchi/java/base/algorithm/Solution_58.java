package com.jinchi.java.base.algorithm;

class Solution_58 {
    public int lengthOfLastWord(String s) {
        int length = 0;
        int index = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if(s.charAt(i) == ' '){
                if(index != 0) {
                    length = index;
                    break;
                }
                index = 0;
            }else{
                index ++ ;
            }
            length = index;
        }
        return length;
    }
}