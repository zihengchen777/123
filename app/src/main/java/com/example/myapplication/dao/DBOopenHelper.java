package com.example.myapplication.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOopenHelper extends SQLiteOpenHelper {


    public DBOopenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context,"SqliteTest.db",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql=
                "create table if not exists t_user("+
                        "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"+
                        "uname VARCHAR(255),"+
                        "upwd VARCHAR(255),"+
                        "isDel INTEGER DEFAULT 0"+
                        ")";
        sqLiteDatabase.execSQL(sql);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
