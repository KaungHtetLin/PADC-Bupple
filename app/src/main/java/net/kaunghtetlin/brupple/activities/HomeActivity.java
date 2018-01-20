package net.kaunghtetlin.brupple.activities;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationAdapter;
import com.rd.PageIndicatorView;

import net.kaunghtetlin.brupple.R;
import net.kaunghtetlin.brupple.adapters.GuidesAdapter;
import net.kaunghtetlin.brupple.adapters.FeaturedImagePagerAdapter;
import net.kaunghtetlin.brupple.adapters.PromotionsAdapter;
import net.kaunghtetlin.brupple.components.ViewPagerCustomDuration;
import net.kaunghtetlin.brupple.data.vos.FeaturedVO;
import net.kaunghtetlin.brupple.data.vos.GuidesVO;
import net.kaunghtetlin.brupple.data.vos.PromotionsVO;
import net.kaunghtetlin.brupple.persistance.BurppleContract;
import net.kaunghtetlin.brupple.utils.AppConstants;

import java.util.ArrayList;
import java.util.List;
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

    FeaturedImagePagerAdapter mFeaturedImagePagerAdapter;

    GuidesAdapter mGudieAdapter;
    PromotionsAdapter mPromotionsAdapter;

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
        mFeaturedImagePagerAdapter = new FeaturedImagePagerAdapter(getApplicationContext());
        vpHighlightImages.setAdapter(mFeaturedImagePagerAdapter);
        vpHighlightImages.setOffscreenPageLimit(mFeaturedImagePagerAdapter.getCount());

        //for pageIndicator
        pageIndicatorView.setCount(mFeaturedImagePagerAdapter.getCount()); // specify total count of indicators
        pageIndicatorView.setSelection(0);

        //RecyclerView for Burpple Guide
        rvGuide.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.HORIZONTAL, false));

        mGudieAdapter = new GuidesAdapter(getApplicationContext());
        rvGuide.setAdapter(mGudieAdapter);

        //RecyclerView for Promotions
        rvPromotion.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.HORIZONTAL, false));

        mPromotionsAdapter = new PromotionsAdapter(getApplicationContext());
        rvPromotion.setAdapter(mPromotionsAdapter);

        //set up for image view pager rotate automatically
        setupImageViewPagerRotateAuto();

        //for bottom navigation
        setupBottomNavigation();

        getSupportLoaderManager().initLoader(AppConstants.GUIDES_LIST_LOADER, null, guidesLoaderListener);
        getSupportLoaderManager().initLoader(AppConstants.FEATURED_LIST_LOADER, null, FeaturedLoaderListener);
        getSupportLoaderManager().initLoader(AppConstants.PROMOTIONS_LIST_LOADER, null, PromotionsLoaderListener);

//        getSupportLoaderManager().initLoader(AppConstants.GUIDES_LIST_LOADER, null, this);
//        getSupportLoaderManager().initLoader(AppConstants.FEATURED_LIST_LOADER, null, this);
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

    private LoaderManager.LoaderCallbacks<Cursor> guidesLoaderListener = new LoaderManager.LoaderCallbacks<Cursor>() {
        @Override
        public Loader<Cursor> onCreateLoader(int id, Bundle args) {
            return new CursorLoader(getApplicationContext(),
                    BurppleContract.GuidesEntry.CONTENT_URI,
                    null,
                    null, null,
                    null);
        }

        @Override
        public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
            if (data != null && data.moveToFirst()) {
                List<GuidesVO> guidesList = new ArrayList<>();

                do {
                    GuidesVO guides = GuidesVO.parseFromCursor(data);
                    guidesList.add(guides);
                } while (data.moveToNext());
                mGudieAdapter.setNewData(guidesList);
            }
        }

        @Override
        public void onLoaderReset(Loader<Cursor> loader) {

        }
    };

    private LoaderManager.LoaderCallbacks<Cursor> FeaturedLoaderListener = new LoaderManager.LoaderCallbacks<Cursor>() {
        @Override
        public Loader<Cursor> onCreateLoader(int id, Bundle args) {
            return new CursorLoader(getApplicationContext(),
                    BurppleContract.FeaturedEntry.CONTENT_URI,
                    null,
                    null, null,
                    null);
        }

        @Override
        public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
            if (data != null && data.moveToFirst()) {
//            List<FeaturedVO> featuredList = new ArrayList<>();
                List<String> images = new ArrayList<>();
                do {
                    FeaturedVO featured = FeaturedVO.parseFromCursor(data);
//                featuredList.add(featured);
                    images.add(featured.getBurppleFeaturedImage());
                } while (data.moveToNext());
                mFeaturedImagePagerAdapter.setImages(images);

            }
        }

        @Override
        public void onLoaderReset(Loader<Cursor> loader) {

        }
    };


    private LoaderManager.LoaderCallbacks<Cursor> PromotionsLoaderListener = new LoaderManager.LoaderCallbacks<Cursor>() {
        @Override
        public Loader<Cursor> onCreateLoader(int id, Bundle args) {
            return new CursorLoader(getApplicationContext(),
                    BurppleContract.PromotionsEntry.CONTENT_URI,
                    null,
                    null, null,
                    null);
        }

        @Override
        public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
            if (data != null && data.moveToFirst()) {
                List<PromotionsVO> promotionsList = new ArrayList<>();
                do {
                    PromotionsVO promotions = PromotionsVO.parseFromCursor(getApplicationContext(),data);
//                featuredList.add(featured);
                    promotionsList.add(promotions);
                } while (data.moveToNext());
                mPromotionsAdapter.setNewData(promotionsList);
            }
        }

        @Override
        public void onLoaderReset(Loader<Cursor> loader) {

        }
    };


    private void setupImageViewPagerRotateAuto() {
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                currentPage = vpHighlightImages.getCurrentItem() + 1;
                if (currentPage == mFeaturedImagePagerAdapter.getCount()) {
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

    }

    private void setupBottomNavigation() {

        AHBottomNavigationAdapter navigationAdapter = new AHBottomNavigationAdapter(this, R.menu.bottom_navigation_menu);
        navigationAdapter.setupWithBottomNavigation(bottomNavigation);

        bottomNavigation.setDefaultBackgroundColor(getResources().getColor(R.color.pure_white));
        bottomNavigation.setBehaviorTranslationEnabled(false);
        bottomNavigation.setAccentColor(getResources().getColor(R.color.primary));
        bottomNavigation.setInactiveColor(getResources().getColor(R.color.divider));
        bottomNavigation.setForceTint(true);
        bottomNavigation.setTranslucentNavigationEnabled(true);

        bottomNavigation.setBehaviorTranslationEnabled(true);
        bottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);
        bottomNavigation.setCurrentItem(0);
        bottomNavigation.setNotificationBackgroundColor(Color.parseColor("#F63D2B"));
        bottomNavigation.setNotification("1", 3);

    }
}
