package edu.auburn.eng.csse.comp3710.cma0036.droidclicker.drawer.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import edu.auburn.eng.csse.comp3710.cma0036.droidclicker.R;
import edu.auburn.eng.csse.comp3710.cma0036.droidclicker.drawer.utils.Constant;
import edu.auburn.eng.csse.comp3710.cma0036.droidclicker.models.user.User;

public class ProfileFragment extends Fragment {

	private static String firstName;
	private static String lastName;
	private static String login;
	private static String password;
	private static String rePassword;
	private static String mobileToken;
	// private static Bitmap profilePicture;
	private static int gender;
	private static String email;
	private static String dob;

	private EditText edtFisrtName;
	private EditText edtLastName;
	private EditText edtEmail;
	private EditText edtLogin;
	private ImageView profilePicture;

	public ProfileFragment newInstance(String text){
		ProfileFragment mFragment = new ProfileFragment();		
		Bundle mBundle = new Bundle();
		mBundle.putString(Constant.TEXT_FRAGMENT, text);
		mFragment.setArguments(mBundle);
		return mFragment;
	}
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub		
		View rootView = inflater.inflate(R.layout.fragment_profile, container, false);
		super.onCreate(savedInstanceState);

		edtFisrtName = (EditText) rootView.findViewById(R.id.edtFirstName);
		edtLastName = (EditText) rootView.findViewById(R.id.edtLastName);
		edtEmail = (EditText) rootView.findViewById(R.id.edtEmail);
		edtLogin = (EditText) rootView.findViewById(R.id.edtLogin);
		profilePicture = (ImageView) rootView.findViewById(R.id.imgvProfile);

		try{
			edtFisrtName.setText(User.getFirstName());
			edtLastName.setText(User.getLastName());
			//edtAge.setText(String.valueOf(User.getAge()));
			edtEmail.setText(User.getEmail());
			edtLogin.setText(User.getLogin());
			//profilePicture.setProfileId(User.getFacebookId());

			
		}catch(Exception ex){
			edtFisrtName.setText("Default");
			edtLastName.setText("Name");
			//edtAge.setText("Default Age");
			edtEmail.setText("Default Email");
			edtLogin.setText("Default Login");
			//profilePicture.setProfileId(User.getFacebookId());
		}
		
		
		rootView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT ));		
		return rootView;
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// Inflate the menu items for use in the action bar
		super.onCreateOptionsMenu(menu, inflater);		
		inflater.inflate(R.menu.menu_main, menu);

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
