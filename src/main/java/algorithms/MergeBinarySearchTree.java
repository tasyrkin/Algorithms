package algorithms;

public class MergeBinarySearchTree {

    public static class Node {
        int data;
        Node left;
        Node right;

        public Node(int data) {
            this(data, null, null);
        }

        public Node(int data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Node node = (Node) o;

            if (data != node.data) return false;
            if (left != null ? !left.equals(node.left) : node.left != null) return false;
            return right != null ? right.equals(node.right) : node.right == null;

        }

        @Override
        public int hashCode() {
            int result = data;
            result = 31 * result + (left != null ? left.hashCode() : 0);
            result = 31 * result + (right != null ? right.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }

    public static Node createTree(int[] sortedArray) {
        if(sortedArray == null || sortedArray.length == 0) {
            return null;
        }

        return restore(sortedArray, 0, sortedArray.length-1);
    }

    public static Node merge(Node tree1, Node tree2) {
        if(tree1 == null && tree2 == null) return null;

        int[] arr1 = flatten(tree1);
        int[] arr2 = flatten(tree2);

        int[] result = new int[arr1.length + arr2.length];

        return restore(result, 0, result.length);
    }

    public static int[] flatten(Node tree) {

        int size = size(tree);

        int[]result = new int[size];

        int currIdx = flatten(tree.left, result, 0);

        result[currIdx] = tree.data;

        flatten(tree.right, result, currIdx+1);

        return result;
    }

    private static int flatten(Node tree, int[] result, int index) {
        if(tree == null) return index;

        int currIdx = flatten(tree.left, result, index);

        result[currIdx] = tree.data;

        return flatten(tree.right, result, currIdx+1)+1;
    }

    private static int size(Node tree) {

        if(tree == null) return 0;

        return size(tree.left) + 1 + size(tree.right);
    }

    public static Node restore(int[] array, int start, int end) {

        if(start > end || start >= array.length) return null;

        int mid = start + (end - start) / 2;

        return new Node(array[mid], restore(array, start, mid-1), restore(array, mid+1, end));
    }

}
