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



}
