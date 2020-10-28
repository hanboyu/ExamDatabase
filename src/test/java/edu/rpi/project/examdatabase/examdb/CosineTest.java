package edu.rpi.project.examdatabase.examdb;

import edu.rpi.project.examdatabase.examdb.HelperFunctions.Cosine;
import edu.rpi.project.examdatabase.examdb.HelperFunctions.Jaccard;
import org.junit.jupiter.api.Test;

public class CosineTest {

    @Test
    void same_string() {
        String a = "Alexander";
        String b = "Alexander";
        /* Double equality is weird */
        assert( 1 - Cosine.Distance( a, b, 3 ) < 0.0001 );
    }


    @Test
    void dissimilar_strings() {
        String a = "Alexander";
        String b = "Grrrrrrrr";
        assert( Cosine.Distance( a, b,3  ) <= 0.2 );
    }

    @Test
    void hand_calculated_test() {
        String a = "Hello";
        String b = "Jello";
        double ans = Cosine.Distance( a, b, 3 ) - (2.0 / 3.0);
        assert( ans < 0.00001 && ans > -0.00001 );
    }

    @Test
    void order_independence_test() {
        String a = "Hello";
        String b = "Jello";
        double ans = Cosine.Distance( a, b, 3 ) - Cosine.Distance( b, a, 3 );
        assert(  ans < 0.00001 && ans > -0.00001  );
    }


    @Test
    void multiple_strings() {
        String keyword = "Lewis";
        String most = "Dr. Lewis ____";
        String second = "Using a Lewis dot diagram we can analyze the bonding of atoms";
        String least = "Nothing about the above string";

        Double m = Cosine.Distance( keyword, most,3  );
        Double s = Cosine.Distance( keyword, second,3  );
        Double l = Cosine.Distance( keyword, least,3  );

        assert( m > s && s > l );

    }
}
