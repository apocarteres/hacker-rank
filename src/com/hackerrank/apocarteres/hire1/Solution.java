package com.hackerrank.apocarteres.hire1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.String.format;
import static java.util.Collections.emptySet;
import static java.util.Collections.singleton;

public class Solution {

    public List<Integer> getAnagramIndices(String haystack, String needle) {
        Set<String> p = perm(needle);
        List<Integer> result = new ArrayList<>();
        for (String s : p) {
            Pattern pattern = Pattern.compile(s);
            Matcher matcher = pattern.matcher(haystack);
            while (matcher.find()) {
                result.add(matcher.start());
            }
        }
        result.sort(Integer::compare);
        return result;
    }


    private static Set<String> perm(String needle) {
        if (needle.isEmpty()) {
            return emptySet();
        }
        if (needle.length() == 1) {
            return singleton(needle);
        }
        String prefix = needle.substring(0,1);
        needle = needle.substring(1);
        Set<String> permSet = perm(needle);
        Set<String> result = new HashSet<>();
        for (String p : permSet) {
            for (int i = 0; i <= p.length(); i++) {
                result.add(format("%s%s%s", p.substring(0, i), prefix, p.substring(i)));
            }
        }
        return result;
    }
}