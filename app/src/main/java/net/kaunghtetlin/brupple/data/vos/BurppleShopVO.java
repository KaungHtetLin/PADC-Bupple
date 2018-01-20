package net.kaunghtetlin.brupple.data.vos;

import android.content.ContentValues;
import android.database.Cursor;

import com.google.gson.annotations.SerializedName;

import net.kaunghtetlin.brupple.persistance.BurppleContract;

/**
 * Created by Kaung Htet Lin on 1/18/2018.
 */

public class BurppleShopVO {

    @SerializedName("burpple-shop-id")
    private String burppleShopId;

    @SerializedName("burpple-shop-name")
    private String burppleShopName;

    @SerializedName("burpple-shop-area")
    private String burppleShopArea;

    public String getBurppleShopId() {
        return burppleShopId;
    }

    public String getBurppleShopName() {
        return burppleShopName;
    }

    public String getBurppleShopArea() {
        return burppleShopArea;
    }

    public ContentValues parseToContentValues() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(BurppleContract.ShopEntry.COLUMN_SHOP_ID, burppleShopId);
        contentValues.put(BurppleContract.ShopEntry.COLUMN_NAME, burppleShopName);
        contentValues.put(BurppleContract.ShopEntry.COLUMN_AREA, burppleShopArea);
//        contentValues.put(BurppleContract.ShopEntry.COLUMN_PROMOTIONS_ID, promotionsId);
        return contentValues;
    }

    public static BurppleShopVO parseFromCursor(Cursor cursor) {
        BurppleShopVO shop = new BurppleShopVO();
        shop.burppleShopId = cursor.getString(cursor.getColumnIndex(BurppleContract.ShopEntry.COLUMN_SHOP_ID));
        shop.burppleShopName = cursor.getString(cursor.getColumnIndex(BurppleContract.ShopEntry.COLUMN_NAME));
        shop.burppleShopArea = cursor.getString(cursor.getColumnIndex(BurppleContract.ShopEntry.COLUMN_AREA));
        return shop;
    }
}
