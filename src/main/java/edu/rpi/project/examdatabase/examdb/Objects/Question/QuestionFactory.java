package edu.rpi.project.examdatabase.examdb.Objects.Question;

import edu.rpi.project.examdatabase.examdb.Exceptions.UnknownPermissionTypeException;

import java.util.List;

public class QuestionFactory {

    private static final QuestionFactory instance = new QuestionFactory();
    private long serial_number;

    //Constructor
    private QuestionFactory() {
        serial_number = 0;
    }

    public static QuestionFactory getInstance() {
        return instance;
    }

    public Question makeQuestion(List<String> tags, String class_code,
                                 String permission, String question_body,
                                 List<String> choices, String answer)
            throws UnknownPermissionTypeException {
        permission = permission.trim();
        permission = permission.toLowerCase();
        if (!(permission.equals("student") || permission.equals("ta") ||
                permission.equals("instructor") ||
                permission.equals("visitor"))) {
            throw new UnknownPermissionTypeException();
        }

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
        String serial_code = generateSerialCode();

        return new Question(serial_code, tags, class_code, permission,
                question_body, choices, answer);
    }

    private String generateSerialCode() {
        String serialCode = Long.toHexString(serial_number++);
        String prv = "";
        for (int i = 0; i < 8 - serialCode.length(); ++i) {
            prv = prv.concat("0");
        }

        return prv.concat(serialCode);
    }

}
