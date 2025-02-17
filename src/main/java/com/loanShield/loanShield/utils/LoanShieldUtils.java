package com.loanShield.loanShield.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class LoanShieldUtils {

    public static List<String> generatePairs(String... args) {
        List<String> pairs = new ArrayList<>();
                List<String> elements = Arrays.stream(args)
                .filter(Objects::nonNull)
                .toList();
        int n = elements.size();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    String pair = elements.get(i) + elements.get(j);
                    pairs.add(pair);
                }
            }
        }

        return pairs;
    }
}
