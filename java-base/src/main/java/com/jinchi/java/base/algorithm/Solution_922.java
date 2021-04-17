package com.jinchi.java.base.algorithm;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author: vhtk
 * @Description:
 * @Date: 2020/11/10
 */
public class Solution_922 {

    public static void main(String[] args) {
        int[] A = new int[]{17, 13, 11, 2, 3, 5, 7};
        int[] B = deckRevealedIncreasing(A);
        for (int i = 0; i < B.length; i++) {
            System.out.println(B[i]);
        }
    }

    public static int[] deckRevealedIncreasing(int[] deck) {
        if (deck == null || deck.length < 2) {
            return deck;
        }
        Arrays.sort(deck);
        int[] res = new int[deck.length];
        Queue<Integer> queue = new LinkedList<>();
        for (int i = deck.length - 1; i >= 0; i--) {
            Integer tmp = queue.poll();
            if (tmp != null) {
                queue.offer(tmp);
            }
            queue.offer(deck[i]);

        }
        for (int i = deck.length - 1; i >= 0; i--) {
            res[i] = queue.poll();
        }
        return res;
    }

    public static int[] sortArrayByParityII(int[] A) {
        int[] res = new int[A.length];

        int f = 0;
        int s = 1;

        for (int i = 0; i < A.length; i++) {
            if (A[i] % 2 == 0) {
                res[f] = A[i];
                f = f + 2;
            } else {
                res[s] = A[i];
                s = s + 2;
            }
        }

        return res;
    }
}
