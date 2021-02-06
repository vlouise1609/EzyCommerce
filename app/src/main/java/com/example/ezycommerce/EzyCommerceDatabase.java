package com.example.ezycommerce;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class EzyCommerceDatabase extends SQLiteOpenHelper {
    private EzyCommerceDatabase ezDb;

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "EzyCommerce"; //nama db
    //nama tabel
    public static final String TABLE_CART = "Carts";
    //nama kolom tabel user
    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "name";
    public static final String KEY_PRICE = "price";
    public static final String KEY_AUTHOR = "author";
    public static final String KEY_TYPE = "type";
    public static final String KEY_IMAGE = "img";
    public static final String KEY_QUANTITY= "quantity";
    public static final String KEY_INCART = "in_cart";
    public static final String KEY_CATEGORY = "category";

    public EzyCommerceDatabase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public EzyCommerceDatabase(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_CART_TABLE = "CREATE TABLE " + TABLE_CART+ "("
                + KEY_ID + " INTEGER, " + KEY_NAME + " TEXT," + KEY_PRICE + " INTEGER,"
                + KEY_AUTHOR + " TEXT," + KEY_TYPE + " TEXT, " + KEY_IMAGE + " TEXT," + KEY_QUANTITY + " INTEGER," + KEY_INCART + " TEXT, " + KEY_CATEGORY + " TEXT " + ")";


        sqLiteDatabase.execSQL(CREATE_CART_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
