package edu.rpi.project.examdatabase.examdb;

import edu.rpi.project.examdatabase.examdb.HelperFunctions.StringHelperFunctions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

//@SpringBootTest
public class SplitStringTest {

    @Test
    void SplitWithOneDelimiter()
    {
        String test_str = "Students will work with existing grade data to identify trends and effectiveness of " +
                "curriculum changes within Freshman Chemistry.";
        List<String> res = StringHelperFunctions.split(test_str, ' ');
        assert(res.equals(Arrays.asList("Students", "will", "work", "with", "existing", "grade", "data", "to",
                "identify", "trends", "and", "effectiveness", "of", "curriculum", "changes", "within", "Freshman",
                "Chemistry.")));
    }

    @Test
    void SplitWithMultipleDelimiters()
    {
        String test_str = "1+2+3=6";
        List<String> res = StringHelperFunctions.split(test_str, '=', '+');
        assert(res.equals(Arrays.asList("1", "2", "3", "6")));
    }

    @Test
    void SplitWithDelimitersAtFrontAndBack()
    {
        String test_str = "+abc;def;ghi-";
        List<String> res = StringHelperFunctions.split(test_str,  '+', '-', ';');
        assert(res.equals(Arrays.asList("abc", "def", "ghi")));
    }

    @Test
    void SplitWithMultipleDelimitersNextToEachOther()
    {
        String test_str = "+;abc;;def;;ghi-;";
        List<String> res = StringHelperFunctions.split(test_str,  '+', '-', ';');
        assert(res.equals(Arrays.asList("abc", "def", "ghi")));
    }
}
