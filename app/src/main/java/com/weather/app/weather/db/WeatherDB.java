package com.weather.app.weather.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.weather.app.weather.model.City;
import com.weather.app.weather.model.County;
import com.weather.app.weather.model.Province;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by andy on 2016/9/11.
 */
public class WeatherDB {

    public static final String DB_NAME = "weather";

    public static final int VERSION = 1;

    private static WeatherDB WeatherDB;

    private SQLiteDatabase db;

    private WeatherDB(Context context){
        WeatherOpenHelper dbHelper = new WeatherOpenHelper(context,DB_NAME,null,VERSION);
        db = dbHelper.getWritableDatabase();
    }

    public synchronized static WeatherDB getInstance(Context context){
        if(WeatherDB == null){
            WeatherDB = new WeatherDB(context);
        }
        return WeatherDB;
    }

    public void saveProvince(Province province){
        if(province != null){
            ContentValues values = new ContentValues();
            values.put("province_name",province.getProvinceName());
            values.put("province_code",province.getProvinceCode());
            db.insert("Province",null,values);
        }
    }

    public List<Province> loadProvince(){
        List<Province> list = new ArrayList<Province>();
        Cursor cursor = db.query("Province",null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            do{
                Province province = new Province();
                province.setId(cursor.getInt(cursor.getColumnIndex("id")));
                province.setProvinceName(cursor.getString(cursor.getColumnIndex("province_name")));
                province.setProvinceCode(cursor.getString(cursor.getColumnIndex("province_code")));
                list.add(province);
            }while(cursor.moveToNext());

        }
        if(cursor != null){
            cursor.close();
        }
        return list;
    }

    public void saveCity(City city){
        if(city!=null){
            ContentValues values = new ContentValues();
            values.put("city_name",city.getCityName());
            values.put("city_code",city.getCityCode());
            values.put("province_id",city.getProvinceId());
            db.insert("City", null, values);
        }
    }

    public List<City> loadCities(int provinceId){
        List<City> list = new ArrayList<City>();
        Cursor cursor = db.query("City",null,"province_id = ?",new String[]{String.valueOf(provinceId)},null,null,null);

    }

    public void saveCounty(County county){

    }

    public  List<County> loadCounties(int cityId){

    }
}