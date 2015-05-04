package edu.auburn.eng.csse.comp3710.cma0036.droidclicker.drawer.fragment;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.FontAwesomeText;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.util.ArrayList;

import edu.auburn.eng.csse.comp3710.cma0036.droidclicker.R;
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
public class ResultFragment extends Fragment {

   // private QuestionsLoadingTask resultTask = null;

    private static final String TAG_QUESTIONS = "Questions";
    private static final String TAG_ANSWERS = "Answers";

    private AnswerCollection answers;
    private ArrayList<Question> questions;

    private Activity activity;

    private FontAwesomeText txtv_result_message;

    private long total = 0;
    private long corrects = 0;
    private long wrongs = 0;

    public ResultFragment newInstance(String text, ArrayList<Question> questions, AnswerCollection answers){
        ResultFragment mFragment = new ResultFragment();

        Bundle mBundle = new Bundle();
        mBundle.putString(Constant.TEXT_FRAGMENT, text);
        mBundle.putSerializable(TAG_QUESTIONS, questions);
        mBundle.putSerializable(TAG_ANSWERS, answers);
        mFragment.setArguments(mBundle);

        return mFragment;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(TAG_QUESTIONS, questions);
        outState.putSerializable(TAG_ANSWERS, answers);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_result, container, false);
            super.onCreate(savedInstanceState);

        getActivity().setRequestedOrientation(
                ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

         txtv_result_message = (FontAwesomeText) rootView.findViewById(R.id.result_message);

        if (getArguments()!= null) {
            questions = (ArrayList<Question>) getArguments().getSerializable(TAG_QUESTIONS);
            answers = (AnswerCollection) getArguments().getSerializable(TAG_ANSWERS);
        }

        total = questions.size();

        int i = 0;
        for(Answer answer : answers.getAnswers()) {
            System.out.println(answer.getId_alternative() + " " + questions.get(i).getIdRightAlternative());
            if(answer.getId_alternative().equals(questions.get(i++).getIdRightAlternative()))corrects++;
        }

            wrongs = total - corrects;

            PieChart mPieChart = (PieChart) rootView.findViewById(R.id.pie_chart);

            mPieChart.addPieSlice(new PieModel("Corrects", corrects, Color.parseColor("#FE6DA8")));
            mPieChart.addPieSlice(new PieModel("Wrongs", wrongs, Color.parseColor("#56B7F1")));

            long result = corrects*100/total;

            if(result >= 90)txtv_result_message.setText(getResources().getString(R.string.congratulations) + " A");
            else if(result >= 80)txtv_result_message.setText(getResources().getString(R.string.congratulations)+ " B");
            else if(result >= 70)txtv_result_message.setText(getResources().getString(R.string.congratulations)+ " C");
            else if(result >= 60)txtv_result_message.setText(getResources().getString(R.string.congratulations)+ " D");
            else txtv_result_message.setText(getResources().getString(R.string.try_again) + " F");

            mPieChart.startAnimation();

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

}




