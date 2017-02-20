package com.demo.levenshtein.services.implementation;

import com.demo.levenshtein.model.ComparedIdentificator;
import com.demo.levenshtein.services.CsvWriterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.supercsv.io.CsvListWriter;
import org.supercsv.io.ICsvListWriter;
import org.supercsv.prefs.CsvPreference;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * @author Alexander Eveler, alexander.eveler@gmail.com
 * @since 2/20/17.
 */
@Service
public class CsvWriterServiceImpl implements CsvWriterService {

    private static final Logger LOG = LoggerFactory.getLogger(CsvWriterServiceImpl.class);

    @Override
    public File getComparedIdentificator(List<ComparedIdentificator> identificators) throws IOException {

        ICsvListWriter listWriter = null;
        File file = null;
        try {
            Path path = Files.createTempFile("compared_identificators", ".csv");
            file = path.toFile();

            LOG.info("temp file ==> " + file);

            listWriter = new CsvListWriter(new FileWriter(file), CsvPreference.STANDARD_PREFERENCE);

            String[] headers = new String[]{"leftIdentificators", "rightIdentificators"};
            listWriter.writeHeader(headers);
            for (ComparedIdentificator identificator : identificators) {
                listWriter.write(identificator.getLeftIdentificator(), identificator.getRightIdentificator());
            }
        } finally {
            if (listWriter != null) {
                listWriter.close();
            }
        }

        return file;
    }
}