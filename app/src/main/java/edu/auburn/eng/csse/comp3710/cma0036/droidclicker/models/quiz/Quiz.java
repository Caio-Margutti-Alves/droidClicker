package edu.auburn.eng.csse.comp3710.cma0036.droidclicker.models.quiz;

import android.graphics.Bitmap;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;

public class Quiz {

    private LinkedList<Question> questions;
    private String duration;

    protected Quiz() {
    }

    protected Quiz(LinkedList<Question> questions, String duration) {
        this.questions = questions;
        this.duration = duration;
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
}
