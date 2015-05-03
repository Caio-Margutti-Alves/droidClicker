package edu.auburn.eng.csse.comp3710.cma0036.droidclicker.drawer.fragment;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.FontAwesomeText;

import java.util.ArrayList;

import edu.auburn.eng.csse.comp3710.cma0036.droidclicker.R;
import edu.auburn.eng.csse.comp3710.cma0036.droidclicker.RegisterAccountActivity;
import edu.auburn.eng.csse.comp3710.cma0036.droidclicker.drawer.utils.Constant;
import edu.auburn.eng.csse.comp3710.cma0036.droidclicker.models.alternatives.Alternative;
import edu.auburn.eng.csse.comp3710.cma0036.droidclicker.models.answer.Answer;
import edu.auburn.eng.csse.comp3710.cma0036.droidclicker.models.answer.AnswerCollection;
import edu.auburn.eng.csse.comp3710.cma0036.droidclicker.models.questions.Question;
import edu.auburn.eng.csse.comp3710.cma0036.droidclicker.models.quiz.Quiz;
import edu.auburn.eng.csse.comp3710.cma0036.droidclicker.models.user.User;

/**
 * Created by caioa_000 on 23/04/2015.
 */
public class QuizFragment extends Fragment {

   // private QuestionsLoadingTask authTask = null;

    private static final String TAG_QUIZ = "Quiz";

    private QuestionsLoadingTask authTask = null;

    private LinearLayout layout_alternatives = null;
    private ScrollView layoutList = null;
    private View loadQuestionStatusView;

    private Quiz quiz;
    private Question cur_question;
    private AnswerCollection answers;
    private ArrayList<Question> questions;
    private ArrayList<Alternative> alternatives;

    private FontAwesomeText txtv_num_quiz;
    private FontAwesomeText txtv_num_question;
    private FontAwesomeText txtv_question;

    private Chronometer chrono;

    private int duration = Integer.MAX_VALUE;

    public QuizFragment newInstance(String text, Quiz quiz){
        QuizFragment mFragment = new QuizFragment();

        Bundle mBundle = new Bundle();
        mBundle.putString(Constant.TEXT_FRAGMENT, text);
        mBundle.putSerializable(TAG_QUIZ, quiz);
        mFragment.setArguments(mBundle);

        return mFragment;
    }

    Chronometer.OnChronometerTickListener lstn_chrono = new Chronometer.OnChronometerTickListener() {
        public void onChronometerTick(Chronometer chronometer) {
            long elapsedMillis = SystemClock.elapsedRealtime() - chronometer.getBase();
            if (duration <= elapsedMillis/1000){
                Toast.makeText(chronometer.getContext(), "Time is Over!", Toast.LENGTH_LONG).show();
                chronometer.stop();
            }
        }
    };

    Button.OnClickListener lstnSubmit = new Button.OnClickListener() {
        public void onClick(View view) {
            Answer answer = new Answer(cur_question.getId(), String.valueOf(view.getId()), User.getId());
            answers.add(answer);

            if(questions.size()>0){
                setLayoutNewQuestion();
            }
            else{
                String text = "";
                for(Answer ans : answers.getAnswers())text+= ans.getId_alternative() + " ";
                Toast.makeText(view.getContext(), text, Toast.LENGTH_LONG).show();

                //answers.NewAnswers();
            }
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            quiz = (Quiz) savedInstanceState.getSerializable(TAG_QUIZ);
        }else {
            quiz = new Quiz();

        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(TAG_QUIZ, quiz);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_quiz, container, false);
            super.onCreate(savedInstanceState);

            answers = new AnswerCollection();

            chrono = (Chronometer) rootView.findViewById(R.id.chr_duration);
            chrono.setOnChronometerTickListener(lstn_chrono);
            chrono.setBase(SystemClock.elapsedRealtime());

            layoutList = (ScrollView) rootView.findViewById(R.id.layout_question);
            loadQuestionStatusView = rootView.findViewById(R.id.layout_load_status);
            layout_alternatives = (LinearLayout) rootView.findViewById(R.id.layout_alternatives);

            txtv_num_quiz = (FontAwesomeText) rootView.findViewById(R.id.txtv_quiz);
            txtv_question = (FontAwesomeText) rootView.findViewById(R.id.txtv_question);
            txtv_num_question = (FontAwesomeText) rootView.findViewById(R.id.txtv_num_question);

        if (getArguments()!= null) {
            quiz = (Quiz) getArguments().getSerializable(TAG_QUIZ);
            duration = Integer.parseInt(quiz.getDuration());
            txtv_num_quiz.setText("Quiz " + quiz.getId());
        }

        attemptQuestionsRetrieve();

        return rootView;
    }

    @Override
    public void onStart() {
        super.onResume();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public void setLayoutNewQuestion(){
               cur_question = questions.remove(0);

                txtv_question.setText(cur_question.getEnunciate());
                txtv_num_question.setText(cur_question.getId());

                layout_alternatives.removeAllViews();

            GradientDrawable shape= new GradientDrawable();
            shape.setColor(getResources().getColor(R.color.green_light));
            shape.setCornerRadius(10);
            alternatives = cur_question.getAlternatives();

                for(Alternative alternative: alternatives) {
                    //Toast.makeText(this.getActivity(), alternative.getDescription(), Toast.LENGTH_LONG).show();

                    Button btn_alternative = new Button(getActivity());
                    btn_alternative.setText(alternative.getDescription());
                    btn_alternative.setTextSize(22);
                    btn_alternative.setBackground(shape);

                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT);
                    params.setMargins(30,10,30,10);
                    btn_alternative.setLayoutParams(params);
                    //btn_alternative.setBackgroundColor(getResources().getColor(R.color.green_light));
                    btn_alternative.setOnClickListener(lstnSubmit);
                    btn_alternative.setId(Integer.parseInt(alternative.getId()));

                    layout_alternatives.addView(btn_alternative);

                }
    }


    public void onAlternativeClicked(){
        setLayoutNewQuestion();
    }

    public void attemptQuestionsRetrieve(){
        showProgress(true);
        authTask = new QuestionsLoadingTask();
        authTask.execute((Void) null);
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
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            loadQuestionStatusView.setVisibility(View.VISIBLE);
            loadQuestionStatusView.animate().setDuration(shortAnimTime).alpha(show ? 1 : 0)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            loadQuestionStatusView.setVisibility(show ? View.VISIBLE
                                    : View.GONE);
                        }
                    });

            layoutList.setVisibility(View.VISIBLE);
            layoutList.animate().setDuration(shortAnimTime)
                    .alpha(show ? 0 : 1)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            layoutList.setVisibility(show ? View.GONE
                                    : View.VISIBLE);
                        }
                    });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            loadQuestionStatusView.setVisibility(show ? View.VISIBLE : View.GONE);
            layoutList.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    public class QuestionsLoadingTask extends AsyncTask<Void, Void, Boolean> {
        @Override
        protected Boolean doInBackground(Void... params) {

            try {
                quiz.getQuestionsFromQuiz();
                questions = quiz.getQuestions();
            } catch (Exception e) {
                return false;
            }

            if (questions== null)return false;
            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            authTask = null;
            showProgress(false);

            if (success) {
                setLayoutNewQuestion();
                chrono.start();
            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage(R.string.dialog_message).setTitle(R.string.dialog_title);
                AlertDialog dialog = builder.create();
            }
        }

        @Override
        protected void onCancelled() {
            authTask = null;
            showProgress(false);
        }
    }
    
}




