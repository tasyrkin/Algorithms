package algorithms;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;


public class TreePruning {


    private static class Node {
        int id;
        int cost;
        Node parent;
        int step;
        int stepCost;
        LinkedList<Node> children = new LinkedList<>();

        public Node(int id, int cost) {
            this.id = id;
            this.cost = cost;
        }

        public void add(Node node) {
            children.add(node);
        }
    }

    private static class TraverseNode {
        Node node;
        boolean stopTraversing;

        public TraverseNode(Node node, boolean stopTraversing) {
            this.node = node;
            this.stopTraversing = stopTraversing;
        }
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        Node[] index = new Node[n + 1];
        for (int i = 0; i < n; i++) {
            int cost = sc.nextInt();
            index[i + 1] = new Node(i + 1, cost);
        }
        for (int i = 0; i < n; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            index[from].add(index[to]);
            index[to].parent = index[from];
        }

        Set<Integer> removedNodeIds = new HashSet<>();
        while (k-- > 0) {
            Node minCostNode = determineMinCost(removedNodeIds, index, k);
            if (minCostNode.cost >= 0) break;
        }
        //determine the sum
    }

    public static Node determineMinCost(Set<Integer> removedNodeIds, Node[] index, int step) {
        Stack<TraverseNode> stack = new Stack<>();
        TraverseNode curr = new TraverseNode(index[1], false);
        Node minCostNode = null;
        while (!stack.empty() || curr.node != null) {
            if (curr.node != null) {
                if (curr.stopTraversing) {
                    if (curr.node.step != step) {
                        curr.node.step = step;
                        curr.node.stepCost = curr.node.cost;
                    } else {
                        curr.node.stepCost += curr.node.cost;
                    }
                    if (minCostNode == null || minCostNode.stepCost > curr.node.stepCost) minCostNode = curr.node;
                    if (curr.node.parent != null) {
                        if (curr.node.parent.step != step) {
                            curr.node.parent.step = step;
                            curr.node.parent.stepCost = curr.node.stepCost;
                        } else {
                            curr.node.parent.stepCost += curr.node.stepCost;
                        }
                    }
                    curr.node = null;
                } else {
                    stack.push(new TraverseNode(curr.node, true));
                    int cnt = 0;
                    for (Node child : curr.node.children) {
                        if (cnt++ > 0) {
                            stack.push(new TraverseNode(child, false));
                        }
                    }
                    if (curr.node.children.size() >= 1) {
                        curr.node = curr.node.children.getFirst();
                        curr.stopTraversing = false;
                    } else {
                        curr.node = null;
                    }
                }
            } else {
                curr = stack.pop();
            }
        }
        return minCostNode;
    }          
    
 }