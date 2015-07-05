package algorithms;

import java.util.Arrays;
import java.util.Stack;

public class BinaryTreeTraversal {
    
    private static class Node{
        int data;
        Node left;
        Node right;
        Node parent;

        private Node(int data, Node parent, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }

        private Node(int data, Node parent) {
            this(data, parent, null, null);
        }
    }
    
    public static class TraverseNode{
        Node node;
        boolean stopTraversing;

        public TraverseNode(Node node, boolean stopTraversing) {
            this.node = node;
            this.stopTraversing = stopTraversing;
        }
    }
    
    public static void inOrderIterative(Node root){
        Stack<Node> s = new Stack<>();
        Node curr = root;
        while(!s.isEmpty() || curr != null){
            if(curr != null){
                s.push(curr);
                curr = curr.left;
            } else {
                curr = s.pop();
                System.out.print(" " + curr.data);
                curr = curr.right;
            }
        }
        System.out.println();
    }
    
    public static void preOrderIterative(Node root){
        Stack<Node> s = new Stack<>();
        Node curr = root;
        while(!s.isEmpty()||curr!=null){
            if(curr != null){
                System.out.print(" " + curr.data);
                if(curr.right!=null)s.push(curr.right);
                curr = curr.left;
            } else {
                curr = s.pop();
            }
        }
        System.out.println();
    }
    
    public static void postOrderIterative(Node root){
        Stack<TraverseNode> stack = new Stack<>();
        TraverseNode curr = new TraverseNode(root, false);
        while(!stack.isEmpty()||curr.node!=null){
            if(curr.node != null){
                if(curr.stopTraversing){
                    System.out.print(" " + curr.node.data);
                    curr.node = null;
                } else {
                    stack.push(new TraverseNode(curr.node, true));
                    if(curr.node.right!=null){
                        stack.push(new TraverseNode(curr.node.right, false));
                    }
                    curr.node = curr.node.left;
                }
            } else {
                curr = stack.pop();
            }
        }
        System.out.println();
    }
    
    public static Node buildTree(int[]arr){
        if(arr == null || arr.length == 0){
            return null;
        }
        int mid = (arr.length-1)/2;
        Node root = new Node(arr[mid], null);
        root.left = buildNode(root, arr, 0, mid - 1);
        root.right = buildNode(root, arr, mid+1, arr.length-1);
        return root;
    }

    private static  Node buildNode(Node root, int[] arr, int start, int end) {
        if(start>end)return null;
        int mid = start + (end-start)/2;
        Node node = new Node(arr[mid], root);
        node.left = buildNode(node, arr, start, mid-1);
        node.right = buildNode(node, arr, mid+1, end);
        return node;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
        Node root = buildTree(arr);
        System.out.println(Arrays.toString(arr));
        inOrderIterative(root);
        preOrderIterative(root);
        postOrderIterative(root);
    }
}
