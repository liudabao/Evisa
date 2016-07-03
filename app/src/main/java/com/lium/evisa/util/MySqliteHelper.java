package com.lium.evisa.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2016/6/22.
 */
public class MySqliteHelper extends SQLiteOpenHelper {

    public static String CREATE_LOGIN="create table login (id varchar primary key,status boolean, token varchar, imageUrl varchar,name varchar )";
    private  Context mContext;

    public MySqliteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_LOGIN);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
