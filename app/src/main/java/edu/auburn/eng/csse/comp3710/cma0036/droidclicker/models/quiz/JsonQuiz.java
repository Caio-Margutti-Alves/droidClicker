package edu.auburn.eng.csse.comp3710.cma0036.droidclicker.models.quiz;

import org.json.JSONObject;

import edu.auburn.eng.csse.comp3710.cma0036.droidclicker.http.HttpUtil;

public class JsonQuiz {

	private static final String TAG_USER = "usuario";
	private static final String TAG_USER_BY = "usuario.";
	//private static final String TAG_GET_USER = "getUser";
	//private static final String TAG_GET_USER_FACEBOOK = "getUserFacebook";
	// private static final String TAG_NEW_USER = "novo_usuario";

	private static final String TAG_ID = "id";
	private static final String TAG_LOGIN = "login";
	private static final String TAG_PASSWORD = "password";
	//private static final String TAG_PROFILE_PICTURE = "profilePicture";
	private static final String TAG_FACEBOOK_ID = "facebook";
	private static final String TAG_MOBILE_TOKEN = "mobileToken";
	private static final String TAG_EMAIL = "email";
	private static final String TAG_FIRST_NAME = "firstName";
	private static final String TAG_LAST_NAME = "lastName";
	private static final String TAG_GENDER = "gender";
	private static final String TAG_DOB = "dob";
	private static final String TAG_LAST_LOCATION = "localizacao";
	private static final String TAG_LAST_ACTIVITY = "ultimaAtividade";
	private static final String TAG_DATE_ACC_CREATION = "dataDeCriacao";
	//private static final String TAG_ANIMALS = "animais";

	
	//SOMENTE TESTE - NÃO DEIXAR NO FINAL
	public static boolean getUserById(String id) {
		HttpUtil client = new HttpUtil();

		// MEXER AQUI
		client.AddParam(TAG_USER_BY + TAG_ID, id);

		try {
			client.Execute(HttpUtil.RequestMethod.POST, HttpUtil.getUrlGetUserById());
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		//System.out.println(client.getResponse());
		parseJsonUser(client.getResponse());
		return true;
	}
	
	public static boolean getUserByLoginPassword(String login, String password) {
		HttpUtil client = new HttpUtil();

		// MEXER AQUI
		client.AddParam(TAG_LOGIN, login);
		client.AddParam(TAG_PASSWORD, password);

		try {
			client.Execute(HttpUtil.RequestMethod.POST, HttpUtil.getUrlGetUserByLoginPassword());
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		System.out.println(client.getResponse());
		//parseJsonUser(client.getResponse());
		return true;
	}

	public static boolean getUserByFacebookId(String facebookId) {
		HttpUtil client = new HttpUtil();

		// MEXER AQUI
		client.AddParam(TAG_FACEBOOK_ID, facebookId);

		try {
			client.Execute(HttpUtil.RequestMethod.POST, HttpUtil.getUrlGetUserByFacebookId());
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		System.out.println(client.getResponse());
		//parseJsonUser(client.getResponse());
		return true;
	}

	public static void parseJsonUser(String in) {
		try {
			JSONObject reader = new JSONObject(in);
			JSONObject jsonUser = reader.getJSONObject(TAG_USER);
			
			Quiz.setId(jsonUser.getString(TAG_ID));
			Quiz.setLogin(jsonUser.getString(TAG_LOGIN));
			Quiz.setFacebookId(jsonUser.getString(TAG_FACEBOOK_ID));
			Quiz.setMobileToken(jsonUser.getString(TAG_MOBILE_TOKEN));
			Quiz.setFirstName(jsonUser.getString(TAG_FIRST_NAME));
			Quiz.setLastName(jsonUser.getString(TAG_LAST_NAME));
			Quiz.setGender(jsonUser.getString(TAG_GENDER));
			Quiz.setEmail(jsonUser.getString(TAG_EMAIL));
			Quiz.setDob(jsonUser.getString(TAG_DOB));
			Quiz.setLastActivity(jsonUser.getString(TAG_LAST_ACTIVITY));
			Quiz.setLastLocation(jsonUser.getString(TAG_LAST_LOCATION));
			Quiz.setDateAccCreation(jsonUser.getString(TAG_DATE_ACC_CREATION));
			
			//User.setAnimals(jsonUser.getString(TAG_ANIMALS));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static boolean newUser(String facebookId, String login, String password,
			String mobileToken, String firstName, String lastName,
			String email, String gender, String dob) {

		HttpUtil client = new HttpUtil();

		// MEXER AQUI
		client.AddParam(TAG_FACEBOOK_ID, facebookId);
		client.AddParam(TAG_LOGIN, login);
		client.AddParam(TAG_PASSWORD, password);
		client.AddParam(TAG_MOBILE_TOKEN, mobileToken);
		client.AddParam(TAG_FIRST_NAME, firstName);
		client.AddParam(TAG_LAST_NAME, lastName);
		client.AddParam(TAG_GENDER, gender);
		client.AddParam(TAG_EMAIL, email);
		client.AddParam(TAG_DOB, dob);

		//client.AddHeader(TAG_USER, "2");

		try {
			client.Execute(HttpUtil.RequestMethod.POST, HttpUtil.getUrlNewUser());
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		System.out.println(client.getResponse());
		return true;
		//parseJsonUser(client.getResponse());
	}
}