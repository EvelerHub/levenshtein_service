package com.demo.levenshtein.utils;

import com.demo.levenshtein.services.implementation.CompareIdentificatorsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alexander Eveler, alexander.eveler@gmail.com
 * @since 1/25/17.
 */
public class IdentificatorUtils {

    /**
     * @param identificators - clear identificators from characters different from letters.
     * @return - List of cleared identificators.
     */
    public static List<String> clearListOfIdentificators(List<String> identificators) {
        List<String> clearedList = new ArrayList<>();

        for (String identificator : identificators) {
            String clearedIdentificator = identificator.replaceAll("\\W", "").toLowerCase();
            clearedList.add(clearedIdentificator);
        }
        return clearedList;
    }

}
