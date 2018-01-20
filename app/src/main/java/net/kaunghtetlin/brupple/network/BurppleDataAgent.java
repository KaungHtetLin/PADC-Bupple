package net.kaunghtetlin.brupple.network;

import android.content.Context;

/**
 * Created by Kaung Htet Lin on 1/18/2018.
 */

public interface BurppleDataAgent {
    void loadFeatured(String accessToken, int page, Context context);
    void loadGuides(String accessToken, int page, Context context);
    void loadPromotions(String accessToken, int page, Context context);
}
