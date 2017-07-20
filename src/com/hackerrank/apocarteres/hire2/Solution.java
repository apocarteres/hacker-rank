package com.hackerrank.apocarteres.hire2;

import java.util.*;

import static java.util.Collections.emptySet;

public class Solution {

    public String[][] matchLunches(String[][] lunchMenuPairs, String[][] teamCuisinePreference) {
        Map<String, Set<String>> menu = new HashMap<>();
        for (String[] lunchMenuPair : lunchMenuPairs) {
            Set<String> set = menu.computeIfAbsent(lunchMenuPair[1], k -> new HashSet<>());
            set.add(lunchMenuPair[0]);
        }
        Map<String, Set<String>> order = new HashMap<>();
        for (String[] preference : teamCuisinePreference) {
            Set<String> strings = order.computeIfAbsent(preference[0], k -> new HashSet<>());
            Iterator<String> itemsIt;
            String p = preference[1];
            if ("*".equals(p)) {
                itemsIt = menu.values().stream().flatMap(Collection::stream).iterator();
            } else {
                itemsIt = menu.getOrDefault(p, emptySet()).iterator();
            }
            while (itemsIt.hasNext()) {
                strings.add(itemsIt.next());
            }
        }
        order.entrySet().removeIf(e -> e.getValue().isEmpty());
        int size = order.values().stream().mapToInt(Set::size).sum();
        String[][] result = new String[size][];
        int i = 0;
        for (Map.Entry<String, Set<String>> entry : order.entrySet()) {
            Set<String> set = entry.getValue();
            for (String item : set) {
                result[i++] = new String[]{entry.getKey(), item};
            }
        }
        return result;
    }
}