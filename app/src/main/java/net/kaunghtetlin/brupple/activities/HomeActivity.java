package net.kaunghtetlin.brupple.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationAdapter;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.rd.PageIndicatorView;

import net.kaunghtetlin.brupple.R;
import net.kaunghtetlin.brupple.adapters.GuideAdapter;
import net.kaunghtetlin.brupple.adapters.HighlightImagePagerAdapter;
import net.kaunghtetlin.brupple.adapters.PromotionAdapter;
import net.kaunghtetlin.brupple.components.ViewPagerCustomDuration;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity {

    @BindView(R.id.vp_highlight_images)
    ViewPagerCustomDuration vpHighlightImages;

    @BindView(R.id.pageIndicatorView)
    PageIndicatorView pageIndicatorView;

    @BindView(R.id.rv_guide)
    RecyclerView rvGuide;

    @BindView(R.id.rv_promotion)
    RecyclerView rvPromotion;

//    @BindView(R.id.bottom_navigation)
//    BottomNavigationView bottomNavigationView;

    @BindView(R.id.bottom_navigation)
    AHBottomNavigation bottomNavigation;


    HighlightImagePagerAdapter mHighlightImagePagerAdapter;

    GuideAdapter mGudieAdapter;
    PromotionAdapter mPromotionAdapter;

    //variables for image view pager auto rotate
    int currentPage = 0;
    Timer timer;
    final long DELAY_MS = 5; //delay in milliseconds before task is to be executed
    final long PERIOD_MS = 3000; // time in milliseconds between successive task executions.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ButterKnife.bind(this, this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        // ViewPager Image
        mHighlightImagePagerAdapter = new HighlightImagePagerAdapter(getApplicationContext());
        vpHighlightImages.setAdapter(mHighlightImagePagerAdapter);
        vpHighlightImages.setOffscreenPageLimit(mHighlightImagePagerAdapter.getCount());

        //for pageIndicator
        pageIndicatorView.setCount(mHighlightImagePagerAdapter.getCount()); // specify total count of indicators
        pageIndicatorView.setSelection(0);

        //RecyclerView for Burpple Guide
        rvGuide.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.HORIZONTAL, false));

        mGudieAdapter = new GuideAdapter(getApplicationContext());
        rvGuide.setAdapter(mGudieAdapter);

        //RecyclerView for Promotions
        rvPromotion.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.HORIZONTAL, false));

        mPromotionAdapter = new PromotionAdapter(getApplicationContext());
        rvPromotion.setAdapter(mPromotionAdapter);

        //set up for image view pager rotate automatically
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                currentPage = vpHighlightImages.getCurrentItem() + 1;
                if (currentPage == mHighlightImagePagerAdapter.getCount()) {
                    currentPage = 0;
                }
                // for smoothscroll.. for this line i added two custom component
                vpHighlightImages.setScrollDurationFactor(5);
                vpHighlightImages.setCurrentItem(currentPage, true);
            }
        };

        timer = new Timer(); // This will create a new Thread
        timer.schedule(new TimerTask() { // task to be scheduled

            @Override
            public void run() {
                handler.post(Update);
            }
        }, DELAY_MS, PERIOD_MS);

     /*   // for bottom navigation view
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_explore:

                            case R.id.action_guide:

                            case R.id.action_add:

                            case R.id.action_activity:

                            case R.id.action_profile:

                        }
                        return true;
                    }
                });*/

        //new bottom navigation
//        AHBottomNavigationItem iExplore = new AHBottomNavigationItem(R.string.txt_explore, R.drawable.ic_explore_24dp, R.color.primary);
//        AHBottomNavigationItem iGuide = new AHBottomNavigationItem(R.string.txt_guide, R.drawable.ic_guide_24dp, R.color.primary);
//        AHBottomNavigationItem iAdd = new AHBottomNavigationItem(R.string.txt_add, R.drawable.ic_add_box_24dp, R.color.primary);
//        AHBottomNavigationItem iActivity= new AHBottomNavigationItem(R.string.txt_activity, R.drawable.ic_activity_24dp, R.color.primary);
//        AHBottomNavigationItem iProfile= new AHBottomNavigationItem(R.string.txt_profile, R.drawable.ic_person_24dp, R.color.primary);

//        int[] tabColors = getApplicationContext().getResources().getIntArray(R.array.tab_colors);
        AHBottomNavigationAdapter navigationAdapter = new AHBottomNavigationAdapter(this, R.menu.bottom_navigation_menu);
        navigationAdapter.setupWithBottomNavigation(bottomNavigation);

        // Set background color
        bottomNavigation.setDefaultBackgroundColor(getResources().getColor(R.color.pure_white));

// Disable the translation inside the CoordinatorLayout
        bottomNavigation.setBehaviorTranslationEnabled(false);

// Change colors
        bottomNavigation.setAccentColor(getResources().getColor(R.color.primary));
        bottomNavigation.setInactiveColor(getResources().getColor(R.color.divider));

// Force to tint the drawable (useful for font with icon for example)
        bottomNavigation.setForceTint(true);

// Display color under navigation bar (API 21+)
// Don't forget these lines in your style-v21
// <item name="android:windowTranslucentNavigation">true</item>
// <item name="android:fitsSystemWindows">true</item>
        bottomNavigation.setTranslucentNavigationEnabled(true);

        bottomNavigation.setBehaviorTranslationEnabled(true);
// Manage titles
//        bottomNavigation.setTitleState(AHBottomNavigation.TitleState.SHOW_WHEN_ACTIVE);
        bottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);
//        bottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_HIDE);

// Use colored navigation with circle reveal effect
//        bottomNavigation.setColored(true);

// Set current item programmatically
        bottomNavigation.setCurrentItem(0);

// Customize notification (title, background, typeface)
        bottomNavigation.setNotificationBackgroundColor(Color.parseColor("#F63D2B"));

// Add or remove notification for each item
        bottomNavigation.setNotification("1", 3);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
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
    /*
    appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
        boolean isShow = false;
        int scrollRange = -1;

        @Override public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
            if (scrollRange == -1) {
                scrollRange = appBarLayout.getTotalScrollRange();
            }
            if (scrollRange + verticalOffset == 0) {
                getSupportActionBar().setTitle(series.title_english);
                isShow = true;
            } else if (isShow) {
                getSupportActionBar().setTitle("");
                isShow = false;
            }
        }
    });*/
}
