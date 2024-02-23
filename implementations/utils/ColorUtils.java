package implementations.utils;
import java.awt.Color;
public final class ColorUtils {
    private ColorUtils() { }
    private static String hexColor;
    private static Integer red, green, blue;
    public static Color getColor(final String rgb, final int alpha) {
        final int redStartIndex = 1, redEndIndex = 3;
        final int greenStartIndex = 3, greenEndIndex = 5;
        final int blueStartIndex = 5, blueEndIndex = 7;
        hexColor = rgb.substring(redStartIndex, redEndIndex);
        //System.out.println(hexColor);
        red = convertToDec(hexColor);
        hexColor = rgb.substring(greenStartIndex, greenEndIndex);
        //System.out.println(hexColor);
        green = convertToDec(hexColor);
        hexColor = rgb.substring(blueStartIndex, blueEndIndex);
        //System.out.println(hexColor);
        blue = convertToDec(hexColor);
        //System.out.println(red + " " + green + " " + blue + " " + alpha);
        return new Color(red, green, blue, alpha);
    }
    private static Integer convertToDec(final String hex) {
        final int hexIndex = 16;
        Integer dec = Integer.parseInt(hex, hexIndex);
        return dec;
    }
}
