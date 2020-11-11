package com.eltech.lab3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PhoneBook {
    private final List<Contact> contacts = new ArrayList<>();
    private final SearchCache<Contact> cache = new SearchCache<>(contacts);

    public void addContact(Contact contact) {
        contacts.add(contact);
        cache.onAdded(contact);
    }

    public Contact find(String substring) {
        return cache.find(substring);
    }

    public List<Contact> findAll(String substring) {
        return cache.findAll(substring);
    }

    public List<Contact> getContacts() {
        return Collections.unmodifiableList(contacts);
    }
}
