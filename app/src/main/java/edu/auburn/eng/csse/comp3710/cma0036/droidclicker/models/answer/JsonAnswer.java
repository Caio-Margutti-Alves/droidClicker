package edu.auburn.eng.csse.comp3710.cma0036.droidclicker.models.answer;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import edu.auburn.eng.csse.comp3710.cma0036.droidclicker.codeUtils.Util;
import edu.auburn.eng.csse.comp3710.cma0036.droidclicker.http.HttpUtil;

public class JsonAnswer {

    private static final String TAG_ANSWER = "answer";
    private static final String TAG_ANSWERS = "answers";

	private static final String TAG_ID_QUESTION = "id_question";
    private static final String TAG_ID = "id";
	private static final String TAG_ID_USER = "id_user";
	private static final String TAG_ID_ALTERNATIVE = "id_alternative";

    public static ArrayList<Answer> getAnswers(String id) {
        HttpUtil client = new HttpUtil();

        // MEXER AQUI
        client.AddParam(TAG_ID, id);

        try {
            client.Execute(HttpUtil.RequestMethod.GET, HttpUtil.getUrlGetAnswerByUser());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        System.out.println(Util.fix(client.getResponse()));
        return parseJsonAnswer(Util.fix(client.getResponse()));
    }

    public static ArrayList<Answer> parseJsonAnswer(String in) {
        ArrayList<Answer> answers = null;

        try {
            answers = new ArrayList<Answer>();

            JSONObject reader = new JSONObject(in);
            JSONArray jsonAnswerFromUser = reader.getJSONArray(TAG_ANSWERS);

            // looping through All Contacts
            for (int i = 0; i < jsonAnswerFromUser.length(); i++) {
                JSONObject jsonCurQuiz = jsonAnswerFromUser.getJSONObject(i);
                JSONObject jsonAnswer = jsonCurQuiz.getJSONObject(TAG_ANSWER);

                Answer answer = new Answer();

                System.out.println(jsonAnswer.toString());

                answer.setId_user(jsonAnswer.getString(TAG_ID_USER));
                answer.setId_question(jsonAnswer.getString(TAG_ID_QUESTION));
                answer.setId_alternative(jsonAnswer.getString(TAG_ID_ALTERNATIVE));

                answers.add(answer);

            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return answers;
    }

    public static boolean NewAnswers(AnswerCollection answers) {

        HttpUtil client = new HttpUtil();

        for(Answer answer : answers.getAnswers()) {
            System.out.println(answer.getId_user());
            System.out.println(answer.getId_question());
            System.out.println(answer.getId_alternative());

            client.AddParam(TAG_ID_USER, answer.getId_user());
            client.AddParam(TAG_ID_QUESTION, answer.getId_question());
            client.AddParam(TAG_ID_ALTERNATIVE, answer.getId_alternative());


            try {
                client.Execute(HttpUtil.RequestMethod.GET, HttpUtil.getUrlSetNewAnswer());
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e.toString());
                return false;
            }
            System.out.println(client.getResponse());
        }
        return true;
        //parseJsonUser(client.getResponse());
    }

}