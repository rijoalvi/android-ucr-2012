package com.moneyorganizer;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SwipoeViews extends FragmentActivity {

	CollectionPagerAdapter mCollectionPagerAdapter;
	 
    /**
     * The {@link android.support.v4.view.ViewPager} that will display the
     * object collection.
     */
    ViewPager mViewPager;
 
    public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_swipoe_views);
 
    // Create an adapter that when requested, will return a fragment
    // representing an object in
    // the collection.
    //
    // ViewPager and its adapters use support library fragments, so we must
    // use
    // getSupportFragmentManager.
    mCollectionPagerAdapter = new CollectionPagerAdapter(
        getSupportFragmentManager());
 
    // Set up action bar.
    // Specify that the Home button should show an "Up" caret, indicating
    // that touching the
    // button will take the user one step up in the application's hierarchy.
 
    // Set up the ViewPager, attaching the adapter.
    mViewPager = (ViewPager) findViewById(R.id.pager);
    mViewPager.setAdapter(mCollectionPagerAdapter);
    }
 
    /**
     * A {@link android.support.v4.app.FragmentStatePagerAdapter} that returns a
     * fragment representing an object in the collection.
     */
    public class CollectionPagerAdapter extends FragmentStatePagerAdapter {
 
    final int NUM_ITEMS = 3; // number of tabs
 
    public CollectionPagerAdapter(FragmentManager fm) {
        super(fm);
    }
 
    @Override
    public Fragment getItem(int i) {
        Fragment fragment = new TabFragment();
        Bundle args = new Bundle();
        args.putInt(TabFragment.ARG_OBJECT, i);
        fragment.setArguments(args);
        return fragment;
    }
 
    @Override
    public int getCount() {
        return NUM_ITEMS;
    }
 
    @Override
    public CharSequence getPageTitle(int position) {
 
        String tabLabel = null;
        switch (position) {
        case 0:
        tabLabel = getString(R.string.title_section1);
        break;
        case 1:
        tabLabel = getString(R.string.title_section2);
        break;
        case 2:
        tabLabel = getString(R.string.title_section3);
        break;
        }
 
        return tabLabel;
    }
    }
 
    /**
     * A dummy fragment representing a section of the app, but that simply
     * displays dummy text.
     */
    public static class TabFragment extends Fragment {
 
    public static final String ARG_OBJECT = "object";
 
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
 
        Bundle args = getArguments();
        int position = args.getInt(ARG_OBJECT);
 
        int tabLayout = 0;
        switch (position) {
        case 0:
        tabLayout = R.layout.activity_pantalla_principal;
        break;
        case 1:
        tabLayout = R.layout.activity_total_de_gastos;
        break;
        case 2:
        tabLayout = R.layout.activity_pantalla_principal;
        break;
        }
 
        View rootView = inflater.inflate(tabLayout, container, false);
 
        return rootView;
		}
	}
}
