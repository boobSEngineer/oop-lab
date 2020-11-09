package com.eltech.lab1;

public class Main {
    private static String addPrefixToMultilineString(String prefix, Object obj) {
        StringBuilder spaceSb = new StringBuilder();
        for (int i = 0; i < prefix.length(); i++) {
            spaceSb.append(' ');
        }
        String space = spaceSb.toString();

        String[] lines = obj.toString().split("\n");
        for (int i = 0; i < lines.length; i++) {
            lines[i] = (i == 0 ? prefix : space) + lines[i];
        }
        return String.join("\n", lines);
    }

    private static void demoBinaryOperation(String title, String operation, Object A, Object B, Object result) {
        System.out.println("\n\n" + title);
        System.out.println(addPrefixToMultilineString("A = ", A) + "\n" + addPrefixToMultilineString("B = ", B));
        System.out.println(addPrefixToMultilineString("A " + operation + " B = ", result));
    }

    public static void main(String[] args) {
        Matrix A, B;


        A = new Matrix(new double[] {
                7, 2, 3, 4,
                2, 7, 4, 5,
                3, 4, 7, 6,
                4, 5, 6, 7
        }, 4, 4);
        System.out.println(addPrefixToMultilineString("A = ", A));
        System.out.println("A(1, 2) = " + A.get(1, 2));
        System.out.println("det(A) = " + A.det());

        A = new Matrix(new double[] {
                1, 2, 3,
                4, 5, 6,
                7, 8, 9
        }, 3, 3);
        B = new Matrix(new double[] {
                -9, -8, -7,
                -6, -5, -4,
                -3, -2, -1
        }, 3, 3);
        demoBinaryOperation("addition", "+", A, B, A.add(B));

        A = new Matrix(new double[] {
                1, 5,
                2, 3,
                1, 7
        }, 2, 3);
        B = new Matrix(new double[] {
                1, 2, 3, 7,
                5, 2, 8, 1
        }, 4, 2);
        demoBinaryOperation("multiplication", "*", A, B, A.multiply(B));
        demoBinaryOperation("multiplication", "*", A, 2, A.multiply(2));
    }
}
