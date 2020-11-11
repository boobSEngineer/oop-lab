package com.eltech.lab3;

import java.util.HashSet;
import java.util.Set;

public interface SearchCacheElement {
    String toReadableString();

    void addCacheListener(SearchCacheListener listener);

    default Set<Character> getCharacterSetForCaching() {
        Set<Character> characters = new HashSet<>();
        for (char c : toReadableString().toLowerCase().toCharArray()) {
            characters.add(c);
        }
        return characters;
    }
}
