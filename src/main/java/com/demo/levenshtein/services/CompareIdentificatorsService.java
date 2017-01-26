package com.demo.levenshtein.services;

import com.demo.levenshtein.model.ComparedIdentificator;

import java.util.List;

/**
 * @author Alexander Eveler, alexander.eveler@gmail.com
 * @since 1/25/17.
 */
public interface CompareIdentificatorsService {

    List<ComparedIdentificator> compareIdentificators(List<String> leftColumn, List<String> rightColumn);

}
