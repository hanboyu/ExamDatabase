package edu.rpi.project.examdatabase.examdb.HelperFunctions.OldClassesCanDelete;

import java.util.List;

/**
 * FuzzyWeight is an interface that defines the necessary
 *  functions to weight the results of a divide and conquer
 *  fuzzy search
 */
public interface FuzzyWeight {
    double merge( Double topLevelResult, List<Double> recursiveResult );
}
