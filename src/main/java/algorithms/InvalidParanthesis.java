package algorithms;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class InvalidParanthesis {

    private int observedMin = Integer.MAX_VALUE;
    private Set<String> visited = new HashSet<>();

    public static void main(String[] args) {
        final List<String> result = new InvalidParanthesis().removeInvalidParentheses("()())()");

        System.out.println(Arrays.toString(result.toArray(new String[0])));
    }

    public List<String> removeInvalidParentheses(String s) {
        if (s == null || s.length() == 0) {
            List<String> result = new LinkedList<>();
            result.add("");
            return result;
        }
        Set<String> result = new HashSet<>();
        ;
        solve(s.toCharArray(), 0, new StringBuilder(), 0, result);

        List<String> finalResult = new LinkedList<>();
        finalResult.addAll(result);

        return finalResult;
    }

    void solve(char[] str, int idx, StringBuilder expression, int removedTillNow, Set<String> result) {
        if (observedMin < removedTillNow) {
            return;
        }

        int actuallyRemoved = removedTillNow + str.length - idx;
        System.out.println(String.format("expression = [%s], actuallyRemoved = [%s], removedTillNow = [%s], idx = [%s], observedMin = [%s]",
                expression.toString(),
                actuallyRemoved,
                removedTillNow,
                idx,
                observedMin
                )
        );

        if (isValid(expression.toString())) {
            if (observedMin > actuallyRemoved) {
                System.out.println(String.format("observedMin = [%s], result = [%s]", observedMin, Arrays.toString(result.toArray(new String[0]))));
                result.clear();
                observedMin = actuallyRemoved;
            }
            if (observedMin == actuallyRemoved) {
                result.add(expression.toString());
            }
        }
        if (idx >= str.length) {
            return;
        } else {
            if (str[idx] == '(' || str[idx] == ')') {
                solve(str, idx + 1, expression, removedTillNow + 1, result);
            }
            expression.append(str[idx]);
            solve(str, idx + 1, expression, removedTillNow, result);
            expression.deleteCharAt(expression.length() - 1);
        }
    }

    boolean isValid(String expression) {
        Stack<Character> stack = new Stack<>();
        for (int idx = 0; idx < expression.length(); idx++) {
            if (expression.charAt(idx) == '(') {
                stack.push(expression.charAt(idx));
            } else if (expression.charAt(idx) == ')') {
                if (!stack.isEmpty() && stack.peek() == '(') {
                    stack.pop();
                } else return false;
            }
        }
        return stack.isEmpty();
    }
}