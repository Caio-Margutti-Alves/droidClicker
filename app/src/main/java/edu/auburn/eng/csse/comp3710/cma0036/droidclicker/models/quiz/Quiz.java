package edu.auburn.eng.csse.comp3710.cma0036.droidclicker.models.quiz;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;

public class Quiz {

    private String id;
    private LinkedList<Question> questions;
    private String duration;
    private String owner;

    protected Quiz() {
    }

    public Quiz(LinkedList<Question> questions, String duration, String owner) {
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

    public LinkedList<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(LinkedList<Question> questions) {
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

    public static ArrayList<Quiz> getQuizzes() {
        return JsonQuiz.getQuizzes();
    }

}