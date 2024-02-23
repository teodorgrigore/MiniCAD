package interfaces.visitables;
import interfaces.visitors.Visitor;
public interface Visitable {
    void accept(Visitor visitor);
}
