package closest.pair;

public final class Point implements Comparable<Point> {

    private int x;
    private int y;

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
    public int compareTo(Point o) {
        return this.x - o.x;
    }

}
