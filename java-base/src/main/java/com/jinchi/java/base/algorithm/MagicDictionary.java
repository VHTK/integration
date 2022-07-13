package com.jinchi.java.base.algorithm;


public class MagicDictionary {
    private MagicDictionary[] children;
    private boolean isEnd;

    public MagicDictionary() {
        children = new MagicDictionary[26];
        isEnd = false;
    }

    public static void main(String[] args) {
        MagicDictionary magicDictionary = new MagicDictionary();
        magicDictionary.buildDict(new String[]{"hello", "hallo"});
        System.out.println(magicDictionary.search("hello")); // 返回 False
        //System.out.println(magicDictionary.search("hell")); // 将第二个 'h' 替换为 'e' 可以匹配 "hello" ，所以返回 True
        //System.out.println(magicDictionary.search("leetcoded")); // 将第二个 'h' 替换为 'e' 可以匹配 "hello" ，所以返回 True
        System.out.println('a' - 'a');
        System.out.println('z' - 'a');

    }

    public void buildDict(String[] dictionary) {
        for (String word : dictionary) {
            MagicDictionary node = this;//指针指向当前的根
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);//待插入的字符
                int index = ch - 'a';//参数
                //当前的节点为null，就新建一个节点
                if (node.children[index] == null) {
                    node.children[index] = new MagicDictionary();
                }
                //当前节点不为null,就将node沿指针移动到子节点
                node = node.children[index];
            }
            //完成插入后，就将此时node所指向的节点isEnd置为true
            node.isEnd = true;
        }
    }

    public boolean search(String searchWord) {
        // TODO
        return false;
    }

    private MagicDictionary searchPrefix(String prefix) {
        MagicDictionary node = this;//指针指向当前的根
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