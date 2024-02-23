package implementations.factories;
import implementations.shapes.Canvas;
import implementations.shapes.Circle;
import implementations.shapes.Diamond;
import implementations.shapes.Line;
import implementations.shapes.Polygon;
import implementations.shapes.Rectangle;
import implementations.shapes.Square;
import implementations.shapes.Triangle;
import interfaces.shape.Shape;
public final class ShapeFactory {
    private static ShapeFactory instance = new ShapeFactory();
    private ShapeFactory() { }
    public static ShapeFactory getInstance() {
        return instance;
    }
    public Shape getShape(final String shapeType) {
        if (shapeType == null) {
             return null;
        }
        if (shapeType.equalsIgnoreCase("CANVAS")) {
           return Canvas.getInstance();
        } else if (shapeType.equalsIgnoreCase("LINE")) {
           return new Line();
        } else if (shapeType.equalsIgnoreCase("SQUARE")) {
             return new Square();
        } else if (shapeType.equalsIgnoreCase("RECTANGLE")) {
             return new Rectangle();
        } else if (shapeType.equalsIgnoreCase("CIRCLE")) {
           return new Circle();
        } else if (shapeType.equalsIgnoreCase("TRIANGLE")) {
           return new Triangle();
        } else if (shapeType.equalsIgnoreCase("DIAMOND")) {
           return new Diamond();
        } else if (shapeType.equalsIgnoreCase("POLYGON")) {
           return new Polygon();
        }
        return null;
    }
}
