package edu.rpi.project.examdatabase.examdb.HelperFunctions;

import java.util.List;
import java.util.Iterator;

public class WeightAverage implements FuzzyWeight {
    /**
     * This function takes the mean average of the values in the
     *  recursive result, and then averages it with the value of the
     *  top-level result. This merge **should** favour the toplevel similarity.
     *  
     * @param topLevelResult The similarity score [0, 1] of the toplevel
     * @param recursiveResult The similarity score [0, 1] of the each of the split strings
     * @return An averaged similarity score, with more weight going to the toplevel
     */
    public double merge( Double topLevelResult, List<Double> recursiveResult ) {
        /* Accumulate the total similarity in recursive_average */
        Double recursive_average = 0.;
        for( Double d : recursiveResult ) {
            recursive_average += d;
        }

        /* Divide by length of the list to get the average */
        recursive_average /= recursiveResult.size();

        /* Return the average of toplevel and recursive average */
        return ( topLevelResult + recursive_average ) / 2;

    }
}
