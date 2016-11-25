package com.example.abdotarek.restapp.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

/**
 * Created by AbdoTarek on 8/22/2016.
 */
public class ControllerDB {

    Context context;
    HelperDB help;
    SQLiteDatabase database;

    public ControllerDB (Context c){
        this.context=c;
        help = new HelperDB(c);
    }

    public void OpenDB (){
       database = help.getWritableDatabase();
    }

    public void CloseDB (){
        help.close();
    }

    public long InsertDB (String name,String email,String pass,Double age,Double phone,String type){
        OpenDB();

        ContentValues values = new ContentValues();

        values.put(HelperDB.KEY_Name , name);
        values.put(HelperDB.KEY_Email , email);
        values.put(HelperDB.KEY_password , pass);
        values.put(HelperDB.KEY_Age , age);
        values.put(HelperDB.KEY_Phone , phone);
        values.put(HelperDB.KEY_Type , type);

        long insert =database.insert(HelperDB.TABLE_NAME,null,values);

        CloseDB();
        return insert;
    }


    public void UpdateDB (String _id ,String name,String email,String pass,Double age,Double phone,String type){
        OpenDB();

        ContentValues values = new ContentValues();

        values.put(HelperDB.KEY_Name , name);
        values.put(HelperDB.KEY_Email , email);
        values.put(HelperDB.KEY_password , pass);
        values.put(HelperDB.KEY_Age , age);
        values.put(HelperDB.KEY_Phone , phone);
        values.put(HelperDB.KEY_Type , type);

        database.update(HelperDB.TABLE_NAME, values, HelperDB.KEY_ROWID + "=" + _id, null);

        CloseDB();

    }

    public void DeleteDB (String name){

        OpenDB();

        database.delete(HelperDB.TABLE_NAME, HelperDB.KEY_Name + "=" + name, null);

        CloseDB();
    }

    public Cursor GetData (){

        OpenDB();
        String  [] columns = {HelperDB.KEY_ROWID,HelperDB.KEY_Name,HelperDB.KEY_Email,
                HelperDB.KEY_password ,HelperDB.KEY_Age ,HelperDB.KEY_Type };

        Cursor c = database.query(HelperDB.TABLE_NAME, columns, null, null, null, null, null);

        if (c != null ){
            c.moveToFirst();
        }

        CloseDB();

        return c;
    }

    public Cursor select(String email,String pass,Context context){
        OpenDB();

        Cursor c=database.rawQuery("select * from user where email='"+email+"' and password='"+pass+"'",null);
        c.moveToFirst();

        if(c == null){
            Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
        }else {
//            Toast.makeText(context, "That is Ok "+c.getInt(0), Toast.LENGTH_SHORT).show();
        }
        CloseDB();
        return c;
    }

// byte [] image;
    public long InsertREST (String name, byte [] photo, String adress, String number){
        OpenDB();


        ContentValues values = new ContentValues();

        values.put(HelperDB.REST_Name , name);
        values.put(HelperDB.REST_Photo , photo);
        values.put(HelperDB.REST_Addres , adress);
        values.put(HelperDB.REST_Number , number);

        long inset = database.insert(HelperDB.TABLE_REST_NAME,null,values);

        CloseDB();
        return inset;
    }

    public Cursor GetDataRest (){

        OpenDB();
        String  [] columns = {HelperDB.REST_ROWID,HelperDB.REST_Name,HelperDB.REST_Photo,
                HelperDB.REST_Addres ,HelperDB.REST_Number};

        Cursor c = database.query(HelperDB.TABLE_REST_NAME, columns, null, null, null, null, null);

        if (c != null ){
            c.moveToFirst();
        } else {
            c.moveToNext();
        }

        CloseDB();

        return c;
    }
}

