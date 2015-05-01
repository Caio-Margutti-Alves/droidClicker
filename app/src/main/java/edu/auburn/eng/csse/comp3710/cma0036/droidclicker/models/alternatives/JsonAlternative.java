package edu.auburn.eng.csse.comp3710.cma0036.droidclicker.models.alternatives;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import edu.auburn.eng.csse.comp3710.cma0036.droidclicker.codeUtils.Util;
import edu.auburn.eng.csse.comp3710.cma0036.droidclicker.http.HttpUtil;

public class JsonAlternative {

    private static final String TAG_ALTERNATIVES = "alternatives";
    private static final String TAG_ALTERNATIVE = "alternative";

	private static final String TAG_ID = "id";
	private static final String TAG_DESCRIPTION = "description";
	private static final String TAG_ID_QUIZ = "id_question";

    public static ArrayList<Alternative> getAlternativesFromQuestion(String id) {
        HttpUtil client = new HttpUtil();

        // MEXER AQUI
        client.AddParam(TAG_ID, id);

        try {
            client.Execute(HttpUtil.RequestMethod.GET, HttpUtil.getUrlGetAlternativesFromQuestion());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        System.out.println(Util.fix(client.getResponse()));
        return parseJsonAlternative(Util.fix(client.getResponse()));
    }

    public static ArrayList<Alternative> parseJsonAlternative(String in) {
        ArrayList<Alternative> alternatives = null;

        try {
            alternatives = new ArrayList<Alternative>();

            JSONObject reader = new JSONObject(in);
            JSONArray jsonAlternatives = reader.getJSONArray(TAG_ALTERNATIVES);

            // looping through All Contacts
            for (int i = 0; i < jsonAlternatives.length(); i++) {
                JSONObject jsonCurAlternative = jsonAlternatives.getJSONObject(i);
                JSONObject jsonAlternative = jsonCurAlternative.getJSONObject(TAG_ALTERNATIVE);

                Alternative alternative = new Alternative();

                System.out.println(jsonAlternative.toString());

                alternative.setId(jsonAlternative.getString(TAG_ID));
                alternative.setDescription(jsonAlternative.getString(TAG_DESCRIPTION));
                alternative.setId_quiz(jsonAlternative.getString(TAG_ID_QUIZ));

                alternatives.add(alternative);

            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return alternatives;
    }
}