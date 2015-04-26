package edu.auburn.eng.csse.comp3710.cma0036.droidclicker.drawer.fragment;

import android.app.ListFragment;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import edu.auburn.eng.csse.comp3710.cma0036.droidclicker.R;
import edu.auburn.eng.csse.comp3710.cma0036.droidclicker.drawer.navigation.NavigationMain;
import edu.auburn.eng.csse.comp3710.cma0036.droidclicker.drawer.utils.Constant;
import edu.auburn.eng.csse.comp3710.cma0036.droidclicker.models.user.User;
import edu.auburn.eng.csse.comp3710.cma0036.droidclicker.viewUtils.RangeSeekBar;

/**
 * Created by caioa_000 on 23/04/2015.
 */
public class QuizFragment extends Fragment {

    private QuizzesLoadingTask authTask = null;

    /*@Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        String item = (String) getListAdapter().getItem(position);
        //if (item.equals(getString(R.string.find_partners))) {
            //Intent intent = new Intent(this, FindPartnerActivity.class);
            //startActivity(intent);
       // }
            Toast.makeText(this.getActivity(), item + " selected", Toast.LENGTH_LONG).show();
    }*/


    public static QuizFragment newInstance(String sentence) {
        QuizFragment QuizFragment = new QuizFragment();

        Bundle args = new Bundle();
        //args.putString(TAG_HAIKU_TEXT, sentence);
        QuizFragment.setArguments(args);

        return QuizFragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            // TODO Auto-generated method stub
            View rootView = inflater.inflate(R.layout.fragment_quiz, container, false);
            super.onCreate(savedInstanceState);

            final ListView listview = (ListView) rootView.findViewById(R.id.lst_quizzes);

            String[] values = new String[] { "Android", "iPhone",};

            final ListArrayAdpater adapter = new ListArrayAdpater(this.getActivity(), values);
            listview.setAdapter(adapter);


            rootView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT ));
            return rootView;
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
            View rowView = null;

            rowView = inflater.inflate(R.layout.list_item_layout, parent,false);
            TextView txtTitle = (TextView) rowView.findViewById(R.id.firstLine);
            TextView txtDescription = (TextView) rowView.findViewById(R.id.secondLine);
            ImageView imgvIcon = (ImageView) rowView.findViewById(R.id.icon);
            txtTitle.setText(values[position]);
            String s = values[position];
            /*if (s.equals(context.getString(R.string.find_partners))) {
                imgvIcon.setImageResource(R.drawable.ic_action_find_partner);
                txtDescription.setText(R.string.d_find_partners);
            }*/

            return rowView;
        }
    }

    public class QuizzesLoadingTask extends AsyncTask<Void, Void, Boolean> {
        @Override
        protected Boolean doInBackground(Void... params) {
            try {
                Quiz.get();
            } catch (Exception e) {
                return false;
            }

            if (User.getId()== null || User.getId().isEmpty())return false;
            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            authTask = null;
            showProgress(false);

            if (success) {

            } else {

            }
        }

        @Override
        protected void onCancelled() {
            authTask = null;
            showProgress(false);
        }
    }
}

}


