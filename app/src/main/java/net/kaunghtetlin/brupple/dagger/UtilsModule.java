package net.kaunghtetlin.brupple.dagger;

import android.content.Context;

import net.kaunghtetlin.brupple.utils.ConfigUtils;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Kaung Htet Lin on 1/20/2018.
 */

@Module
public class UtilsModule {

    @Provides
    @Singleton
    public ConfigUtils provideConfigUtils(Context context) {
        return new ConfigUtils(context);
    }

}
