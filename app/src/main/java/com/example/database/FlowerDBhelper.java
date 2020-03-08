package com.example.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;


public class FlowerDBhelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "db-flower";
    private static final int DB_VERSION = 1;
    private static final String TABLE_FLOWERS = "tb_flowers";
    private static final String CMD = "CREATE TABLE IF NOT EXISTS'" + TABLE_FLOWERS  + "'('" +
                   Flower.KEY_ID    + "'INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,'" +
                   Flower.KEY_NAME  +"'TEXT,'"+
                   Flower.KEY_CAT   +"'TEXT,'"+
                   Flower.KEY_INSTR +"'TEXT,'"+
                   Flower.KEY_PHOTO +"'TEXT,'"+
                   Flower.KEY_PRICE +"'NUMERIC"+")";



    public FlowerDBhelper(@Nullable Context context) {

        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CMD);
        Log.i("database", "onCreate: table ");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " +TABLE_FLOWERS ) ;
        Log.i("database", "onUpgrade: table dropped ");
        onCreate(db);

    }

    public void insertFlower(Flower flower ){
        ContentValues values = new ContentValues();
        values.put(Flower.KEY_ID,flower.getProductId());
        values.put(Flower.KEY_CAT,flower.getCategory());
        values.put(Flower.KEY_INSTR,flower.getInstructions());
        values.put(Flower.KEY_NAME,flower.getName());
        values.put(Flower.KEY_PHOTO,flower.getPhoto());
        values.put(Flower.KEY_PRICE,flower.getPrice());

        //database
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_FLOWERS,null,values);
        long insertID =  db.insert(TABLE_FLOWERS,null,values);


        Log.i("database", "insertFlower: with id of : " + insertID);
        if (db.isOpen())db.close();
    }
}
