package implementations.utils;

import java.awt.Color;
import java.util.LinkedList;
import java.util.Queue;

public final class DrawAlgorithms {
    private DrawAlgorithms() { }
    public static void bresenhamLine(final Point start, final Point end, final Color color) {
        int x = start.getX();
        int y = start.getY();
        int deltaX = Math.abs(end.getX() - start.getX());
        int deltaY = Math.abs(end.getY() - start.getY());
        int s1 = Integer.signum(end.getX() - start.getX());
        int s2 = Integer.signum(end.getY() - start.getY());
        Boolean interchanged;
        if (deltaY > deltaX) { //swap folosind XOR pe biti
            deltaX = deltaX ^ deltaY;
            deltaY = deltaX ^ deltaY;
            deltaX = deltaX ^ deltaY;
            interchanged = true;
        } else {
            interchanged = false;
        }
        int error = 2 * deltaY - deltaX;
        for (int i = 0; i <= deltaX; i++) {
            if (PointUtils.isWithinLimits(x, y)) {
                PointUtils.setPixel(x, y, color);
            }
            while (error > 0) {
                if (interchanged) {
                    x += s1;
                } else {
                    y += s2;
                }
                error -= 2 * deltaX;
            }
            if (interchanged) {
                y += s2;
            } else {
                x += s1;
            }
            error += 2 * deltaY;
        }
    }
    public static void bresenhamCircle(final Point center, final int radius, final Color color) {
        int x = 0, y = radius;
        int d = 3 - 2 * radius;
        while (y >= x) {
            drawCircle(center.getX(), center.getY(), x, y, color);
            x++;
            if (d > 0) {
                y--;
                d = d + 4 * (x - y) + 10;
            } else {
                d = d + 4 * x + 6;
            }
            drawCircle(center.getX(), center.getY(), x, y, color);
        }
        //http://www.geeksforgeeks.org/bresenhams-circle-drawing-algorithm/
    }
    private static void drawCircle(final int x, final int y, final int p, final int q,
            final Color color) {
        if (PointUtils.isWithinLimits(x + p, y + q)) {
            PointUtils.setPixel(x + p, y + q, color);
        }
        if (PointUtils.isWithinLimits(x - p, y + q)) {
            PointUtils.setPixel(x - p, y + q, color);
        }
        if (PointUtils.isWithinLimits(x + p, y - q)) {
            PointUtils.setPixel(x + p, y - q, color);
        }
        if (PointUtils.isWithinLimits(x - p, y - q)) {
            PointUtils.setPixel(x - p, y - q, color);
        }
        if (PointUtils.isWithinLimits(x + q, y + p)) {
            PointUtils.setPixel(x + q, y + p, color);
        }
        if (PointUtils.isWithinLimits(x - q, y + p)) {
            PointUtils.setPixel(x - q, y + p, color);
        }
        if (PointUtils.isWithinLimits(x + q, y - p)) {
            PointUtils.setPixel(x + q, y - p, color);
        }
        if (PointUtils.isWithinLimits(x - q, y - p)) {
            PointUtils.setPixel(x - q, y - p, color);
        }
    }
    public static void floodFill(final Point startNode, final Color stopColor,
            final Color replacementColor) {
        int x = startNode.getX(), y = startNode.getY();
        Queue<Point> queue = new LinkedList<Point>();
        if (PointUtils.isWithinLimits(x, y)) {
            PointUtils.setPixel(x, y, replacementColor);
            queue.add(startNode);
        }
        while (!queue.isEmpty()) {
            Point n = queue.remove();
            if (PointUtils.isWithinLimits(n.getX() - 1, n.getY())
                    && PointUtils.getPixelRGB(n.getX() - 1, n.getY()) != stopColor.getRGB()
                    && PointUtils.getPixelRGB(n.getX() - 1, n.getY()) != replacementColor.getRGB()) {
                queue.add(new Point(n.getX() - 1, n.getY()));
                PointUtils.setPixel(n.getX() - 1, n.getY(), replacementColor);
            }
            if (PointUtils.isWithinLimits(n.getX() + 1, n.getY())
                    && PointUtils.getPixelRGB(n.getX() + 1, n.getY()) != stopColor.getRGB()
                    && PointUtils.getPixelRGB(n.getX() + 1, n.getY()) != replacementColor.getRGB()) {
                queue.add(new Point(n.getX() + 1, n.getY()));
                PointUtils.setPixel(n.getX() + 1, n.getY(), replacementColor);
            }
            if (PointUtils.isWithinLimits(n.getX(), n.getY() - 1)
                    && PointUtils.getPixelRGB(n.getX(), n.getY() - 1) != stopColor.getRGB()
                    && PointUtils.getPixelRGB(n.getX(), n.getY() - 1) != replacementColor.getRGB()) {
                queue.add(new Point(n.getX(), n.getY() - 1));
                PointUtils.setPixel(n.getX(), n.getY() - 1, replacementColor);
            }
            if (PointUtils.isWithinLimits(n.getX(), n.getY() + 1)
                    && PointUtils.getPixelRGB(n.getX(), n.getY() + 1) != stopColor.getRGB()
                    && PointUtils.getPixelRGB(n.getX(), n.getY() + 1) != replacementColor.getRGB()) {
                queue.add(new Point(n.getX(), n.getY() + 1));
                PointUtils.setPixel(n.getX(), n.getY() + 1, replacementColor);
            }
        }
    }
}

