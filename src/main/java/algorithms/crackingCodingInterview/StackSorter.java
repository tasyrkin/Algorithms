package algorithms.crackingCodingInterview;

import java.util.Stack;

public class StackSorter {
    public static void sort(final Stack<Integer> inputStack) {
        if (inputStack == null || inputStack.isEmpty()) {
            return;
        }

        final Stack<Integer> tmpStack = new Stack<>();
        while (!inputStack.isEmpty()) {
            Integer elem = inputStack.pop();
            if (tmpStack.isEmpty() || tmpStack.peek() <= elem) {
                tmpStack.push(elem);
            } else {
                while (!tmpStack.isEmpty()) {
                    Integer tmpElem = tmpStack.pop();
                    if (tmpElem <= elem) {
                        tmpStack.push(tmpElem);
                        break;
                    } else {
                        inputStack.push(tmpElem);
                    }
                }

                tmpStack.push(elem);
            }
        }
        while (!tmpStack.isEmpty()) {
            inputStack.push(tmpStack.pop());
        }
    }

    public static void main(final String[] args) {

        Stack<Integer> stack = new Stack<>();

        stack.push(2);
        stack.push(3);
        stack.push(1);
        stack.push(5);
        stack.push(4);

        StackSorter.sort(stack);

        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + ", ");
        }

    }
}
