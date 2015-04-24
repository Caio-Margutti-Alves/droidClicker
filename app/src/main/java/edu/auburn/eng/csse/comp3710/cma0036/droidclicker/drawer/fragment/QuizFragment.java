package edu.auburn.eng.csse.comp3710.cma0036.droidclicker.drawer.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import edu.auburn.eng.csse.comp3710.cma0036.droidclicker.R;
import edu.auburn.eng.csse.comp3710.cma0036.droidclicker.drawer.utils.Constant;
import edu.auburn.eng.csse.comp3710.cma0036.droidclicker.viewUtils.RangeSeekBar;

/**
 * Created by caioa_000 on 23/04/2015.
 */
public class QuizFragment extends Fragment {


        private SeekBar skbAgeBetween;
        private RangeSeekBar<Integer> rskbRadiusBetween;
        private TextView txtvSearchRadiusResp;


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

            if (savedInstanceState != null) {

            }else{
                AlternativeFragment alternativeFragment = new AlternativeFragment();

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.add(R.id.fragment_alternatives, alternativeFragment, TAG_ALTERNATIVES[i]);
                //transaction.addToBackStack(TAG_HAIKU);
                transaction.commit();

            }

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
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            return super.onOptionsItemSelected(item);
        }
    }

}
