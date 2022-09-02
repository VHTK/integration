package com.jinchi.java.base.algorithm;

class Solution_1475 {
    public int[] finalPrices(int[] prices) {
        int[] result = new int[prices.length];
        for (int i = 0; i < prices.length; i++) {
            int discount = 0;
            for (int j = i + 1; j < prices.length; j++) {
                if(prices[j]<prices[i]){
                    discount = prices[j];
                    break;
                }
            }
            int finalPrice = prices[i] - discount;
            result[i] = finalPrice;
        }
        return result;
    }
}