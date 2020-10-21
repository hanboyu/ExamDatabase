package edu.rpi.project.examdatabase.examdb;

import edu.rpi.project.examdatabase.examdb.HelperFunctions.FuzzyQuestionWrapper;
import edu.rpi.project.examdatabase.examdb.HelperFunctions.FuzzySearch;
import edu.rpi.project.examdatabase.examdb.HelperFunctions.StringDistanceFunctions;
import edu.rpi.project.examdatabase.examdb.HelperFunctions.WeightAverage;
import edu.rpi.project.examdatabase.examdb.Objects.Question.Question;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.LinkedList;

@SpringBootTest
public class FuzzySearchTests {

    @Test
    void EditDistanceTest() {
        // One edit
        String s1 = "Test";
        String s2 = "Tist";
        assert( StringDistanceFunctions.editDistance( s1, s2 ) == 1 );

        // Empty string & non-empty string
        String empty = "";
        String non_empty = "Build";
        assert( StringDistanceFunctions.editDistance( empty, non_empty ) == 5 );

        // Small string & big string
        String small = "hut";
        String big = "doughnut";
        assert( StringDistanceFunctions.editDistance( small, big ) == 5 );
    }

    @Test
    void NonSpittableTest() {
        String keyword = "Yankees";
        String text = "The New York Yankees are the best baseball team";
        double res = FuzzySearch.similarity( keyword, text, new WeightAverage() );
        assert( 0 <= res && res <= 1 );
    }

    @Test
    void SplittableTest() {

    }

    @Test
    void SimilarityTest() {
        // Create a single keyword
        String keyword = "Columbus day";
        // Create a 3 questions to compare a keyword to
        Question q1 = new Question( null, new LinkedList<>(), null,
                0, "The holiday Columbus day is in October", new LinkedList<>(), null );
        Question q2 = new Question( null, new LinkedList<>(), null,
        0, "In 1492 Columbus sailed the ocean blue", new LinkedList<>(), null );
        Question q3 = new Question( null, new LinkedList<>(), null,
                0,"Henry Hudson sailed New York", new LinkedList<>(), null );

        double s1, s2, s3;
        s1 = FuzzyQuestionWrapper.similarity( keyword, q1 );
        s2 = FuzzyQuestionWrapper.similarity( keyword, q2 );
        s3 = FuzzyQuestionWrapper.similarity( keyword, q3 );

        assert( s1 > s2 && s2 > s3 ); // Make sure the questions are ordered most to least similar
        assert( 0 <= s1 && s1 <= 1 && 0 <= s2 && s2 <= 1 && 0 <= s3 && s3 <= 1 );
    }
}
