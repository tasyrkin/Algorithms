package algorithms;

import java.util.LinkedList;
import java.util.ListIterator;

public class ReverseInteger {

    public static void main(String[] args) {
        new ReverseInteger().reverse(-2147483648);
    }

    public int reverse(int x) {
        LinkedList<Integer> digits = new LinkedList<>();
        int mult = x >= 0 ? 1 : -1;
        while (true) {
            int curr = x / mult;
            if (curr == 0) break;
            mult *= 10;
            digits.add(curr % 10);
        }
        ListIterator<Integer> listIter = digits.listIterator(digits.size());
        mult = 1;
        int result = 0;
        int sign = x > 0 ? 1 : -1;
        while (listIter.hasPrevious()) {
            int digit = listIter.previous();
            try {
                int add = Math.multiplyExact(mult, digit);
                add = Math.multiplyExact(add, sign);
                result = Math.addExact(result, add);
            } catch (Exception e) {
                return 0;
            }
            mult *= 10;
        }
        return result;
    }
}