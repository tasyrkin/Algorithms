package algorithms;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class WordLadder {
    public static void main(String[] args) {
        final List<List<String>> result = new WordLadder().findLadders(
                "a",
                "c",
                Lists.newArrayList("b", "a", "c"));

        for (List<String> path : result) {
            System.out.println(Arrays.toString(path.toArray(new String[0])));
        }
    }

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> dict) {
        if (dict == null || dict.size() == 0) return new ArrayList<>();

        Map<String, Set<String>> graph = buildGraph(beginWord, dict);
        int minLevel = dict.size();

        Queue<TNode> queue = new LinkedList<>();
        queue.offer(new TNode(0, beginWord, null));
        List<List<String>> result = new ArrayList<>();

        Set<String> visited = new HashSet<>();
        while (!queue.isEmpty()) {
            TNode currNode = queue.poll();
            visited.add(currNode.word);
            System.out.println(String.format("level [%s], word = [%s]", currNode.level, currNode.word));
            if (currNode.level <= minLevel) {
                if (endWord.equals(currNode.word)) {
                    minLevel = currNode.level;
                    TNode resultGatherer = currNode;
                    LinkedList<String> currResult = new LinkedList<>();
                    while (resultGatherer != null) {
                        currResult.addFirst(resultGatherer.word);
                        resultGatherer = resultGatherer.prev;
                    }
                    result.add(currResult);
                }
                Set<String> neighbours = graph.get(currNode.word);
                if (neighbours != null) {
                    for (String neighbour : neighbours) {
                        if (!visited.contains(neighbour)) {
                            queue.offer(new TNode(currNode.level + 1, neighbour, currNode));
                        }
                    }
                }
            }
        }

        return result;
    }

    Map<String, Set<String>> buildGraph(String beginWord, List<String> dict) {

        Map<String, Set<String>> graph = new HashMap<>();

        String[] array = dict.toArray(new String[0]);
        for (int i = 0; i < array.length; i++) {
            if (difference(beginWord, array[i]) == 1) {
                Set<String> nbw = graph.getOrDefault(beginWord, new HashSet<>());
                nbw.add(array[i]);
                graph.put(beginWord, nbw);
            }
            for (int j = i + 1; j < array.length; j++) {
                if (difference(array[i], array[j]) == 1) {
                    Set<String> ni = graph.getOrDefault(array[i], new HashSet<>());
                    ni.add(array[j]);
                    graph.put(array[i], ni);

                    Set<String> nj = graph.getOrDefault(array[j], new HashSet<>());
                    nj.add(array[i]);
                    graph.put(array[j], nj);
                }
            }
        }

        return graph;
    }

    private int difference(String s1, String s2) {
        int result = 0;
        for (int k = 0; k < s1.length(); k++) {
            result += s1.charAt(k) == s2.charAt(k) ? 0 : 1;
        }
        return result;
    }

    static class TNode {
        int level;
        String word;
        TNode prev;

        TNode(int level, String word, TNode prev) {
            this.level = level;
            this.word = word;
            this.prev = prev;
        }
    }
}
