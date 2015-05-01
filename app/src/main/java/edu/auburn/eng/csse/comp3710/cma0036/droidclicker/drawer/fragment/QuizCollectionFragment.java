package edu.auburn.eng.csse.comp3710.cma0036.droidclicker.drawer.fragment;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import edu.auburn.eng.csse.comp3710.cma0036.droidclicker.R;
import edu.auburn.eng.csse.comp3710.cma0036.droidclicker.drawer.utils.Constant;
import edu.auburn.eng.csse.comp3710.cma0036.droidclicker.models.quiz.Quiz;
import edu.auburn.eng.csse.comp3710.cma0036.droidclicker.models.quiz.QuizCollection;

/**
 * Created by caioa_000 on 23/04/2015.
 */
public class QuizCollectionFragment extends ListFragment {

    private Activity activity;

    private QuizzesLoadingTask authTask = null;

    private QuizCollection quizCollection = null;

    private ListArrayAdpater adapter = null;
    private LinearLayout layoutList = null;
    private View loadQuizStatusView;

    @Override
    public void onListItemClick(ListView l, View v, int pos, long id) {
        String item = (String) getListAdapter().getItem(pos);
        //if (item.equals(getString(R.string.find_partners))) {
        ((QuizCollectionFragmentInterface)activity).onQuizClicked(quizCollection.getQuizzes().get(pos));
        //}
        //Toast.makeText(this.getActivity(), quizCollection.getQuizzes().get(pos).getDuration(), Toast.LENGTH_LONG).show();
    }

    public QuizCollectionFragment newInstance(String text){
        QuizCollectionFragment mFragment = new QuizCollectionFragment();
        Bundle mBundle = new Bundle();
        mBundle.putString(Constant.TEXT_FRAGMENT, text);
        mFragment.setArguments(mBundle);
        return mFragment;
    }

    public void refreshList() {
            int i = 0;
        String[] values = new String[quizCollection.getQuizzes().size()];
        for(Quiz quiz : quizCollection.getQuizzes()){
            values[i++] = "Quiz " + quiz.getId();
        }
        adapter = new ListArrayAdpater(this.getActivity(), values);
        setListAdapter(adapter);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            // TODO Auto-generated method stub
            View rootView = inflater.inflate(R.layout.fragment_quiz_collection, container, false);
            super.onCreate(savedInstanceState);

            quizCollection = new QuizCollection();

            layoutList = (LinearLayout) rootView.findViewById(R.id.layout_list);
            loadQuizStatusView = rootView.findViewById(R.id.layout_load_status);

            rootView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT ));
            return rootView;
       }

    @Override
    public void onResume() {
        super.onResume();
        attemptQuizRetrieve();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
    }

    public void attemptQuizRetrieve(){
        showProgress(true);
        authTask = new QuizzesLoadingTask();
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

            loadQuizStatusView.setVisibility(View.VISIBLE);
            loadQuizStatusView.animate().setDuration(shortAnimTime).alpha(show ? 1 : 0)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            loadQuizStatusView.setVisibility(show ? View.VISIBLE
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
            loadQuizStatusView.setVisibility(show ? View.VISIBLE : View.GONE);
            layoutList.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    public class QuizzesLoadingTask extends AsyncTask<Void, Void, Boolean> {
        @Override
        protected Boolean doInBackground(Void... params) {

            try {
                quizCollection.loadAllQuizzes();
            } catch (Exception e) {
                return false;
            }

            if (quizCollection.getQuizzes()== null)return false;
            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            authTask = null;
            showProgress(false);

            if (success) {
                refreshList();
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

    public class ListArrayAdpater extends ArrayAdapter<String> {
        private final Context context;
        private final String[] values;

        public ListArrayAdpater(Context context, String[] values) {
            super(context, R.layout.list_item_layout, values);
            this.context = context;
            this.values = values;
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rowView = inflater.inflate(R.layout.list_item_layout, parent,false);

            TextView txtTitle = (TextView) rowView.findViewById(R.id.firstLine);
            TextView txtDescription = (TextView) rowView.findViewById(R.id.secondLine);
            ImageView imgvIcon = (ImageView) rowView.findViewById(R.id.icon);


            imgvIcon.setImageResource(R.drawable.default_user);
            txtTitle.setText(values[position]);
            txtDescription.setText(quizCollection.getQuizzes().get(position).getDuration() + " seconds");

            return rowView;
        }
    }

    public interface QuizCollectionFragmentInterface {
        public void onQuizClicked(Quiz quiz);
    }

}




