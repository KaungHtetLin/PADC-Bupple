package net.kaunghtetlin.brupple.dagger;

import android.content.Context;

import net.kaunghtetlin.brupple.BurppleApp;
import net.kaunghtetlin.brupple.data.models.FeaturedModel;
import net.kaunghtetlin.brupple.data.models.GuidesModel;
import net.kaunghtetlin.brupple.data.models.PromotionsModel;
import net.kaunghtetlin.brupple.mvp.presenters.BurpplePresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Kaung Htet Lin on 1/20/2018.
 */

@Module
public class AppModule {

    private BurppleApp mApp;

    public AppModule(BurppleApp application) {
        mApp = application;
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return mApp.getApplicationContext();
    }

    @Provides
    public BurpplePresenter provideBurpplePresenter() {
        return new BurpplePresenter();
    }

    @Provides
    @Singleton
    public FeaturedModel provideFeaturedModel(Context context) {
        return new FeaturedModel(context);
    }

    @Provides
    @Singleton
    public GuidesModel provideGuidesModel(Context context) {
        return new GuidesModel(context);
    }

    @Provides
    @Singleton
    public PromotionsModel providePromotionsModel(Context context) {
        return new PromotionsModel(context);
    }
}
