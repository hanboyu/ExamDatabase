package edu.rpi.project.examdatabase.examdb.HelperFunctions;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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
    public static Double Distance( String str1, String str2, Integer n ) {
        /* Get the n-gram lists, and convert them to vectors/maps */
        HashMap<String, Integer> vector1 = StringDistanceFunctions.NgramMap(str1, n);
        HashMap<String, Integer> vector2 = StringDistanceFunctions.NgramMap(str2, n);

        /* Find the dot product */
        int dot_product = DotProduct( vector1, vector2 );

        /* Calculate the magnitude of each of the vectors */
        Double magnitude1 = Magnitude( vector1 );
        Double magnitude2 = Magnitude( vector2 );

        /* Return the dot product / ( mag * mag ) */
        return dot_product / ( magnitude1 * magnitude2 );
    }

    /**
     * This function finds the dot product of two vectorized Strings
     * @param vector1 The first vector
     * @param vector2 The second vector
     * @return The dot product
     */
    private static int DotProduct( HashMap<String, Integer> vector1, HashMap<String, Integer> vector2 ) {
        /* Find the combined set of n-grams */
        Set<String> union = SetOperations.Union( vector1.keySet(), vector2.keySet() );

        /* Calculate the dot product of the two vectors */
        int dot_product = 0;
        for( String str : union ) {
            dot_product += vector1.getOrDefault( str, 0 ) * vector2.getOrDefault( str, 0 );
        }

        return dot_product;
    }

    /**
     * This function finds the magnitude of a vector
     * @param vector The input vector
     * @return The magnitude of the vector
     */
    private static Double Magnitude(Map< String, Integer > vector) {
        /* Get the value/components of the vector */
        Collection<Integer> value_set = vector.values();

        /* Sum the squares of each entry in the value set */
        double squares = 0.;
        for( Integer i : value_set ) {
            squares += Math.pow( i, 2 );
        }

        /* Return the square root of the squares */
        return Math.sqrt( squares );
    }
}
