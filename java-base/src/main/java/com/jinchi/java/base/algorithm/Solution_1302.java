package com.jinchi.java.base.algorithm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution_1302 {
    public int deepestLeavesSum(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            // 记录每一层的宽度
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                list.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            result.add(list);
        }
        List<Integer> list = result.get(result.size()-1);
        return list.stream().mapToInt(Integer::intValue).sum();
    }
}