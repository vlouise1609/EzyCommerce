package com.example.ezycommerce;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBManager {

    private EzyCommerceDatabase EzyDb;
    private Context ctx;
    private SQLiteDatabase database;

    public DBManager(Context ctx) {
        EzyDb = new EzyCommerceDatabase(ctx);
    }

    public DBManager() {

    }

    public void addCartRecords(Product product){
        database = EzyDb.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(EzyCommerceDatabase.KEY_ID, product.getId());
        values.put(EzyCommerceDatabase.KEY_NAME, product.getName());
        values.put(EzyCommerceDatabase.KEY_PRICE, product.getPrice());
        values.put(EzyCommerceDatabase.KEY_AUTHOR, product.getAuthor());
        values.put(EzyCommerceDatabase.KEY_CATEGORY, product.getCategory());
        values.put(EzyCommerceDatabase.KEY_TYPE, product.getType());
        values.put(EzyCommerceDatabase.KEY_IMAGE, product.getImg());
        values.put(EzyCommerceDatabase.KEY_QUANTITY, product.getQuantity());
        values.put(EzyCommerceDatabase.KEY_INCART, product.getInCart());
        values.put(EzyCommerceDatabase.KEY_CATEGORY, product.getCategory());

        database.insert(EzyCommerceDatabase.TABLE_CART, null, values);
        database.close();

    }

    public ArrayList<Product> getCartRecords() {

        ArrayList<Product> productList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + EzyCommerceDatabase.TABLE_CART;
        database = EzyDb.getReadableDatabase();

        //cara raw query
        Cursor cursor = database.rawQuery(selectQuery, null);


        if (cursor != null) {
            if(cursor.moveToFirst()){
                do {
                    Product product = new Product();
                    product.setId(cursor.getLong(0));
                    product.setName(cursor.getString(1));
                    product.setPrice(cursor.getDouble(2));
                    product.setAuthor(cursor.getString(3));
                    product.setType(cursor.getString(4));
                    product.setImg(cursor.getString(5));
                    product.setQuantity(cursor.getInt(6));
                    boolean value = cursor.getInt(7) > 0;
                    product.setInCart(value);
                    product.setCategory(cursor.getString(8));

                    productList.add(product);
                } while (cursor.moveToNext());
            }
        }

        cursor.close();
        database.close();

        return productList;
    }

}
