package com.david916.ibuy.ibuy;



import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import java.util.ArrayList;

class Database extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="ibuy.db";//数据库名称
    private static final int SCHEMA_VERSION=2;//版本号,则是升级之后的,升级方法请看onUpgrade方法里面的判断

    public Database(Context context) {//构造函数,接收上下文作为参数,直接调用的父类的构造函数
        super(context, DATABASE_NAME, null, SCHEMA_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE category (category TEXT);");
        db.execSQL("insert into category(category) Values('apple')");
        db.execSQL("insert into category(category) Values('banana')");
        db.execSQL("insert into category(category) Values('orange')");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public Cursor getAll() {
        StringBuilder buf=new StringBuilder("SELECT * FROM category");
        return(getReadableDatabase().rawQuery(buf.toString(), null));
    }


    public void insert(String category) {
        ContentValues cv=new ContentValues();
        cv.put("category", category);
        getWritableDatabase().insert("category", "category", cv);
    }

}