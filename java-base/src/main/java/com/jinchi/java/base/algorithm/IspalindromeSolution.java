package com.jinchi.java.base.algorithm;

public class IspalindromeSolution {
    public static void main(String[] args){
        System.out.println(isPalindrome(10));
    }
    public static boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        String str = String.valueOf(x);
        int i = 0;
        int j = str.length() - 1;
        while (i != j && i < j) {
            if (str.charAt(i) != str.charAt(j)){
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
}