package edu.auburn.eng.csse.comp3710.cma0036.droidclicker.models.questions;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import edu.auburn.eng.csse.comp3710.cma0036.droidclicker.codeUtils.Util;
import edu.auburn.eng.csse.comp3710.cma0036.droidclicker.http.HttpUtil;
import edu.auburn.eng.csse.comp3710.cma0036.droidclicker.models.alternatives.Alternative;
import edu.auburn.eng.csse.comp3710.cma0036.droidclicker.models.alternatives.JsonAlternative;
import edu.auburn.eng.csse.comp3710.cma0036.droidclicker.models.quiz.Quiz;

public class JsonQuestion {

	private static final String TAG_QUESTION = "question";
    private static final String TAG_QUESTIONS = "questions";

	private static final String TAG_ID = "id";
	private static final String TAG_ENUNCIATE = "enunciate";
	private static final String TAG_ID_RIGHT_ALTERNATIVE = "id_right_alternative";
    private static final String TAG_ID_QUIZ = "id";


    public static ArrayList<Question> getQuestionsByQuizId(String id) {
        HttpUtil client = new HttpUtil();

        // MEXER AQUI
        client.AddParam(TAG_ID_QUIZ, id);

        try {
            client.Execute(HttpUtil.RequestMethod.GET, HttpUtil.getUrlGetQuestionByQuizId());
        } catch (Exception e) {
            e.printStackTrace();
        }

        //System.out.println(client.getResponse());
        return parseJsonQuestion(Util.fix(client.getResponse()));
    }


    public static ArrayList<Question> parseJsonQuestion(String in) {
        ArrayList<Question> questions = null;
        ArrayList<Alternative> alternatives;

		try {
            questions = new ArrayList<Question>();

                JSONObject reader = new JSONObject(in);
                JSONArray jsonQuestions = reader.getJSONArray(TAG_QUESTIONS);

                // looping through All Contacts
                for (int i = 0; i < jsonQuestions.length(); i++) {
                    JSONObject jsonCurQuestion = jsonQuestions.getJSONObject(i);
                    JSONObject jsonQuestion = jsonCurQuestion.getJSONObject(TAG_QUESTION);

                    Question question = new Question();

                    System.out.println(jsonQuestion.toString());

                    question.setId(jsonQuestion.getString(TAG_ID));
                    question.setEnunciate(jsonQuestion.getString(TAG_ENUNCIATE));
                    question.setIdRightAlternative(jsonQuestion.getString(TAG_ID_RIGHT_ALTERNATIVE));

                    //aqui
                    alternatives = JsonAlternative.getAlternativesFromQuestion(question.getId());

                    question.setAlternatives(alternatives);

                    questions.add(question);

                }

		} catch (Exception e) {
            System.out.println(e.toString());
			e.printStackTrace();
		}
        return questions;
	}
}