package edu.rpi.project.examdatabase.examdb.HelperFunctions;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class StringDistanceFunctions {

    /**
     * This function finds the matching n-grams between
     *  two strings
     * @param str1 First string to be split into n-grams
     * @param str2 Second string to be split into n-grams
     * @return The number of matching n-grams between the strings
     */
    public static Integer JaccardDriver( String str1, String str2, Integer n ) {
        return JaccardDistance( StringHelperFunctions.ngram( str1, 3 ),
                                StringHelperFunctions.ngram( str2, 3 ) );
    }

    /**
     * This function finds the number of matching n-grams between
     * two sets of n-grams
     * @param ngrams1 Set of n-grams from first string
     * @param ngrams2 Set of n-grams from second string
     * @return The number of n-grams matched
     */
    public static Integer JaccardDistance( List<String> ngrams1, List<String> ngrams2 ) {

        /* Create a HashMap from the first list of n-grams for fast comparison */
        HashMap<String, Integer> comparison_map = new HashMap<>();

        /* Insert all n-grams from the first list into the HashMap s.t.
            value = # of duplicates */
        for( String str : ngrams1 ) {
            /* putIfAbsent returns NULL if insertion is successful
                ( the key wasn't already in the map ) */
            if( comparison_map.putIfAbsent( str, 1 ) != null ) {
                // If the string is a duplicate, add 1 to the value
                comparison_map.replace( str, comparison_map.get(str) + 1 );
            }
        }

        /* Calculate the total number of exactly matching n-grams in the second list */
        int total = 0;
        for( String str : ngrams2 ) {
            if( comparison_map.containsKey( str ) ) {
                total += comparison_map.get(str);
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
