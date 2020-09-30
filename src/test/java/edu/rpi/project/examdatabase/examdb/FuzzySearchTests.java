package edu.rpi.project.examdatabase.examdb;

import edu.rpi.project.examdatabase.examdb.HelperFunctions.FuzzySearch;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FuzzySearchTests {

    @Test
    void EditDistanceTest() {
        // One edit
        String s1 = "Test";
        String s2 = "Tist";
        assert( FuzzySearch.editDistance( s1, s2 ) == 1 );

        // Empty string & non-empty string
        String empty = "";
        String non_empty = "Build";
        assert( FuzzySearch.editDistance( empty, non_empty ) == 5 );

        // Small string & big string
        String small = "hut";
        String big = "doughnut";
        assert( FuzzySearch.editDistance( small, big ) == 5 );
    }
}
