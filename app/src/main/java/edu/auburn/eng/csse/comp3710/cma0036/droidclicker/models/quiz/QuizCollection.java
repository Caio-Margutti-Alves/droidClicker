package edu.auburn.eng.csse.comp3710.cma0036.droidclicker.models.quiz;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;

import edu.auburn.eng.csse.comp3710.cma0036.droidclicker.models.questions.Question;

public class QuizCollection {

    private ArrayList<Quiz> quizzes;

    public QuizCollection() {
        this.quizzes = null;
    }

    public QuizCollection(ArrayList<Quiz> quizzes) {
        this.quizzes = quizzes;
    }

    public ArrayList<Quiz> getQuizzes() {
        return quizzes;
    }

    public void setQuizzes(ArrayList<Quiz> quizzes) {
        this.quizzes = quizzes;
    }

    public void loadAllQuizzes() {
        this.quizzes = JsonQuiz.getQuizzes();
    }

}