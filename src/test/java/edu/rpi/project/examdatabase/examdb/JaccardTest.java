package edu.rpi.project.examdatabase.examdb;

import edu.rpi.project.examdatabase.examdb.HelperFunctions.Jaccard;
import edu.rpi.project.examdatabase.examdb.HelperFunctions.StringDistanceFunctions;
import org.junit.jupiter.api.Test;

public class JaccardTest {

    @Test
    void same_string() {
        String a = "Alexander";
        String b = "Alexander";
        assert( Jaccard.Distance( a, b, 3 ) == 1 );
    }


    @Test
    void dissimilar_strings() {
        String a = "Alexander";
        String b = "Grrrrrrrr";
        assert( Jaccard.Distance( a, b,3  ) <= 0.2 );
    }

    @Test
    void multiple_strings() {
        String keyword = "Lewis";
        String most = "Dr. Lewis ____";
        String second = "Using a Lewis dot diagram we can analyze the bonding of atoms";
        String least = "Nothing about the above string";

        Double m = Jaccard.Distance( keyword, most,3  );
        Double s = Jaccard.Distance( keyword, second,3  );
        Double l = Jaccard.Distance( keyword, least,3  );

        assert( m > s && s > l );

    }

}
