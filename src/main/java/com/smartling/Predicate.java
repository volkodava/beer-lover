package com.smartling;

public interface Predicate<T> {

    /**
     * Evaluates this predicate on the given argument.
     *
     * @param obj the input argument
     * @return {@code true} if the input argument matches the predicate,
     * otherwise {@code false}
     */
    boolean test(T obj);
}
