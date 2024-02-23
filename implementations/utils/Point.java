package implementations.utils;

public final class Point {
    private int x, y;
    public Point(final int coordX, final int coordY) {
        x = coordX;
        y = coordY;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public String toString() {
        return this.getX() + " " + this.getY();
    }
}
