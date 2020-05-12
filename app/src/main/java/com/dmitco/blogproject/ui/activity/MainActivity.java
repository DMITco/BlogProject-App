package com.dmitco.blogproject.ui.activity;
import android.os.Bundle;

import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.dmitco.blogproject.R;
import com.dmitco.blogproject.ui.fragment.AddFragment;
import com.dmitco.blogproject.ui.fragment.BaseFragment;
import com.dmitco.blogproject.ui.fragment.HomeFragment;
import com.dmitco.blogproject.ui.fragment.ProfileFragment;
import com.dmitco.blogproject.ui.fragment.SearchFragment;
import com.dmitco.blogproject.utility.FragmentHistory;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;
import com.ncapdevi.fragnav.FragNavController;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MainActivity extends BaseActivity implements
        BottomNavigationView.OnNavigationItemSelectedListener,
        BottomNavigationView.OnNavigationItemReselectedListener,
        FragNavController.RootFragmentListener,
        FragNavController.TransactionListener,
        BaseFragment.FragmentNavigation {



    private BottomNavigationView bottomNavigation;
    private FragNavController mNavController;
    private FragmentHistory fragmentHistory;
    private final int BOTTOM_NAVIGATION_COUNTS = 4;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeViews();
        initialize(savedInstanceState);
    }

    @Override
    public void initializeViews() {
        bottomNavigation = findViewById(R.id.bottomnavigation);

    }

    @Override
    public void initialize(Bundle savedInstanceState) {

        //bottom navigation

        bottomNavigation.setItemTextAppearanceActive(R.style.bottomNavigationFont);
        bottomNavigation.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_UNLABELED);
        bottomNavigation.setOnNavigationItemReselectedListener(this);
        bottomNavigation.setOnNavigationItemSelectedListener(this);

        //fragment navigation controller
        mNavController = new FragNavController(getSupportFragmentManager(),R.id.container);
        mNavController.setRootFragmentListener(this);
        mNavController.setTransactionListener(this);
        mNavController.setFragmentHideStrategy(FragNavController.HIDE);//prevent call onCreateView again when switch between tabs
        mNavController.initialize(BOTTOM_NAVIGATION_COUNTS-1,savedInstanceState);

        //fragmentHistory
        fragmentHistory = new FragmentHistory();

        //----
//        switchTab(BOTTOM_NAVIGATION_COUNTS-1);
        updateTabSelection(BOTTOM_NAVIGATION_COUNTS-1);


    }

    //region frag nav controller
    //return number of root fragments
    @Override
    public int getNumberOfRootFragments() {
        return BOTTOM_NAVIGATION_COUNTS;
    }
    //fragment navigation controller
    @NotNull
    @Override
    public Fragment getRootFragment(int index) {

        switch (index) {
            case FragNavController.TAB1:
                return new ProfileFragment();
            case FragNavController.TAB2:
                return new AddFragment();
            case FragNavController.TAB3:
                return new SearchFragment();
            case FragNavController.TAB4:
                return new HomeFragment();
        }
        throw new IllegalStateException("Need to send an index that we know");
    }

    //fragment navigation controller
    @Override
    public void onFragmentTransaction(@Nullable Fragment fragment, @NotNull FragNavController.TransactionType transactionType) {

    }
    //fragment navigation controller
    @Override
    public void onTabTransaction(@Nullable Fragment fragment, int i) {

    }

    //endregion

    //region fragment navigation interface
    @Override
    public void pushFragment(Fragment fragment) {
        if (mNavController != null) {
            mNavController.pushFragment(fragment);
        }
    }

    @Override
    public void popFragment(int popDepth) {
        if (mNavController != null) {
            mNavController.popFragments(popDepth);
        }
    }
    //endregion

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mNavController != null) {
            mNavController.onSaveInstanceState(outState);
        }
    }

    //bottom navigation
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int position = getSelectedItemPosition(item.getItemId());
        fragmentHistory.push(position);
        switchTab(position);
        return true;
    }

    //bottom navigation
    @Override
    public void onNavigationItemReselected(@NonNull MenuItem item) {
        int position = getSelectedItemPosition(item.getItemId());
        mNavController.clearStack();
        switchTab(position);
    }

    private void switchTab(int position) {
        mNavController.switchTab(position);
    }

    private void updateTabSelection(int position) {
        //after switch tab by NavigationController and update bottom navigation. for prevent call bottom navigation listener
        // rest and set listener before and after setSelectedItemId()
        bottomNavigation.setOnNavigationItemSelectedListener(null);
        bottomNavigation.setOnNavigationItemReselectedListener(null);
        bottomNavigation.setSelectedItemId(getSelectedPositionID(position));
        bottomNavigation.setOnNavigationItemSelectedListener(this);
        bottomNavigation.setOnNavigationItemReselectedListener(this);
    }

    @Override
    public void onBackPressed() {
        if (!mNavController.isRootFragment()) {
            mNavController.popFragment();
        } else {
            if (fragmentHistory.isEmpty()) {
                super.onBackPressed();
            } else {
                if (fragmentHistory.getStackSize() > 1) {
                    int position = fragmentHistory.popPrevious();
                    switchTab(position);
                    updateTabSelection(position);

                } else {
                    switchTab(BOTTOM_NAVIGATION_COUNTS-1);
                    updateTabSelection(BOTTOM_NAVIGATION_COUNTS-1);
                    fragmentHistory.emptyStack();
                }
            }

        }
    }

    /**
    *@return selected bottom navigation id
     */
    private int getSelectedPositionID(int position){
        switch (position) {
            case FragNavController.TAB1:
                return R.id.profile;
            case FragNavController.TAB2:
                return R.id.add;
            case FragNavController.TAB3:
                return R.id.search;
            case FragNavController.TAB4:
                return R.id.home;
        }
        throw new IllegalStateException("Need to send an index that we know");
    }

    /**
     * @return the position of selected item Id in bottom navigation
     */
    private int getSelectedItemPosition(int selectedItemId){
        switch (selectedItemId) {
            case R.id.profile:
                return FragNavController.TAB1;
            case R.id.add :
                return FragNavController.TAB2;
            case R.id.search:
                return FragNavController.TAB3;
            case R.id.home:
                return FragNavController.TAB4 ;
        }
        throw new IllegalStateException("Need to send an id that we know");
    }
}
