package com.tsystems.javaschool.tasks.pyramid;

import java.util.List;
import java.util.Arrays;
import java.util.Collections;

public class PyramidBuilder {

    private int validateInput(List<Integer> inputNumbers) {
        if (inputNumbers.size() >= Integer.MAX_VALUE - 1) {
            throw new CannotBuildPyramidException();
        }
        for (int i = 0; i < inputNumbers.size(); i++) {
            if (inputNumbers.get(i) == null) {
                throw new CannotBuildPyramidException();
            }
        }
        int acceptedSize = 1;
        int rowCount = 1;
        while (inputNumbers.size() >= acceptedSize) {
            if (inputNumbers.size() == acceptedSize) {
                return rowCount;
            }
            rowCount++;
            acceptedSize = acceptedSize + rowCount;

        }
        throw new CannotBuildPyramidException();
    }

    /**
     * Builds a pyramid with sorted values (with minumum value at the top line and
     * maximum at the bottom,
     * from left to right). All vacant positions in the array are zeros.
     *
     * @param inputNumbers to be used in the pyramid
     * @return 2d array with pyramid inside
     * @throws {@link CannotBuildPyramidException} if the pyramid cannot be build
     *                with given input
     */
    public int[][] buildPyramid(List<Integer> inputNumbers) {
        int rows = validateInput(inputNumbers);
        int cols = rows * 2 - 1;
        int[][] pyramid = new int[rows][cols];
        Collections.sort(inputNumbers, Collections.reverseOrder());
        int currentElementIdx = 0;
        int currentElement = inputNumbers.get(currentElementIdx);
        int margin = 0;
        for (int row = rows - 1; row >= 0; row--) {
            for (int col = cols - 1 - margin; col >= 0 + margin; col = col - 2) {
                pyramid[row][col] = currentElement;
                currentElementIdx++;
                if (currentElementIdx == inputNumbers.size()) {
                    return pyramid;
                }
                currentElement = inputNumbers.get(currentElementIdx);
            }
            margin++;
        }
        return pyramid;
    }

    public static void main(String[] args) {
        PyramidBuilder pyramidBuilder = new PyramidBuilder();
        List<Integer> input = Arrays.asList(1, 3, 2, 9, 4, 5, 10, 8, 7, 6);
        int[][] pyramid = pyramidBuilder.buildPyramid(input);
        for (int i = 0; i < pyramid.length; i++) {
            for (int j = 0; j < pyramid[i].length; j++) {
                System.out.print(pyramid[i][j]);
            }
            System.out.println();
        }
    }
}
