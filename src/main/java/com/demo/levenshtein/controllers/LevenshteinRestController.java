package com.demo.levenshtein.controllers;

import com.demo.levenshtein.model.ComparableIdentificators;
import com.demo.levenshtein.model.ComparedIdentificator;
import com.demo.levenshtein.services.CompareIdentificatorsService;
import com.demo.levenshtein.services.CsvParserService;
import com.demo.levenshtein.services.CsvWriterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.supercsv.exception.SuperCsvException;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * @author Alexander Eveler, alexander.eveler@gmail.com
 * @since 1/25/17.
 */
@RestController
@RequestMapping("/levenshtein")
public class LevenshteinRestController {

    private static final Logger LOG = LoggerFactory.getLogger(LevenshteinRestController.class);

    @Autowired
    private CompareIdentificatorsService compareIdentificatorsService;

    @Autowired
    private CsvParserService csvParserService;

    @Autowired
    private CsvWriterService csvWriterService;

    @RequestMapping(value = "/compare", method = RequestMethod.POST)
    public List<ComparedIdentificator> compareIdentificators(
            @RequestBody ComparableIdentificators comparableIdentificators) {

        LOG.info("ComparableIdentificators ==> " + comparableIdentificators);
        return compareIdentificatorsService.compareIdentificators(
                comparableIdentificators.getLeftIdentificators(),
                comparableIdentificators.getRightIdentificators()
        );
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test() {
        return "OK";
    }

    @RequestMapping(value = "/uploadcsv", method = RequestMethod.POST, produces = "application/csv")
    public @ResponseBody Resource processUpload(@RequestParam MultipartFile file, HttpServletResponse response) {

        LOG.info("File ContentType ==>" + file.getContentType());
        LOG.info("File Size ==>" + file.getSize());
        LOG.info("File isEmpty ==>" + file.isEmpty());

        // transforming CSV file to ComparableIdentificators object
        InputStreamReader fileReader = null;
        ComparableIdentificators comparableIdentificators;
        try {
            fileReader = new InputStreamReader(file.getInputStream());
            comparableIdentificators = csvParserService.getComparableIdentificators(fileReader);
        } catch (IOException | SuperCsvException e) {
            LOG.error(e.getMessage(), e.getCause());
            response.setStatus(400);
            return null;
        } finally {
            try {
                fileReader.close();
            } catch (IOException | NullPointerException e) {
                LOG.error(e.getMessage(), e.getCause());
            }
        }

        // comparing
        LOG.info("ComparableIdentificators ==> " + comparableIdentificators);
        List<ComparedIdentificator> comparedIdentificators = compareIdentificatorsService.compareIdentificators(
                comparableIdentificators.getLeftIdentificators(),
                comparableIdentificators.getRightIdentificators());

        // transforming comparedIdentificators object to CSV file
        File outputFile = null;
        try {
            outputFile = csvWriterService.getComparedIdentificator(comparedIdentificators);
            response.setContentType("application/csv");
            response.setHeader("Content-Disposition", "compared_" + file.getOriginalFilename());
            response.setHeader("Content-Length", String.valueOf(outputFile.length()));
            return new FileSystemResource(outputFile);
        } catch (IOException e) {
            LOG.error(e.getMessage(), e.getCause());
            response.setStatus(400);
            return null;
        }
    }
}
