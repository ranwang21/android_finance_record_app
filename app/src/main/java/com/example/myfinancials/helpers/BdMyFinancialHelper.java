package com.example.myfinancials.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


public class BdMyFinancialHelper extends SQLiteOpenHelper {

    public BdMyFinancialHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE \"category\" (\n" +
                "\t\"id\"\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,\n" +
                "\t\"name\"\tTEXT NOT NULL UNIQUE\n" +
                ")");
        db.execSQL("CREATE TABLE \"record\" (\n" +
                "\t\"id\"\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,\n" +
                "\t\"amount\"\tREAL NOT NULL,\n" +
                "\t\"description\"\tTEXT,\n" +
                "\t\"id_user\"\tINTEGER NOT NULL,\n" +
                "\t\"id_category\"\tINTEGER NOT NULL,\n" +
                "\t\"date\"\tTEXT NOT NULL,\n" +
                "\tFOREIGN KEY(\"id_user\") REFERENCES \"user\"(\"id\"),\n" +
                "\tFOREIGN KEY(\"id_category\") REFERENCES \"category\"(\"id\")\n" +
                ")");
        db.execSQL("CREATE TABLE \"user\" (\n" +
                "\t\"id\"\tINTEGER PRIMARY KEY AUTOINCREMENT UNIQUE,\n" +
                "\t\"email\"\tTEXT NOT NULL UNIQUE,\n" +
                "\t\"password\"\tTEXT NOT NULL\n" +
                ")");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
