package com.jinchi.java.base.algorithm;

class Solution_67 {
    public String addBinary(String a, String b) {
        StringBuffer big = new StringBuffer(a);
        String small = b;
        if(a.length() < b.length()){
            big = new StringBuffer(b);
            small = a;
        }
        for (int i = small.length() - 1; i >= 0; i--) {
            if (small.charAt(i) == '1') {
                plus(big,big.length()- (small.length() - i));
                if(big.charAt(0) == '0'){
                    StringBuffer head = new StringBuffer("1");
                    big = head.append(big);
                }
            }
        }
        return big.toString();
    }

    public void plus(StringBuffer digits, int index) {
        if (index == -1) {
            return;
        }
        char value = digits.charAt(index);
        if ('0' == value) {
            digits.replace(index, index + 1, "1");
        } else {
            digits.replace(index, index + 1, "0");
            plus(digits, index - 1);
        }
    }
}