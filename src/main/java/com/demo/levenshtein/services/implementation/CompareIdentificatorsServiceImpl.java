package com.demo.levenshtein.services.implementation;

import com.demo.levenshtein.controllers.LevenshteinRestController;
import com.demo.levenshtein.model.ComparedIdentificator;
import com.demo.levenshtein.services.CompareIdentificatorsService;
import com.demo.levenshtein.utils.IdentificatorUtils;
import com.demo.levenshtein.utils.LevenshteinDistanceAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Alexander Eveler, alexander.eveler@gmail.com
 * @since 1/25/17.
 */
@Service
public class CompareIdentificatorsServiceImpl implements CompareIdentificatorsService {

    private static final Logger LOG = LoggerFactory.getLogger(CompareIdentificatorsServiceImpl.class);

    /**
     * Compare identificators by min distance.
     *
     * @param leftIdentificators  - Identificators with which compare another word.
     * @param rightIdentificators - Identificators for comparison with given word.
     * @return - list of compared identificators.
     */
    @Override
    public List<ComparedIdentificator> compareIdentificators(List<String> leftIdentificators,
                                                             List<String> rightIdentificators) {
        LOG.info("left identificators ==> " + leftIdentificators);
        LOG.info("right identificators ==> " + rightIdentificators);

        List<String> clearedLeftIdentificators = IdentificatorUtils.clearListOfIdentificators(leftIdentificators);
        List<String> clearedRightIdentificators = IdentificatorUtils.clearListOfIdentificators(rightIdentificators);

        LOG.info("cleared left Identificators ==> " + clearedLeftIdentificators);
        LOG.info("cleared right Identificators ==> " + clearedRightIdentificators);

        List<ComparedIdentificator> comparedIdentificators = new ArrayList<>();
        Set<Integer> removedIdentificators = new HashSet<>();

        for (int i = 0; i < clearedLeftIdentificators.size(); i++) {
            int identificatorIndex = 0;
            int minDistance = Integer.MAX_VALUE;

            for (int j = 0; j < clearedRightIdentificators.size(); j++) {
                if (!removedIdentificators.contains(j)) {
                    int distance = LevenshteinDistanceAlgorithm.calculateDisntance(
                            clearedLeftIdentificators.get(i),
                            clearedRightIdentificators.get(j)
                    );
                    if (minDistance > distance) {
                        minDistance = distance;
                        identificatorIndex = j;
                    }
                }
            }

            ComparedIdentificator comparedIdentificator = new ComparedIdentificator(
                    leftIdentificators.get(i),
                    rightIdentificators.get(identificatorIndex)
            );
            LOG.info("compared identificator ==> " + comparedIdentificator);
            LOG.info("compared identificator distance ==> " + minDistance);
            comparedIdentificators.add(comparedIdentificator);
            removedIdentificators.add(identificatorIndex);
        }

        LOG.info("compared identificators ==> " + comparedIdentificators);
        return comparedIdentificators;
    }
}
