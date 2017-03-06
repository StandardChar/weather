package com.weather.app.weather.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by andy on 2016/9/11.
 */
public class WeatherOpenHelper extends SQLiteOpenHelper{

    public static final String create_province = "create table Province (" + "id integer primary key autoincrement," +"province_name text, "+"province_code text";

    public static final String create_city = "create table City ("+"id integer primary key autoincrement, "+"city_name text"+"city_code text, "+"province_id integer)";

    public static final String create_county = "create table County ("+"id integer primary key autoincrement, "+"county_name text, "+"county_code text, "+"city_id integer)";

    public  WeatherOpenHelper(Context context,String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context,name,factory,version);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(create_province);
        db.execSQL(create_city);
        db.execSQL(create_county);
    }
}
