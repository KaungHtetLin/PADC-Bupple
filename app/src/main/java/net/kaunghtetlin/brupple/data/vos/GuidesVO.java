package net.kaunghtetlin.brupple.data.vos;

import android.content.ContentValues;
import android.database.Cursor;

import com.google.gson.annotations.SerializedName;

import net.kaunghtetlin.brupple.persistance.BurppleContract;

/**
 * Created by Kaung Htet Lin on 1/10/2018.
 */

public class GuidesVO {


    @SerializedName("burpple-guide-id")
    private String burppleGuidesId;

    @SerializedName("burpple-guide-image")
    private String burppleGuidesImage;

    @SerializedName("burpple-guide-title")
    private String burppleGuidesTitle;

    @SerializedName("burpple-guide-desc")
    private String burppleGuidesDesc;

    public String getBurppleGuidesId() {
        return burppleGuidesId;
    }

    public String getBurppleGuidesImage() {
        return burppleGuidesImage;
    }

    public String getBurppleGuidesTitle() {
        return burppleGuidesTitle;
    }

    public String getBurppleGuidesDesc() {
        return burppleGuidesDesc;
    }

    public ContentValues parseToContentValues() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(BurppleContract.GuidesEntry.COLUMN_ID, burppleGuidesId);
        contentValues.put(BurppleContract.GuidesEntry.COLUMN_IMAGE, burppleGuidesImage);
        contentValues.put(BurppleContract.GuidesEntry.COLUMN_TITLE, burppleGuidesTitle);
        contentValues.put(BurppleContract.GuidesEntry.COLUMN_DESC, burppleGuidesDesc);
        return contentValues;
    }

    public static GuidesVO parseFromCursor(Cursor data) {
        GuidesVO guides = new GuidesVO();
        guides.burppleGuidesId = data.getString(data.getColumnIndex(BurppleContract.GuidesEntry.COLUMN_ID));
        guides.burppleGuidesImage = data.getString(data.getColumnIndex(BurppleContract.GuidesEntry.COLUMN_IMAGE));
        guides.burppleGuidesTitle = data.getString(data.getColumnIndex(BurppleContract.GuidesEntry.COLUMN_TITLE));
        guides.burppleGuidesDesc = data.getString(data.getColumnIndex(BurppleContract.GuidesEntry.COLUMN_DESC));
        return guides;
    }

}
