package com.demo.levenshtein.services.implementation;

import com.demo.levenshtein.controllers.LevenshteinRestController;
import com.demo.levenshtein.model.ComparableIdentificators;
import com.demo.levenshtein.services.CsvParserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.exception.SuperCsvException;
import org.supercsv.io.CsvListReader;
import org.supercsv.io.ICsvListReader;
import org.supercsv.prefs.CsvPreference;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Alexander Eveler, alexander.eveler@gmail.com
 * @since 2/20/17.
 */
@Service
public class CsvPerserServiceImp implements CsvParserService {

    private static final Logger LOG = LoggerFactory.getLogger(CsvPerserServiceImp.class);

    @Override
    public ComparableIdentificators getComparableIdentificators(Reader reader) throws SuperCsvException, IOException {
        ICsvListReader listReader = new CsvListReader(reader, CsvPreference.STANDARD_PREFERENCE);

        String[] headers = listReader.getHeader(true);
        LOG.info("file Headers ==>" + Arrays.toString(headers));

        final CellProcessor[] processors = new CellProcessor[]{
                new NotNull(),
                new NotNull(),
        };

        List<Object> customerList;
        List<String> leftIdentificators = new ArrayList<String>();
        List<String> rightIdentificators = new ArrayList<String>();
        while ((customerList = listReader.read(processors)) != null) {
            leftIdentificators.add((String) customerList.get(0));
            rightIdentificators.add((String) customerList.get(1));
        }

        return new ComparableIdentificators(leftIdentificators, rightIdentificators);
    }
}
