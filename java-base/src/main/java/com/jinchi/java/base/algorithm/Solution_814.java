package com.jinchi.java.base.algorithm;

class Solution_814 {

    public TreeNode pruneTree(TreeNode root) {
        doPruneTree(root, root.left, true);
        doPruneTree(root, root.right, false);
        if(root.left == null && root.right == null){
            if(root.val == 0){
                root = null;
            }
        }
        return root;
    }

    private void doPruneTree(TreeNode parent, TreeNode node, boolean isLeft) {
        if(node == null){
            return;
        }

        if(node.left != null){
            doPruneTree(node, node.left, true);
        }
        if(node.right != null){
            doPruneTree(node, node.right, false);
        }
        if(node.left == null && node.right == null){
            if(node.val == 0){
                if(isLeft){
                    parent.left = null;
                }else{
                    parent.right = null;
                }
            }
        }
    }
}
