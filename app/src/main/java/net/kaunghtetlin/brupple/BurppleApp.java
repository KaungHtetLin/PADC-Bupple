package net.kaunghtetlin.brupple;

import android.app.Application;

import net.kaunghtetlin.brupple.data.models.FeaturedModel;
import net.kaunghtetlin.brupple.data.models.GuidesModel;
import net.kaunghtetlin.brupple.data.models.PromotionsModel;

/**
 * Created by Kaung Htet Lin on 1/18/2018.
 */

public class BurppleApp extends Application {
    public static final String LOG_TAG = "BurppleApp";

    @Override
    public void onCreate() {
        super.onCreate();

        GuidesModel.getObjInstance().startLoadingGuides(getApplicationContext());
        FeaturedModel.getObjInstance().startLoadingFeatured(getApplicationContext());
        PromotionsModel.getObjInstance().startLoadingPromotions(getApplicationContext());

    }
}
