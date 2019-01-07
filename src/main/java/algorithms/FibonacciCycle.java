package algorithms;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class FibonacciCycle {
    public static int cycleIndex(FibIterator fibIterator) {
        if(fibIterator == null) {
            throw new IllegalArgumentException("Missing fibIterator");
        }

        final Set<PairHolder> knownPairs = new HashSet<>();

        PairHolder currPair = PairHolder.of(fibIterator.next(), fibIterator.next());

        int index = 2;

        do {
            knownPairs.add(currPair);

            int nextNumber = fibIterator.next();

            currPair = PairHolder.of(currPair.second, nextNumber);
            index++;

        } while (!knownPairs.contains(currPair));

        return index;
    }

    private static class FibIterator implements Iterator<Integer> {

        int first;
        int second;

        private FibIterator(final int first, final int second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public boolean hasNext() {
            return true;
        }

        @Override
        public Integer next() {

            int next = (first + second) % 10;

            first = second;
            second = next;

            return second;
        }
    }

    public static void main(String[] args) {
        System.out.println(cycleIndex(new FibIterator(1, 1)));
    }

    private static class PairHolder {
        final int first;
        final int second;

        public PairHolder(final int first, final int second) {
            this.first = first;
            this.second = second;
        }

        public static PairHolder of(final int first, final int second) {
            return new PairHolder(first, second);
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            final PairHolder that = (PairHolder) o;

            if (first != that.first) return false;
            return second == that.second;

        }

        @Override
        public int hashCode() {
            int result = first;
            result = 31 * result + second;
            return result;
        }
    }
}
