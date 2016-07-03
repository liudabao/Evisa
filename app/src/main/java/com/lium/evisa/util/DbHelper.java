package com.lium.evisa.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.lium.evisa.entity.User;
import com.lium.evisa.global.GlobalValue;

/**
 * Created by Administrator on 2016/6/22.
 */
public class DbHelper {

    MySqliteHelper helper;

    public DbHelper(Context context, String db, int version){
        // helper=new MySqliteHelper(context, "person.db", null, 2);
        helper=new MySqliteHelper(context, db, null, version);
    }

    public void save(ContentValues values, String table){
        SQLiteDatabase db=helper.getWritableDatabase();
        db.beginTransaction();
        db.insert(table, null, values);
        Log.e("insert", values.get("id")+"");
        db.setTransactionSuccessful();
        db.endTransaction();
    }

    public void update( String id, ContentValues values, String table) {
        SQLiteDatabase db=helper.getWritableDatabase();
        db.beginTransaction();
       // ContentValues values=new ContentValues();
       // values.put("length", length);
        //db.execSQL("update person set age=? where id=?",new String[]{"222","1"});
        db.update(table, values, "id=?", new String[]{id});
        db.setTransactionSuccessful();
        db.endTransaction();
    }

    public void deleteAll(String table) {
        SQLiteDatabase db=helper.getWritableDatabase();
        db.beginTransaction();
        Cursor cursor=db.query(table, null, null, null, null, null, null);
        if(cursor.moveToFirst()){
            do{
                // Log.e("name", cursor.getString(cursor.getColumnIndex("name")));
                // Log.e("age", ""+cursor.getInt(cursor.getColumnIndex("age")));
                // Log.e("id", ""+cursor.getInt(cursor.getColumnIndex("id")));
                db.delete(table, "id=?", new String[]{cursor.getString(cursor.getColumnIndex("id"))});
            }while(cursor.moveToNext());
        }

        db.setTransactionSuccessful();
        db.endTransaction();
    }

    public void delete(String id,String table) {
        SQLiteDatabase db=helper.getWritableDatabase();
        db.beginTransaction();
        db.delete(table, "id=?", new String[]{id});
        db.setTransactionSuccessful();
        db.endTransaction();
    }

    public User query( String table) {
        User user=new User();
        SQLiteDatabase db=helper.getReadableDatabase();
        db.beginTransaction();
        Cursor cursor=db.query(table, null, null, null, null, null, null);
        if(cursor.moveToFirst()){
            do{
                // Log.e("name", cursor.getString(cursor.getColumnIndex("name")));
                // Log.e("age", ""+cursor.getInt(cursor.getColumnIndex("age")));
                // Log.e("id", ""+cursor.getInt(cursor.getColumnIndex("id")));
                user.setId(cursor.getString(cursor.getColumnIndex("id")));
                user.setImageUrl(cursor.getString(cursor.getColumnIndex("imageUrl")));
            }while(cursor.moveToNext());
        }
        cursor.close();
        //db.rawQuery("select * from person",null);
        db.setTransactionSuccessful();
        db.endTransaction();
        return user;
    }

    public boolean isExist(String table) {
        boolean find=false;
        SQLiteDatabase db=helper.getReadableDatabase();
        db.beginTransaction();
        Cursor cursor=db.query(table, null, null, null, null, null, null);
        if(cursor.moveToFirst()){
            do{
                 Log.e("name", cursor.getString(cursor.getColumnIndex("name")));
                // Log.e("age", ""+cursor.getInt(cursor.getColumnIndex("age")));
                 find=true;
                 break;

            }while(cursor.moveToNext());
        }
        cursor.close();
        //db.rawQuery("select * from person",null);
        db.setTransactionSuccessful();
        db.endTransaction();
        return find;
    }
}
