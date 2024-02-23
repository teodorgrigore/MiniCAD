package interfaces.visitors;

import implementations.shapes.Rectangle;
import implementations.shapes.Canvas;
import implementations.shapes.Circle;
import implementations.shapes.Diamond;
import implementations.shapes.Line;
import implementations.shapes.Polygon;
import implementations.shapes.Square;
import implementations.shapes.Triangle;

public interface Visitor {
    void visit(Canvas canvas);
    void visit(Line line);
    void visit(Square square);
    void visit(Rectangle rectangle);
    void visit(Circle circle);
    void visit(Triangle triangle);
    void visit(Diamond diamond);
    void visit(Polygon polygon);
}
