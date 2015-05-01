package edu.auburn.eng.csse.comp3710.cma0036.droidclicker.models.quiz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;

import edu.auburn.eng.csse.comp3710.cma0036.droidclicker.models.questions.JsonQuestion;
import edu.auburn.eng.csse.comp3710.cma0036.droidclicker.models.questions.Question;

public class Quiz implements Serializable {

    private String id;
    private ArrayList<Question> questions;
    private String duration;
    private String owner;

    public Quiz() {
    }

    public Quiz(ArrayList<Question> questions, String duration, String owner) {
        this.questions = questions;
        this.duration = duration;
        this.owner = owner;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void getQuestionsFromQuiz() {
        this.questions = JsonQuestion.getQuestionsByQuizId(getId());
    }

}