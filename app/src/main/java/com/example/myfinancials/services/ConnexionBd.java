package com.example.myfinancials.services;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.myfinancials.helpers.BdMyFinancialHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;


public class ConnexionBd {

    private static SQLiteDatabase bd;
    public static String nomBd = "bd_android";
    private static int version = 1;
    public static SQLiteDatabase getBd(Context ctx) {
        BdMyFinancialHelper helper = new BdMyFinancialHelper(ctx, nomBd, null, version);
        bd = helper.getWritableDatabase();
        return bd;
    }
    public static void close() {
        bd.close();
    }
    public static void copyBdFromAssets(Context context) {
        File bdApp = context.getDatabasePath(nomBd);
        AssetManager assetManager = context.getAssets();
        try {
            InputStream in = assetManager.open("bd/bd_android.db");
            FileOutputStream out = new FileOutputStream(bdApp);
            byte[] buffer = new byte[256];
            while (in.read(buffer) != -1) {
                out.write(buffer);
                buffer = new byte[256];
            }
            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
