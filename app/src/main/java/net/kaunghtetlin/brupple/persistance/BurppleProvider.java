package net.kaunghtetlin.brupple.persistance;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by Kaung Htet Lin on 1/19/2018.
 */

public class BurppleProvider extends ContentProvider {

    public static final int FEATURED = 100;
    public static final int GUIDES = 200;
    public static final int PROMOTIONS = 300;
    public static final int SHOP = 400;
    public static final int TERMS = 500;

    private static final UriMatcher sUriMatcher = buildUriMather();
    private static final SQLiteQueryBuilder sPromotionsWithShop_IJ;

    static {
        sPromotionsWithShop_IJ = new SQLiteQueryBuilder();
        sPromotionsWithShop_IJ.setTables(
                BurppleContract.PromotionsEntry.TABLE_NAME + " INNER JOIN " +
                        BurppleContract.ShopEntry.TABLE_NAME + " ON " +
                        BurppleContract.PromotionsEntry.TABLE_NAME + "." + BurppleContract.PromotionsEntry.COLUMN_SHOP_ID + " = " +
                        BurppleContract.ShopEntry.TABLE_NAME + "." + BurppleContract.ShopEntry.COLUMN_SHOP_ID
        );
    }


    private BurppleDBHelper mDBHelper;

    private static UriMatcher buildUriMather() {
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        uriMatcher.addURI(BurppleContract.CONTENT_AUTHORITY, BurppleContract.PATH_FEATURED, FEATURED);
        uriMatcher.addURI(BurppleContract.CONTENT_AUTHORITY, BurppleContract.PATH_GUIDES, GUIDES);
        uriMatcher.addURI(BurppleContract.CONTENT_AUTHORITY, BurppleContract.PATH_PROMOTIONS, PROMOTIONS);
        uriMatcher.addURI(BurppleContract.CONTENT_AUTHORITY, BurppleContract.PATH_SHOPS, SHOP);
        uriMatcher.addURI(BurppleContract.CONTENT_AUTHORITY, BurppleContract.PATH_PROMOTIONS_TERMS, TERMS);

        return uriMatcher;
    }


    private String getTableName(Uri uri) {
        switch (sUriMatcher.match(uri)) {
            case FEATURED:
                return BurppleContract.FeaturedEntry.TABLE_NAME;
            case GUIDES:
                return BurppleContract.GuidesEntry.TABLE_NAME;
            case PROMOTIONS:
                return BurppleContract.PromotionsEntry.TABLE_NAME;
            case SHOP:
                return BurppleContract.ShopEntry.TABLE_NAME;
            case TERMS:
                return BurppleContract.TermsEntry.TABLE_NAME;

            default:
                throw new UnsupportedOperationException("Unknown uri : " + uri);
        }
    }

    private Uri getContentUri(Uri uri) {
        switch (sUriMatcher.match(uri)) {
            case FEATURED:
                return BurppleContract.FeaturedEntry.CONTENT_URI;
            case GUIDES:
                return BurppleContract.GuidesEntry.CONTENT_URI;
            case PROMOTIONS:
                return BurppleContract.PromotionsEntry.CONTENT_URI;
            case SHOP:
                return BurppleContract.ShopEntry.CONTENT_URI;
            case TERMS:
                return BurppleContract.TermsEntry.CONTENT_URI;

            default:
                throw new UnsupportedOperationException("Unknown uri : " + uri);
        }
    }

    @Override
    public boolean onCreate() {
        mDBHelper = new BurppleDBHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Cursor queryCursor;
        switch (sUriMatcher.match(uri)) {
            case PROMOTIONS:
                queryCursor = sPromotionsWithShop_IJ.query(mDBHelper.getReadableDatabase(),
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;
            default:
                queryCursor = mDBHelper.getReadableDatabase().query(getTableName(uri),
                        projection,
                        selection,
                        selectionArgs,
                        null, //group_by
                        null, //having
                        sortOrder);

                if (getContext() != null) {
                    queryCursor.setNotificationUri(getContext().getContentResolver(), uri);
                }
        }

        return queryCursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (sUriMatcher.match(uri)) {
            case FEATURED:
                return BurppleContract.FeaturedEntry.DIR_TYPE;
            case GUIDES:
                return BurppleContract.GuidesEntry.DIR_TYPE;
            case PROMOTIONS:
                return BurppleContract.PromotionsEntry.DIR_TYPE;
            case SHOP:
                return BurppleContract.ShopEntry.DIR_TYPE;
            case TERMS:
                return BurppleContract.TermsEntry.DIR_TYPE;
            default:
                throw new UnsupportedOperationException("Unknown uri : " + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        SQLiteDatabase db = mDBHelper.getWritableDatabase();
        String tableName = getTableName(uri);
        long _id = db.insert(tableName, null, contentValues);

        if (_id > 0) {
            Uri tableContentUri = getContentUri(uri);
            Uri insertedUri = ContentUris.withAppendedId(tableContentUri, _id);

            if (getContext() != null) {
                getContext().getContentResolver().notifyChange(uri, null);
            }
        }
        return null;
    }

    @Override
    public int bulkInsert(@NonNull Uri uri, @NonNull ContentValues[] values) {
        final SQLiteDatabase db = mDBHelper.getWritableDatabase();

        String tableName = getTableName(uri);
        int insertedCount = 0;

        try {
            db.beginTransaction();
            for (ContentValues cv : values) {
                long _id = db.insert(tableName, null, cv);
                if (_id > 0) {
                    insertedCount++;
                }
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();

//            db.close();

        }

        Context context = getContext();
        if (context != null) {
            context.getContentResolver().notifyChange(uri, null);
        }

        return insertedCount;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        final SQLiteDatabase db = mDBHelper.getWritableDatabase();
        int rowDeleted;
        String tableName = getTableName(uri);

        rowDeleted = db.delete(tableName, selection, selectionArgs);
        Context context = getContext();
        if (context != null && rowDeleted > 0) {
            context.getContentResolver().notifyChange(uri, null);
        }
        return rowDeleted;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String selection, @Nullable String[] selectionArgs) {
        final SQLiteDatabase db = mDBHelper.getWritableDatabase();
        int rowUpdated;
        String tableName = getTableName(uri);

        rowUpdated = db.update(tableName, contentValues, selection, selectionArgs);
        Context context = getContext();
        if (context != null && rowUpdated > 0) {
            context.getContentResolver().notifyChange(uri, null);
        }
        return rowUpdated;
    }
}
