package com.xfc.lovebank;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import com.xfc.lovebank.ui.BBPomodoroView;
import com.xfc.lovebank.ui.BBUtilsView;
import com.xfc.lovebank.ui.base.BBBaseView;
import com.xfc.lovebank.utils.CONST_VALUES;


public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, PlaceholderFragment.newInstance(position))
                .commit();
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case CONST_VALUES.PAGE_INDEX.PAGE_UTILS_INDEX:
                mTitle = getString(R.string.title_utils);
                break;
            case CONST_VALUES.PAGE_INDEX.PAGE_POMODORO_INDEX:
                mTitle = getString(R.string.title_pomodoro);
                break;
            case CONST_VALUES.PAGE_INDEX.PAGE_OVERVIEW_INDEX:
                mTitle = getString(R.string.title_overview);
                break;
            case CONST_VALUES.PAGE_INDEX.PAGE_STATISTICS_INDEX:
                mTitle = getString(R.string.title_statistics);
                break;
            case CONST_VALUES.PAGE_INDEX.PAGE_HISTORY_INDEX:
                mTitle = getString(R.string.title_history);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */

        private BBBaseView baseView;

        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            int sectionIdx = getArguments().getInt(ARG_SECTION_NUMBER);
            View rootView;
            switch (sectionIdx) {
                case CONST_VALUES.PAGE_INDEX.PAGE_UTILS_INDEX:
                    baseView = new BBUtilsView(getActivity());
                    rootView = baseView.onCreateView();
                    break;
                case CONST_VALUES.PAGE_INDEX.PAGE_POMODORO_INDEX:
                    baseView = new BBPomodoroView(getActivity());
                    rootView = baseView.onCreateView();
                    break;
                case CONST_VALUES.PAGE_INDEX.PAGE_OVERVIEW_INDEX:
                case CONST_VALUES.PAGE_INDEX.PAGE_STATISTICS_INDEX:
                case CONST_VALUES.PAGE_INDEX.PAGE_HISTORY_INDEX:
//                    rootView = inflater.inflate(R.layout.fragment_main, container, false);
                    baseView = new BBUtilsView(getActivity());
                    rootView = baseView.onCreateView();
                    break;
                default:
                    baseView = new BBUtilsView(getActivity());
                    rootView = baseView.onCreateView();
                    // rootView = inflater.inflate(R.layout.fragment_main, container, false);
                    break;
            }

            return rootView;
        }

        @Override
        public void onDestroyView() {
            baseView.saveState();
            super.onDestroyView();
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }

        @Override
        public void onPause() {
            baseView.onHidView();
            super.onPause();
        }
    }

}

// TODO ToastManager
// TODO HostGenerator