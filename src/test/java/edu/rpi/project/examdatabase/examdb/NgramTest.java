package edu.rpi.project.examdatabase.examdb;

import edu.rpi.project.examdatabase.examdb.HelperFunctions.StringSimilarity.Ngram;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class NgramTest {
    @Test
    void smallString() {
        List<String> res = Ngram.ngram( "Major", 3 );
        assert( res.equals( Arrays.asList( "Maj", "ajo", "jor" ) ) );
    }

    @Test
    void NequalsString() {
        List<String> res = Ngram.ngram( "Major", 5 );
        assert( res.equals( Arrays.asList( "Major" ) ) );
    }

    @Test
    void emptyList() {
        List<String> res = Ngram.ngram( "Major", 999 );
        assert( res.size() == 0 );
    }
}
