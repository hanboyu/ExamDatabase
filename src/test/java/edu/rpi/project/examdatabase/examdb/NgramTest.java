package edu.rpi.project.examdatabase.examdb;

import edu.rpi.project.examdatabase.examdb.HelperFunctions.StringHelperFunctions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

public class NgramTest {
    @Test
    void largeN() {

    }

    @Test
    void smallN() {

    }

    @Test
    void smallString() {
        List<String> res = StringHelperFunctions.ngram( "Major", 3 );
        assert( res.equals( Arrays.asList( "Maj", "ajo", "jor" ) ) );
    }

    @Test
    void largeString() {

    }

    @Test
    void NequalsString() {
        List<String> res = StringHelperFunctions.ngram( "Major", 5 );
        assert( res.equals( Arrays.asList( "Major" ) ) );
    }
}
