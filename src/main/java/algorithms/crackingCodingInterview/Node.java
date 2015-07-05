package algorithms.crackingCodingInterview;

import java.util.LinkedList;
import java.util.Queue;

public class Node {
    private int value;
    Node left;
    Node right;
    Node parent;

    public Node(final Node parent, final int value, final Node left, final Node right) {
        this.parent = parent;
        this.value = value;
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return "Node{" + "value=" + value + '}';
    }

    public Node(final Node parent, final int value) {
        this(parent, value, null, null);
    }

    public boolean isLeaf() {
        return left == null && right == null;
    }

    public static Node createRoot(final int value) {
        return new Node(null, value);
    }

    public void setLeft(final Node left) {
        this.left = left;
    }

    public void setRight(final Node right) {
        this.right = right;
    }

    private static class NodeWithLevel {
        Node node;
        int level;

        public NodeWithLevel(final Node node, final int level) {
            this.node = node;
            this.level = level;
        }
    }

    public static boolean isBalanced(final Node root) {
        if (root == null) {
            return true;
        }

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        Queue<NodeWithLevel> queue = new LinkedList<NodeWithLevel>();
        queue.add(new NodeWithLevel(root, 0));
        while (!queue.isEmpty()) {
            NodeWithLevel current = queue.remove();
            if (current.node.isLeaf()) {
                if (min > current.level) {
                    min = current.level;
                }

                if (max < current.level) {
                    max = current.level;
                }
            } else {
                if (current.node.left != null) {
                    queue.offer(new NodeWithLevel(current.node.left, current.level + 1));
                }

                if (current.node.right != null) {
                    queue.offer(new NodeWithLevel(current.node.right, current.level + 1));
                }
            }
        }

        return max - min <= 1;
    }
}
