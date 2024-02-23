package implementations.utils;

import java.awt.Color;

import implementations.shapes.Canvas;

public final class PointUtils {
    private PointUtils() { }
    public static void setPixel(final int x, final int y, final Color color) {
        Canvas.image.setRGB(x, y, color.getRGB());
}
    public static int getPixelRGB(final int x, final int y) {
        return Canvas.getImage().getRGB(x, y);
        }
    public static Boolean isWithinLimits(final int x, final int y) {
        if (x >= 0 && y >= 0 && x < Canvas.getWidth() && y < Canvas.getHeight()) {
            return true;
        }
        return false;
    }
}
