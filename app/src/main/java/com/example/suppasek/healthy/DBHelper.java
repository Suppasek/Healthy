package com.example.suppasek.healthy;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private final String TAG = getClass().getSimpleName();

    private SQLiteDatabase sqLiteDatabase;

    public DBHelper(Context context) {
        super(context, "sleep.db", null, 1);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_SLEEP_TABLE = "CREATE TABLE sleep ( id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "date TEXT, sleepTime TEXT, wakeTime TEXT, totalSleep TEXT)";
        db.execSQL(CREATE_SLEEP_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP_SLEEP_TABLE = "DROP TABLE IF EXISTS sleep";

        db.execSQL(DROP_SLEEP_TABLE);

        onCreate(db);
    }

    public ArrayList<Sleep> getSleepList() {
        ArrayList<Sleep> sleeps = new ArrayList<>();

        sqLiteDatabase = this.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.query
                ("sleep", null, null, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        while(!cursor.isAfterLast()) {

            Sleep sleep = new Sleep(cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));

            sleeps.add(sleep);

            cursor.moveToNext();
        }

        sqLiteDatabase.close();

        return sleeps;
    }

    public void addSleep(Sleep sleep) {
        sqLiteDatabase = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        //values.put(Friend.Column.ID, friend.getId());
        values.put("date", sleep.getDate());
        values.put("sleepTime", sleep.getSleepTime());
        values.put("wakeTime", sleep.getWakeTime());
        values.put("totalSleep", sleep.getTotalSleepTime());

        sqLiteDatabase.insert("sleep", null, values);

        sqLiteDatabase.close();
    }
}

