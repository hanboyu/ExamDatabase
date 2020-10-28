package edu.rpi.project.examdatabase.examdb.HelperFunctions;

import java.util.Set;

public class Jaccard {
    /**
     * This function finds degree to which two strings are similar
     *  on a scale from [0, 1] with 1 being identical and 0 being dissimilar
     * @param str1 First string to be split into n-grams
     * @param str2 Second string to be split into n-grams
     * @param n The size of n-grams to be created
     * @return The number of matching n-grams between the strings
     */
    public static Double Distance( String str1, String str2, Integer n ) {
        return JaccardDistance( StringDistanceFunctions.NgramSet( StringHelperFunctions.ngram( str1, n ) ),
                StringDistanceFunctions.NgramSet( StringHelperFunctions.ngram( str2, n )  ));
    }

    /**
     * This function finds the Jaccard distance between two sets of Strings
     *  where the definiton of Jaccard distance is as follows
     *    | Intersection | / | Union |
     * @require At least one set is non-empty
     * @param set1 Set of Strings
     * @param set2 Set of Strings
     * @return | Intersection | / | Union |
     */
    public static Double JaccardDistance(Set<String> set1, Set<String> set2 ) {
        return SetOperations.IntersectionSize( set1, set2 ).doubleValue() / SetOperations.UnionSize( set1, set2 ).doubleValue();
    }
}
