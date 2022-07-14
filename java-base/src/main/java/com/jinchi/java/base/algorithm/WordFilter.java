package com.jinchi.java.base.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordFilter {
    private Trie prefTrie;
    //private Trie suffTrie;
    Map<String, Integer> wordAndIndexMap;
    Map<Integer, String> indexAndWordMap;

    public static void main(String[] args) {
        String[] words = new String[]{"apple"};
        WordFilter wordFilter = new WordFilter(words);
        System.out.println(wordFilter.f("a", "e"));
    }

    public WordFilter(String[] words) {
        prefTrie = new Trie();
        //suffTrie = new Trie();
        wordAndIndexMap = new HashMap<>();
        indexAndWordMap = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            wordAndIndexMap.put(words[i], i);
        }
        wordAndIndexMap.forEach((k, v) -> {
            indexAndWordMap.put(v, k);
            prefTrie.insert(k, v);
            //suffTrie.reversInsert(k, v);
        });
    }

    public int f(String pref, String suff) {
        List<Integer> indexs = prefTrie.startsWithIndexs(pref);
        if (indexs.size() == 0) {
            return -1;
        }

        Trie suffTrie = new Trie();
        indexs.forEach(idx -> {
            if (null != indexAndWordMap.get(idx)) {
                suffTrie.reversInsert(indexAndWordMap.get(idx), idx);
            }
        });

        List<Integer> reverseIndexs = suffTrie.endWithIndexs(suff);
        if (reverseIndexs.size() == 0) {
            return -1;
        }

        Integer max = Integer.MIN_VALUE;
        for (int i : indexs) {
            if (reverseIndexs.contains(i)) {
                if (i > max) {
                    max = i;
                }
            }
        }

        return max == Integer.MIN_VALUE ? -1 : max;
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

        public void reversInsert(String word, int idx) {
            Trie node = this;//指针指向当前的根
            for (int i = word.length() - 1; i >= 0; i--) {
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

        public List<Integer> endWithIndexs(String suff) {
            List<Integer> indexs = new ArrayList<>();
            //只要返回值不为null，说明搜索到了前缀的末尾就为true，否则为false
            Trie node = searchSuff(suff);
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

        private Trie searchSuff(String suff) {
            Trie node = this;//指针指向当前的根
            for (int i = suff.length() - 1; i >= 0; i--) {
                //当前访问的字符及其参数
                char ch = suff.charAt(i);
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
