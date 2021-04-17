package com.jinchi.java.base.algorithm;

import java.util.HashMap;

/**
 * 前缀树
 */
class TrieSolution {

    public static void main(String[] args) {
       // String[] ss = new String[]{"dog", "racecar", "car"};
        //String[] ss = new String[]{"flower", "flow", "flight"};
        String[] ss = new String[]{"aa","a"};

        System.out.println(longestCommonPrefix(ss));
    }

    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }

        Trie2 trie2 = new Trie2();
        Integer min = Integer.MAX_VALUE;
        for (int j = 0; j < strs.length; j++) {
            trie2.insert(strs[j]);
            min = Math.min(min,strs[j].length());
        }
        Node2 cur = trie2.root;
        if (cur.nexts.size() > 1) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        String str = strs[0];
        if("".equals(str)){
            return "";
        }
        for (int i = 0; i < min; i++) {
            if (cur.nexts.size() > 1) {
                break;
            }
            sb.append(str.charAt(i));
            cur = cur.nexts.get((int) str.charAt(i));
        }
        return sb.toString();
    }

    public static class Node2 {
        public int pass;
        public int end;
        public HashMap<Integer, Node2> nexts;

        public Node2() {
            pass = 0;
            end = 0;
            nexts = new HashMap<>();
        }
    }

    public static class Trie2 {
        private Node2 root;

        public Trie2() {
            root = new Node2();
        }

        public void insert(String word) {
            if (word == null) {
                return;
            }
            char[] chs = word.toCharArray();
            Node2 node = root;
            node.pass++;
            int index = 0;
            for (int i = 0; i < chs.length; i++) {
                index = (int) chs[i];
                if (!node.nexts.containsKey(index)) {
                    node.nexts.put(index, new Node2());
                }
                node = node.nexts.get(index);
                node.pass++;
            }
            node.end++;
        }

        public void delete(String word) {
            if (search(word) != 0) {
                char[] chs = word.toCharArray();
                Node2 node = root;
                node.pass--;
                int index = 0;
                for (int i = 0; i < chs.length; i++) {
                    index = (int) chs[i];
                    if (--node.nexts.get(index).pass == 0) {
                        node.nexts.remove(index);
                        return;
                    }
                    node = node.nexts.get(index);
                }
                node.end--;
            }
        }

        // word这个单词之前加入过几次
        public int search(String word) {
            if (word == null) {
                return 0;
            }
            char[] chs = word.toCharArray();
            Node2 node = root;
            int index = 0;
            for (int i = 0; i < chs.length; i++) {
                index = (int) chs[i];
                if (!node.nexts.containsKey(index)) {
                    return 0;
                }
                node = node.nexts.get(index);
            }
            return node.end;
        }

        // 所有加入的字符串中，有几个是以pre这个字符串作为前缀的
        public int prefixNumber(String pre) {
            if (pre == null) {
                return 0;
            }
            char[] chs = pre.toCharArray();
            Node2 node = root;
            int index = 0;
            for (int i = 0; i < chs.length; i++) {
                index = (int) chs[i];
                if (!node.nexts.containsKey(index)) {
                    return 0;
                }
                node = node.nexts.get(index);
            }
            return node.pass;
        }
    }
}