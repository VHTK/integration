package com.jinchi.java.base.algorithm;

import java.util.*;

public class Solution_648 {

    public static String replaceWords(List<String> dictionary, String sentence) {
        String[] sentences = sentence.split(" ");
        Trie trie = new Trie();
        for (int i = 0; i < sentences.length; i++) {
            trie.insert(sentences[i], i);
        }

        Map<Integer, String> map = new HashMap<>();
        for (String word : dictionary) {
            List<Integer> indexs = trie.startsWithIndexs(word);
            for(Integer index : indexs){
                String value = map.get(index);
                if(null != value) {
                    if (word.length() < value.length()) {
                        map.remove(index);
                        map.put(index,word);
                    }
                }else{
                    map.put(index,word);
                }
            }
        }

        map.forEach( (k,v) -> sentences[k] = v);

        StringBuffer result = new StringBuffer();
        for (int i = 0; i < sentences.length; i++) {
            result.append(sentences[i]);
            if(i != sentences.length-1){
                result.append(" ");
            }
        }
        return result.toString();
    }



    static class Trie {
        //Trie的两个属性，指向子节点的指针数组和表示该节点是否为结尾的布尔值
        private Trie[] children;
        private boolean isEnd;
        private List<Integer> endIndexs;

        //构造
        public Trie() {
            children = new Trie[26];
            isEnd = false;
            endIndexs = new ArrayList<>();
        }

        //插入节点。
        public void insert(String word, int idx) {
            Trie node = this;//指针指向当前的根
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);//待插入的字符
                int index = ch - 'a';//参数
                //当前的节点为null，就新建一个节点
                if (node.children[index] == null) {
                    node.children[index] = new Trie();
                }
                //当前节点不为null,就将node沿指针移动到子节点
                node = node.children[index];
            }
            //完成插入后，就将此时node所指向的节点isEnd置为true
            node.isEnd = true;
            node.endIndexs.add(idx);
        }

        //查询前缀树中是否含有本字符串，使用查询前缀和的函数得到节点node,
        //若返回的node不为null,则说明找到了word的前缀，且如果此时isEnd为true，说明node是叶子
        //则说明此时的word存在于前缀树中。
        public boolean search(String word) {
            Trie node = searchPrefix(word);
            return node != null && node.isEnd;
        }

        //查询前缀
        public boolean startsWith(String prefix) {
            //只要返回值不为null，说明搜索到了前缀的末尾就为true，否则为false
            return searchPrefix(prefix) != null;
        }

        public List<Integer> startsWithIndexs(String prefix) {
            List<Integer> indexs = new ArrayList<>();
            //只要返回值不为null，说明搜索到了前缀的末尾就为true，否则为false
            Trie node = searchPrefix(prefix);
            if (null == node) {
                return indexs;
            }
            getEndIndexs(node, indexs);
            return indexs;
        }

        public void getEndIndexs(Trie node, List<Integer> indexs) {
            if (node != null) {
                if (node.isEnd) {
                    indexs.addAll(node.endIndexs);
                }
                Trie[] childrens = node.children;
                if (null != childrens) {
                    for (Trie child : childrens) {
                        getEndIndexs(child, indexs);
                    }
                }
            }
        }

        private Trie searchPrefix(String prefix) {
            Trie node = this;//指针指向当前的根
            for (int i = 0; i < prefix.length(); i++) {
                //当前访问的字符及其参数
                char ch = prefix.charAt(i);
                int index = ch - 'a';
                //访问的节点不存在，就返回一个null
                if (node.children[index] == null) {
                    return null;
                }
                //访问的节点存在，就沿着指针指向的节点移动
                node = node.children[index];
            }
            return node;//最后搜索到了末尾就返回这个末尾的节点，说明存在这个前缀
        }
    }
}
