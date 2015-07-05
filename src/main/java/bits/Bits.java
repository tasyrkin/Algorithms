package bits;

public class Bits {

    /**
     * @param  args
     */
    public static void main(final String[] args) {
        int x = 1;
        x = ~x;
        System.out.println(Integer.toBinaryString(1 << 31));

        System.out.println(Integer.toBinaryString(~(1 << 2)));
    }

}
