package edu.rpi.project.examdatabase.examdb.DataContainers.Database.dbaccess;

import edu.rpi.project.examdatabase.examdb.Objects.QueryObject;
import edu.rpi.project.examdatabase.examdb.Objects.Question.Question;
import edu.rpi.project.examdatabase.examdb.Objects.Question.QuestionFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class QueryQuestionFromDatabase<E> implements Query<E> {
    @Override
    public List<E> doQuery(QueryParameters param) {
        List<E> questions = new ArrayList<>();
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "Xuran123#");
            Statement statement = con.createStatement();
            String query = param.createQueryString();
            ResultSet rs = statement.executeQuery(query);
            QuestionFactory qf = new QuestionFactory();
            while (rs.next()) {
                int id = rs.getInt("ID");
                String serialCode = rs.getString("SERIAL_CODE");
                String tags = rs.getString("TAGS");
                List<String> tagsList = this.split(tags);
                String classID = rs.getString("CLASS_ID");
                int permission = rs.getInt("PERMISSION");
                String questionBody = rs.getString("QUESTION_BODY");
                String choiceA = rs.getString("CHOICE_A");
                String choiceB = rs.getString("CHOICE_B");
                String choiceC = rs.getString("CHOICE_C");
                String choiceD = rs.getString("CHOICE_D");
                String choiceE = rs.getString("CHOICE_E");
                String choiceAImg = rs.getString("CHOICE_A_IMAGE");
                String choiceBImg = rs.getString("CHOICE_B_IMAGE");
                String choiceCImg = rs.getString("CHOICE_C_IMAGE");
                String choiceDImg = rs.getString("CHOICE_D_IMAGE");
                String choiceEImg = rs.getString("CHOICE_E_IMAGE");
                String answer = rs.getString("ANSWER");
                List<String> questionImages = new ArrayList<>();
                List<String> choiceImages = new ArrayList<>();
                questionImages.add(choiceAImg);
                questionImages.add(choiceBImg);
                questionImages.add(choiceCImg);
                questionImages.add(choiceDImg);
                questionImages.add(choiceEImg);
                List<String> choices = new ArrayList<>();
                choices.add(choiceA);
                choices.add(choiceB);
                choices.add(choiceC);
                choices.add(choiceD);
                choices.add(choiceE);
                Question q = qf.makeQuestion(serialCode, tagsList, classID, permission, questionBody, questionImages,
                   choices, choiceImages, answer);
                questions.add((E)q);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return questions;
    }

    private List<String> split(String str) {
        return new ArrayList<>(Arrays.asList(str.split(",", -1)));
    }
}
