package com.eltech.lab1;

import java.util.Arrays;
import java.util.Objects;

public class Matrix {
    private final double[] data;
    private final int width, height;

    public Matrix(double[] data, int width, int height) {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("invalid matrix dimensions: " + width + "x" + height);
        }
        if (data.length != width * height) {
            throw new IllegalArgumentException("input data does not match with given dimensions: " + width + "x" + height + ", data length=" + data.length);
        }
        this.data = data;
        this.width = width;
        this.height = height;
    }

    public Matrix(int width, int height) {
        this(new double[width * height], width, height);
    }

    public double[] getData() {
        return data;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    private void assertValidCoordinates(int x, int y) {
        if (x < 0 || x >= width || y < 0 || y >= height) {
            throw new IllegalArgumentException(String.format("invalid coordinates (%d, %d) in matrix %dx%d", x, y, width, height));
        }
    }

    public double get(int x, int y) {
        assertValidCoordinates(x, y);
        return data[y * width + x];
    }

    public void set(int x, int y, double value) {
        assertValidCoordinates(x, y);
        data[y * width + x] = value;
    }

    public Matrix add(Matrix other) {
        if (other.width != width || other.height != height) {
            throw new IllegalArgumentException(String.format("adding matrices with different dimensions: %dx%d and %dx%d", width, height, other.width, other.height));
        }
        double[] newData = new double[width * height];
        for (int i = 0; i < newData.length; i++) {
            newData[i] = data[i] + other.data[i];
        }
        return new Matrix(newData, width, height);
    }

    public Matrix subtract(Matrix other) {
        if (other.width != width || other.height != height) {
            throw new IllegalArgumentException(String.format("subtracting matrices with different dimensions: %dx%d and %dx%d", width, height, other.width, other.height));
        }
        double[] newData = new double[width * height];
        for (int i = 0; i < newData.length; i++) {
            newData[i] = data[i] - other.data[i];
        }
        return new Matrix(newData, width, height);
    }

    public Matrix multiply(Matrix other) {
        if (width != other.height) {
            throw new IllegalArgumentException(String.format("invalid dimensions for multiplying matrices: %dx%d by %dx%d", width, height, other.width, other.height));
        }
        double[] newData = new double[height * other.width];
        for (int x = 0; x < other.width; x++) {
            for (int y = 0; y < height; y++) {
                double value = 0;
                for (int i = 0; i < width; i++) {
                    value += get(i, y) * other.get(x, i);
                }
                newData[y * other.width + x] = value;
            }
        }
        return new Matrix(newData, other.width, height);
    }

    public Matrix multiply(double value) {
        double[] newData = new double[width * height];
        for (int i = 0; i < data.length; i++) {
            newData[i] = data[i] * value;
        }
        return new Matrix(newData, width, height);
    }

    private double internalDet(int y, boolean[] columnsToSkip) {
        if (y == height - 1) {
            // on this stage, only one column will not be skipped, so we return its value (det of 1x1 matrix)
            for (int x = 0; x < width; x++) {
                if (!columnsToSkip[x]) {
                    return get(x, y);
                }
            }
            throw new RuntimeException("this should not happen");
        } else {
            // otherwise do recursion step
            double result = 0;
            int i = 0;
            for (int x = 0; x < width; x++) {
                if (!columnsToSkip[x]) {
                    columnsToSkip[x] = true;
                    result += (i++ % 2 == 0 ? -1 : 1) * get(x, y) * internalDet(y + 1, columnsToSkip);
                    columnsToSkip[x] = false;
                }
            }
            return result;
        }
    }

    public double det() {
        if (width != height) {
            throw new IllegalStateException("cannot call det() on non-square matrices");
        }
        return internalDet(0, new boolean[width]);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Matrix matrix = (Matrix) o;
        return width == matrix.width &&
                height == matrix.height &&
                Arrays.equals(data, matrix.data);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(width, height);
        result = 31 * result + Arrays.hashCode(data);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (int y = 0; y < height; y++) {
            if (y > 0) {
                sb.append(' ');
            }
            for (int x = 0; x < width; x++) {
                sb.append(String.format(x < width - 1 ? "%-8.1f " : "%.1f", data[y * width + x]));
            }
            sb.append(y != height - 1 ? '\n' : ']');
        }
        return sb.toString();
    }
}
