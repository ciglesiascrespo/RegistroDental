package com.iglesias.c.registrodental.Db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Ciglesias on 18/02/2018.
 */

public class DbHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "data";
    private final String TAG = getClass().getName();

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.e(TAG, "Creo DbHelper");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(UserDb.getCreateTable());


        insertData(db);
    }

    private void insertData(SQLiteDatabase db) {
        ContentValues cv = new ContentValues();
        cv.put(UserDb.KEY_ID, 1);
        cv.put(UserDb.KEY_APELLIDO1, "Iglesias");
        cv.put(UserDb.KEY_NOMBRE1, "Carlos");
        cv.put(UserDb.KEY_CONTRASENA, "123");
        cv.put(UserDb.KEY_CORREO, "ciglesiascrespo@gmail.com");
        db.insert(UserDb.TABLE, null, cv);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + UserDb.TABLE);

        onCreate(db);
    }

    public long insert(String tableName, ContentValues cv) {
        long i = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            i = db.insert(tableName, null, cv);
            //if (db.isOpen()) db.close();
        } catch (Exception e) {
            //db.close();
            Log.e(TAG, "Error insertando en la base de datos: " + e.getMessage());
            e.printStackTrace();

        }
        return i;

    }

    public Cursor execSql(String query) {
        Cursor c = null;
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            c = db.rawQuery(query, null);
            // db.close();
        } catch (Exception e) {
            //if (db.isOpen()) db.close();
            Log.e(TAG, "Error ejecutando sql: " + query + " " + e.getMessage());
            e.printStackTrace();
        }

        return c;
    }

    public void update(String tableName, ContentValues cv, String condition) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            db.update(tableName, cv, condition, null);
            //  db.close();
        } catch (Exception e) {
            // if (db.isOpen()) db.close();
            Log.e(TAG, "Error update: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void delete(String tableName, String condition) {
        SQLiteDatabase db = this.getWritableDatabase();

        try {
            db.delete(tableName, condition, null);
            //  db.close();
        } catch (Exception e) {
            //  if (db.isOpen()) db.close();
            Log.e(TAG, "Error delete: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
