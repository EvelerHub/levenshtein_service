package com.demo.levenshtein.services.implementation;

import com.demo.levenshtein.model.ComparedIdentificator;
import com.demo.levenshtein.model.IdentificatorAndDistance;
import com.demo.levenshtein.services.CompareIdentificatorsService;
import com.demo.levenshtein.utils.IdentificatorUtils;
import com.demo.levenshtein.utils.LevenshteinDistanceAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        List<IdentificatorAndDistance> identificatorsAndDistances;

        LOG.info("cleared left Identificators ==> " + clearedLeftIdentificators);
        LOG.info("cleared right Identificators ==> " + clearedRightIdentificators);
        List<ComparedIdentificator> comparedIdentificators = new ArrayList<>();

        for (int i = 0; i < clearedLeftIdentificators.size(); i++) {
            int minDistance = Integer.MAX_VALUE;
            identificatorsAndDistances = new ArrayList<>();

            for (String clearedRightIdentificator : clearedRightIdentificators) {
                int distance = LevenshteinDistanceAlgorithm.calculateDisntance(
                        clearedLeftIdentificators.get(i),
                        clearedRightIdentificator
                );
                if (minDistance > distance) {
                    minDistance = distance;
                }

                identificatorsAndDistances.add(new IdentificatorAndDistance(clearedRightIdentificator, distance));
            }

            String rightIdentificatorsGroup = "";
            for (IdentificatorAndDistance identificatorAndDistance : identificatorsAndDistances) {
                if (identificatorAndDistance.getDistance() == minDistance) {
                    if (!rightIdentificatorsGroup.equals("")) {
                        rightIdentificatorsGroup += ", " + identificatorAndDistance.getIdentificator();
                    } else {
                        rightIdentificatorsGroup = identificatorAndDistance.getIdentificator();
                    }
                }
            }

            ComparedIdentificator comparedIdentificator = new ComparedIdentificator(
                    leftIdentificators.get(i),
                    rightIdentificatorsGroup
            );
            LOG.info("compared identificator ==> " + comparedIdentificator);
            LOG.info("compared identificator distance ==> " + minDistance);
            comparedIdentificators.add(comparedIdentificator);
        }

        LOG.info("compared identificators ==> " + comparedIdentificators);
        return comparedIdentificators;
    }
}