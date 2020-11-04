package edu.rpi.project.examdatabase.examdb;

import edu.rpi.project.examdatabase.examdb.HelperFunctions.StringSimilarity.Cosine;
import org.junit.jupiter.api.Test;

public class CosineTest {
    Cosine c = new Cosine(3);

    @Test
    void same_string() {
        String a = "Alexander";
        String b = "Alexander";
        /* Double equality is weird */
        assert( 1 - c.Similarity( a, b ) < 0.0001 );
    }


    @Test
    void dissimilar_strings() {
        String a = "Alexander";
        String b = "Grrrrrrrr";
        assert( c.Similarity( a, b ) <= 0.2 );
    }

    @Test
    void hand_calculated_test() {
        String a = "Hello";
        String b = "Jello";
        double ans = c.Similarity( a, b ) - (2.0 / 3.0);
        assert( ans < 0.00001 && ans > -0.00001 );
    }

    @Test
    void order_independence_test() {
        String a = "Hello";
        String b = "Jello";
        double ans = c.Similarity( a, b ) - c.Similarity( b, a );
        assert(  ans < 0.00001 && ans > -0.00001  );
    }


    @Test
    void multiple_strings() {
        String keyword = "Lewis";
        String most = "Dr. Lewis ____";
        String second = "Using a Lewis dot diagram we can analyze the bonding of atoms";
        String least = "Nothing about the above string";

        Double m = c.Similarity( keyword, most );
        Double s = c.Similarity( keyword, second );
        Double l = c.Similarity( keyword, least );

        assert( m > s && s > l );

    }
}
