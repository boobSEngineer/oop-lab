package com.eltech.lab5;

import com.eltech.lab2.Circle;
import com.eltech.lab2.Rectangle;
import com.eltech.lab2.Shape;
import com.eltech.lab2.Triangle;
import com.eltech.lab4.ShapeAccumulator;

import java.io.File;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ShapeAccumulator shapes = new ShapeAccumulator();
        ShapeSaver saver = new ShapeSaver(shapes, new File("shapes.bin"));
        saver.read();

        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("input command (exit, random, clear or output)");
            String command = input.nextLine();
            switch (command.toLowerCase().trim()) {
                case "exit":
                    return;
                case "random":
                    Random random = new Random();
                    switch (random.nextInt(3)) {
                        case 0:
                            int side1 = random.nextInt(4) + 1;
                            int side2 = random.nextInt(4) + 1;
                            shapes.add(new Triangle(side1, side2, side1 + side2 - 1));
                            break;
                        case 1:
                            shapes.add(new Circle(random.nextInt(4) + 1));
                            break;
                        case 2:
                            shapes.add(new Rectangle(random.nextInt(4) + 1, random.nextInt(4) + 1));
                            break;
                    }
                    break;
                case "output":
                    System.out.println("shapes:");
                    for (Shape shape : shapes.getShapes()) {
                        System.out.println(shape);
                    }
                    System.out.println("\n");
                    break;
                case "clear":
                    shapes.clear();
                    break;
                default:
                    System.out.println("unknown command\n");
            }
        }
    }
}
