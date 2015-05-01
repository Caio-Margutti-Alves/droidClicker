package edu.auburn.eng.csse.comp3710.cma0036.droidclicker.models.quiz;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import edu.auburn.eng.csse.comp3710.cma0036.droidclicker.codeUtils.Util;
import edu.auburn.eng.csse.comp3710.cma0036.droidclicker.http.HttpUtil;
import edu.auburn.eng.csse.comp3710.cma0036.droidclicker.models.questions.JsonQuestion;
import edu.auburn.eng.csse.comp3710.cma0036.droidclicker.models.questions.Question;
import edu.auburn.eng.csse.comp3710.cma0036.droidclicker.models.user.User;

public class JsonQuiz {

	private static final String TAG_QUIZ = "quiz";
    private static final String TAG_QUIZZES = "quizzes";

	private static final String TAG_ID = "id_quiz";
	private static final String TAG_DURATION = "duration";
	private static final String TAG_ID_OWNER = "id_owner";

    public static ArrayList<Quiz> getQuizzes() {
        HttpUtil client = new HttpUtil();

        Log.i(TAG_QUIZ, "Json getQuizzes");

        try {
            client.Execute(HttpUtil.RequestMethod.GET, HttpUtil.getUrlGetQuizzes());
        } catch (Exception e) {
            e.printStackTrace();
        }

        //System.out.println(client.getResponse());
        return parseJsonQuizzes(Util.fix(client.getResponse()));
    }

    public static ArrayList<Quiz> parseJsonQuizzes(String in) {
        ArrayList<Quiz> quizzes = null;
        ArrayList<Question> questions = null;

        try {
            quizzes = new ArrayList<Quiz>();

            JSONObject reader = new JSONObject(in);
            JSONArray jsonQuizzes = reader.getJSONArray(TAG_QUIZZES);

            // looping through All Contacts
            for (int i = 0; i < jsonQuizzes.length(); i++) {
                JSONObject jsonCurQuiz = jsonQuizzes.getJSONObject(i);
                JSONObject jsonQuiz = jsonCurQuiz.getJSONObject(TAG_QUIZ);

                Quiz quiz = new Quiz();

                System.out.println(jsonQuiz.toString());

                quiz.setId(jsonQuiz.getString(TAG_ID));
                quiz.setDuration(jsonQuiz.getString(TAG_DURATION));
                quiz.setOwner(jsonQuiz.getString(TAG_ID_OWNER));

                quiz.setQuestions(questions);

                quizzes.add(quiz);

            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return quizzes;
    }

	public static boolean newQuiz(String id, String duration, String id_owner) {

		HttpUtil client = new HttpUtil();

		// MEXER AQUI
		client.AddParam(TAG_ID, id);
		client.AddParam(TAG_DURATION, duration);
		client.AddParam(TAG_ID_OWNER, id_owner);

		//client.AddHeader(TAG_USER, "2");

		try {
			client.Execute(HttpUtil.RequestMethod.GET, HttpUtil.getUrlNewUser());
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		System.out.println(client.getResponse());
		return true;
		//parseJsonUser(client.getResponse());
	}
}