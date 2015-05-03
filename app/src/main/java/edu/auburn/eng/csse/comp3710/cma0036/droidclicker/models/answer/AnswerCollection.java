package edu.auburn.eng.csse.comp3710.cma0036.droidclicker.models.answer;

import java.util.ArrayList;

import edu.auburn.eng.csse.comp3710.cma0036.droidclicker.models.quiz.JsonQuiz;
import edu.auburn.eng.csse.comp3710.cma0036.droidclicker.models.quiz.Quiz;

public class AnswerCollection {

    private ArrayList<Answer> answers;

    public AnswerCollection() {
        this.answers = new ArrayList<Answer>();
    }

    public AnswerCollection(ArrayList<Answer> answers) {
        this.answers = answers;
    }

    public Answer getAnswer(int pos) {
        return this.answers.get(pos);
    }

    public ArrayList<Answer> getAnswers() {
        return answers;
    }

    public void setAnswer(int pos, Answer value) {
        this.answers.set(pos, value);
    }

    public void setAnswers(ArrayList<Answer> answers) {
        this.answers = answers;
    }

    public void add(Answer value) {
        this.answers.add(value);
    }

    public void NewAnswers() {
        JsonAnswer.NewAnswers(this);
    }

}