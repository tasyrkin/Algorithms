package algorithms;

import com.google.common.base.Objects;

public class IntersectingTwoNodes {
    public static void main(String[] args) {
        ListNode a = build(1, 2, 3, 4);
        ListNode b = build(7, 8, 9, 10, 20, 30);

        //b.next.next.next.next.next = a.next.next;

        System.out.println(toString(a));
        System.out.println(toString(b));


        final ListNode node = getIntersectionNode(a, b);
        System.out.println(node);
    }

    private static ListNode build(int... vals) {
        ListNode head = new ListNode(vals[0]);
        ListNode tmp = head;
        for (int i = 1; i < vals.length; i++) {
            tmp.next = new ListNode(vals[i]);
            tmp = tmp.next;
        }
        return head;
    }

    static String toString(ListNode node) {
        StringBuilder sb = new StringBuilder();
        while (node != null) {
            if (sb.length() > 0) sb.append("->");
            sb.append(node.val);
            node = node.next;
        }
        return sb.toString();
    }

    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        //boundary check
        if (headA == null || headB == null) return null;

        ListNode a = headA;
        ListNode b = headB;

        //if a & b have different len, then we will stop the loop after second iteration
        while (a != b) {
            //for the end of first iteration, we just reset the pointer to the head of another linkedlist

            System.out.println("a = " + a);
            System.out.println("b = " + b);

            a = a == null ? headB : a.next;
            b = b == null ? headA : b.next;
        }

        return a;
    }

    private static class ListNode {
        final int val;
        ListNode next;

        public ListNode(final int val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return Objects.toStringHelper(this)
                    .add("val", val)
                    .toString();
        }
    }

}
