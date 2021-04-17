package com.jinchi.java.base.algorithm;

/**
 * 两两交换链表元素
 * 合并K个链表
 * 合并链表
 * 删除链表第N个元素
 */
public class MergeListAndDeleteListSolution {


    public ListNode swapPairs(ListNode head) {
        if (head == null) {
            return head;
        }
        if (head.next == null) {
            return head;
        }


        ListNode first = head;
        ListNode second = head.next;
        head = second;

        while (first != null && second != null) {
            ListNode next = second.next;

            second.next = first;
            if(next == null){
                first.next = null;
            }else{
                if(next.next == null){
                    first.next = next;
                }else{
                    first.next = next.next;
                }
            }

            first = next;
            if(next != null) {
                second = next.next;
            }else{
                second = null;
            }
        }
        return head;
    }


    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        return processMergeList(lists, 0, lists.length - 1);
    }

    private ListNode processMergeList(ListNode[] lists, int l, int r) {
        if (l == r) {
            return lists[l];
        }
        int mid = l + ((r - l) >> 1);
        ListNode leftMergeListNode = processMergeList(lists, l, mid);
        ListNode rightMergeListNode = processMergeList(lists, mid + 1, r);
        return mergeTwoLists(leftMergeListNode, rightMergeListNode);
    }


    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode merge = new ListNode();
        ListNode current = merge;
        while (l1 != null || l2 != null) {
            if (l1 == null) {
                current.next = l2;
                l2 = l2.next;
                current = current.next;
                continue;
            }
            if (l2 == null) {
                current.next = l1;
                l1 = l1.next;
                current = current.next;
                continue;
            }

            if (l1.val > l2.val) {
                current.next = l2;
                l2 = l2.next;
            } else {
                current.next = l1;
                l1 = l1.next;
            }

            current = current.next;
        }
        return merge.next;
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode first = head;
        ListNode second = head;
        for (int i = 1; i < n; i++) {
            if (second == null) {
                return head;
            }
            second = second.next;
        }

        ListNode pre = null;

        while (second.next != null) {

            pre = first;
            first = first.next;
            second = second.next;

        }
        if (pre == null) {
            head = head.next;
        } else {
            pre.next = pre.next.next;
        }

        return head;
    }

    public static class ListNode {
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