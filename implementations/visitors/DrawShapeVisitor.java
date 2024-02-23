package implementations.visitors;
import implementations.shapes.Canvas;
import implementations.shapes.Circle;
import implementations.shapes.Diamond;
import implementations.shapes.Line;
import implementations.shapes.Polygon;
import implementations.shapes.Rectangle;
import implementations.shapes.Square;
import implementations.shapes.Triangle;
import interfaces.visitors.Visitor;

public final class DrawShapeVisitor implements Visitor {
    private static DrawShapeVisitor instance = new DrawShapeVisitor();
    private DrawShapeVisitor() { }
    public static DrawShapeVisitor getInstance() {
        return instance;
    }

    @Override
    public void visit(final Canvas canvas) {
        canvas.draw();

    }

    @Override
    public void visit(final Line line) {
        line.draw();

    }

    @Override
    public void visit(final Square square) {
        square.draw();

    }

    @Override
    public void visit(final Rectangle rectangle) {
        rectangle.draw();

    }

    @Override
    public void visit(final Circle circle) {
        circle.draw();
    }

    @Override
    public void visit(final Triangle triangle) {
        triangle.draw();

    }

    @Override
    public void visit(final Diamond diamond) {
        diamond.draw();

    }

    @Override
    public void visit(final Polygon polygon) {
        polygon.draw();

    }

}
