package net.erchen.adventofcode2019.day04;

import java.util.LinkedList;
import java.util.List;

public class VenusFuelDepot {

    public static List<Integer> validCodes(Integer min, Integer max, boolean ignoreLargerGroups) {
        List<Integer> validCodes = new LinkedList<>();
        int[] digit = new int[6];

        for (digit[0] = 0; digit[0] <= 9; digit[0]++) {
            for (digit[1] = digit[0]; digit[1] <= 9; digit[1]++) {
                for (digit[2] = digit[1]; digit[2] <= 9; digit[2]++) {
                    for (digit[3] = digit[2]; digit[3] <= 9; digit[3]++) {
                        for (digit[4] = digit[3]; digit[4] <= 9; digit[4]++) {
                            for (digit[5] = digit[4]; digit[5] <= 9; digit[5]++) {
                                int[] digitCounts = new int[10];
                                for (int i : digit) {
                                    digitCounts[i]++;
                                }
                                boolean hasValidPair = false;
                                for (int digitCount : digitCounts) {
                                    hasValidPair = (ignoreLargerGroups && digitCount == 2) || (!ignoreLargerGroups && digitCount >= 2);
                                    if (hasValidPair) {
                                        break;
                                    }
                                }
                                if (!hasValidPair) {
                                    continue;
                                }

                                var code = digit[0] * 100000 + digit[1] * 10000 + digit[2] * 1000 + digit[3] * 100 + digit[4] * 10 + digit[5];
                                if (code < min || code > max) {
                                    continue;
                                }
                                validCodes.add(code);
                            }
                        }
                    }
                }
            }

        }
        return validCodes;
    }
}
