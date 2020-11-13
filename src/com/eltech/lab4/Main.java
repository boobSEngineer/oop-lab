package com.eltech.lab4;

import com.eltech.lab2.Circle;
import com.eltech.lab2.Rectangle;
import com.eltech.lab2.Shape;
import com.eltech.lab2.Triangle;

import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ShapeAccumulator shapes = new ShapeAccumulator();
        shapes.add(new Triangle(4, 8, 6));
        shapes.add(new Triangle(2, 2, 3));
        shapes.add(new Rectangle(4, 4));
        shapes.add(new Circle(1));
        shapes.add(new Circle(2));

        System.out.println("area sum: " + shapes.getTotalArea() + ", perimeter sum: " + shapes.getTotalPerimeter());

        Shape minAreaShape = shapes.getMinAreaShape();
        Shape maxAreaShape = shapes.getMaxAreaShape();
        Shape minPerimeterShape = shapes.getMinPerimeterShape();
        Shape maxPerimeterShape = shapes.getMaxPerimeterShape();

        System.out.println("min area shape: " + minAreaShape + " with area " + minAreaShape.calcArea());
        System.out.println("max area shape: " + maxAreaShape + " with area " + maxAreaShape.calcArea());
        System.out.println("min perimeter shape: " + minPerimeterShape + " with perimeter " + minPerimeterShape.calcPerimeter());
        System.out.println("max perimeter shape: " + maxPerimeterShape + " with perimeter " + maxPerimeterShape.calcPerimeter());

    }
}
