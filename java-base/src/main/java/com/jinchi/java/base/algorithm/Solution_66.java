package com.jinchi.java.base.algorithm;

class Solution_66 {
    public int[] plusOne(int[] digits) {
        plus(digits, digits.length - 1);
        if(digits[0] > 0){
            return digits;
        }else{
            int[] result = new int[digits.length+1];
            result[0] = 1;
            for(int i = 0;i<digits.length;i++){
                result[i+1] = digits[i];
            }
            return result;
        }
    }

    public void plus(int[] digits, int index) {
        if(index == -1){
            return;
        }
        int plusResult = digits[index] + 1;
        if (plusResult < 10) {
            digits[index] = plusResult;
        } else {
            digits[index] = plusResult % 10;
            plus(digits, index - 1);
        }
    }
}