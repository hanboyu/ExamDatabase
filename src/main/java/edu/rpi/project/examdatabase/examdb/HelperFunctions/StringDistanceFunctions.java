package edu.rpi.project.examdatabase.examdb.HelperFunctions;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StringDistanceFunctions {


    /**
     * This function ignores duplicates because sets do not allow duplicate elements.
     *  If the desired effect is to factor duplication into subsiquent calculations
     *  use NgramMap().
     * @param ngrams The list of ngrams from which to construct the set
     * @return A set of
     */
    public static HashSet<String> NgramSet( List<String> ngrams ) {
        /* Create a HashSet using constructor */
        return new HashSet<>( ngrams );
    }

    /**
     * This function keeps track of duplicates using the value of each pair in
     *  the map
     * @param ngrams The list of ngrams from which to construct the map
     * @return A map of ngrams s.t. the value is the number of occurrences in the list
     */
    public static HashMap<String, Integer> NgramMap( List<String> ngrams ) {
        /* Create a HashMap from the first list of n-grams for fast comparison */
        HashMap<String, Integer> ngram_map = new HashMap<>();

        /* Insert all n-grams from the first list into the HashMap s.t.
            value = # of duplicates */
        for( String str : ngrams ) {
            /* putIfAbsent returns NULL if insertion is successful
                ( the key wasn't already in the map ) */
            if( ngram_map.putIfAbsent( str, 1 ) != null ) {
                // If the string is a duplicate, add 1 to the value
                ngram_map.replace( str, ngram_map.get(str) + 1 );
            }
        }

        return ngram_map;
    }

    /**
     * This function finds the number of matching n-grams between
     * two sets of n-grams
     * @param ngram_map Map of ngrams from the query/keyword
     * @param comparison_list Set of n-grams from comparison string
     * @return The number of n-grams matched
     */
    public static Integer CommonNgrams( HashMap<String, Integer> ngram_map, List<String> comparison_list ) {
        /* Calculate the total number of exactly matching n-grams in the second list */
        int total = 0;
        for( String str : comparison_list ) {
            if( ngram_map.containsKey( str ) ) {
                total += ngram_map.get(str);
            }
        }

        return total;
    }


    /**
     * This function calculates the number of edits need to transform one
     *  string into another. This provides a measurement of similarity between
     *  the two strings. This is just the driver function.
     * @param s1 The first string to compare
     * @param s2 The second string to compare
     * @return The number of edits (insert, delete, replace) needed to
     *  transform s1 into s2
     */
    public static int editDistance( String s1, String s2 ) {
        return editDistanceDeep( s1, s2, s1.length(), s2.length() );
    }

    /**
     *
     * @param s1 The first string to compare
     * @param s2 The second string to compare
     * @param s1_size The upper bound of s1
     * @param s2_size The upper bound of s2
     * @return The number of edits (insert, delete, replace) needed to
     *  transform s1[0 to s1_size - 1] into s2[0 to s2_size - 1]
     */
    private static int editDistanceDeep( String s1, String s2, int s1_size, int s2_size ) {
        // If first string is empty, the answer will necessarily
        // be the size of the second string
        if (s1_size == 0)
            return s2_size;

        // If second string is empty, the answer will necessarily
        // be the size of the first string
        if (s2_size == 0)
            return s1.length();

        // If the last characters are the same, recurse without the
        // last character of both strings
        if ( s1.charAt(s1_size - 1) == s2.charAt(s2_size - 1) )
            return editDistanceDeep(s1, s2, s1_size - 1, s2_size - 1);

        // If last characters are not same, we must make an edit.
        // Consider all 3 cases (insert, delete, replace)
        return 1 + min3( editDistanceDeep(s1, s2, s1_size, s2_size - 1),    // Insert
                editDistanceDeep(s1, s2, s1_size - 1, s2_size),             // Remove
                editDistanceDeep(s1, s2, s1_size - 1, s2_size - 1)   // Replace
        );
    }

    /**
     * This function takes finds the minimum of 3 integers
     * @param i1 The first integer
     * @param i2 The second integer
     * @param i3 The third integer
     * @return The least of the three arguments
     */
    private static int min3( Integer i1, Integer i2, Integer i3 ) {
        return Math.min( i1, Math.min( i2, i3 ) );
    }
}
