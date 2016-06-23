package com.example.persistence.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.persistence.Model.Todo;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by H_P on 2016/4/28.
 *
 * @author luo
 * @version 1.0
 */
public class TodoDatabaseHelper extends SQLiteOpenHelper {
    private static TodoDatabaseHelper sInstance;
    private static final String TABLE_TODO_ITEMS = "todo_items";

    private static final String KEY_TODO_ID = "_id";
    private static final String KEY_TODO_BODY = "body";
    private static final String KEY_TODO_PRIORITY = "priority";

    public static TodoDatabaseHelper getInstance(Context context, int version) {
        if (sInstance == null)
            sInstance = new TodoDatabaseHelper(context.getApplicationContext(), version);
        return sInstance;
    }

    private TodoDatabaseHelper(Context context, int version) {
        super(context, TABLE_TODO_ITEMS, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_TODO = "CREATE TABLE " + TABLE_TODO_ITEMS +
                "(" +
                KEY_TODO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                KEY_TODO_BODY + " TEXT," +
                KEY_TODO_PRIORITY + " INTEGER"
                + ")";
        db.execSQL(CREATE_TABLE_TODO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion == oldVersion)
            try {
                throw new Exception("version is not consistent");
            } catch (Exception e) {
                e.printStackTrace();
            }
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TODO_ITEMS);
        onCreate(db);
    }


    public void addItem(Todo item) {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put(KEY_TODO_BODY, item.getBody());
            values.put(KEY_TODO_PRIORITY, item.getPriority());
            db.insertOrThrow(TABLE_TODO_ITEMS, null, values);
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    public int updateItem(Todo item) {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put(KEY_TODO_PRIORITY, item.getPriority());
            int rows = db.update(TABLE_TODO_ITEMS, values, KEY_TODO_BODY + "=?", new String[]{item.getBody()});
            db.setTransactionSuccessful();
            return rows;
        } finally {
            db.endTransaction();
        }
    }

    public Cursor queryItem() {
        List<Todo> items = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String TODO_SELECT_QUERY = "SELECT rowid _id,* FROM " + TABLE_TODO_ITEMS;
        return db.rawQuery(TODO_SELECT_QUERY,null);

    }
}
