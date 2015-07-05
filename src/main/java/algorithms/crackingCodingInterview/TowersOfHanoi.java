package algorithms.crackingCodingInterview;

import java.util.Stack;

import com.google.common.base.Preconditions;

public class TowersOfHanoi {
    public static void move(final Stack<Integer>[] threeTowers, final int from, final int to) {
        if (from == to) {
            return;
        }

        Preconditions.checkArgument(from >= 0 && from < 3, "wrong from tower");
        Preconditions.checkArgument(to >= 0 && to < 3, "wrong to tower");
        Preconditions.checkArgument(threeTowers != null && threeTowers.length == 3, "expected three towers");
        Preconditions.checkArgument(!threeTowers[from].isEmpty() ^ !threeTowers[(from + 1) % 3].isEmpty()
                ^ !threeTowers[(from + 2) % 3].isEmpty(), "only one tower must be non emtpy");
        move(threeTowers, from, to, threeTowers[from].size());
    }

    private static void move(final Stack<Integer>[] threeTowers, final int from, final int to, final int numOfElems) {
        if (numOfElems == 1) {
            threeTowers[to].push(threeTowers[from].pop());
            return;
        }

        int tmpIndex = getTmpIndex(from, to);
        move(threeTowers, from, tmpIndex, numOfElems - 1);
        move(threeTowers, from, to, 1);
        move(threeTowers, tmpIndex, to, numOfElems - 1);
    }

    private static int getTmpIndex(int from, int to) {
        if (from > to) {
            int tmp = from;
            from = to;
            to = tmp;
        }

        if (from == 0 && to == 1) {
            return 2;
        }

        if (from == 1 && to == 2) {
            return 0;
        }

        return 1;
    }

    public static void main(final String[] args) {
        Stack<Integer>[] threeTowers = new Stack[3];
        threeTowers[0] = new Stack<>();
        threeTowers[1] = new Stack<>();
        threeTowers[2] = new Stack<>();

        threeTowers[2].push(5);
        threeTowers[2].push(4);
        threeTowers[2].push(3);
        threeTowers[2].push(2);
        threeTowers[2].push(1);

        System.out.println(threeTowers[0]);
        System.out.println(threeTowers[1]);
        System.out.println(threeTowers[2]);

        move(threeTowers, 2, 0);
        System.out.println("Moving.....");

        System.out.println(threeTowers[0]);
        System.out.println(threeTowers[1]);
        System.out.println(threeTowers[2]);
    }
}
