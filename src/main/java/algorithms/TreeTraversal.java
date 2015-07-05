package algorithms;

import java.util.LinkedList;
import java.util.Stack;

public class TreeTraversal {
    
    public static class Node{
        Node parent;
        LinkedList<Node> children = new LinkedList<>();
        int data;

        public Node(Node parent, int data) {
            this.parent = parent;
            this.data = data;
        }
    }
    
    public void preOrderIterative(Node root){
        Stack<Node> stack = new Stack<>();
        Node curr = root;
        while(!stack.empty()||curr!=null){
            if(curr != null){
                System.out.print(" " + curr.data);
                int cnt = 0;
                for(Node child : curr.children){
                    if(cnt++>0){
                        stack.push(child);
                    }
                }
                if(curr.children.size()>=1){
                    curr = curr.children.getFirst();
                } else {
                    curr = null;
                }
            } else {
                curr = stack.pop();
            }
        }
    }
    
    public static Node createSearchTree(int[]arr){
        if(arr == null || arr.length == 0)return null;
        Node root = new Node(null, arr[0]);
        for(int i = 1; i < arr.length; i++){
            Node curr = root;
            while(curr != null){
                if(curr.data < arr[i]){
                    //if(curr.)
                }
            }
        }
        return root;
    }

    public static void main(String[] args) {
        int[]arr = new int[]{4,2,3,4,5,5,6,7,8};
        
    }
}
