package edu.auburn.eng.csse.comp3710.cma0036.droidclicker.models.user;

import android.graphics.Bitmap;

import java.util.Calendar;
import java.util.Date;

public abstract class User {

	private static String id;
	private static String login;
    private static String password;
	private static String firstName;
	private static String lastName;
	private static String email;


	public User() {

	}
	
	public static boolean newUser(String login, String password,
			String firstName, String lastName,
			String email) {

		return JsonUser.newUser(login, password, firstName,
                lastName, email);
		
	}
	
	//SOMENTE PARA TESTES - RETIRAR NO FINAL
	public static boolean getUserById(String id) {
		return JsonUser.getUserById(id);
	}


	public static boolean getUserByLoginPassword(String login, String password) {
		return JsonUser.getUserByLoginPassword(login, password);
		
	}

	public static String getEmail() {
		return email;
	}

	public static void setEmail(String email) {
		User.email = email;
	}

	public static String getLogin() {
		return login;
	}

	public static void setLogin(String login) {
		User.login = login;
	}
	
	public static String getId() {
		return id;
	}

	public static void setId(String id) {
		User.id = id;
	}

	public static String getFirstName() {
		return firstName;
	}

	public static void setFirstName(String firstName) {
		User.firstName = firstName;
	}

	public static String getLastName() {
		return lastName;
	}

	public static void setLastName(String lastName) {
		User.lastName = lastName;
	}


    public static boolean updateUser(String id, String login, String password,
                                  String firstName, String lastName,
                                  String email) {

        return JsonUser.updateUser(id, login, password, firstName,
                lastName, email);

    }

}
