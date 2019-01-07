package algorithms;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

class WordBreak {

    public static void main(String[] args) {

        //final String s = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        final String s = "aaaaaaaaaaaaaaaaaaaa";
        final List<String> dict = Lists.newArrayList("a", "aa", "aaa", "aaaa", "aaaaa", "aaaaaa", "aaaaaaa", "aaaaaaaa", "aaaaaaaaa", "aaaaaaaaaa");
        final List<String> result = new WordBreak().wordBreak(s, dict);

        //System.out.println(Arrays.toString(result.toArray(new String[0])));
        System.out.println(result.size() + ", " + new HashSet<>(result).size());
    }

    public List<String> wordBreak(String s, List<String> dict) {
        if (s == null || s.length() == 0) return new ArrayList<>();
        if (dict == null || dict.size() == 0) return new ArrayList<>();

        TrieNode root = TrieNode.build(dict);
        char[] chs = s.toCharArray();
        LinkedList<LinkedList<Integer>> breaks = new LinkedList<>();
        LinkedList<Integer> currBreaks = new LinkedList<>();
        Map<Integer, Integer> cnts = new HashMap<>();
        //Set<Pair> visited = new HashSet<>();
        find(chs, 0, root, root, breaks, currBreaks, cnts);

        List<String> result = new ArrayList<>();
        for (LinkedList<Integer> currBrs : breaks) {
            StringBuilder sb = new StringBuilder();
            int idx = 0;
            for (int currBreak : currBrs) {
                if (sb.length() > 0) sb.append(" ");
                sb.append(s.substring(idx, currBreak + 1));
                idx = currBreak + 1;
            }
            if (idx < s.length()) {
                if (sb.length() > 0) sb.append(" ");
                sb.append(s.substring(idx));
            }
            result.add(sb.toString());
        }

        for (int cnt : cnts.keySet().toArray(new Integer[0])) {
            System.out.println(String.format("%s -> %s", cnt, cnts.get(cnt)));
        }

        return result;
    }

    private void find(char[] chs,
                      int idx,
                      TrieNode node,
                      TrieNode root,
                      LinkedList<LinkedList<Integer>> breaks,
                      LinkedList<Integer> currBreaks,
                      Map<Integer, Integer> cnts) {

//        final Pair pair = new Pair(node, idx);
//        if (visited.contains(pair)) return;

        if (idx >= chs.length) return;

        TrieNode currTN = node.children.get(chs[idx]);
        if (currTN == null) return;

        int count = cnts.getOrDefault(idx, 0);
        cnts.put(idx, count + 1);

        if (currTN.isFinal) {
            currBreaks.add(idx);

            if (idx + 1 == chs.length) {
                LinkedList<Integer> result = new LinkedList<>();
                for (Integer currBreak : currBreaks) {
                    result.add(currBreak);
                }
                breaks.add(result);
            }

            find(chs, idx + 1, root, root, breaks, currBreaks, cnts);
            currBreaks.removeLast();
        }
        find(chs, idx + 1, currTN, root, breaks, currBreaks, cnts);

//        visited.add(pair);
    }

    static class Pair {
        TrieNode node;
        int idx;

        Pair(TrieNode node, int idx) {
            this.node = node;
            this.idx = idx;
        }

        public boolean equals(Object o) {
            if (o == null) return false;
            if (o instanceof Pair) {
                Pair p = (Pair) o;
                return idx == p.idx && node == p.node;
            }
            return false;
        }

        public int hashCode() {
            int result = 1;

            result = 31 * result + Integer.valueOf(idx).hashCode();
            result = 31 * result + node.hashCode();

            return result;
        }
    }

    static class TrieNode {
        boolean isFinal;
        Map<Character, TrieNode> children = new HashMap<>();

        static TrieNode build(List<String> dict) {
            TrieNode root = new TrieNode();

            for (String w : dict) {
                char[] chs = w.toCharArray();
                build(root, chs, 0);
            }
            return root;
        }

        static void build(TrieNode node, char[] chs, int idx) {
            if (idx >= chs.length) return;
            TrieNode curr = node.children.get(chs[idx]);
            if (curr == null) {
                curr = new TrieNode();
            }
            node.children.put(chs[idx], curr);
            curr.isFinal |= chs.length == idx + 1;
            build(curr, chs, idx + 1);
        }
    }
}