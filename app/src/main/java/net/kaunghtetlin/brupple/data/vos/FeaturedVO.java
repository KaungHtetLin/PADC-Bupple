package net.kaunghtetlin.brupple.data.vos;

import android.content.ContentValues;
import android.database.Cursor;

import com.google.gson.annotations.SerializedName;

import net.kaunghtetlin.brupple.persistance.BurppleContract;

/**
 * Created by Kaung Htet Lin on 1/18/2018.
 */

public class FeaturedVO {

    @SerializedName("burpple-featured-id")
    private String burppleFeaturedId;

    @SerializedName("burpple-featured-image")
    private String burppleFeaturedImage;

    @SerializedName("burpple-featured-title")
    private String burppleFeaturedTitle;

    @SerializedName("burpple-featured-desc")
    private String burppleFeaturedDesc;

    @SerializedName("burpple-featured-tag")
    private String burppleFeaturedTag;

    public String getBurppleFeaturedId() {
        return burppleFeaturedId;
    }

    public String getBurppleFeaturedImage() {
        return burppleFeaturedImage;
    }

    public String getBurppleFeaturedTitle() {
        return burppleFeaturedTitle;
    }

    public String getBurppleFeaturedDesc() {
        return burppleFeaturedDesc;
    }

    public String getBurppleFeaturedTag() {
        return burppleFeaturedTag;
    }

    public ContentValues parseToContentValues() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(BurppleContract.FeaturedEntry.COLUMN_ID, burppleFeaturedId);
        contentValues.put(BurppleContract.FeaturedEntry.COLUMN_IMAGE, burppleFeaturedImage);
        contentValues.put(BurppleContract.FeaturedEntry.COLUMN_TITLE, burppleFeaturedTitle);
        contentValues.put(BurppleContract.FeaturedEntry.COLUMN_DESC, burppleFeaturedDesc);
        contentValues.put(BurppleContract.FeaturedEntry.COLUMN_TAG, burppleFeaturedTag);
        return contentValues;
    }

    public static FeaturedVO parseFromCursor(Cursor data) {
        FeaturedVO featured = new FeaturedVO();
        featured.burppleFeaturedId = data.getString(data.getColumnIndex(BurppleContract.FeaturedEntry.COLUMN_ID));
        featured.burppleFeaturedImage = data.getString(data.getColumnIndex(BurppleContract.FeaturedEntry.COLUMN_IMAGE));
        featured.burppleFeaturedTitle = data.getString(data.getColumnIndex(BurppleContract.FeaturedEntry.COLUMN_TITLE));
        featured.burppleFeaturedDesc = data.getString(data.getColumnIndex(BurppleContract.FeaturedEntry.COLUMN_DESC));
        featured.burppleFeaturedTag = data.getString(data.getColumnIndex(BurppleContract.FeaturedEntry.COLUMN_TAG));
        return featured;
    }

}
