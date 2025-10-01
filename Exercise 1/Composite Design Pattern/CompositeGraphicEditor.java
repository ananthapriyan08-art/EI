// Component Interface
interface Shape {
    void draw();
}

// Leaf Class
class Circle implements Shape {
    public void draw() {
        System.out.println("Drawing Circle.");
    }
}

// Leaf Class
class Rectangle implements Shape {
    public void draw() {
        System.out.println("Drawing Rectangle.");
    }
}

// Composite Class
class ShapeGroup implements Shape {
    private List<Shape> shapes = new ArrayList<>();

    public void addShape(Shape shape) {
        shapes.add(shape);
    }

    public void draw() {
        for (Shape shape : shapes) {
            shape.draw();
        }
    }
}

// Client Code
public class CompositeGraphicEditor {
    public static void main(String[] args) {
        Shape circle1 = new Circle();
        Shape circle2 = new Circle();
        Shape rectangle = new Rectangle();

        ShapeGroup group = new ShapeGroup();
        group.addShape(circle1);
        group.addShape(circle2);
        group.addShape(rectangle);

        // Draw all shapes in the group
        group.draw();
    }
}
