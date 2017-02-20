package com.demo.levenshtein.services;

import com.demo.levenshtein.model.ComparableIdentificators;
import org.supercsv.exception.SuperCsvException;

import java.io.IOException;
import java.io.Reader;

/**
 * @author Alexander Eveler, alexander.eveler@gmail.com
 * @since 2/20/17.
 */
public interface CsvParserService {

    ComparableIdentificators getComparableIdentificators(Reader reader) throws IOException, SuperCsvException;
}
