/**
 * @author  tasyrkin
 * @since   2014/02/03
 */
public class BoundingBox {
    public int smallestArea(final int[] X, final int[] Y) {

        int x1 = -100, y1 = -100;
        int x2 = 100, y2 = 100;

        int minx1 = 1000;
        int minx2 = 1000;
        int miny1 = 1000;
        int miny2 = 1000;
        for (int i = 0; i < X.length; i++) {
            int x1diff = Math.abs(X[i] - x1);
            if (x1diff < minx1) {
                minx1 = x1diff;
            }

            int x2diff = Math.abs(x2 - X[i]);
            if (x2diff < minx2) {
                minx2 = x2diff;
            }

            int y1diff = Math.abs(Y[i] - y1);
            if (y1diff < miny1) {
                miny1 = y1diff;
            }

            int y2diff = Math.abs(Y[i] - y2);
            if (y2diff < miny2) {
                miny2 = y2diff;
            }
        }

        x1 += minx1;
        y1 += miny1;
        x2 -= minx2;
        y2 -= miny2;

        return (x2 - x1) * (y2 - y1);
    }
}
