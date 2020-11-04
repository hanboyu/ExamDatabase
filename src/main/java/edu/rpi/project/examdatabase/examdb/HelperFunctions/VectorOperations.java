package edu.rpi.project.examdatabase.examdb.HelperFunctions;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class VectorOperations {
    /**
     * This function finds the dot product of two vectorized Strings
     * @param vector1 The first vector
     * @param vector2 The second vector
     * @return The dot product
     */
    public static int DotProduct(HashMap<String, Integer> vector1, HashMap<String, Integer> vector2 ) {
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
    public static Double Magnitude(Map< String, Integer > vector) {
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
