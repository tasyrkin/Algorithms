package algorithms;

import com.google.common.base.MoreObjects;

import java.util.HashMap;
import java.util.Map;

public class LongestConsequtive {

    public static void main(String[] args) {
        final Range range = new LongestConsequtive().getLongest(new int[]{7, 6, 5, 0, 8});
        System.out.println(range);
    }

    Range getLongest(int[] array) {
        if (array == null || array.length == 0) return null;

        Map<Integer, Range> map = buildMap(array);

        return getLongest(map);
    }

    private Map<Integer, Range> buildMap(final int[] array) {
        Map<Integer, Range> result = new HashMap<>();
        for (final int element : array) {
            Range currentRange = result.getOrDefault(element, new Range(element, element));
            final Range nextRange = result.get(element + 1);
            if (nextRange != null) {
                currentRange = currentRange.merge(nextRange);

            }
            final Range prevRange = result.get(element - 1);
            if (prevRange != null) {
                currentRange = currentRange.merge(prevRange);
            }
            result.put(element, currentRange);
            if (nextRange != null) {
                result.put(element + 1, currentRange);
            }
            if (prevRange != null) {
                result.put(element - 1, currentRange);
            }
        }
        return result;
    }

    private Range getLongest(final Map<Integer, Range> map) {
        Range max = null;
        for (Map.Entry<Integer, Range> entry : map.entrySet()) {
            if (max == null) {
                max = entry.getValue();
            } else if (max.getLength() < entry.getValue().getLength()) {
                max = entry.getValue();
            }
        }
        return max;
    }

    private static class Range {
        int start, finish;

        public Range(final int start, final int finish) {
            this.start = start;
            this.finish = finish;
        }

        Range merge(Range range) {
            return new Range(Math.min(this.start, range.start), Math.max(this.finish, range.finish));
        }

        boolean overlaps(Range range) {
            return this.start - 1 <= range.finish || range.start - 1 >= this.finish;
        }

        int getLength() {
            return finish - start + 1;
        }

        @Override
        public String toString() {
            return MoreObjects.toStringHelper(this)
                    .add("start", start)
                    .add("finish", finish)
                    .toString();
        }
    }
}
