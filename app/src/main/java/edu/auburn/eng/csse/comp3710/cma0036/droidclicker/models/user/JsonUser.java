package edu.auburn.eng.csse.comp3710.cma0036.droidclicker.models.user;

import org.json.JSONArray;
import org.json.JSONObject;

import edu.auburn.eng.csse.comp3710.cma0036.droidclicker.codeUtils.Util;
import edu.auburn.eng.csse.comp3710.cma0036.droidclicker.http.HttpUtil;
import edu.auburn.eng.csse.comp3710.cma0036.droidclicker.models.user.User;

public class JsonUser {

	private static final String TAG_USERS = "users";
    private static final String TAG_USER = "user";
	private static final String TAG_USER_BY = "user-by-id";

	private static final String TAG_ID = "id";
	private static final String TAG_LOGIN = "login";
	private static final String TAG_PASSWORD = "password";
	private static final String TAG_EMAIL = "email";
	private static final String TAG_FIRST_NAME = "firstname";
	private static final String TAG_LAST_NAME = "lastname";

	
	//SOMENTE TESTE - NÃO DEIXAR NO FINAL
	public static boolean getUserById(String id) {
		HttpUtil client = new HttpUtil();

		// MEXER AQUI
		client.AddParam(TAG_USER_BY + TAG_ID, id);

		try {
			client.Execute(HttpUtil.RequestMethod.GET, HttpUtil.getUrlGetUserById());
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		//System.out.println(client.getResponse());
		parseJsonUser(Util.fix(client.getResponse()));
		return true;
	}
	
	public static boolean getUserByLoginPassword(String login, String password) {
		HttpUtil client = new HttpUtil();

		// MEXER AQUI
		client.AddParam(TAG_LOGIN, login);
		client.AddParam(TAG_PASSWORD, password);

		try {
			client.Execute(HttpUtil.RequestMethod.GET, HttpUtil.getUrlGetUserByLoginPassword());
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}


		System.out.println(client.getResponse());
        parseJsonUser(Util.fix(client.getResponse()));
		return true;
	}

	public static void parseJsonUser(String in) {
		try {
			JSONObject reader = new JSONObject(in);
            JSONArray jsonUsers = reader.getJSONArray(TAG_USERS);

            // looping through All Contacts
            for (int i = 0; i < jsonUsers.length(); i++) {
                JSONObject jsonCurUser = jsonUsers.getJSONObject(i);
                JSONObject jsonUser = jsonCurUser.getJSONObject(TAG_USER);

                System.out.println(jsonUser.toString());

                User.setId(jsonUser.getString(TAG_ID));
                User.setLogin(jsonUser.getString(TAG_LOGIN));
                User.setFirstName(jsonUser.getString(TAG_FIRST_NAME));
                User.setLastName(jsonUser.getString(TAG_LAST_NAME));
                User.setEmail(jsonUser.getString(TAG_EMAIL));
            }

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static boolean newUser(String login, String password,
			String firstName, String lastName,
			String email) {

		HttpUtil client = new HttpUtil();

		// MEXER AQUI
		client.AddParam(TAG_LOGIN, login);
		client.AddParam(TAG_PASSWORD, password);
		client.AddParam(TAG_FIRST_NAME, firstName);
		client.AddParam(TAG_LAST_NAME, lastName);
		client.AddParam(TAG_EMAIL, email);


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


    public static boolean updateUser(String id, String login, String password,
                                  String firstName, String lastName,
                                  String email) {

        HttpUtil client = new HttpUtil();

        // MEXER AQUI
        client.AddParam(TAG_ID, id);
        client.AddParam(TAG_LOGIN, login);
        client.AddParam(TAG_PASSWORD, password);
        client.AddParam(TAG_FIRST_NAME, firstName);
        client.AddParam(TAG_LAST_NAME, lastName);
        client.AddParam(TAG_EMAIL, email);


        try {
            client.Execute(HttpUtil.RequestMethod.GET, HttpUtil.getUrlUpdateUser());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        System.out.println(client.getResponse());
        return true;
        //parseJsonUser(client.getResponse());
    }
}