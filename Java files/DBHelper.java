package com.example.leap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context){
        super(context,"BusinessDatabase.db",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("create table Business(id int primary key ,"+
                "B_name TEXT not null, Category TEXT NOT NULL,"+
                " Description TEXT,Products TEXT, website TEXT, owner TEXT not null,"+
                "contact TEXT not null, address TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE if exists Business");
    }

    public boolean insertBusiness(int id, String b_name, String category, String description,String products, String website, String owner, String contact, String address){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id",id);
        contentValues.put("b_name",b_name);
        contentValues.put("category",category);
        contentValues.put("description",description);
        contentValues.put("products",products);
        contentValues.put("website",website);
        contentValues.put("owner",owner);
        contentValues.put("contact",contact);
        contentValues.put("address",address);
        long result = db.insert("Business",null,contentValues);
        if(result==-1)
            return false;
        else
            return true;
    }

    public Cursor getData(String cat){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        Cursor cursor = db.rawQuery("Select * from Business where category = ?",new String[]{cat},null);
        return cursor;
    }
}

