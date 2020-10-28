package edu.rpi.project.examdatabase.examdb.HelperFunctions.StringSimilarity;

import edu.rpi.project.examdatabase.examdb.HelperFunctions.StringDistanceFunctions;
import edu.rpi.project.examdatabase.examdb.HelperFunctions.VectorOperations;

import java.util.HashMap;

public class Cosine {
    /**
     * This function finds degree to which two strings are similar
     *  on a scale from [0, 1] with 1 being identical and 0 being dissimilar.
     *  This is done by vectorizing the strings & finding cosine of the angle
     *  between them.
     * @param str1 First string to be compared
     * @param str2 Second string to be compared
     * @param n The size of n-grams to be created
     * @return The number of matching n-grams between the strings
     */
    public static Double Similarity( String str1, String str2, Integer n ) {
        /* Get the n-gram lists, and convert them to vectors/maps */
        HashMap<String, Integer> vector1 = StringDistanceFunctions.NgramMap(str1, n);
        HashMap<String, Integer> vector2 = StringDistanceFunctions.NgramMap(str2, n);

        /* Find the dot product */
        int dot_product = VectorOperations.DotProduct( vector1, vector2 );

        /* Calculate the magnitude of each of the vectors */
        Double magnitude1 = VectorOperations.Magnitude( vector1 );
        Double magnitude2 = VectorOperations.Magnitude( vector2 );

        /* Return the dot product / ( mag * mag ) */
        return dot_product / ( magnitude1 * magnitude2 );
    }
}
