package edu.rpi.project.examdatabase.examdb.HelperFunctions;

import edu.rpi.project.examdatabase.examdb.Objects.Question.Question;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Demo {

    public static List<Question> demoQuestions() {
        // Create a list of choices for demonstration purposes
        List<String> demo_choices = new LinkedList<>();
        demo_choices.add( "Choice1" );
        demo_choices.add( "Choice2" );
        demo_choices.add( "Choice3" );
        demo_choices.add( "Choice4" );

        // Create a list of question bodies
        List<String> question_bodies = new ArrayList<>();
        question_bodies.add( "carbon-carbon single bonds are found in:" );
        question_bodies.add( "The lewis dot diagram of carbon is:" );
        question_bodies.add( "Dr. Ma's first name is:" );
        question_bodies.add( "Does oxygen exist naturally as a gas?" );
        question_bodies.add( "Is this chiral center R or S?" );
        question_bodies.add( "Something about carbohydrates" );
        question_bodies.add( "Which molecule is nonpolar" );
        question_bodies.add( "What is the hybridization of chlorine (Cl) in ClF_3"  );
        question_bodies.add( "How many hydrogens are in ethyl butanoate?" );
        question_bodies.add( "What is the IUPAC name for the following compound?" );
        question_bodies.add( "How many isomers are there for trichlorobenzene" );
        question_bodies.add( "What type of linkage is holding the base pairs of the DNA together in order to form the double helical structure" );
        //question_bodies.add( "" );

        // Create an arraylist to return
        List<Question> res = new ArrayList<>();

        for( Integer i = 0; i < question_bodies.size(); ++i ) {
            res.add( new Question( i.toString(), new LinkedList<>(), null,
                    0, question_bodies.get(i), new LinkedList<>(), demo_choices, new LinkedList<>(), "Choice1" ) );
        }

        return res;
    }
}
