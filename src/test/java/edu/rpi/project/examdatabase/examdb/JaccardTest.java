package edu.rpi.project.examdatabase.examdb;

import edu.rpi.project.examdatabase.examdb.HelperFunctions.StringSimilarity.Jaccard;
import org.junit.jupiter.api.Test;

public class JaccardTest {
    Jaccard j = new Jaccard(3);

    @Test
    void same_string() {
        String a = "Alexander";
        String b = "Alexander";
        assert( j.Similarity( a, b ) == 1 );
    }


    @Test
    void dissimilar_strings() {
        String a = "Alexander";
        String b = "Grrrrrrrr";
        assert( j.Similarity( a, b ) <= 0.2 );
    }

    @Test
    void hand_calculated_test() {
        String a = "Hello";
        String b = "Jello";
        double ans = j.Similarity( a, b ) - ( 0.5 );
        assert( ans < 0.00001 && ans > -0.00001 );
    }

    @Test
    void order_independence_test() {
        String a = "Hello";
        String b = "Jello";
        double ans = j.Similarity( a, b ) - j.Similarity( b, a );
        assert(  ans < 0.00001 && ans > -0.00001  );
    }

    @Test
    void multiple_strings() {
        String keyword = "Lewis";
        String most = "Dr. Lewis ____";
        String second = "Using a Lewis dot diagram we can analyze the bonding of atoms";
        String least = "Nothing about the above string";

        Double m = j.Similarity( keyword, most );
        Double s = j.Similarity( keyword, second );
        Double l = j.Similarity( keyword, least );

        assert( m > s && s > l );

    }

}
