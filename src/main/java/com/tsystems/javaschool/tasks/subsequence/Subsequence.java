package com.tsystems.javaschool.tasks.subsequence;

import java.util.List;

public class Subsequence {

    /**
     * Checks if it is possible to get a sequence which is equal to the first
     * one by removing some elements from the second one.
     *
     * @param x first sequence
     * @param y second sequence
     * @return <code>true</code> if possible, otherwise <code>false</code>
     */
    public <T> boolean find(List<T> x, List<T> y) {
        if (y == null || x == null) {
            throw new IllegalArgumentException("The arguments can't be null");
        }
        if (x.size() == 0) {
            return true;
        }
        int xPosition = 0;
        T xValue = x.get(xPosition);

        for (int i = 0; i < y.size(); i++) {
            T yValue = y.get(i);
            if (yValue == xValue) {
                xPosition = xPosition + 1;
                if (xPosition == x.size()) {
                    return true;
                }
                xValue = x.get(xPosition);
            }
        }
        return false;
    }
}
