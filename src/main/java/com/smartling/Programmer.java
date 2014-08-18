package com.smartling;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.commons.lang3.Validate;

/**
 * The simple class that represents a programmer.
 */
public class Programmer {

    public static final int NUM_OF_PINTS_IN_SMALL_BOTTLE = 1;
    public static final int NUM_OF_PINTS_IN_BIG_BOTTLE = 3;

    /**
     * Check whenever it is possible to get the desired amount of beers by
     * choosing from the given (whole) bottles or no.
     *
     * @param goalPints the number of pints programmer wants to drink
     * @param smallBottles the given amount of small bottles with beer
     * @param bigBottles the given amount of big bottles with beer
     * @return {@code true} if it is possible to make the goal by choosing from
     * the given (whole) bottles, otherwise {@code false}
     * @throws IllegalArgumentException if the number of pints programmer wants
     * to drink less then 0
     * @throws IllegalArgumentException if the given amount of small bottles
     * with beer less then 0
     * @throws IllegalArgumentException if the given amount of big bottles with
     * beer less then 0
     */
    public boolean gotBeer(final int goalPints, int smallBottles, int bigBottles) {
        Validate.isTrue(goalPints >= 0, "Goal pints must be any positive integer or 0.");
        Validate.isTrue(smallBottles >= 0, "Number of small bottles must be any positive integer or 0.");
        Validate.isTrue(bigBottles >= 0, "Number of big bottles must be any positive integer or 0.");

        if (goalPints == 0) {
            return true;
        }

        // init the condition verifier
        Predicate<List<Integer>> conditionVerifier = new Predicate<List<Integer>>() {
            @Override
            public boolean test(List<Integer> pintValues) {
                int sumOfConsumedPints = 0;
                for (int pintValue : pintValues) {
                    sumOfConsumedPints += pintValue;
                    if (sumOfConsumedPints == goalPints) {
                        return true;
                    } else if (sumOfConsumedPints > goalPints) {
                        return false;
                    }
                }

                return false;
            }
        };

        int numOfBottles = smallBottles + bigBottles;
        List<Integer> pintValues = new ArrayList<Integer>(numOfBottles);

        // fill in list with pint values
        for (int i = 0; i < smallBottles; i++) {
            pintValues.add(NUM_OF_PINTS_IN_SMALL_BOTTLE);
        }
        for (int i = 0; i < bigBottles; i++) {
            pintValues.add(NUM_OF_PINTS_IN_BIG_BOTTLE);
        }

        // try consume the beers
        boolean result = tryConsume(goalPints, pintValues, conditionVerifier);
        return result;
    }

    protected boolean tryConsume(int goalPints, List<Integer> pintValues, Predicate<List<Integer>> conditionVerifier) {
        return tryConsume(pintValues, 0, conditionVerifier);
    }

    protected boolean tryConsume(List<Integer> pintValues, int offset, Predicate<List<Integer>> conditionVerifier) {
        for (int i = offset; i < pintValues.size(); i++) {
            Collections.swap(pintValues, i, offset);
            boolean conditionMet = tryConsume(pintValues, offset + 1, conditionVerifier);
            // swap back to protect from repeating values
            Collections.swap(pintValues, offset, i);

            if (conditionMet) {
                return true;
            }
        }

        if (offset == pintValues.size() - 1) {
            boolean conditionMet = conditionVerifier.test(pintValues);
            if (conditionMet) {
                return true;
            }
        }

        return false;
    }
}
