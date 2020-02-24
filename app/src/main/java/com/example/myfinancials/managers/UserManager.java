package com.example.myfinancials.managers;

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
    public static int checkUserLogin(Context ctx, String email, String password) {
        SQLiteDatabase bd = ConnexionBd.getBd(ctx);
        int userId = -1;
        ArrayList<User> users = getAllUsers(ctx);
        for (User user : users) {
            if (email.equals(user.getEmail()) && password.equals(user.getPassword())) {
                userId = user.getId_user();
            }
        }
        return userId;
    }
//    public boolean checkLogin(String username, String password) {
//        SQLiteDatabase db = md.getWritableDatabase();
//
//        String s;
//        Cursor c = db.rawQuery("SELECT * FROM MyTable WHERE " + username + " =? AND" + password + " =?", null);
//
//        if(c.getCount() <= 0) {
//            c.close();
//            db.close();
//            return false;
//        } else {
//            c.close();
//            db.close();
//            return true;
//        }
//    }
}
