package implementations.shapes;

import java.awt.Color;
import java.io.IOException;

import fileio.implementations.FileReader;
import implementations.utils.ColorUtils;
import implementations.utils.DrawAlgorithms;
import implementations.utils.Point;
import interfaces.shape.Shape;
import interfaces.visitables.Visitable;
import interfaces.visitors.Visitor;

public final class Circle implements Shape, Visitable {
    private int centerX, centerY, radius, alphaContour, alphaFilling;
    private Point center;
    private String rgbContour, rgbFilling;
    private Color contourColor, fillingColor;
    @Override
    public void accept(final Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void draw() {
        contourColor = ColorUtils.getColor(rgbContour, alphaContour);
        DrawAlgorithms.bresenhamCircle(center, radius, contourColor);
        fillingColor = ColorUtils.getColor(rgbFilling, alphaFilling);
        DrawAlgorithms.floodFill(center, contourColor, fillingColor);
    }

    @Override
    public void readData(final FileReader f) {
        try {
            centerX = f.nextInt();
            centerY = f.nextInt();
            center = new Point(centerX, centerY);
            radius = f.nextInt();
            rgbContour = f.nextWord();
            alphaContour = f.nextInt();
            rgbFilling = f.nextWord();
            alphaFilling = f.nextInt();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
