package com.demo.levenshtein.controllers;

import com.demo.levenshtein.model.ComparableIdentificators;
import com.demo.levenshtein.model.ComparedIdentificator;
import com.demo.levenshtein.services.CompareIdentificatorsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping(value = "/compare", method = RequestMethod.POST)
    public List<ComparedIdentificator> compareIdentificators(
            @RequestBody ComparableIdentificators comparableIdentificators){

        LOG.info("Request ==> " + comparableIdentificators);
        return compareIdentificatorsService.compareIdentificators(
                comparableIdentificators.getLeftIdentificators(),
                comparableIdentificators.getRightIdentificators()
        );
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test(){
        return "OK";
    }
}
