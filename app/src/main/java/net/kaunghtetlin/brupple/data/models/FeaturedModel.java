package net.kaunghtetlin.brupple.data.models;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import net.kaunghtetlin.brupple.BurppleApp;
import net.kaunghtetlin.brupple.data.vos.FeaturedVO;
import net.kaunghtetlin.brupple.events.RestApiEvents;
import net.kaunghtetlin.brupple.network.BurppleDataAgentImpl;
import net.kaunghtetlin.brupple.persistance.BurppleContract;
import net.kaunghtetlin.brupple.utils.AppConstants;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Kaung Htet Lin on 1/18/2018.
 */

public class FeaturedModel {

    private static FeaturedModel objInstance;

    private List<FeaturedVO> mFeatured;
    private int mFeaturedPageIndex = 1;

    @Inject
    BurppleDataAgentImpl mBurppleDataAgentImpl;

    public FeaturedModel(Context context) {
        EventBus.getDefault().register(this);
        mFeatured = new ArrayList<>();

        BurppleApp burppleApp = (BurppleApp) context.getApplicationContext();
        burppleApp.getAppComponent().inject(this);
    }

  /*  public static FeaturedModel getObjInstance() {
        if (objInstance == null) {
            objInstance = new FeaturedModel();
        }
        return objInstance;
    }*/

    public void startLoadingFeatured(Context context) {
        mBurppleDataAgentImpl.loadFeatured(AppConstants.ACCESS_TOKEN,
                mFeaturedPageIndex, context);
    }

    public List<FeaturedVO> getFeatured() {
        if (mFeatured == null) {
            return new ArrayList<>();
        }
        return mFeatured;
    }

    public void loadMoreFeatured(Context context) {
        mBurppleDataAgentImpl.loadFeatured(AppConstants.ACCESS_TOKEN,
                mFeaturedPageIndex, context);
    }

    public void forceRefresFeatured(Context context) {
        mFeatured = new ArrayList<>();
        mFeaturedPageIndex = 1;
//        ConfigUtils.getObjInstance().savePageIndex(1);
        startLoadingFeatured(context);
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onFeaturedDataLoaded(RestApiEvents.FeaturedDataLoadedEvent event) {

        mFeatured.addAll(event.getLoadedFeatured());
        mFeaturedPageIndex = event.getLoadedPageIndex() + 1;
//        ConfigUtils.getObjInstance().savePageIndex(event.getLoadedPageIndex()+1);

        ContentValues[] featuredCVs = new ContentValues[event.getLoadedFeatured().size()];
//        List<ContentValues> featuredCVList = new ArrayList<>();

        for (int index = 0; index < featuredCVs.length; index++) {
            FeaturedVO featured = event.getLoadedFeatured().get(index);
            featuredCVs[index] = featured.parseToContentValues();
        }

//        ContentValues[] healthCVs = new ContentValues[healthList.size()+1];

//        for (int index = 0; index < healthList.size(); index++) {
//            HealthVO health = healthList.get(index);
//            healthCVs[index] = health.parseToContentValues("0");
//        }

        int insertedRows = event.getContext().getContentResolver().bulkInsert(BurppleContract.FeaturedEntry.CONTENT_URI, featuredCVs);
        Log.d(BurppleApp.LOG_TAG, "Inserted Rows" + insertedRows);
    }

}
