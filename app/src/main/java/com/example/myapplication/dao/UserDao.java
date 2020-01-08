package com.example.myapplication.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.entity.User;

import java.util.ArrayList;

public class UserDao {
    private DBOopenHelper dbOpenHelper;      //创建DBOpenHelper对象
    private SQLiteDatabase sqLiteDatabase; //创建SQLiteDatabase对象
    public UserDao(Context context){       //定义构造函数
        dbOpenHelper=new DBOopenHelper(context,null,null,0);  //初始化DBOpenHelper对象
    }
    //插入数据
    public void dbInsert(String uname, String upwd){
        sqLiteDatabase =dbOpenHelper.getWritableDatabase();
        String sql="insert into t_user(uname,upwd,isDel) values (?,?,0)";
        Object bindArgs[] = new Object[]{ uname,upwd};
        sqLiteDatabase.execSQL(sql,bindArgs);
    }
    //查询数据
    public int dbGetUserSize(){
        sqLiteDatabase = dbOpenHelper.getWritableDatabase();
        String sql="select count(*) from t_user where isDel=0";
        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);
        if (cursor.moveToNext())       //判断Cursor中是否有数据
        {
            return cursor.getInt(0);  //返回总记录数
        }
        return 0;                     //如果没有数据，则返回0
    }

    public User dbQueryOneByUsername(String uname){
        sqLiteDatabase = dbOpenHelper.getWritableDatabase();
        String sql="select * from t_user where uname=? and isDel=0";
        String[] selectionArgs = new String[]{ uname };
        Cursor cursor = sqLiteDatabase.rawQuery(sql,selectionArgs);
        if (cursor.moveToNext())  //判断Cursor中是否有数据
        {
            User user=new User();
            user.setId(cursor.getInt(cursor.getColumnIndex("id")));
            user.setUname(cursor.getString(cursor.getColumnIndex("uname")));
            user.setUpwd(cursor.getString(cursor.getColumnIndex("upwd")));
            return user; //返回总记录行数
        }
        return null;
    }
    //修改密码
    public void dbUpdatePassword(String uname, String newUpwd){
        sqLiteDatabase = dbOpenHelper.getWritableDatabase();
        String sql="update t_user set upwd=? where uname=? and isDel=0";
        Object bindArgs[] = new Object[]{ newUpwd,uname };
        sqLiteDatabase.execSQL(sql,bindArgs);
    }
    //查询新增数
    public ArrayList<User> dbQueryAll(){
        ArrayList<User> userArrayList = new ArrayList<User>();
        sqLiteDatabase = dbOpenHelper.getWritableDatabase();
        String sql="select * from t_user where isDel=0";
        Cursor cursor= sqLiteDatabase.rawQuery(sql,null);
        for (cursor.moveToFirst(); !(cursor.isAfterLast()); cursor.moveToNext()){
            if (cursor.getInt(cursor.getColumnIndex("isDel"))!=1){
                User user=new User();
                user.setId(cursor.getInt(cursor.getColumnIndex("id")));
                user.setUname(cursor.getString(cursor.getColumnIndex("uname")));
                user.setUpwd(cursor.getString(cursor.getColumnIndex("upwd")));
                userArrayList.add(user);
            }
        }
        return userArrayList;
    }

    //更新数据
    public void dbDeleteUser(int id){
        sqLiteDatabase = dbOpenHelper.getWritableDatabase();
        String sql="update t_user set isDel=1 where id=?";
        Object bindArgs[] = new Object[]{ id };
        sqLiteDatabase.execSQL(sql,bindArgs);
    }

}

