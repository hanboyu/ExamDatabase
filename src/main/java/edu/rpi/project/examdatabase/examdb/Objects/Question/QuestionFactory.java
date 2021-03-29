package edu.rpi.project.examdatabase.examdb.Objects.Question;

import edu.rpi.project.examdatabase.examdb.Exceptions.UnknownPermissionTypeException;

import java.util.List;

public class QuestionFactory {

    public Question makeQuestion(String serial_code, List<String> tags, String class_code,
                                 int permission, String question_body, List<String> question_image_addr,
                                 List<String> choices, List<String> choice_image_addr, String answer) {
        //trim white space and make all tags to lower case
        for (int i = 0; i < tags.size(); ++i) {
            String temp = tags.get(i);
            temp = temp.trim();
            temp = temp.toLowerCase();
            tags.set(i, temp);
        }

        question_body = question_body.trim();

        for (int i = 0; i < choices.size(); ++i) {
            String temp = choices.get(i);
            temp = temp.trim();
            choices.set(i, temp);
        }

        answer = answer.trim();

        return new Question(serial_code, tags, class_code, permission,
                question_body, question_image_addr, choices, choice_image_addr, answer);
    }

    public QuestionLite makeQuestionLite(String serial_code, List<String> tags, String question_body){
        //trim white space and make all tags to lower case
        for (int i = 0; i < tags.size(); ++i) {
            String temp = tags.get(i);
            temp = temp.trim();
            temp = temp.toLowerCase();
            tags.set(i, temp);
        }
        question_body = question_body.trim();

        return new QuestionLite(serial_code, tags, question_body);
    }

}
