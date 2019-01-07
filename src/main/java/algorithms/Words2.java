package algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

class Words2 {

    public static void main(String[] args) {
        //final char[][] b = {{'a', 'b'}, {'c', 'd'}};
        final char[][] b = {{'a', 'b'}, {'a', 'a'}};
        //final String[] ws = {"ab", "cb", "ad", "bd", "ac", "ca", "da", "bc", "db", "adcb", "dabc", "abb", "acb"};
        //final String[] ws = {"ab", "abb"};
        //final String[] ws = {"aaa","aaab","aaba","aba","baa"};
        final String[] ws = {"aaa","aaab","aaba","aba","baa"};
        final List<String> result = new Words2().findWords(b, ws);

        System.out.println(Arrays.toString(result.toArray(new String[0])));
    }

    public List<String> findWords(char[][] b, String[] ws) {
        if (b == null || b.length == 0) return new ArrayList<>();
        if (ws == null || ws.length == 0) return new ArrayList<>();

        TrieNode root = TrieNode.build(ws);
        Set<String> result = new HashSet<>();
        for (int i = 0; i < b.length; i++) {
            for (int j = 0; j < b[0].length; j++) {
                Set<Pos> visited = new HashSet<>();
                LinkedList<Character> curr = new LinkedList<>();
                find(new Pos(i, j), b, root, visited, result, curr);
            }
        }
        List<String> resultList = new ArrayList<>();
        for (String s : result) {
            resultList.add(s);
        }
        return resultList;
    }

    private boolean isValid(char[][] b, Pos p) {
        return p.x >= 0 && p.x < b.length && p.y >= 0 && p.y < b[0].length;
    }

    private void find(Pos pos, char[][] b, TrieNode node, Set<Pos> visited, Set<String> result, LinkedList<Character> currStr) {
        if (!isValid(b, pos)) return;
        if (visited.contains(pos)) return;
        visited.add(pos);
        char ch = b[pos.x][pos.y];
        TrieNode currTN = node.cs.get(ch);
        if (currTN == null) {
            visited.remove(pos);
            return;
        }
        currStr.add(ch);
        if (currTN.isFinal) {
            StringBuilder sb = new StringBuilder();
            for (char currCh : currStr) {
                sb.append(currCh);
            }
            result.add(sb.toString());
            //currStr.removeLast();
            //visited.remove(pos);
            //System.out.println("[" + pos.x + ", "+ pos.y + "] :" + sb.toString());
            //return;
        }
        find(new Pos(pos.x - 1, pos.y), b, currTN, visited, result, currStr);
        find(new Pos(pos.x + 1, pos.y), b, currTN, visited, result, currStr);
        find(new Pos(pos.x, pos.y - 1), b, currTN, visited, result, currStr);
        find(new Pos(pos.x, pos.y + 1), b, currTN, visited, result, currStr);
        currStr.removeLast();
        visited.remove(pos);
    }

    static class TrieNode {
        boolean isFinal;
        Map<Character, TrieNode> cs = new HashMap<>();

        static TrieNode build(String[] ws) {
            TrieNode root = new TrieNode();

            for (int i = 0; i < ws.length; i++) {
                build(root, ws[i].toCharArray(), 0);
            }

            return root;
        }

        static void build(TrieNode node, char[] w, int idx) {
            if (idx >= w.length) return;

            TrieNode currNode = node.cs.get(w[idx]);
            if (currNode == null) {
                currNode = new TrieNode();
            }
            currNode.isFinal |= idx == w.length - 1;
            node.cs.put(w[idx], currNode);
            build(currNode, w, idx + 1);
        }
    }

    static class Pos {
        int x;
        int y;

        Pos(int i, int j) {
            x = i;
            y = j;
        }

        public boolean equals(Object o) {
            if (o == null) return false;
            if (o instanceof Pos) {
                Pos p = (Pos) o;
                return x == p.x && y == p.y;
            }
            return false;
        }

        public int hashCode() {
            int result = 1;

            result = 31 * result + Integer.valueOf(x).hashCode();
            result = 31 * result + Integer.valueOf(y).hashCode();

            return result;
        }
    }
}