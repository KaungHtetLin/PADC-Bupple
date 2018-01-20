package net.kaunghtetlin.brupple.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Kaung Htet Lin on 1/20/2018.
 */


public class ConfigUtils {

    private static final String KEY_FEATURED_PAGE_INDEX = "KEY_FEATURED_PAGE_INDEX";
    private static final String KEY_GUIDES_PAGE_INDEX = "KEY_GUIDES_PAGE_INDEX";
    private static final String KEY_PROMOTIONS_PAGE_INDEX = "KEY_PROMOTIONS_PAGE_INDEX";

    private SharedPreferences mSharedPreferences;

//    private static ConfigUtils sObjInstance;

    public ConfigUtils(Context context) {
        mSharedPreferences = context.getSharedPreferences("ConfigUtils", Context.MODE_PRIVATE);
    }

/*
    public static void initConfigUtils(Context context) {
        sObjInstance = new ConfigUtils(context);
    }
*/

    /*public static ConfigUtils getObjInstance() {
        return sObjInstance;
    }
*/
    public void saveFeaturedPageIndex(int pageIndex) {
        mSharedPreferences.edit().putInt(KEY_FEATURED_PAGE_INDEX, pageIndex).apply();
    }

    public void saveGuidesPageIndex(int pageIndex){
        mSharedPreferences.edit().putInt(KEY_GUIDES_PAGE_INDEX,pageIndex).apply();
    }

    public void savePromotionsPageIndex(int pageIndex){
        mSharedPreferences.edit().putInt(KEY_PROMOTIONS_PAGE_INDEX,pageIndex).apply();
    }

    public int loadFeaturedPageIndex() {
        return mSharedPreferences.getInt(KEY_FEATURED_PAGE_INDEX, 1);
    }

    public int loadGuidesPageIndex() {
        return mSharedPreferences.getInt(KEY_GUIDES_PAGE_INDEX, 1);
    }

    public int loadPromotionsPageIndex() {
        return mSharedPreferences.getInt(KEY_PROMOTIONS_PAGE_INDEX, 1);
    }
}
