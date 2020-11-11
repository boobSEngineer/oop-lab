package com.eltech.lab3;

import java.util.Objects;

public class PhoneNumber {
    public enum NumberType {
        NONE,
        WORK,
        HOME,
        MOBILE
    }

    private final String data;
    private final NumberType type;

    public PhoneNumber(NumberType type, String data) {
        this.data = data;
        this.type = type;
    }

    public String getData() {
        return data;
    }

    @Override
    public String toString() {
        return "PhoneNumber{" +
                "data='" + data + '\'' +
                ", type=" + type +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhoneNumber that = (PhoneNumber) o;
        return Objects.equals(data, that.data) &&
                type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(data, type);
    }
}
