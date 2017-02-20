package com.demo.levenshtein.model;

/**
 * @author Alexander Eveler, alexander.eveler@gmail.com
 * @since 2/20/17.
 */
public class IdentificatorAndDistance {

    private String identificator;
    private int distance;

    public IdentificatorAndDistance() {
    }

    public IdentificatorAndDistance(String identificator, int distance) {
        this.identificator = identificator;
        this.distance = distance;
    }

    public String getIdentificator() {
        return identificator;
    }

    public void setIdentificator(String identificator) {
        this.identificator = identificator;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "IdentificatorAndDistance{" +
                "identificator='" + identificator + '\'' +
                ", distance=" + distance +
                '}';
    }
}
