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

public final class Diamond implements Shape, Visitable {
    private int centerX, centerY, horizontalDiag, verticalDiag;
    private int alphaContour, alphaFilling;
    private String rgbContour, rgbFilling;
    private Point center, up, down, left, right;
    private Color contourColor, fillingColor;
    @Override
    public void accept(final Visitor visitor) {
        visitor.visit(this);

    }
    @Override
    public void draw() {
        contourColor = ColorUtils.getColor(rgbContour, alphaContour);
        createCorners();
        DrawAlgorithms.bresenhamLine(left, down, contourColor);
        DrawAlgorithms.bresenhamLine(down, right, contourColor);
        DrawAlgorithms.bresenhamLine(right, up, contourColor);
        DrawAlgorithms.bresenhamLine(up, left, contourColor);
        fillingColor = ColorUtils.getColor(rgbFilling, alphaFilling);
        DrawAlgorithms.floodFill(center, contourColor, fillingColor);

    }

    @Override
    public void readData(final FileReader f) {
        try {
            centerX = f.nextInt();
            centerY = f.nextInt();
            center = new Point(centerX, centerY);
            horizontalDiag = f.nextInt();
            verticalDiag = f.nextInt();
            roundDiag();
            rgbContour = f.nextWord();
            alphaContour = f.nextInt();
            rgbFilling = f.nextWord();
            alphaFilling = f.nextInt();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void roundDiag() {
        if (horizontalDiag % 2 == 1) {
            horizontalDiag--;
        }
        if (verticalDiag % 2 == 1) {
            verticalDiag--;
        }
    }
    private void createCorners() {
        up = new Point(center.getX(), center.getY() - (verticalDiag / 2));
        down = new Point(center.getX(), center.getY() + (verticalDiag / 2));
        left = new Point(center.getX() - (horizontalDiag / 2), center.getY());
        right = new Point(center.getX() + (horizontalDiag / 2), center.getY());
    }

}
