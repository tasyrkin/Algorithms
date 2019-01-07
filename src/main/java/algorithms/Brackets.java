package algorithms;

import java.util.Arrays;
import java.util.Stack;

public class Brackets {
    public static void main(String[] args) {
        System.out.println(
                Arrays.toString(
                        braces(new String[]{
                                "{([])}",
                                "{([]})",
                        })
                )
        );
    }

    static String[] braces(String[] values) {
        if (values == null || values.length == 0) return new String[0];

        final String no = "NO";
        final String yes = "YES";

        final String[] result = new String[values.length];

        for (int vidx = 0; vidx < values.length; ++vidx) {
            Stack<Integer> one = new Stack<>();
            Stack<Integer> two = new Stack<>();
            Stack<Integer> three = new Stack<>();

            char[] braces = values[vidx].toCharArray();

            for (int bidx = 0; bidx < braces.length; ++bidx) {
                if (braces[bidx] == '[') one.push(bidx);
                if (braces[bidx] == '{') two.push(bidx);
                if (braces[bidx] == '(') three.push(bidx);

                if (braces[bidx] == ']') {
                    if (!isValid(one, two, three)) {
                        result[vidx] = no;
                        break;
                    } else {
                        one.pop();
                    }
                }
                if (braces[bidx] == '}') {
                    if (!isValid(two, one, three)) {
                        result[vidx] = no;
                        break;
                    } else {
                        two.pop();
                    }
                }
                if (braces[bidx] == ')') {
                    if (!isValid(three, one, two)) {
                        result[vidx] = no;
                        break;
                    } else {
                        three.pop();
                    }
                }
            }
            if (one.isEmpty() && two.isEmpty() && three.isEmpty()) {
                result[vidx] = yes;
            } else {
                result[vidx] = no;
            }
        }
        return result;
    }

    private static boolean isValid(final Stack<Integer> self, final Stack<Integer> other1, final Stack<Integer> other2) {
        if (!self.isEmpty()) {
            if (!other1.isEmpty()) {
                if (self.peek() < other1.peek()) return false;
            }
            if (!other2.isEmpty()) {
                if (self.peek() < other2.peek()) return false;
            }
        }
        return true;
    }
}
