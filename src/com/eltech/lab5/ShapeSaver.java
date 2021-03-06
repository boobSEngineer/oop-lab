package com.eltech.lab5;

import com.eltech.lab2.Shape;
import com.eltech.lab4.ShapeAccumulator;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ShapeSaver implements ShapeAccumulator.OnChangeListener {
    public interface ExceptionHandler {
        void onException(Exception exception);
    }


    private final ShapeAccumulator accumulator;
    private final File file;
    private final List<ExceptionHandler> exceptionHandlers = new ArrayList<>();

    public ShapeSaver(ShapeAccumulator accumulator, File file) {
        this.accumulator = accumulator;
        this.file = file;
        accumulator.addListener(this);
    }

    public void addExceptionHandler(ExceptionHandler handler) {
        exceptionHandlers.add(handler);
    }

    private boolean isWorking = false;

    public synchronized boolean read() {
        isWorking = true;
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file))) {
            //noinspection unchecked
            List<Shape> shapes = (List<Shape>) inputStream.readObject();
            accumulator.clear();
            accumulator.addAll(shapes);
            isWorking = false;
            return true;
        } catch (IOException | ClassNotFoundException | ClassCastException exception) {
            isWorking = false;
            for (ExceptionHandler handler : exceptionHandlers) {
                handler.onException(exception);
            }
            return false;
        }
    }

    public synchronized boolean save() {
        isWorking = true;
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file))) {
            outputStream.writeObject(new ArrayList<>(accumulator.getShapes()));
            isWorking = false;
            return true;
        } catch (IOException exception) {
            isWorking = false;
            for (ExceptionHandler handler : exceptionHandlers) {
                handler.onException(exception);
            }
            return false;
        }
    }

    @Override
    public synchronized void onChange() {
        if (!isWorking) {
            save();
        }
    }
}
