package com.jinchi.java.base.algorithm;

import java.util.LinkedList;
import java.util.Queue;

class Solution_623 {
    public TreeNode addOneRow(TreeNode root, int val, int depth) {
        if(depth == 1){
            TreeNode addRoot = new TreeNode();
            addRoot.val = val;

            addRoot.left = root;
            return addRoot;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int level = 0;
        while (!queue.isEmpty()) {
            // 记录层数
            level++;
            // 记录每一层的宽度
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();

                if (node.left != null) {
                    // 如果到达了目标层数
                    if (level + 1 == depth) {
                        TreeNode addNode = new TreeNode();
                        addNode.val = val;

                        TreeNode left = node.left;
                        node.left = addNode;
                        addNode.left = left;

                        queue.offer(left);
                    } else {
                        queue.offer(node.left);
                    }
                }else{
                    if (level + 1 == depth) {
                        TreeNode addNode = new TreeNode();
                        addNode.val = val;

                        node.left = addNode;
                    }
                }

                if (node.right != null) {
                    // 如果到达了目标层数
                    if (level + 1 == depth) {
                        TreeNode addNode = new TreeNode();
                        addNode.val = val;

                        TreeNode right = node.right;
                        node.right = addNode;
                        addNode.right = right;

                        queue.offer(right);
                    } else {
                        queue.offer(node.right);
                    }
                }else{
                    if (level + 1 == depth) {
                        TreeNode addNode = new TreeNode();
                        addNode.val = val;

                        node.right = addNode;
                    }
                }
            }
        }
        return root;
    }
}