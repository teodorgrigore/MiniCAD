package implementations.shapes;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;

import fileio.implementations.FileReader;
import implementations.utils.ColorUtils;
import implementations.utils.DrawAlgorithms;
import implementations.utils.Point;
import interfaces.shape.Shape;
import interfaces.visitables.Visitable;
import interfaces.visitors.Visitor;

public final class Polygon implements Shape, Visitable {
    private int numberOfPoints, readCoordX, readCoordY, alphaContour, alphaFilling;
    private ArrayList<Point> points = new ArrayList<Point>();
    private Point aux, mass;
    private String rgbContour, rgbFilling;
    private Color contourColor, fillingColor;
    @Override
    public void accept(final Visitor visitor) {
        visitor.visit(this);
        }
    private Point calculateCenterOfMass() {
        int x = 0, y = 0;
        for (int i = 0; i < points.size(); i++) {
            x += points.get(i).getX();
            y += points.get(i).getY();
        }
        x /= points.size();
        y /= points.size();
        return new Point(x, y);
    }

    @Override
    public void draw() {
        contourColor = ColorUtils.getColor(rgbContour, alphaContour);
        for (int i = 0; i < points.size() - 1; i++) {
            DrawAlgorithms.bresenhamLine(points.get(i), points.get(i + 1), contourColor);
        }
        DrawAlgorithms.bresenhamLine(points.get(points.size() - 1), points.get(0), contourColor);
        fillingColor = ColorUtils.getColor(rgbFilling, alphaFilling);
        mass = calculateCenterOfMass();
        DrawAlgorithms.floodFill(mass, contourColor, fillingColor);
    }

    @Override
    public void readData(final FileReader f) {
        try {
            numberOfPoints = f.nextInt();
            for (int i = 0; i < numberOfPoints; i++) {
                readCoordX = f.nextInt();
                readCoordY = f.nextInt();
                aux = new Point(readCoordX, readCoordY);
                points.add(aux);
            }
            rgbContour = f.nextWord();
            alphaContour = f.nextInt();
            rgbFilling = f.nextWord();
            alphaFilling = f.nextInt();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
