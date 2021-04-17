package com.jinchi.java.base.algorithm;

/**
 * @Author: vhtk
 * @Description:
 * @Date: 2020/11/10
 */
public class Solution_328 {

    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }

        ListNode first = head;
        ListNode second = head.next;

        ListNode currFirst = first;
        ListNode currSecond = second;

        while (currSecond.next != null) {
            currFirst.next = currSecond.next;
            currFirst = currSecond.next;

            if (currFirst.next == null) {
                currFirst.next = null;
                currSecond.next = null;
            } else {
                currSecond.next = currFirst.next;
                currSecond = currFirst.next;
            }
        }
        currFirst.next = second;
        return head;
    }


    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}