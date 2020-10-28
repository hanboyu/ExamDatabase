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
}
