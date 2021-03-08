package com.rku_18fotca11036.tutorial07;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String MYDB = "student";
    public static final String MYTABLE = "student_master";
    public static final String COLUMN_1 = "id";
    public static final String COLUMN_2 = "fname";
    public static final String COLUMN_3 = "lname";
    public static final String COLUMN_4 = "username";
    public static final String COLUMN_5 = "password"; //vairable


    public DatabaseHelper(@Nullable Context context) {
        super(context, MYDB, null, 1); //1.3
//        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "create table if not exists " + MYTABLE + "(id integer " +
                "primary key autoincrement, fname text, lname text, username text, password text)";
        db.execSQL(sql);


        //db.execsql("create table if not existes")
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table " + MYTABLE);
        onCreate(db);
    }

    public boolean insertData(String fname, String lname, String username, String password)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_2, fname);
        contentValues.put(COLUMN_3, lname);
        contentValues.put(COLUMN_4, username);
        contentValues.put(COLUMN_5, password);

        long result = db.insert(MYTABLE,null,contentValues);
        if(result == -1)
        {
            return false;
        }
        else
            {
            return true;
        }

    }

    public Cursor getData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.query(MYTABLE,
                null,
                null,
                null,
                null,
                null,
                null
        );
    }

    public Boolean match(String username, String password)
    {
        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.rawQuery("select * from student_master where username=? and password=?",new String[]{username,password});
        Cursor cursor = db.rawQuery("select * from student_master where username=? and password=?", new String[]{username, password});
        if(cursor.getCount() > 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

}
