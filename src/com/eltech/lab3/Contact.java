package com.eltech.lab3;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class Contact implements SearchCacheElement {
    private final String firstName, secondName;
    private final List<PhoneNumber> numbers = new ArrayList<>();
    private final List<SearchCacheListener> cacheListeners = new ArrayList<>();

    public Contact(String firstName, String secondName) {
        this.firstName = firstName;
        this.secondName = secondName;
    }

    public Contact(String name) {
        this(name, null);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getName() {
        return firstName + (secondName != null ? " " + secondName : "");
    }

    public void addNumber(PhoneNumber number) {
        numbers.add(number);
        invokeCacheListeners();
    }


    public String toReadableString() {
        return getName() + " " + numbers.stream().map(PhoneNumber::getData).collect(Collectors.joining(", "));
    }

    @Override
    public void addCacheListener(SearchCacheListener listener) {
        cacheListeners.add(listener);
    }

    private void invokeCacheListeners() {
        for (SearchCacheListener listener : cacheListeners) {
            listener.onElementChanged(this);
        }
    }


    public static class Builder {
        private final Contact contact;

        public Builder(String name) {
            contact = new Contact(name);
        }

        public Builder(String firstName, String secondName) {
            contact = new Contact(firstName, secondName);
        }

        public Builder addNumber(PhoneNumber.NumberType type, String number) {
            contact.addNumber(new PhoneNumber(type, number));
            return this;
        }

        public Builder addNumber(String number) {
            contact.addNumber(new PhoneNumber(PhoneNumber.NumberType.NONE, number));
            return this;
        }

        public Contact build() {
            return contact;
        }
    }
}
