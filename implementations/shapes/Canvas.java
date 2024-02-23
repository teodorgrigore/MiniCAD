package implementations.shapes;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import fileio.implementations.FileReader;
import implementations.utils.ColorUtils;
import interfaces.shape.Shape;
import interfaces.visitables.Visitable;
import interfaces.visitors.Visitor;;

public final class Canvas implements Shape, Visitable {
    private int alpha;
    private static int height, width;
    private String rgb;
    private Color canvasColor;
    public static BufferedImage image;
    private static Canvas instance = new Canvas();
    private Canvas() { }
    public static int getWidth() {
        return width;
    }
    public static int getHeight() {
        return height;
    }
    public static BufferedImage getImage() {
        return image;
    }
    public static Canvas getInstance() {
        return instance;
    }
    @Override
    public void draw() {
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        canvasColor = ColorUtils.getColor(rgb, alpha);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                image.setRGB(i, j, canvasColor.getRGB());
            }
        }
    }

    @Override
    public void readData(final FileReader f) {
        try {
            height = f.nextInt();
            width = f.nextInt();
            rgb = f.nextWord();
            alpha = f.nextInt();
        } catch (IOException e) {
        e.printStackTrace();
        }
    }
    public void accept(final Visitor visitor) {
        visitor.visit(this);
    }

}
