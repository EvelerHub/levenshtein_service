package com.demo.levenshtein.model;

/**
 * @author Alexander Eveler, alexander.eveler@gmail.com
 * @since 1/25/17.
 */
public class ComparedIdentificator {

    private String leftIdentificator;
    private String rightIdentificator;

    public ComparedIdentificator(String leftIdentificator, String rightIdentificators) {
        this.leftIdentificator = leftIdentificator;
        this.rightIdentificator = rightIdentificators;
    }

    public String getLeftIdentificator() {
        return leftIdentificator;
    }

    public void setLeftIdentificator(String leftIdentificator) {
        this.leftIdentificator = leftIdentificator;
    }

    public String getRightIdentificator() {
        return rightIdentificator;
    }

    public void setRightIdentificator(String rightIdentificator) {
        this.rightIdentificator = rightIdentificator;
    }

    @Override
    public String toString() {
        return "ComparedIdentificator{" +
                "leftIdentificator='" + leftIdentificator + '\'' +
                ", rightIdentificator='" + rightIdentificator + '\'' +
                '}';
    }
}
