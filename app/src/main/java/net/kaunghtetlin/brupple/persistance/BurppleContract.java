package net.kaunghtetlin.brupple.persistance;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

import net.kaunghtetlin.brupple.BurppleApp;

/**
 * Created by Kaung Htet Lin on 1/19/2018.
 */

public class BurppleContract {

    public static final String CONTENT_AUTHORITY = BurppleApp.class.getPackage().getName();
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_FEATURED = "featured";
    public static final String PATH_GUIDES = "guides";
    public static final String PATH_PROMOTIONS = "promotions";
    public static final String PATH_SHOPS = "shops";
    public static final String PATH_PROMOTIONS_TERMS = "terms";


    public static final class FeaturedEntry implements BaseColumns {

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_FEATURED).build();

        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_FEATURED;

        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_FEATURED;

        public static final String TABLE_NAME = "featured";

        public static final String COLUMN_ID = "burpple_featured_id";
        public static final String COLUMN_IMAGE = "burpple_featured_image";
        public static final String COLUMN_TITLE = "burpple_featured_title";
        public static final String COLUMN_DESC = "burpple_featured_desc";
        public static final String COLUMN_TAG = "burpple_featured_tag";

        public static Uri buildFeaturedUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }

    public static final class GuidesEntry implements BaseColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_GUIDES).build();

        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_GUIDES;

        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_GUIDES;

        public static final String TABLE_NAME = "guides";

        public static final String COLUMN_ID = "burpple_guide_id";
        public static final String COLUMN_IMAGE = "burpple_guide_image";
        public static final String COLUMN_TITLE = "burpple_guide_title";
        public static final String COLUMN_DESC = "burpple_guide_desc";

        public static Uri buildGuidesUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }

    public static final class PromotionsEntry implements BaseColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_PROMOTIONS).build();

        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PROMOTIONS;

        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PROMOTIONS;

        public static final String TABLE_NAME = "promotions";

        public static final String COLUMN_PROMOTIONS_ID = "burpple_promotion_id";
        public static final String COLUMN_IMAGE = "burpple_promotion_image";
        public static final String COLUMN_TITLE = "burpple_promotion_title";
        public static final String COLUMN_UNTIL = "burpple_promotion_until";
        public static final String COLUMN_IS_EXCLUSIVE = "is_burpple_exclusive";
        public static final String COLUMN_SHOP_ID = "burpple_shop_id";

        public static Uri buildPromotionsUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }

    public static final class ShopEntry implements BaseColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_SHOPS).build();

        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_SHOPS;

        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_SHOPS;

        public static final String TABLE_NAME = "shops";

        public static final String COLUMN_SHOP_ID = "burpple_shop_id";
        public static final String COLUMN_NAME = "burpple_shop_name";
        public static final String COLUMN_AREA = "burpple_shop_area";

        public static Uri buildShopUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }

    public static final class TermsEntry implements BaseColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_PROMOTIONS_TERMS).build();

        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PROMOTIONS_TERMS;

        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PROMOTIONS_TERMS;

        public static final String TABLE_NAME = "terms";

        public static final String COLUMN_DESC = "burpple_promotion_terms";
        public static final String COLUMN_PROMOTIONS_ID = "burpple_promotion_id";

        public static Uri buildTermsUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }

}