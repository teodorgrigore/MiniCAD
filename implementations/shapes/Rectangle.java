package implementations.shapes;
import java.awt.Color;
import java.io.IOException;

import fileio.implementations.FileReader;
import implementations.utils.ColorUtils;
import implementations.utils.DrawAlgorithms;
import implementations.utils.Point;
import implementations.utils.PointUtils;
import interfaces.shape.Shape;
import interfaces.visitables.Visitable;
import interfaces.visitors.Visitor;

public final class Rectangle implements Shape, Visitable {
    private Point leftUp, leftDown, rightUp, rightDown;
    private int height, width, alphaContour, alphaFilling;
    private String rgbContour, rgbFilling;
    private Color contourColor, fillingColor;
    @Override
    public void accept(final Visitor visitor) {
        visitor.visit(this);

    }
    private void createCorners() {
        leftDown = new Point(leftUp.getX(), leftUp.getY() + height - 1);
        rightUp = new Point(leftUp.getX() + width - 1, leftUp.getY());
        rightDown = new Point(rightUp.getX(), rightUp.getY() + height - 1);
    }
    @Override
    public void draw() {
        contourColor = ColorUtils.getColor(rgbContour, alphaContour);
        createCorners();
        DrawAlgorithms.bresenhamLine(leftUp, leftDown, contourColor);
        DrawAlgorithms.bresenhamLine(leftDown, rightDown, contourColor);
        DrawAlgorithms.bresenhamLine(rightDown, rightUp, contourColor);
        DrawAlgorithms.bresenhamLine(rightUp, leftUp, contourColor);
        fillingColor = ColorUtils.getColor(rgbFilling, alphaFilling);
        for (int i = (leftUp.getY() + 1); i < (leftDown.getY()); i++) {
            for (int j = leftUp.getX() + 1; j < rightUp.getX(); j++) {
                if (PointUtils.isWithinLimits(j, i)) {
                    PointUtils.setPixel(j, i, fillingColor);
                }
            }
        }
    }

    @Override
    public void readData(final FileReader f) {
        try {
            int leftUpX = f.nextInt();
            int leftUpY = f.nextInt();
            leftUp = new Point(leftUpX, leftUpY);
            height = f.nextInt();
            width = f.nextInt();
            rgbContour = f.nextWord();
            alphaContour = f.nextInt();
            rgbFilling = f.nextWord();
            alphaFilling = f.nextInt();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
