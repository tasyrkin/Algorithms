package algorithms;

import java.util.BitSet;

public class BitSetUsage {
    public static void main(final String[] args) {
        BitSet bitSet = new BitSet(1000);
        bitSet.set(0);
        bitSet.set(1000000);

        System.out.println(bitSet + "," + bitSet.length());
    }
}
