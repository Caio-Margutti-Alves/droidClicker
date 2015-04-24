package edu.auburn.eng.csse.comp3710.cma0036.droidclicker.models.quiz;

import java.util.ArrayList;

/**
 * Created by caioa_000 on 23/04/2015.
 */
public class Question {

    private String questionText;
    private ArrayList<Object> extra;
    private ArrayList<Question> alternatives;

    public Question() {
    }

    public Question(String questionText, ArrayList<Object> extra, ArrayList<Question> alternatives) {
        this.questionText = questionText;
        this.extra = extra;
        this.alternatives = alternatives;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public ArrayList<Object> getExtra() {
        return extra;
    }

    public void setExtra(ArrayList<Object> extra) {
        this.extra = extra;
    }

    public ArrayList<Question> getAlternatives() {
        return alternatives;
    }

    public void setAlternatives(ArrayList<Question> alternatives) {
        this.alternatives = alternatives;
    }
}