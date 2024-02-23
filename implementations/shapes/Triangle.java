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

public final class Triangle implements Shape, Visitable {
    private int readCoordX, readCoordY, alphaContour, alphaFilling;
    private Point p1, p2, p3, mass;
    private String rgbContour, rgbFilling;
    private Color contourColor, fillingColor;
    private final int numberOfSides = 3;
    @Override
    public void accept(final Visitor visitor) {
        visitor.visit(this);

    }
    private Point calculateCenterOfMass() {
        int x = (p1.getX() + p2.getX() + p3.getX()) / numberOfSides;
        int y = (p1.getY() + p2.getY() + p3.getY()) / numberOfSides;
        return new Point(x, y);
    }

    @Override
    public void draw() {
        contourColor = ColorUtils.getColor(rgbContour, alphaContour);
        DrawAlgorithms.bresenhamLine(p1, p2, contourColor);
        DrawAlgorithms.bresenhamLine(p2, p3, contourColor);
        DrawAlgorithms.bresenhamLine(p3, p1, contourColor);
        fillingColor = ColorUtils.getColor(rgbFilling, alphaFilling);
        mass = calculateCenterOfMass();
        DrawAlgorithms.floodFill(mass, contourColor, fillingColor);
    }

    @Override
    public void readData(final FileReader f) {
        try {
            readCoordX = f.nextInt();
            readCoordY = f.nextInt();
            p1 = new Point(readCoordX, readCoordY);
            readCoordX = f.nextInt();
            readCoordY = f.nextInt();
            p2 = new Point(readCoordX, readCoordY);
            readCoordX = f.nextInt();
            readCoordY = f.nextInt();
            p3 = new Point(readCoordX, readCoordY);
            rgbContour = f.nextWord();
            alphaContour = f.nextInt();
            rgbFilling = f.nextWord();
            alphaFilling = f.nextInt();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
