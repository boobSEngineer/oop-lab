package com.eltech.lab2;

public class Rectangle implements Shape {
    private final double width, height;

    public Rectangle(double side1, double side2) {
        if (side1 <= 0 || side2 <= 0) {
            throw new IllegalArgumentException(String.format("invalid rectangle sides: %f, %f", side1, side2));
        }
        this.width = side1;
        this.height = side2;
    }

    @Override
    public String toString() {
        return "Rectangle{" + width + ", " + height + '}';
    }

    @Override
    public double calcArea() {
        return width * height;
    }

    @Override
    public double calcPerimeter() {
        return (width + height) * 2;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }
}
