package com.demo.levenshtein.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * @author Alexander Eveler, alexander.eveler@gmail.com
 * @since 1/23/17.
 */
public class LevenshteinDistanceAlgorithm {

    /**
     * @param givenWord      - word with which compare another word.
     * @param comparableWord - word for comparison with given word.
     */
    public static int calculateDisntance(String givenWord, String comparableWord) {
        int[][] distanceMatrix = getDistanceMatrix(givenWord, comparableWord);

        return getDistance(distanceMatrix);
    }

    /**
     * @param givenWord      - word with which compare another word.
     * @param comparableWord - word for comparison with given word.
     */
    public static int[][] getDistanceMatrix(String givenWord, String comparableWord) {
        int[][] distanceMatrix = new int[givenWord.length() + 1][comparableWord.length() + 1];
        fillFirstColumnAndFirsRow(distanceMatrix);
        fillMatrix(distanceMatrix, givenWord, comparableWord);

        return distanceMatrix;
    }

    /**
     * Calculate distances in matrix.
     *
     * @param distanceMatrix - matrix where every position it's distance to character in column.
     * @param givenWord      - word with which compare another word.
     * @param comparableWord - word for comparison with given word.
     */
    private static void fillMatrix(int[][] distanceMatrix, String givenWord, String comparableWord) {
        for (int i = 1; i < givenWord.length() + 1; i++) {
            for (int j = 1; j < comparableWord.length() + 1; j++) {
                char leftLetter = givenWord.charAt(i - 1);
                char upperLetter = comparableWord.charAt(j - 1);
                int left = distanceMatrix[i][j - 1];
                int leftUpper = distanceMatrix[i - 1][j - 1];
                int upper = distanceMatrix[i - 1][j];

                distanceMatrix[i][j] = getDistanceOnCurrentStep(leftLetter, upperLetter, left, leftUpper, upper);
            }
        }
    }

    /**
     * Fill first column and first row of matrix.
     * This is the initial state of the matrix.
     * Each operation represents the insert operation.
     */
    private static void fillFirstColumnAndFirsRow(int[][] distanceMatrix) {
        for (int i = 1; i < distanceMatrix.length; i++) {
            distanceMatrix[i][0] = i;
        }

        for (int j = 1; j < distanceMatrix[0].length; j++) {
            distanceMatrix[0][j] = j;
        }
    }

    /**
     * Calculate value of current position in distance matrix.
     *
     * @param leftLetter  - character of given word on current step.
     * @param upperLetter - character of comparable word on current step.
     * @param left        - value of left cell relatively current position.
     * @param leftUpper   - value of left-upper cell relatively current position.
     * @param upper       - value of upper cell relatively current position.
     * @return - value in current position.
     */
    private static int getDistanceOnCurrentStep(char leftLetter, char upperLetter, int left, int leftUpper, int upper) {
        if (leftLetter != upperLetter) {
            return Math.min(left, Math.min(leftUpper, upper)) + 1;
        }

        return leftUpper;
    }

    /**
     * @param distanceMatrix - matrix where every position it's distance to character in column.
     * @return - distance to last character in column.
     */
    private static int getDistance(int[][] distanceMatrix) {
        return distanceMatrix[distanceMatrix.length - 1][distanceMatrix[0].length - 1];
    }
}