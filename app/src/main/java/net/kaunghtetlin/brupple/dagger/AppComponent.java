package net.kaunghtetlin.brupple.dagger;

import net.kaunghtetlin.brupple.BurppleApp;
import net.kaunghtetlin.brupple.activities.HomeActivity;
import net.kaunghtetlin.brupple.data.models.FeaturedModel;
import net.kaunghtetlin.brupple.data.models.GuidesModel;
import net.kaunghtetlin.brupple.data.models.PromotionsModel;
import net.kaunghtetlin.brupple.mvp.presenters.BurpplePresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Kaung Htet Lin on 1/20/2018.
 */

@Component(modules = {AppModule.class, NetworkModule.class, UtilsModule.class})
@Singleton
public interface AppComponent {
    void inject(BurppleApp app);

    void inject(FeaturedModel featuredModel);

    void inject(GuidesModel guidesModel);

    void inject(PromotionsModel promotionsModel);

    void inject(BurpplePresenter burpplePresenter);

    void inject(HomeActivity homeActivity);

}