package com.example.myfinancials.managers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myfinancials.entities.Record;
import com.example.myfinancials.services.ConnexionBd;

import java.util.ArrayList;

// TODO : getExpensesSumByCategoryByUser
// TODO : getIncomeSumByUserId

public class RecordManager {

    /**
     * Add one record to database
     * Tested
     * @param ctx
     * @param record
     */
    public static long addOneRecord(Context ctx, Record record) {
        // init result
        long result;
        // get the bd connection
        SQLiteDatabase bd = ConnexionBd.getBd(ctx);
        // fill query content values
        ContentValues contentValues = new ContentValues();
        contentValues.put("amount", record.getAmount());
        contentValues.put("description", record.getDescription());
        contentValues.put("date", record.getDate());
        contentValues.put("id_category", record.getId_category());
        contentValues.put("id_user", record.getId_user());
        // Call SQLite api
        result = bd.insertOrThrow("record", null, contentValues);
        bd.close();
        return result;
    }

    /**
     * Delete a record by id
     * Tested
     * @param ctx
     * @param id  record's id
     */
    public static void deleteOneRecordById(Context ctx, int id) {
        // get the bd connection
        SQLiteDatabase bd = ConnexionBd.getBd(ctx);
        // call SQLite api
        bd.delete("record", "id=?", new String[]{"1"});
    }

    /**
     * Get records from a user
     *
     * @param ctx
     * @param id_user
     * @return all records of a user
     */
    public static ArrayList<Record> getRecordsByUserId(Context ctx, int id_user) {
        // get the bd connection
        SQLiteDatabase bd = ConnexionBd.getBd(ctx);
        // init the object to return
        ArrayList<Record> records = new ArrayList<>();
        // Write sql query
        String queryGetRecordsByUserId = "SELECT * FROM record WHERE id_user = ?";
        // get result(s) cursor
        Cursor cursor = bd.rawQuery(queryGetRecordsByUserId, new String[]{"" + id_user});
        // iterate through lines to fill the object to return
        while (cursor.moveToNext()) {
            records.add(new Record(cursor.getDouble(1), cursor.getString(2), cursor.getString(3), cursor.getInt(4), cursor.getInt(5)));
        }
        // return result
        return records;
    }

    /**
     * Get all records
     *
     * @param ctx
     * @return a list of record objects
     */
    public static ArrayList<Record> getAllRecords(Context ctx) {
        // get the bd connection
        SQLiteDatabase bd = ConnexionBd.getBd(ctx);
        // init the object to return
        ArrayList<Record> records = new ArrayList<>();
        // write sql query
        String queryGetAllRecords = "SELECT * FROM record";
        // get result(s) cursor
        Cursor cursor = bd.rawQuery(queryGetAllRecords, null);
        // iterate through lines to fill the object to return
        while (cursor.moveToNext()) {
            records.add(new Record(cursor.getDouble(1), cursor.getString(2), cursor.getString(3), cursor.getInt(4), cursor.getInt(5)));
        }
        ConnexionBd.close();
        // return result
        return records;
    }

}
