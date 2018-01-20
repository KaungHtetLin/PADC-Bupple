package net.kaunghtetlin.brupple.data.models;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import net.kaunghtetlin.brupple.BurppleApp;
import net.kaunghtetlin.brupple.data.vos.BurppleShopVO;
import net.kaunghtetlin.brupple.data.vos.PromotionsVO;
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
 * Created by Kaung Htet Lin on 1/10/2018.
 */

public class PromotionsModel {

    private static PromotionsModel objInstance;

    private List<PromotionsVO> mPromotions;
    private int mPromotionsPageIndex = 1;

    @Inject
    BurppleDataAgentImpl mBurppleDataAgentImpl;

    public PromotionsModel(Context context) {
        EventBus.getDefault().register(this);
        mPromotions = new ArrayList<>();

        BurppleApp burppleApp = (BurppleApp) context.getApplicationContext();
        burppleApp.getAppComponent().inject(this);
    }
/*
    public static PromotionsModel getObjInstance() {
        if (objInstance == null) {
            objInstance = new PromotionsModel();
        }
        return objInstance;
    }
*/

    public void startLoadingPromotions(Context context) {
        mBurppleDataAgentImpl.loadPromotions(AppConstants.ACCESS_TOKEN,
                mPromotionsPageIndex, context);
    }

    public List<PromotionsVO> getmPromotions() {
        if (mPromotions == null) {
            return new ArrayList<>();
        }
        return mPromotions;
    }

    public void loadMorePromotions(Context context) {
        mBurppleDataAgentImpl.loadPromotions(AppConstants.ACCESS_TOKEN,
                mPromotionsPageIndex, context);
    }

    public void forceRefresPromotions(Context context) {
        mPromotions = new ArrayList<>();
        mPromotionsPageIndex = 1;
//        ConfigUtils.getObjInstance().savePageIndex(1);
        startLoadingPromotions(context);
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onPromotionsDataLoaded(RestApiEvents.PromotionsDataLoadedEvent event) {

        mPromotions.addAll(event.getLoadedPromotions());
        mPromotionsPageIndex = event.getLoadedPageIndex() + 1;
//        ConfigUtils.getObjInstance().savePageIndex(event.getLoadedPageIndex()+1);

        ContentValues[] promotionsCVs = new ContentValues[event.getLoadedPromotions().size()];
        List<ContentValues> burppleShopCVList = new ArrayList<>();
        List<ContentValues> termsInPromotionsCVList = new ArrayList<>();

//        List<ContentValues> guidesCVList = new ArrayList<>();

        for (int index = 0; index < promotionsCVs.length; index++) {
            PromotionsVO promotions = event.getLoadedPromotions().get(index);
            promotionsCVs[index] = promotions.parseToContentValues();

            BurppleShopVO publication = promotions.getBurpplePromotionShop();
            burppleShopCVList.add(publication.parseToContentValues());


            for (String terms : promotions.getBurpplePromotionTerms()) {
                ContentValues termsInPromotionsCV = new ContentValues();
                termsInPromotionsCV.put(BurppleContract.TermsEntry.COLUMN_PROMOTIONS_ID, promotions.getBurpplePromotionId());
                termsInPromotionsCV.put(BurppleContract.TermsEntry.COLUMN_DESC, terms);
                termsInPromotionsCVList.add(termsInPromotionsCV);
            }

        }

        int insertedShop = event.getContext().getContentResolver().bulkInsert(BurppleContract.ShopEntry.CONTENT_URI,
                burppleShopCVList.toArray(new ContentValues[0]));
        Log.d(BurppleApp.LOG_TAG, "inserted shops : " + insertedShop);

        int insertedTermsInPromotions = event.getContext().getContentResolver().bulkInsert(BurppleContract.TermsEntry.CONTENT_URI,
                termsInPromotionsCVList.toArray(new ContentValues[0]));
        Log.d(BurppleApp.LOG_TAG, "inserted terms-in-promotions : " + insertedTermsInPromotions);

        int insertedPromotions = event.getContext().getContentResolver().bulkInsert(BurppleContract.PromotionsEntry.CONTENT_URI, promotionsCVs);
        Log.d(BurppleApp.LOG_TAG, "Inserted Rows" + insertedPromotions);

    }

}
