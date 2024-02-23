import fileio.implementations.FileReader;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import implementations.factories.ShapeFactory;
import implementations.shapes.Canvas;
import implementations.visitors.DrawShapeVisitor;
import interfaces.shape.Shape;
import interfaces.visitables.Visitable;
import interfaces.visitors.Visitor;
public final class Main {
    private Main() { }
    public static void main(final String[] args) throws IOException {
        int numberOfShapes;
        String shape;
        try {
            FileReader fileReader = new FileReader(args[0]);
            numberOfShapes = fileReader.nextInt();
            ShapeFactory shapeFactory = ShapeFactory.getInstance();
            Visitor drawVisitor = DrawShapeVisitor.getInstance();
            for (int i = 0; i < numberOfShapes; i++) {
                shape = fileReader.nextWord();
                Shape sh = shapeFactory.getShape(shape);
                sh.readData(fileReader);
                Visitable visitableShape = (Visitable) sh;
                visitableShape.accept(drawVisitor);
            }
            fileReader.close();
        } catch (IOException e) {
        e.printStackTrace();
        }
        File newPNGFile = new File("drawing.png");
        ImageIO.write(Canvas.getImage(), "png", newPNGFile);
    }
}
