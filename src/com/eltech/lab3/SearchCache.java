package com.eltech.lab3;

import com.sun.deploy.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

public class SearchCache<T extends SearchCacheElement> implements SearchCacheListener {
    private final Map<Character, List<T>> cache = new HashMap<>();
    private final Collection<T> source;

    public SearchCache(Collection<T> source) {
        this.source = source;
    }

    public T find(String substring) {
        if (substring == null || substring.length() == 0) {
            return null;
        }
        List<T> result = cache.get(substring.charAt(0));
        if (result != null) {
            for (T element : result) {
                if (element.toReadableString().contains(substring)) {
                    return element;
                }
            }
        }
        return null;
    }

    public List<T> findAll(String substring) {
        if (substring == null || substring.length() == 0) {
            return new ArrayList<>();
        }
        List<T> result = cache.get(substring.charAt(0));
        if (result != null) {
            return result.stream().filter(element -> element.toReadableString().contains(substring)).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    public void onAdded(T element) {
        element.addCacheListener(this);
        onElementChanged(element);
    }

    @Override
    public void onElementChanged(SearchCacheElement element) {
        for (Character character : element.getCharacterSetForCaching()) {
            cache.computeIfAbsent(character, key -> new ArrayList<>()).add((T) element);
        }
    }
}
