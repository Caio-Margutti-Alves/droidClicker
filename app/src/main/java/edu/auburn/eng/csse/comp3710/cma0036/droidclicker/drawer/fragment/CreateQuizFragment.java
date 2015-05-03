package edu.auburn.eng.csse.comp3710.cma0036.droidclicker.drawer.fragment;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.beardedhen.androidbootstrap.BootstrapButton;

import java.util.ArrayList;

import edu.auburn.eng.csse.comp3710.cma0036.droidclicker.drawer.utils.Constant;
import edu.auburn.eng.csse.comp3710.cma0036.droidclicker.models.alternatives.Alternative;
import edu.auburn.eng.csse.comp3710.cma0036.droidclicker.models.questions.Question;
import edu.auburn.eng.csse.comp3710.cma0036.droidclicker.R;
import edu.auburn.eng.csse.comp3710.cma0036.droidclicker.models.quiz.Quiz;

/**
 * Created by Carlos on 5/2/2015.
 */
public class CreateQuizFragment extends Fragment {
    private RegisterQuizTask registerTask = null;


    private String question;
    private String alternative1;
    private String alternative2;
    private String alternative3;
    private String alternative4;
    private String alternativeCorrect;

    private EditText edtQuestion;
    private EditText edtAlternative1;
    private EditText edtAlternative2;
    private EditText edtAlternative3;
    private EditText edtAlternative4;

    private View quizFormView;
    private View quizStatusView;
    private BootstrapButton btnRegister;


    Button.OnClickListener lstnRegister = new Button.OnClickListener() {
        @Override
        public void onClick(View view) {
            attemptRegister();
        }
    };

    public CreateQuizFragment newInstance(String text){
        CreateQuizFragment mFragment = new CreateQuizFragment();
        Bundle mBundle = new Bundle();
        mBundle.putString(Constant.TEXT_FRAGMENT, text);
        mFragment.setArguments(mBundle);
        return mFragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View rootView = inflater.inflate(R.layout.fragment_create_quiz, container, false);
        super.onCreate(savedInstanceState);

        quizFormView = rootView.findViewById(R.id.quiz_form);
        quizStatusView = rootView.findViewById(R.id.quiz_status);

        btnRegister = (BootstrapButton) rootView.findViewById(R.id.btnRegisterQuiz);
        btnRegister.setOnClickListener(lstnRegister);


        rootView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT ));
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
        switch (item.getItemId()) {
            case R.id.action_settings:
                // openSettings();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(
                    android.R.integer.config_shortAnimTime);

            quizStatusView.setVisibility(View.VISIBLE);
            quizStatusView.animate().setDuration(shortAnimTime)
                    .alpha(show ? 1 : 0)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            quizStatusView.setVisibility(show ? View.VISIBLE
                                    : View.GONE);
                        }
                    });

            quizFormView.setVisibility(View.VISIBLE);
            quizFormView.animate().setDuration(shortAnimTime)
                    .alpha(show ? 0 : 1)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            quizFormView.setVisibility(show ? View.GONE
                                    : View.VISIBLE);
                        }
                    });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            quizStatusView.setVisibility(show ? View.VISIBLE : View.GONE);
            quizFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    public void attemptRegister() {
        if (registerTask != null) {
            return;
        }

        // Reset errors.
        edtQuestion.setError(null);
        edtAlternative1.setError(null);
        edtAlternative2.setError(null);
        edtAlternative3.setError(null);
        edtAlternative4.setError(null);


        // Store values at the time of the login attempt.
        question = edtQuestion.getText().toString();
        alternative1 = edtAlternative1.getText().toString();
        alternative2 = edtAlternative2.getText().toString();
        alternative3 = edtAlternative3.getText().toString();
        alternative4 = edtAlternative4.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid question.
        if (TextUtils.isEmpty(question)) {
            edtQuestion.setError(getString(R.string.error_field_required));
            focusView = edtQuestion;
            cancel = true;
        }

        // Check for a valid Alternatives.
        if (TextUtils.isEmpty(alternative1)) {
            edtAlternative1.setError(getString(R.string.error_field_required));
            focusView = edtAlternative1;
            cancel = true;
        }

        if (TextUtils.isEmpty(alternative2)) {
            edtAlternative2.setError(getString(R.string.error_field_required));
            focusView = edtAlternative2;
            cancel = true;
        }

        if (TextUtils.isEmpty(alternative3)) {
            edtAlternative3.setError(getString(R.string.error_field_required));
            focusView = edtAlternative3;
            cancel = true;
        }

        if (TextUtils.isEmpty(alternative4)) {
            edtAlternative4.setError(getString(R.string.error_field_required));
            focusView = edtAlternative4;
            cancel = true;
        }



        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);
            registerTask = new RegisterQuizTask();
            registerTask.execute((Void) null);
        }
    }

    public class RegisterQuizTask extends AsyncTask<Void, Void, Boolean>{
        @Override
        protected Boolean doInBackground(Void... params) {
            try {
//                User.newUser(login, password, firstName, lastName,
//                        email);
                Alternative alternativeObj1 = new Alternative(alternative1, "" , "");
                Alternative alternativeObj2 = new Alternative(alternative2, "" , "");
                Alternative alternativeObj3 = new Alternative(alternative3, "" , "");
                Alternative alternativeObj4 = new Alternative(alternative4, "" , "");

                ArrayList<Alternative> alternatives = new ArrayList<Alternative>();
                alternatives.add(alternativeObj1);
                alternatives.add(alternativeObj2);
                alternatives.add(alternativeObj3);
                alternatives.add(alternativeObj4);


                Question questionObj = new Question(question, null, alternatives);


            } catch (Exception e) {
                return false;
            }

            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            registerTask = null;
            showProgress(false);

            if (success) {
				/*Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
				startActivity(intent);*/
                //finish();
            } else {

                //TODO
                //
            }
        }

        @Override
        protected void onCancelled() {
            registerTask = null;
            showProgress(false);
        }
    }

}

