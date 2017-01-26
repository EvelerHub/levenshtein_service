package com.demo.levenshtein.model;

import java.util.List;

/**
 * @author Alexander Eveler, alexander.eveler@gmail.com
 * @since 1/25/17.
 */
public class ComparableIdentificators {

    private List<String> leftIdentificators;
    private List<String> rightIdentificators;

    public ComparableIdentificators() {
    }

    public ComparableIdentificators(List<String> leftIdentificators, List<String> rightIdentificators) {
        this.leftIdentificators = leftIdentificators;
        this.rightIdentificators = rightIdentificators;
    }

    public List<String> getLeftIdentificators() {
        return leftIdentificators;
    }

    public void setLeftIdentificators(List<String> leftIdentificators) {
        this.leftIdentificators = leftIdentificators;
    }

    public List<String> getRightIdentificators() {
        return rightIdentificators;
    }

    public void setRightIdentificators(List<String> rightIdentificators) {
        this.rightIdentificators = rightIdentificators;
    }

    @Override
    public String toString() {
        return "ComparableIdentificators{" +
                "leftIdentificators=" + leftIdentificators +
                ", rightIdentificators=" + rightIdentificators +
                '}';
    }
}
