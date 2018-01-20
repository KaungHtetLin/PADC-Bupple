package net.kaunghtetlin.brupple.mvp.views;

import android.content.Context;

import net.kaunghtetlin.brupple.data.vos.FeaturedVO;
import net.kaunghtetlin.brupple.data.vos.GuidesVO;
import net.kaunghtetlin.brupple.data.vos.PromotionsVO;

import java.util.List;

/**
 * Created by Kaung Htet Lin on 1/20/2018.
 */

public interface BurppleView {
    void displayGuidesList(List<GuidesVO> guidesList);
    void displayPromotionsList(List<PromotionsVO> promotionsList);
    void displayFeaturedList(List<String> featuredList);

    Context getContext();
}
