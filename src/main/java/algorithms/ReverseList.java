package algorithms;

public class ReverseList {
    public static class Node {
        int data;
        Node next;

        public Node(final int data, final Node next) {
            this.data = data;
            this.next = next;
        }

        @Override
        public String toString() {
            return "Node{" + "data=" + data + ", next=" + next + '}';
        }
    }

    public static Node from(final int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }

        final Node head = new Node(arr[0], null);
        Node currNode = head;
        for (int i = 1; i < arr.length; i++) {
            currNode.next = new Node(arr[i], null);
            currNode = currNode.next;
        }

        return head;
    }

    public static String toString(Node head) {
        StringBuilder sb = new StringBuilder();

        while (head != null) {
            if (sb.length() > 0) {
                sb.append(",");
            }

            sb.append(head.data);
            head = head.next;
        }

        return sb.toString();
    }

    public static void ReverseNode(final Node n) {
        if (n.next == null || n.next == n) {
            return;
        }

        ReverseNode(n.next);
        n.next.next = n;
        n.next = null;
    }

    public static Node reverse(final Node head) {
        if (head == null) {
            return null;
        }

        Node currHead = head;
        while (head.next != null) {
            Node tmpNode = head.next;
            head.next = tmpNode.next;
            tmpNode.next = currHead;
            currHead = tmpNode;
        }

        return currHead;
    }

    public static void main(final String[] args) {
        Node head = from(new int[] {1, 2, 3, 4, 5});
        System.out.println(toString(head));

        ReverseNode(head);

        // System.out.println(toString(ReverseNode(head)));
        System.out.println(head);
    }

}
