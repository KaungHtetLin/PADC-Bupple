package net.kaunghtetlin.brupple.data.vos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.google.gson.annotations.SerializedName;

import net.kaunghtetlin.brupple.persistance.BurppleContract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kaung Htet Lin on 1/10/2018.
 */

public class PromotionsVO {

    @SerializedName("burpple-promotion-id")
    private String burpplePromotionId;

    @SerializedName("burpple-promotion-image")
    private String burpplePromotionImage;

    @SerializedName("burpple-promotion-title")
    private String burpplePromotionTitle;

    @SerializedName("burpple-promotion-until")
    private String burpplePromotionUntil;

    @SerializedName("burpple-promotion-shop")
    private BurppleShopVO burpplePromotionShop;

    @SerializedName("is-burpple-exclusive")
    private boolean isBurppleExculsive;

    @SerializedName("burpple-promotion-terms")
    private List<String> burpplePromotionTerms;

    public String getBurpplePromotionId() {
        return burpplePromotionId;
    }

    public String getBurpplePromotionImage() {
        return burpplePromotionImage;
    }

    public String getBurpplePromotionTitle() {
        return burpplePromotionTitle;
    }

    public String getBurpplePromotionUntil() {
        return burpplePromotionUntil;
    }

    public BurppleShopVO getBurpplePromotionShop() {
        return burpplePromotionShop;
    }

    public boolean getIsBurppleExculsive() {
        return isBurppleExculsive;
    }

    public List<String> getBurpplePromotionTerms() {
        return burpplePromotionTerms;
    }

    public ContentValues parseToContentValues() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(BurppleContract.PromotionsEntry.COLUMN_PROMOTIONS_ID, burpplePromotionId);
        contentValues.put(BurppleContract.PromotionsEntry.COLUMN_IMAGE, burpplePromotionImage);
        contentValues.put(BurppleContract.PromotionsEntry.COLUMN_TITLE, burpplePromotionTitle);
        contentValues.put(BurppleContract.PromotionsEntry.COLUMN_UNTIL, burpplePromotionUntil);
        contentValues.put(BurppleContract.PromotionsEntry.COLUMN_IS_EXCLUSIVE, isBurppleExculsive);
        contentValues.put(BurppleContract.PromotionsEntry.COLUMN_SHOP_ID, burpplePromotionShop.getBurppleShopId());//for shops
//        contentValues.put(BurppleContract.PromotionsEntry.COLUMN_PROMOTIONS_ID, ); for terms
        return contentValues;
    }

    public static PromotionsVO parseFromCursor(Context context, Cursor cursor) {
        PromotionsVO promotions = new PromotionsVO();
        promotions.burpplePromotionId = cursor.getString(cursor.getColumnIndex(BurppleContract.PromotionsEntry.COLUMN_PROMOTIONS_ID));
        promotions.burpplePromotionImage = cursor.getString(cursor.getColumnIndex(BurppleContract.PromotionsEntry.COLUMN_IMAGE));
        promotions.burpplePromotionTitle = cursor.getString(cursor.getColumnIndex(BurppleContract.PromotionsEntry.COLUMN_TITLE));
        promotions.burpplePromotionUntil = cursor.getString(cursor.getColumnIndex(BurppleContract.PromotionsEntry.COLUMN_UNTIL));

        if (cursor.getColumnIndex(BurppleContract.PromotionsEntry.COLUMN_IS_EXCLUSIVE) == 1) {
            promotions.isBurppleExculsive = true;
        } else if (cursor.getColumnIndex(BurppleContract.PromotionsEntry.COLUMN_IS_EXCLUSIVE) == 0) {
            promotions.isBurppleExculsive = false;
        }
        promotions.burpplePromotionShop = BurppleShopVO.parseFromCursor(cursor);
        promotions.burpplePromotionTerms= loadTermsInPromotions(context, promotions.burpplePromotionId);

        return promotions;
    }

    private static List<String> loadTermsInPromotions(Context context, String burpplePromotionId) {
        Cursor termsInPromotionsCursor = context.getContentResolver().query(BurppleContract.TermsEntry.CONTENT_URI,
                null,
                BurppleContract.TermsEntry.COLUMN_PROMOTIONS_ID + " = ?", new String[]{burpplePromotionId},
                null);

        if (termsInPromotionsCursor != null && termsInPromotionsCursor.moveToFirst()) {
            List<String> termsInPromotions = new ArrayList<>();
            do {
                termsInPromotions.add(
                        termsInPromotionsCursor.getString(
                                termsInPromotionsCursor.getColumnIndex(BurppleContract.TermsEntry.COLUMN_DESC)));
            } while (termsInPromotionsCursor.moveToNext());
            termsInPromotionsCursor.close();
            return termsInPromotions;
        }
        return null;
    }
}
