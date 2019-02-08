package com.example.sqlitedemo1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Sqlitehelper extends SQLiteOpenHelper {

    private static final String mDb_Name = "Student.db";
    private static final String mTable_Name = "Student_table";
    private static final String mCol_1 = "Id ";
    private static final String mCol_2 = "Name ";
    private static final String mCol_3 = "SurName ";




    public Sqlitehelper( Context context) {
        super(context, mDb_Name, null,1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " +mTable_Name + "(" +
                "ID" + "INTEGER PRIMARY KEY , " +
                "NAME"+" TEXT ,"+
                "SURNAME" + " TEXT"+ ")" );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean insert(String Id , String Name , String Surname){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(mCol_1,Id);
        contentValues.put(mCol_2,Name);
        contentValues.put(mCol_3,Surname);
          long i = sqLiteDatabase.insert(mTable_Name,null,contentValues);
          if(i == -1) {
              return false;
          }
          else
              return true;

    }

    public Cursor getAllData(){

        SQLiteDatabase sqlitehelper = this.getWritableDatabase();
        Cursor cursor = (Cursor) sqlitehelper.rawQuery("select * from "+mTable_Name,null);
        return  cursor;
    }
}
