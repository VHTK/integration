package com.jinchi.java.base.algorithm;

class Solution_83 {
    public ListNode deleteDuplicates(ListNode head) {
        if (null == head) {
            return head;
        }
        ListNode cur = head;
        ListNode next = head.next;
        while (null != next) {
            if (cur.val == next.val) {
                cur.next = next.next;
                next = next.next;
            }else{
                cur = next;
                next = next.next;
            }
        }
        return head;
    }
}