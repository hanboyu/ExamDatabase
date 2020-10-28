package edu.rpi.project.examdatabase.examdb;

import edu.rpi.project.examdatabase.examdb.HelperFunctions.Jaccard;
import org.junit.jupiter.api.Test;

public class JaccardTest {

    @Test
    void same_string() {
        String a = "Alexander";
        String b = "Alexander";
        assert( Jaccard.Similarity( a, b, 3 ) == 1 );
    }


    @Test
    void dissimilar_strings() {
        String a = "Alexander";
        String b = "Grrrrrrrr";
        assert( Jaccard.Similarity( a, b,3  ) <= 0.2 );
    }

    @Test
    void hand_calculated_test() {
        String a = "Hello";
        String b = "Jello";
        double ans = Jaccard.Similarity( a, b, 3 ) - ( 0.5 );
        assert( ans < 0.00001 && ans > -0.00001 );
    }

    @Test
    void order_independence_test() {
        String a = "Hello";
        String b = "Jello";
        double ans = Jaccard.Similarity( a, b, 3 ) - Jaccard.Similarity( b, a, 3 );
        assert(  ans < 0.00001 && ans > -0.00001  );
    }

    @Test
    void multiple_strings() {
        String keyword = "Lewis";
        String most = "Dr. Lewis ____";
        String second = "Using a Lewis dot diagram we can analyze the bonding of atoms";
        String least = "Nothing about the above string";

        Double m = Jaccard.Similarity( keyword, most,3  );
        Double s = Jaccard.Similarity( keyword, second,3  );
        Double l = Jaccard.Similarity( keyword, least,3  );

        assert( m > s && s > l );

    }

}
