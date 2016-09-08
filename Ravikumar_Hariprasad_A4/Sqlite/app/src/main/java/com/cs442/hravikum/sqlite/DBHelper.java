package com.cs442.hravikum.sqlite;

/**
 * Created by Hari on 2/28/2016.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;


public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="FoodItemMenuFinal1.db";
    public static final String OrderHistory_TABLE_NAME="OrderHistory";

    public static final String OrderHistory_COLUMN_ID = "id";
    public static final String OrderHistory_COLUMN_NAME = "name";
    public static final String OrderHistory_COLUMN_PRICE = "price";
    public static final String OrderHistory_COLUMN_TIMESTAMP = "timestamp";
    private HashMap hp;

    public DBHelper(Context context)
    {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table OrderHistory " +
                        "(id integer primary key,timestamp text, name text,price text)"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS OrderHistory");
        onCreate(db);
    }

    public boolean insertOrder  (String timestamp,String name, String price)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        //contentValues.put("id",id);
        contentValues.put("name", name);
        contentValues.put("price", price);
        contentValues.put("timestamp", timestamp);
        /*contentValues.put("street", street);
        contentValues.put("place", place);*/
        db.insert("OrderHistory", null, contentValues);
        return true;
    }


    public ArrayList<Order> getAllOrderHistory()
    {
        ArrayList<Order> array_list = new ArrayList<Order>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from OrderHistory", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
          /* Log.d("database", res.getString(0));
            Log.d("database", res.getString(1));
            Log.d("database", res.getString(2));
            Log.d("database", res.getString(3));*/
            array_list.add(new Order(Integer.parseInt(res.getString(0)), res.getString(2), Double.parseDouble(res.getString(3)),Timestamp.valueOf(res.getString(1))));
            res.moveToNext();
        }
        return array_list;
    }

}
