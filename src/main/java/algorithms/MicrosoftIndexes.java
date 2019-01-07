package algorithms;

public class MicrosoftIndexes {

    private static final int LETTERS = 26;

    public static void main(String[] args) {
        final MicrosoftIndexes msidx = new MicrosoftIndexes();
        System.out.println(String.format("1 -> [%s]", msidx.getMsIdx(1)));
        System.out.println(String.format("28 -> [%s]", msidx.getMsIdx(28)));
        System.out.println(String.format("29 -> [%s]", msidx.getMsIdx(29)));
        System.out.println(String.format("702 -> [%s]", msidx.getMsIdx(702)));
        System.out.println(String.format("18280 -> [%s]", msidx.getMsIdx(18280)));
    }

    String getMsIdx(int num) {
        if (num <= 0) throw new IllegalArgumentException("Invalid num, expected positive");

        --num;
        int currNum = num;
        int digits = 0;
        int mult = LETTERS;
        while (currNum >= 0) {
            currNum -= mult;
            ++digits;
            mult *= LETTERS;
        }
        mult /= LETTERS;
        currNum += mult;

        char[] letters = new char[LETTERS];
        int letterIdx = 0;
        for (char ch = 'A'; ch <= 'Z'; ch++, letterIdx++) {
            letters[letterIdx] = ch;
            //System.out.println(ch);
        }
        StringBuilder result = new StringBuilder();
        mult /= LETTERS;
        while (digits-- > 0) {
            int idx = currNum / mult;
            currNum -= mult * idx;
            mult /= LETTERS;
            //System.out.println(String.format("digit = [%s], currNum = [%s], idx = [%s]", digits, currNum, idx));
            result.append(letters[idx]);
        }
        return result.toString();
    }
}
