package interfaces.shape;
import fileio.implementations.FileReader;

public interface Shape {
    void draw();
    void readData(FileReader f);
}
