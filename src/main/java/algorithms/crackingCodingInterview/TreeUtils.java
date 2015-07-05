package algorithms.crackingCodingInterview;

public class TreeUtils {
    public static Node createTree(final int[] sortedArray) {
        if (sortedArray == null || sortedArray.length == 0) {
            return null;
        }

        int midIdx = sortedArray.length / 2;
        Node root = Node.createRoot(sortedArray[midIdx]);
        appendLeft(root, sortedArray, 0, midIdx - 1);
        appendRight(root, sortedArray, midIdx + 1, sortedArray.length - 1);
        return root;
    }

    private static void appendLeft(final Node parent, final int[] sortedArray, final int startIncl, final int endIncl) {
        if (startIncl > endIncl) {
            return;
        }

        int midIdx = startIncl + (endIncl - startIncl) / 2;
        Node node = new Node(parent, sortedArray[midIdx]);
        parent.setLeft(node);
        appendLeft(node, sortedArray, startIncl, midIdx - 1);
        appendRight(node, sortedArray, midIdx + 1, endIncl);
    }

    private static void appendRight(final Node parent, final int[] sortedArray, final int startIncl,
            final int endIncl) {
        if (startIncl > endIncl) {
            return;
        }

        int midIdx = startIncl + (endIncl - startIncl) / 2;
        Node node = new Node(parent, sortedArray[midIdx]);
        parent.setRight(node);
        appendLeft(node, sortedArray, startIncl, midIdx - 1);
        appendRight(node, sortedArray, midIdx + 1, endIncl);
    }

    public static Node successor(final Node node) {
        if (node == null) {
            return null;
        }

        if (node.right != null) {
            Node succ = node.right;
            while (succ.left != null) {
                succ = succ.left;
            }

            return succ;
        }

        if (node.parent != null && node.parent.left == node) {
            return node.parent;
        }

        if (node.parent != null && node.parent.right == node) {
            Node succ = node.parent;
            while (succ.parent != null && succ.parent.left != succ) {
                succ = succ.parent;
            }

            if (succ.parent == null) {
                return null;
            }

            return succ;
        }

        return null;
    }

    public static Node commonAncestor(final Node n1, final Node n2) {
        Node node1 = n1;
        Node node2 = n2;
        if (node1 == null || node2 == null) {
            return null;
        }

        int level1 = getLevel(node1);
        int level2 = getLevel(node2);
        if (level1 > level2) {
            int levelTmp = level1;
            Node nodeTmp = node1;
            level1 = level2;
            node1 = node2;
            level2 = levelTmp;
            node2 = nodeTmp;
        }
        while (level1 != level2) {
            node2 = node2.parent;
            --level2;
        }
        while (node1 != node2) {
            node1 = node1.parent;
            node2 = node2.parent;
        }

        return node1;
    }

    public static int getLevel(Node node) {
        int result = -1;
        while (node != null) {
            node = node.parent;
            ++result;
        }

        return result;
    }

    public static void main(final String[] args) {
        int[] sortedArray = {1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024};

        Node root = TreeUtils.createTree(sortedArray);

        System.out.println("successor for " + root + " is " + successor(root));
        System.out.println("successor for " + root.right.right.right + " is " + successor(root.right.right.right));
        System.out.println("successor for " + root.left.right + " is " + successor(root.left.right));

        System.out.println("common ancestor for " + root.right.right + "and " + root.right.left + " is "
                + commonAncestor(root.right.right, root.right.left));
    }
}
