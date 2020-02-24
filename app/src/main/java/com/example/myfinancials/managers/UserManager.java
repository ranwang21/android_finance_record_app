package com.example.myfinancials.managers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myfinancials.entities.User;
import com.example.myfinancials.services.ConnexionBd;

import java.util.ArrayList;


public class UserManager {

    private static String queryGetAllUsers = "select * from user";
    public static ArrayList<User> getAllUsers(Context ctx) {
        ArrayList<User> users = new ArrayList<>();
        SQLiteDatabase bd = ConnexionBd.getBd(ctx);
        Cursor cursor = bd.rawQuery(queryGetAllUsers, null);
        while (cursor.moveToNext()) {
            users.add(new User(cursor.getString(1), cursor.getString(2)));
        }
        ConnexionBd.close();
        return users;
    }
    // temporary code to test app
//    public static Boolean checkUserLogin(Context ctx, String email, String password){
//        return true;
//    }
    public static User checkUserLogin(Context ctx, String email, String password) {
        SQLiteDatabase bd = ConnexionBd.getBd(ctx);
        ArrayList<User> users = getAllUsers(ctx);
        for (User user : users) {
            if (email.equals(user.getEmail()) && password.equals(user.getPassword())) {
                return user;
            }
        }
        return null;
    }

    public static void addOneUser(Context ctx, User user){
        SQLiteDatabase bd = ConnexionBd.getBd(ctx);
        ContentValues values = new ContentValues();
        values.put("email", user.getEmail());
        values.put("password", user.getPassword());
        bd.insert("user", null, values);
    }
}
