package net.kaunghtetlin.brupple.persistance;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Kaung Htet Lin on 1/19/2018.
 */

public class BurppleDBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "burpple.db";

    private static final String SQL_CREATE_FEATURED_TABLE = "CREATE TABLE " + BurppleContract.FeaturedEntry.TABLE_NAME + " (" +
            BurppleContract.FeaturedEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            BurppleContract.FeaturedEntry.COLUMN_ID + " VARCHAR(256), " +
            BurppleContract.FeaturedEntry.COLUMN_IMAGE + " TEXT, " +
            BurppleContract.FeaturedEntry.COLUMN_TITLE + " TEXT, " +
            BurppleContract.FeaturedEntry.COLUMN_DESC + " TEXT, " +
            BurppleContract.FeaturedEntry.COLUMN_TAG + " TEXT, " +

            " UNIQUE (" + BurppleContract.FeaturedEntry.COLUMN_ID + ") ON CONFLICT REPLACE" +
            ");";

    private static final String SQL_CREATE_GUIDES_TABLE = "CREATE TABLE " + BurppleContract.GuidesEntry.TABLE_NAME + " (" +
            BurppleContract.GuidesEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            BurppleContract.GuidesEntry.COLUMN_ID + " VARCHAR(256), " +
            BurppleContract.GuidesEntry.COLUMN_IMAGE + " TEXT, " +
            BurppleContract.GuidesEntry.COLUMN_TITLE + " TEXT, " +
            BurppleContract.GuidesEntry.COLUMN_DESC + " TEXT, " +

            " UNIQUE (" + BurppleContract.GuidesEntry.COLUMN_ID + ") ON CONFLICT REPLACE" +
            ");";

    private static final String SQL_CREATE_PROMOTIONS_TABLE = "CREATE TABLE " + BurppleContract.PromotionsEntry.TABLE_NAME + " (" +
            BurppleContract.PromotionsEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            BurppleContract.PromotionsEntry.COLUMN_PROMOTIONS_ID + " VARCHAR(256), " +
            BurppleContract.PromotionsEntry.COLUMN_IMAGE + " TEXT, " +
            BurppleContract.PromotionsEntry.COLUMN_TITLE + " TEXT, " +
            BurppleContract.PromotionsEntry.COLUMN_UNTIL + " TEXT, " +
            BurppleContract.PromotionsEntry.COLUMN_IS_EXCLUSIVE + " BOOLEAN, " +
            BurppleContract.PromotionsEntry.COLUMN_SHOP_ID + " VARCHAR(256), " +

            " UNIQUE (" + BurppleContract.PromotionsEntry.COLUMN_PROMOTIONS_ID + ") ON CONFLICT REPLACE" +
            ");";

    private static final String SQL_CREATE_SHOP_TABLE = "CREATE TABLE " + BurppleContract.ShopEntry.TABLE_NAME + " (" +
            BurppleContract.ShopEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            BurppleContract.ShopEntry.COLUMN_SHOP_ID + " VARCHAR(256), " +
            BurppleContract.ShopEntry.COLUMN_NAME + " TEXT, " +
            BurppleContract.ShopEntry.COLUMN_AREA + " TEXT, " +

            " UNIQUE (" + BurppleContract.ShopEntry.COLUMN_SHOP_ID + "," +
            BurppleContract.ShopEntry.COLUMN_SHOP_ID + ") ON CONFLICT IGNORE" +
            " );";

    private static final String SQL_CREATE_TERMS_TABLE = "CREATE TABLE " + BurppleContract.TermsEntry.TABLE_NAME + " (" +
            BurppleContract.TermsEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            BurppleContract.TermsEntry.COLUMN_DESC + " TEXT, " +
            BurppleContract.TermsEntry.COLUMN_PROMOTIONS_ID + " VARCHAR(256), " +

            " UNIQUE (" + BurppleContract.TermsEntry.COLUMN_PROMOTIONS_ID + ") ON CONFLICT IGNORE " +
            " );";

    public BurppleDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_FEATURED_TABLE);
        db.execSQL(SQL_CREATE_GUIDES_TABLE);
        db.execSQL(SQL_CREATE_SHOP_TABLE);
        db.execSQL(SQL_CREATE_TERMS_TABLE);
        db.execSQL(SQL_CREATE_PROMOTIONS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + BurppleContract.FeaturedEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + BurppleContract.GuidesEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + BurppleContract.PromotionsEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + BurppleContract.ShopEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + BurppleContract.TermsEntry.TABLE_NAME);
    }
}
