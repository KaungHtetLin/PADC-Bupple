package net.kaunghtetlin.brupple;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import net.kaunghtetlin.brupple.dagger.AppComponent;
import net.kaunghtetlin.brupple.dagger.AppModule;
import net.kaunghtetlin.brupple.dagger.DaggerAppComponent;
import net.kaunghtetlin.brupple.dagger.NetworkModule;
import net.kaunghtetlin.brupple.dagger.UtilsModule;
import net.kaunghtetlin.brupple.data.models.FeaturedModel;
import net.kaunghtetlin.brupple.data.models.GuidesModel;
import net.kaunghtetlin.brupple.data.models.PromotionsModel;

import javax.inject.Inject;

/**
 * Created by Kaung Htet Lin on 1/18/2018.
 */

public class BurppleApp extends Application {

    public static final String LOG_TAG = "BurppleApp";

    private AppComponent mAppComponent;

    @Inject
    Context mContext;

    @Inject
    FeaturedModel mFeaturedModel;

    @Inject
    GuidesModel mGuidesModel;

    @Inject
    PromotionsModel mPromotionsModel;

    @Override
    public void onCreate() {
        super.onCreate();

        mAppComponent = initDagger();       //dagger init
        mAppComponent.inject(this);     //register consumer

        mGuidesModel.startLoadingGuides(getApplicationContext());
        mFeaturedModel.startLoadingFeatured(getApplicationContext());
        mPromotionsModel.startLoadingPromotions(getApplicationContext());

        Log.d(LOG_TAG, "mContext" + mContext);
    }

    private AppComponent initDagger() {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule())
                .utilsModule(new UtilsModule())
                .build();
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }

}
