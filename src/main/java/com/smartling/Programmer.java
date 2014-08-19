package com.smartling;

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
    public boolean gotBeer(int goalPints, int smallBottles, int bigBottles) {
        Validate.isTrue(goalPints >= 0, "Goal pints must be any positive integer or 0.");
        Validate.isTrue(smallBottles >= 0, "Number of small bottles must be any positive integer or 0.");
        Validate.isTrue(bigBottles >= 0, "Number of big bottles must be any positive integer or 0.");

        // this one doesn't looking for the beers
        if (goalPints == 0) {
            // so just make him happy
            return true;
        }

        // try consume big bottles first
        while (bigBottles > 0) {
            goalPints -= NUM_OF_PINTS_IN_BIG_BOTTLE;
            if (goalPints < 0) {
                // revert back subtraction because we get out of bounds (completely drunk)
                goalPints += NUM_OF_PINTS_IN_BIG_BOTTLE;
                break;
            }
            bigBottles--;
        }

        // check if programmer wants more
        if (goalPints > 0) {
            // try consume small bottles if something remains after the first step
            while (smallBottles > 0) {
                goalPints -= NUM_OF_PINTS_IN_SMALL_BOTTLE;
                if (goalPints == 0) {
                    // goal is achieved
                    break;
                }
                smallBottles--;
            }
        }

        if (goalPints == 0) {
            // we can get the desired amount of beers by choosing from the given bottles
            return true;
        }

        // sadly we can't get the desired amount, because some pints remain...
        // has to run to the store to buy some more bottles...
        return false;
    }
}
