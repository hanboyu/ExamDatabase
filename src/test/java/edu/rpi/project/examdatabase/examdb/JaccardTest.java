package edu.rpi.project.examdatabase.examdb;

import edu.rpi.project.examdatabase.examdb.HelperFunctions.StringDistanceFunctions;
import org.junit.jupiter.api.Test;

public class JaccardTest {

    @Test
    void typo_test() {
        String hello = "hello";
        String typo = "jello";
        assert(StringDistanceFunctions.JaccardDistance(hello,typo, 3) == 2);
    }

    @Test
    void duplicate_ngram_test() {
        String a = "Anastasia";
        String b = "Anastassia";
        assert( StringDistanceFunctions.JaccardDistance( a, b, 3 ) == 9 );
    }
}