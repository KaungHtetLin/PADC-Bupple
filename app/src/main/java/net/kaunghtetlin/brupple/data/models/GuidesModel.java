package net.kaunghtetlin.brupple.data.models;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import net.kaunghtetlin.brupple.BurppleApp;
import net.kaunghtetlin.brupple.data.vos.GuidesVO;
import net.kaunghtetlin.brupple.events.RestApiEvents;
import net.kaunghtetlin.brupple.network.BurppleDataAgentImpl;
import net.kaunghtetlin.brupple.persistance.BurppleContract;
import net.kaunghtetlin.brupple.utils.AppConstants;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kaung Htet Lin on 1/10/2018.
 */

public class GuidesModel {

    private static GuidesModel objInstance;

    private List<GuidesVO> mGuides;
    private int mGuidesPageIndex = 1;

    private GuidesModel() {
        EventBus.getDefault().register(this);
        mGuides = new ArrayList<>();
    }

    public static GuidesModel getObjInstance() {
        if (objInstance == null) {
            objInstance = new GuidesModel();
        }
        return objInstance;
    }

    public void startLoadingGuides(Context context) {
        BurppleDataAgentImpl.getObjInstance().loadGuides(AppConstants.ACCESS_TOKEN,
                mGuidesPageIndex, context);
    }

    public List<GuidesVO> getGuides() {
        if (mGuides == null) {
            return new ArrayList<>();
        }
        return mGuides;
    }

    public void loadMoreGuides(Context context) {
        BurppleDataAgentImpl.getObjInstance().loadGuides(AppConstants.ACCESS_TOKEN,
                mGuidesPageIndex, context);
    }

    public void forceRefresGuides(Context context) {
        mGuides = new ArrayList<>();
        mGuidesPageIndex = 1;
//        ConfigUtils.getObjInstance().savePageIndex(1);
        startLoadingGuides(context);
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onGuidesDataLoaded(RestApiEvents.GuidesDataLoadedEvent event) {

        mGuides.addAll(event.getLoadedGuides());
        mGuidesPageIndex = event.getLoadedPageIndex() + 1;
//        ConfigUtils.getObjInstance().savePageIndex(event.getLoadedPageIndex()+1);

        ContentValues[] guidesCVs = new ContentValues[event.getLoadedGuides().size()];
//        List<ContentValues> guidesCVList = new ArrayList<>();

        for (int index = 0; index < guidesCVs.length; index++) {
            GuidesVO guides = event.getLoadedGuides().get(index);
            guidesCVs[index] = guides.parseToContentValues();
        }

//        ContentValues[] healthCVs = new ContentValues[healthList.size()+1];

//        for (int index = 0; index < healthList.size(); index++) {
//            HealthVO health = healthList.get(index);
//            healthCVs[index] = health.parseToContentValues("0");
//        }

        int insertedRows = event.getContext().getContentResolver().bulkInsert(BurppleContract.GuidesEntry.CONTENT_URI, guidesCVs);
        Log.d(BurppleApp.LOG_TAG, "Inserted Rows" + insertedRows);
    }
}
