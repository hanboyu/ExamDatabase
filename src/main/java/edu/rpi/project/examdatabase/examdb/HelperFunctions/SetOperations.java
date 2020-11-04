package edu.rpi.project.examdatabase.examdb.HelperFunctions;

import java.util.HashSet;
import java.util.Set;

public class SetOperations {
    /**
     * @param set1 Set of strings
     * @param set2 Set of strings
     * @return The number of elements common to both sets
     */
    public static Integer IntersectionSize(Set<String> set1, Set<String> set2 ) {
        int common_elements = 0;
        for( String str : set1 ) {
            if( set2.contains( str ) ) {
                ++common_elements;
            }
        }
        return common_elements;
    }

    /**
     * This function does not affect either of the arguments
     * @param set1 Set of strings
     * @param set2 Set of strings
     * @return The number of elements common to both sets
     */
    public static Integer UnionSize( Set<String> set1, Set<String> set2 ) {
        /* Make a deep copy of the first set */
        HashSet<String> copy = new HashSet<>( set1 );

        /* Add all elements of set2 to the copy, then return the size */
        copy.addAll( set2 );
        return copy.size();
    }

    /**
     * This function constructs the union of two sets. It does not
     *  affect either of the input sets
     * @param set1 The first set
     * @param set2 The second set
     * @param <E> Any Object
     * @return The union of set1 and set2
     */
    public static <E> Set<E> Union( Set<E> set1, Set<E> set2 ) {
        /* Construct a new (empty) set */
        HashSet<E> res = new HashSet<>();

        /* Add the contents of boths sets and return */
        res.addAll( set1 );
        res.addAll( set2 );

        return res;
    }
}
