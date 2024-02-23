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

public final class Line implements Visitable, Shape {
    private Point start, end;
    private int alpha;
    private String rgb;
    private Color lineColor;
    @Override
    public void draw() {
        lineColor = ColorUtils.getColor(rgb, alpha);
        DrawAlgorithms.bresenhamLine(start, end, lineColor);
    }

    @Override
    public void readData(final FileReader f) {
        try {
            int startX = f.nextInt();
            int startY = f.nextInt();
            start = new Point(startX, startY);
            int endX = f.nextInt();
            int endY = f.nextInt();
            end = new Point(endX, endY);
            rgb = f.nextWord();
            alpha = f.nextInt();
        } catch (IOException e) {
        e.printStackTrace();
        }
    }

    @Override
    public void accept(final Visitor visitor) {
        visitor.visit(this);
    }

}
