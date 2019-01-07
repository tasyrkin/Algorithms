package algorithms;

import java.util.ArrayList;
import java.util.Objects;

public class ZigZagConversion {
    public static void main(String[] args) {
        System.out.println(new ZigZagConversion().convert("A", 2));
    }

    public String convert(String s, int numRows) {

        if(s == null || s.length() == 0 || numRows <= 0) return s;

        ArrayList[] sTable = new ArrayList[numRows];
        int col = 0;
        int row = 0;
        boolean down = true;
        char[] chs = s.toCharArray();
        for (int idx = 0; idx < chs.length; idx++) {
            if (sTable[row] == null) sTable[row] = new ArrayList<>();
            sTable[row].add(new CharPos(row, col, chs[idx]));
            System.out.println("row = " + row + ", col = " + col + ", ch = " + chs[idx]);


            if (down) {
                row++;
                if (row == numRows) {
                    down = !down;
                    row = numRows - 2;
                    if (row < 0) {
                        row = 0;
                        down = true;
                    }
                    col++;
                }
            } else {
                row--;
                if (row < 0) {
                    col++;
                    down = !down;
                    row = 1;
                    if (row >= numRows) {
                        row = numRows - 1;
                        down = !down;
                    }
                } else {
                    col++;
                }
            }
        }

        StringBuilder result = new StringBuilder();
        for (int idx = 0; idx < sTable.length; idx++) {
            if(sTable[idx] != null) {
                for (int colidx = 0; colidx < sTable[idx].size(); colidx++) {
                    CharPos curr = (CharPos) sTable[idx].get(colidx);
                    result.append(curr.ch);
                }
            }
            //System.out.println("idx = " + idx + ", str = " + result.toString());
        }
        return result.toString();
    }

    static class CharPos {
        final int row;
        final int col;
        final char ch;

        CharPos(int row, int col, char ch) {
            this.row = row;
            this.col = col;
            this.ch = ch;
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            final CharPos charPos = (CharPos) o;
            return row == charPos.row &&
                    col == charPos.col &&
                    ch == charPos.ch;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, col, ch);
        }
    }
}