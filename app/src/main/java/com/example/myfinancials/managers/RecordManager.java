package com.example.myfinancials.managers;

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
     *
     * @param ctx
     * @param record
     */
    private static void addOneRecord(Context ctx, Record record) {
        // get the bd connection
        SQLiteDatabase bd = ConnexionBd.getBd(ctx);
        // write sql query
        String queryAddOneRecord = "INSERT INTO record (amount, description, date, id_category, id_user) VALUES (?, ?, ?, ?, ?)";
        // execute query
        bd.execSQL(queryAddOneRecord, new String[]{"" + record.getAmount(), record.getDescription(), record.getDate(), "" + record.getId_category(), "" + record.getId()});
    }

    /**
     * Delete a record by id
     * @param ctx
     * @param id record's id
     */
    private static void deleteOneRecordById(Context ctx, int id) {
        // get the bd connection
        SQLiteDatabase bd = ConnexionBd.getBd(ctx);
        // write sql query
        String queryAddOneRecord = "DELETE FROM record WHERE id = ?";
        // execute query
        bd.execSQL(queryAddOneRecord, new String[]{"" + id});
    }

    /**
     * Get records from a user
     * @param ctx
     * @param id_user
     * @return all records of a user
     */
    private static ArrayList<Record> getRecordsByUserId(Context ctx, int id_user){
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
            records.add(new Record(cursor.getInt(0), cursor.getDouble(1), cursor.getString(2), cursor.getString(3), cursor.getInt(4), cursor.getInt(5)));
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
    private static ArrayList<Record> getAllRecords(Context ctx) {
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
            records.add(new Record(cursor.getInt(0), cursor.getDouble(1), cursor.getString(2), cursor.getString(3), cursor.getInt(4), cursor.getInt(5)));
        }
        ConnexionBd.close();
        // return result
        return records;
    }

}
