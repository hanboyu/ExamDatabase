package edu.rpi.project.examdatabase.examdb.HelperFunctions.StringSimilarity;

import edu.rpi.project.examdatabase.examdb.HelperFunctions.SetOperations;

import java.util.Set;

public class Jaccard implements StringSimilarity {
    private int ngram_size;

    /**
     * This consructor fixes the size of n-grams to be used
     *  in this instance of JaccardSimilarity
     * @param n The size of n-grams to be used
     */
    public Jaccard( int n ) {
        ngram_size = n;
    }

    /**
     * This function finds degree to which two strings are similar
     *  on a scale from [0, 1] with 1 being identical and 0 being dissimilar
     * @param str1 First string to be split into n-grams
     * @param str2 Second string to be split into n-grams
     * @return The number of matching n-grams between the strings
     */
    public Double Similarity( String str1, String str2 ) {
        return JaccardSimilarity( Ngram.NgramSet( Ngram.ngram( str1, ngram_size ) ),
                Ngram.NgramSet( Ngram.ngram( str2, ngram_size )  ));
    }

    /**
     * This function finds the Jaccard distance between two sets of Strings
     *  where the definiton of Jaccard distance is as follows
     *    | Intersection | / | Union |
     * This method will not work as expected if both sets are non-empty
     * @param set1 Set of Strings
     * @param set2 Set of Strings
     * @return | Intersection | / | Union |
     */
    public static Double JaccardSimilarity(Set<String> set1, Set<String> set2 ) {
        return SetOperations.IntersectionSize( set1, set2 ).doubleValue() / SetOperations.UnionSize( set1, set2 ).doubleValue();
    }
}
