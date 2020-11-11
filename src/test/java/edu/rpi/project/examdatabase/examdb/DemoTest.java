package edu.rpi.project.examdatabase.examdb;

import edu.rpi.project.examdatabase.examdb.Objects.Question.Question;
import edu.rpi.project.examdatabase.examdb.Objects.User.Student;
import edu.rpi.project.examdatabase.examdb.Services.ReadQuestionService;
import org.junit.jupiter.api.Test;

import java.util.List;

public class DemoTest {
    @Test
    void demo() {
        String keyword = "carbon";
        List<Question> res = ReadQuestionService.GetQuestionsByKeyWord(
                new Student( null, null, null, null, null ), keyword );
        for( Question q : res ) {
            System.out.println( q.getQuestionBody() );
        }
    }
}
