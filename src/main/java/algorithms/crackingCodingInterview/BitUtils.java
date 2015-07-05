package algorithms.crackingCodingInterview;

import com.google.common.base.Preconditions;

public class BitUtils {
    public static int resetBits(final int from, final int to, final int startPos, final int endPos) {
        Preconditions.checkArgument(startPos >= 0 && startPos < 32, "Start position is expected to be in [0,32) range");
        Preconditions.checkArgument(endPos >= 0 && endPos < 32, "End position is expected to be in[0, 32) range");
        Preconditions.checkArgument(startPos <= endPos, "Start pos must be less than or equal to end pos");

        int result = to;
        for (int i = startPos; i <= endPos; i++) {
            int bit = (from >> (i - startPos)) & 0x1;
            if (bit == 0) {
                result &= ~(1 << i);
            } else {
                result |= (1 << i);
            }
        }

        return result;
    }

    public static void main(final String[] args) {

        int to = Integer.valueOf("10000000000", 2);
        int from = Integer.valueOf("10101", 2);

        System.out.println(Integer.toBinaryString(BitUtils.resetBits(from, to, 2, 6)));
    }
}
