package algorithms;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

class Skyline {

    public static void main(String[] args) {

        int[][] input = new int[][]{{2, 4, 70}, {3, 8, 30}, {6, 100, 41}, {7, 15, 70}, {10, 30, 102}, {15, 25, 76}, {60, 80, 91}, {70, 90, 72}, {85, 120, 59}};

        final List<int[]> skyline = new Skyline().getSkyline(input);

        for (int[] point : skyline) {
            System.out.print(Arrays.toString(point) + " ");
        }
    }

    public List<int[]> getSkyline(int[][] bs) {
        if (bs == null || bs.length == 0) return new LinkedList<>();

        Point[] pts = new Point[bs.length * 2];
        for (int i = 0; i < 2 * bs.length; i += 2) {
            int bsIdx = i / 2;
            pts[i] = new Point(bs[bsIdx][0], true, bsIdx);
            pts[i + 1] = new Point(bs[bsIdx][1], false, bsIdx);
        }

        Arrays.sort(pts, new PointsCmp());

        List<int[]> preRes = new LinkedList<>();
        for (int i = 0; i < pts.length; i++) {
            List<Integer> idxEnds = new LinkedList<>();
            List<Integer> idxStartOrIntercect = new LinkedList<>();
            int origIdx = pts[i].idx;
            // int currIdx = i;
            // while(currIdx >= 0 && touches(bs, pts[currIdx].idx, pts[i].x)) {
            //     if(bs[pts[currIdx].idx][1] == pts[i].x) {
            //         idxEnds.add(pts[currIdx].idx);
            //     } else {
            //         idxStartOrIntercect.add(pts[currIdx].idx);
            //     }
            //     --currIdx;
            // }
            // currIdx = i+1;
            // while(currIdx < pts.length && touches(bs, pts[currIdx].idx, pts[i].x)) {
            //     if(bs[pts[currIdx].idx][1] == pts[i].x) {
            //         idxEnds.add(currIdx);
            //     } else {
            //         idxStartOrIntercect.add(pts[currIdx].idx);
            //     }
            //     ++currIdx;
            // }
            for (int currIdx = 0; currIdx < bs.length; currIdx++) {
                if (touches(bs, currIdx, pts[i].x)) {
                    if (bs[currIdx][1] == pts[i].x) {
                        idxEnds.add(currIdx);
                    } else {
                        idxStartOrIntercect.add(currIdx);
                    }
                }
            }
            int height = 0;
            for (int stIdx : idxStartOrIntercect) {
                if (height < bs[stIdx][2]) {
                    height = bs[stIdx][2];
                }
            }
            preRes.add(new int[]{pts[i].x, height});
        }
        List<int[]> result = new LinkedList<>();
        int[] prev = null;
        for (int[] curr : preRes) {
            if (prev == null) {
                prev = curr;
            } else {
                if (curr[1] != prev[1]) {
                    result.add(prev);
                    prev = curr;
                }
            }
        }
        if (prev != null) {
            result.add(prev);
        }
        return result;
    }

    boolean touches(int[][] bs, int idx, int x) {
        return bs[idx][0] <= x && x <= bs[idx][1];
    }

    static class Point {
        int x;
        boolean isStart;
        int idx;

        Point(int x, boolean isStart, int idx) {
            this.x = x;
            this.isStart = isStart;
            this.idx = idx;
        }
    }

    static class PointsCmp implements Comparator<Point> {
        public int compare(Point p1, Point p2) {
            return Integer.compare(p1.x, p2.x);
        }
    }
}