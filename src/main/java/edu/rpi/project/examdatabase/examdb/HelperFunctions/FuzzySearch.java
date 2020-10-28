package edu.rpi.project.examdatabase.examdb.HelperFunctions;

import edu.rpi.project.examdatabase.examdb.HelperFunctions.StringSimilarity.EditDistance;

import java.util.LinkedList;
import java.util.List;

/**
 * StringComparison will contain all methods necessary to compare
 *  a single String with a Question and convey a degree of similarity
 *  between the two.
 */
public class FuzzySearch {

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
                    EditDistance.editDistance( keyword, text.substring( i, i + keyword_len ) ) );
        }

        // Normalize by the length of the keyword, this is toplevel result
        Double topLevelResult = 1 - ( (double)min_edit_distance / (double)keyword.length() );

        // Split the keyword
        List<String> split = StringHelperFunctions.split( keyword, ',', ' ' );

        /* If the split list only contains 1 String, then do not recurse, we were unable to split */
        if( split.size() == 1 ) {
            return topLevelResult;
        }

        // Create a List to store each of the result of comparison
        List<Double> results = new LinkedList<>();
        // Compare to each split string
        for( String str : split ) {
            results.add( similarity( str, text, weight ) );
        }
        // Return the value dictated by the weight object
        return weight.merge( topLevelResult, results );
    }

}
