package com.example.abdotarek.restapp.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by AbdoTarek on 8/21/2016.
 */
public class HelperDB extends SQLiteOpenHelper {

    private static final String DATABASE_NAME ="RestDB";
    private static final int DATABASE_VERSION = 1;


    // User TABLE

    public static final String TABLE_NAME = "user";
    public static final String KEY_ROWID = "_id";
    public static final String KEY_Name = "name";
    public static final String KEY_Email = "email";
    public static final String KEY_password = "password";
    public static final String KEY_Age = "age";
    public static final String KEY_Phone = "phone";
    public static final String KEY_Type = "type";


    // CREATE TABLE

    private static final String DB_CREATE = "CREATE TABLE if not exists " +TABLE_NAME + " ("
            +KEY_ROWID + " integer PRIMARY KEY AUTOINCREMENT,"
            +KEY_Name + " TEXT,"
            +KEY_Email + " TEXT,"
            +KEY_password + " TEXT,"
            +KEY_Age + " DOUBLE,"
            +KEY_Phone + " DOUBLE,"
            +KEY_Type + " TEXT);";

    // Rest TABLE

    public static final String TABLE_REST_NAME = "resturant";
    public static final String REST_ROWID = "_id";
    public static final String REST_Name = "name";
    public static final String REST_Photo = "photo";
    public static final String REST_Addres = "adress";
    public static final String REST_Number = "number";


    // CREATE TABLE

    private static final String REST_CREATE = "CREATE TABLE if not exists " +TABLE_REST_NAME + " ("
            +REST_ROWID + " integer PRIMARY KEY AUTOINCREMENT,"
            +REST_Name + " TEXT,"
            +REST_Photo + " BLOB,"
            +REST_Addres + " TEXT,"
            +REST_Number + " TEXT);";


    public HelperDB(Context context) {
        super(context, DATABASE_NAME ,null ,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(DB_CREATE);
        sqLiteDatabase.execSQL(REST_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS" + TABLE_REST_NAME);
        onCreate(sqLiteDatabase);

    }
}
