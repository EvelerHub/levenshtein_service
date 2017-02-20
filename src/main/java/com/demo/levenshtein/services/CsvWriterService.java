package com.demo.levenshtein.services;

import com.demo.levenshtein.model.ComparedIdentificator;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author Alexander Eveler, alexander.eveler@gmail.com
 * @since 2/20/17.
 */
public interface CsvWriterService {

    File getComparedIdentificator(List<ComparedIdentificator> identificators) throws IOException;
}
