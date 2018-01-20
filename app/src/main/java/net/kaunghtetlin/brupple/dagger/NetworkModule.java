package net.kaunghtetlin.brupple.dagger;

import net.kaunghtetlin.brupple.network.BurppleDataAgentImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Kaung Htet Lin on 1/20/2018.
 */

@Module
public class NetworkModule {

    @Provides
    @Singleton
    public BurppleDataAgentImpl provideBurppleDataAgentImpl(){
        return new BurppleDataAgentImpl();
    }

}
