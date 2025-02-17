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

    public static int calculateDistance(String a, String b) {
        int[][] dp = new int[a.length() + 1][b.length() + 1];

        for (int i = 0; i <= a.length(); i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= b.length(); j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= a.length(); i++) {
            for (int j = 1; j <= b.length(); j++) {
                if (a.charAt(i - 1) == b.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(Math.min(
                                    dp[i - 1][j] + 1,
                                    dp[i][j - 1] + 1),
                            dp[i - 1][j - 1] + 1
                    );
                }
            }
        }

        return dp[a.length()][b.length()];
    }

    public static double calculateNormalizedDistance(String a, String b) {
        int distance = calculateDistance(a, b);
        int maxLength = Math.max(a.length(), b.length());

        if (maxLength == 0) {
            return 0.0;
        }

        return (double) distance / (double) maxLength;
    }

    public static boolean calcFactor(List<String> arg1, List<String> args2) {
        if (arg1.isEmpty() || args2.isEmpty()) {
            return true;
        }
        double distanceRatioThreshold = 0.9;
        Double maxDistance = arg1.stream()
                .flatMap(ar1 -> args2.stream()
                        .map(ar2 -> LoanShieldUtils.calculateNormalizedDistance(ar1, ar2)))
                .max(Double::compare)
                .orElse(0.0);
        return distanceRatioThreshold > maxDistance;
    }
}
