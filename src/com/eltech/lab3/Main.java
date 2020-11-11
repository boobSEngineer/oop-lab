package com.eltech.lab3;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();
        phoneBook.addContact(new Contact.Builder("Eugene", "Smirnov")
                .addNumber(PhoneNumber.NumberType.HOME, "22-505")
                .addNumber(PhoneNumber.NumberType.WORK, "11-101")
                .addNumber(PhoneNumber.NumberType.MOBILE, "88005553535")
                .build());
        phoneBook.addContact(new Contact.Builder("Elena", "Zubareva")
                .addNumber(PhoneNumber.NumberType.HOME, "99-999")
                .addNumber(PhoneNumber.NumberType.WORK, "11-111")
                .addNumber(PhoneNumber.NumberType.MOBILE, "88005505050")
                .build());

        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("input substring to search (exit=stop, empty line=show all): ");
            String command = input.nextLine();

            List<Contact> result;
            if (command.equals("exit")) {
                return;
            } else if (command.equals("")) {
                result = phoneBook.getContacts();
            } else {
                result = phoneBook.findAll(command);
            }

            System.out.println("result: \n" + result.stream().map(Contact::toReadableString).collect(Collectors.joining("\n")));
        }
    }
}
