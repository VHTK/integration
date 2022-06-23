package com.jinchi.java.base.algorithm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution_513 {

    public int findBottomLeftValue(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int result = root.val;
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
                if(i == 0){
                    result = node.val;
                }
            }
        }
        return result;
    }
}