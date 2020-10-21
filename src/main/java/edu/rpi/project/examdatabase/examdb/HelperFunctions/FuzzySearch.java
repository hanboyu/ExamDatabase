package edu.rpi.project.examdatabase.examdb.HelperFunctions;

import edu.rpi.project.examdatabase.examdb.Objects.Question.Question;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * StringComparison will contain all methods necessary to compare
 *  a single String with a Question and convey a degree of similarity
 *  between the two.
 */
public class FuzzySearch {

    /**
     * This function finds the Jaccard distance between two strings. It runs
     *  in O(n^2) time, not sure if this can be improved?
     * @param str1 First string
     * @param str2 Second string
     * @param n Size of n-grams to be used
     * @return The number of n-grams matched
     */
    public static Integer JaccardDistance( String str1, String str2, Integer n ) {
        List<String> str1_ngrams = StringHelperFunctions.ngram( str1, n );
        List<String> str2_ngrams = StringHelperFunctions.ngram( str2, n );
        
        /* Create a HashSet from the first list of n-grams for fast comparison */
        HashSet<String> comparison_set = new HashSet<>( str1_ngrams );
        
        /* Calculate the total number of exactly matching n-grams in the second list */
        Iterator<String> itr = str2_ngrams.iterator();
        int total = 0;
        while( itr.hasNext() ) {
            if( comparison_set.contains( itr.next() ) ) {
                ++total;
            }
        }
        
        return total;
    }

    /**
     * This function calculates the degree of similarity between the
     *  input keyword and a given Question object
     *
     * @param keyword The string to compare with given question
     * @param text The text to compare keyword with
     * @return A value between 0 and 1 inclusive which indicates the degree
     *  of similarity of the arguments, with 1 being the most similar
     */
    public static double similarity( String keyword, String text, FuzzyWeight weight ) {
        // Create a variable to hold the minimum edit distance in any window
        int min_edit_distance = Integer.MAX_VALUE;

        // Fetch the length of the strings so we don't have to do it multiple times
        int keyword_len = keyword.length();
        int question_len = text.length();
        for( int i = 0; i + keyword_len < question_len; ++i ) {
            min_edit_distance = Math.min( min_edit_distance,
                    editDistance( keyword, text.substring( i, i + keyword_len ) ) );
        }

        // Normalize by the length of the keyword, this is toplevel result
        Double topLevelResult = 1 - ( (double)min_edit_distance / (double)keyword.length() );

        // Split the keyword
        List<String> split = StringHelperFunctions.split( keyword, ',', ' ' );
        Iterator<String> itr = split.iterator();
        // Create a List to store each of the result of comparison
        List<Double> results = new LinkedList<>();
        // Compare to each split string
        while( itr.hasNext() ) {
            results.add(similarity(itr.next(), text, weight));
        }
        // Return the value dictated by the weight object
        return weight.merge( topLevelResult, results );
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
