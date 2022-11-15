package closest.pair;

public final class Point implements Comparable<Point> {

    private int x;
    private int y;
/**
 * Generates a point, with the provided x and y coordinates.<p>
 * These coordinates are integers and cannot be modified <p>
 * Inputs: and x and a y integers <p>
 * Outputs: A point object with the specified coordinates
 * @param x  An integer
 * @param y  Another integer
 * */
    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    @Override
    /**
     * This implementation fo comparable prevents the existence of two points with the same x value
     * */
    public int compareTo(Point o) {
        return this.x - o.x;
    }

}
