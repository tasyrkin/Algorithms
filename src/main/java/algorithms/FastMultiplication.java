package algorithms;

import java.util.LinkedList;
import java.util.Scanner;

public class FastMultiplication {

    private static class PairOrderedByLen {
        final String sMax;
        final String sMin;

        public PairOrderedByLen(final String s1, final String s2) {
            sMax = s1.length() > s2.length() ? s1 : s2;
            sMin = s1.length() > s2.length() ? s2 : s1;
        }
    }

    public static void main(final String[] args) {
        Scanner sc = new Scanner(System.in);

        // String n1 = sc.next();
        // String n2 = sc.next();
        // String res = multiply(toBin(n1), toBin(n2));
        // System.out.println(toDec(res));
        String res = multiply("1110001111110100111111011111", "100001101111110011011011101110");
        System.out.println(res);
        System.out.println("This keyboard must be very cool!");
    }

    private static String toDec(final String bin) {
        String res = "0";
        String twoPow = "1";
        for (int i = bin.length() - 1; i >= 0; i--) {
            if (bin.charAt(i) == '1') {
                res = add10(res, twoPow);
            }

            twoPow = multiplyByTwo(twoPow);
        }

        return res;
    }

    private static String multiplyByTwo(final String num) {
        int carry = 0;
        LinkedList<String> result = new LinkedList<>();
        for (int i = num.length() - 1; i >= 0; i--) {
            int digit = Integer.parseInt(String.valueOf(num.charAt(i)));
            int curr = digit * 2 + carry;
            carry = curr / 10;
            result.addFirst(String.valueOf(curr % 10));
        }

        if (carry > 0) {
            result.addFirst(String.valueOf(carry));
        }

        StringBuilder resultStr = new StringBuilder();
        for (String part : result) {
            if (resultStr.length() == 0 && "0".equals(part)) {
                continue;
            }

            resultStr.append(part);
        }

        return resultStr.length() > 0 ? resultStr.toString() : "0";
    }

    private static String add10(final String n1, final String n2) {
        int p1 = n1.length() - 1;
        int p2 = n2.length() - 1;
        int carry = 0;
        LinkedList<String> result = new LinkedList<>();
        while (p1 >= 0 || p2 >= 0) {
            int curr1 = p1 >= 0 ? Integer.parseInt(String.valueOf(n1.charAt(p1))) : 0;
            int curr2 = p2 >= 0 ? Integer.parseInt(String.valueOf(n2.charAt(p2))) : 0;
            int curr = curr1 + curr2 + carry;
            carry = curr / 10;
            result.addFirst(String.valueOf(curr % 10));
            --p1;
            --p2;
        }

        if (carry > 0) {
            result.addFirst(String.valueOf(carry));
        }

        StringBuilder resultStr = new StringBuilder();
        for (String part : result) {
            if (resultStr.length() == 0 && "0".equals(part)) {
                continue;
            }

            resultStr.append(part);
        }

        return resultStr.length() > 0 ? resultStr.toString() : "0";
    }

    private static String toBin(final String dec) {
        String tenPow = "1";
        String res = "0";
        for (int i = dec.length() - 1; i >= 0; i--) {
            int curr = Integer.parseInt(String.valueOf(dec.charAt(i)));
            StringBuilder tenDig = new StringBuilder(curr > 0 ? "" : "0");
            while (curr > 0) {
                tenDig.append(String.valueOf(curr - 2 * (curr / 2)));
                curr /= 2;
            }

            res = add(res, multiply(tenDig.toString(), tenPow));
            tenPow = multiply(tenPow, "1010");
        }

        System.out.println(String.format("dec [%s] to bin [%s]", dec, res));

        return res;
    }

    private static String multiply(final String n1, final String n2) {
        if (n1.length() == 1 && n2.length() == 1) {
            return String.valueOf(Integer.parseInt(n1) * Integer.parseInt(n2));
        }

        PairOrderedByLen p = new PairOrderedByLen(n1, n2);

        int leftDigits = p.sMax.length() / 2;
        int rightDigits = p.sMax.length() - leftDigits;
        final String xLeft = p.sMax.substring(0, leftDigits);
        final String xRight = p.sMax.substring(leftDigits);

        final String yLeft = (p.sMin.length() > rightDigits) ? p.sMin.substring(0, p.sMin.length() - rightDigits) : "0";
        final String yRight = p.sMin.substring(Math.max(0, p.sMin.length() - rightDigits));

        final String xRightYRight = multiply(xRight, yRight);
        final String xLeftYLeft = multiply(xLeft, yLeft);

        final String xSum = add(xRight, xLeft);
        final String ySum = add(yRight, yLeft);

        final String multipliedSum = multiply(xSum, ySum);

        final String sumOfMixedLeftRight = sub(multipliedSum, add(xRightYRight, xLeftYLeft));

        String res = add(add((xLeftYLeft + repl("0", 2 * rightDigits)), (sumOfMixedLeftRight + repl("0", rightDigits))),
                xRightYRight);

        return res;
    }

    private static String repl(final String s, final int times) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < times; i++) {
            res.append(s);
        }

        return res.toString();
    }

    private static String add(final String n1, final String n2) {
        int p1 = n1.length() - 1;
        int p2 = n2.length() - 1;

        int carry = 0;
        LinkedList<String> result = new LinkedList<>();
        while (p1 >= 0 || p2 >= 0) {
            int curr1 = p1 >= 0 ? Integer.parseInt(String.valueOf(n1.charAt(p1))) : 0;
            int curr2 = p2 >= 0 ? Integer.parseInt(String.valueOf(n2.charAt(p2))) : 0;
            int digit = curr1 + curr2 + carry;
            carry = digit / 2;
            result.addFirst(String.valueOf(digit % 2));
            p1--;
            p2--;
        }

        if (carry > 0) {
            result.addFirst(String.valueOf(carry));
        }

        StringBuilder resultStr = new StringBuilder();
        for (String part : result) {
            if (resultStr.length() == 0 && "0".equals(part)) {
                continue;
            }

            resultStr.append(part);
        }

        return resultStr.length() > 0 ? resultStr.toString() : "0";
    }

    private static String sub(final String n1, final String n2) {
        int p1 = n1.length() - 1;
        int p2 = n2.length() - 1;
        int debt = 0;
        LinkedList<String> result = new LinkedList<>();
        while (p1 >= 0 || p2 >= 0) {

            int curr1 = p1 >= 0 ? Integer.parseInt(String.valueOf(n1.charAt(p1))) : 0;
            int curr2 = p2 >= 0 ? Integer.parseInt(String.valueOf(n2.charAt(p2))) : 0;

            if (curr1 - debt - curr2 < 0) {
                result.addFirst(String.valueOf(2 + curr1 - debt - curr2));
                debt = 1;
            } else {
                result.addFirst(String.valueOf(curr1 - debt - curr2));
                debt = 0;
            }

            p1--;
            p2--;
        }

        if (debt != 0) {
            throw new IllegalStateException(String.format("can't subtract [%s] from [%s]", n2, n1));
        }

        StringBuilder resultStr = new StringBuilder("");
        for (String part : result) {
            if (resultStr.length() == 0 && "0".equals(part)) {
                continue;
            }

            resultStr.append(part);
        }

        return resultStr.length() > 0 ? resultStr.toString() : "0";
    }
}
