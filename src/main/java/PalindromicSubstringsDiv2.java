/**
 * @author  tasyrkin
 * @since   2014/02/03
 */
public class PalindromicSubstringsDiv2 {
    public int count(final String[] S1, final String[] S2) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < S1.length; i++) {
            sb.append(S1[i]);
        }

        for (int i = 0; i < S2.length; i++) {
            sb.append(S2[i]);
        }

        char[] chs = sb.toString().toCharArray();

        long res = 0;
        int latest = -1;
        for (int i = 0; i < chs.length; i++) {
            int max = 1;
            for (int len = 1; len + i < chs.length && i - len >= 0; len++, max += 2) {
                if (chs[len + i] != chs[i - len]) {
                    break;
                }
            }

            int newLast = i + (max - 1) / 2;

            if (latest == -1) {
                latest = newLast;
                res += max + (max - 1) / 2;
            }

            if (latest < newLast) {
                res += max + (max - 1) / 2;
                latest = newLast;
            }

        }

        return (int) res;
    }
}
