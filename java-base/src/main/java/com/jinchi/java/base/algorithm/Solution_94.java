package com.jinchi.java.base.algorithm;

import java.util.ArrayList;
import java.util.List;

public class Solution_94 {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if(null == root){
            return result;
        }
        inorderTraversal(result, root);
        return result;
    }

    private void inorderTraversal(List<Integer> result, TreeNode root) {
        if(null != root.left) {
            inorderTraversal(result, root.left);
        }
        result.add(root.val);
        if(null != root.right){
            inorderTraversal(result, root.right);
        }
    }
}