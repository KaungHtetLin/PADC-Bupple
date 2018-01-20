package net.kaunghtetlin.brupple.mvp.presenters;

import android.content.Context;
import android.database.Cursor;

import net.kaunghtetlin.brupple.BurppleApp;
import net.kaunghtetlin.brupple.data.vos.FeaturedVO;
import net.kaunghtetlin.brupple.data.vos.GuidesVO;
import net.kaunghtetlin.brupple.data.vos.PromotionsVO;
import net.kaunghtetlin.brupple.mvp.views.BurppleView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kaung Htet Lin on 1/20/2018.
 */

public class BurpplePresenter extends BasePresenter<BurppleView>{

    @Override
    public void onCreate(BurppleView view) {
        super.onCreate(view);
        BurppleApp burppleApp = (BurppleApp) mView.getContext();
        burppleApp.getAppComponent().inject(this);
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    public void onGuidesDataLoaded(Cursor data) {
        if (data != null && data.moveToFirst()) {
            List<GuidesVO> guidesList = new ArrayList<>();

            do {
                GuidesVO guides = GuidesVO.parseFromCursor(data);
                guidesList.add(guides);
            } while (data.moveToNext());
            mView.displayGuidesList(guidesList);
        }
    }

    public void onFeaturedDataLoaded(Cursor data) {

        if (data != null && data.moveToFirst()) {
//            List<FeaturedVO> featuredList = new ArrayList<>();
            List<String> images = new ArrayList<>();
            do {
                FeaturedVO featured = FeaturedVO.parseFromCursor(data);
//                featuredList.add(featured);
                images.add(featured.getBurppleFeaturedImage());
            } while (data.moveToNext());
           mView.displayFeaturedList(images);
        }
    }

    public void onPromotionsDataLoaded(Context context, Cursor data) {
        if (data != null && data.moveToFirst()) {
            List<PromotionsVO> promotionsList = new ArrayList<>();
            do {
                PromotionsVO promotions = PromotionsVO.parseFromCursor(context,data);
//                featuredList.add(featured);
                promotionsList.add(promotions);
            } while (data.moveToNext());
            mView.displayPromotionsList(promotionsList);
        }
    }
}
