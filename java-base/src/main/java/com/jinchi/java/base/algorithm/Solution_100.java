package com.jinchi.java.base.algorithm;

public class Solution_100 {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        return checkSameTree(p, q);
    }

    private Boolean checkSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        } else if (p != null && q != null) {
            if (p.val != q.val) {
                return false;
            } else {
                return checkSameTree(p.left, q.left) && checkSameTree(p.right, q.right);
            }
        } else {
            return false;
        }
    }
}