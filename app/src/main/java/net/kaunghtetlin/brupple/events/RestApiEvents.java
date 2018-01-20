package net.kaunghtetlin.brupple.events;

import android.content.Context;

import net.kaunghtetlin.brupple.data.vos.FeaturedVO;
import net.kaunghtetlin.brupple.data.vos.GuidesVO;
import net.kaunghtetlin.brupple.data.vos.PromotionsVO;

import java.util.List;

/**
 * Created by Kaung Htet Lin on 1/18/2018.
 */

public class RestApiEvents {


    public static class EmptyResponseEvent {
        // errors when response is empty
    }

    public static class ErrorInvokingAPIEvent {
        // errors when invoking API

        private String errorMsg;

        public ErrorInvokingAPIEvent(String errorMsg) {
            this.errorMsg = errorMsg;
        }

        public String getErrorMsg() {
            return errorMsg;
        }
    }

    public static class GuidesDataLoadedEvent {
        // when data is loaded

        private int loadedPageIndex;
        private List<GuidesVO> loadedGuides;
        private Context context;

        public GuidesDataLoadedEvent(int loadedPageIndex, List<GuidesVO> loadedGuides, Context context) {
            this.loadedPageIndex = loadedPageIndex;
            this.loadedGuides = loadedGuides;
            this.context = context;
        }

        public int getLoadedPageIndex() {
            return loadedPageIndex;
        }

        public List<GuidesVO> getLoadedGuides() {
            return loadedGuides;
        }

        public Context getContext() {
            return context;
        }
    }

    public static class FeaturedDataLoadedEvent {
        // when data is loaded

        private int loadedPageIndex;
        private List<FeaturedVO> loadedFeatured;
        private Context context;

        public FeaturedDataLoadedEvent(int loadedPageIndex, List<FeaturedVO> loadedFeatured, Context context) {
            this.loadedPageIndex = loadedPageIndex;
            this.loadedFeatured = loadedFeatured;
            this.context = context;
        }

        public int getLoadedPageIndex() {
            return loadedPageIndex;
        }

        public List<FeaturedVO> getLoadedFeatured() {
            return loadedFeatured;
        }

        public Context getContext() {
            return context;
        }
    }

    public static class PromotionsDataLoadedEvent {
        // when data is loaded

        private int loadedPageIndex;
        private List<PromotionsVO> loadedPromotions;
        private Context context;

        public PromotionsDataLoadedEvent(int loadedPageIndex, List<PromotionsVO> loadedPromotions, Context context) {
            this.loadedPageIndex = loadedPageIndex;
            this.loadedPromotions = loadedPromotions;
            this.context = context;
        }

        public int getLoadedPageIndex() {
            return loadedPageIndex;
        }

        public List<PromotionsVO> getLoadedPromotions() {
            return loadedPromotions;
        }

        public Context getContext() {
            return context;
        }
    }

}
