package com.example.persistence.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.persistence.Model.Post;
import com.example.persistence.Model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by H_P on 2016/4/27.
 *
 * @author luo
 * @version 1.0
 */
public class PostsDatabaseHelper extends SQLiteOpenHelper {
    private static PostsDatabaseHelper sInstance;
    //Database Info
    private static final String DATABASE_NAME = "postsDatabase";
    private static final int DATABASE_VERSION = 1;
    //Table Names
    private static final String TABLE_POSTS = "posts";
    private static final String TABLE_USERS = "users";
    //Table Posts Columns
    private static final String KEY_POST_ID = "id";
    private static final String KEY_POST_USER_ID_FK = "userId";
    private static final String KEY_POST_TEXT = "text";
    //Table Users Columns
    private static final String KEY_USER_ID = "id";
    private static final String KEY_USER_NAME = "userName";
    private static final String KEY_USER_PROFILE_PICTURE_URL = "profilePictureUrl";

    public static synchronized PostsDatabaseHelper getInstance(Context context) {
        if (sInstance == null)
            sInstance = new PostsDatabaseHelper(context.getApplicationContext());
        return sInstance;
    }

    public PostsDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_POSTS_TABLE = "CREATE TABLE " + TABLE_POSTS +
                "(" +
                KEY_POST_ID + " INTEGER PRIMARY KEY," +
                KEY_POST_USER_ID_FK + " INTEGER REFERENCES " + TABLE_USERS + "," +
                KEY_POST_TEXT + " TEXT"
                + ")";
        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS +
                "(" +
                KEY_USER_ID + " INTEGER PRIMARY KEY," +
                KEY_USER_NAME + " TEXT," +
                KEY_USER_PROFILE_PICTURE_URL + " TEXT"
                + ")";
        db.execSQL(CREATE_POSTS_TABLE);
        db.execSQL(CREATE_USERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_POSTS);
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_USERS);
        onCreate(db);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        db.setForeignKeyConstraintsEnabled(true);
    }

    public void addPost(Post post) {

        SQLiteDatabase db = getWritableDatabase();
        try {
            db.beginTransaction();
            long userId = addOrUpdateUser(post.getUser());
            ContentValues values = new ContentValues();
            values.put(KEY_POST_TEXT, post.getText());
            values.put(KEY_POST_USER_ID_FK, userId);

            db.insertOrThrow(TABLE_POSTS, null, values);
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    public long addOrUpdateUser(User user) {
        SQLiteDatabase db = getWritableDatabase();
        long userId = -1;
        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put(KEY_USER_NAME, user.getUserName());
            values.put(KEY_USER_PROFILE_PICTURE_URL, user.getProfilePictureUrl());

            int rows = db.update(TABLE_USERS, values, KEY_USER_NAME + "=?", new String[]{user.getUserName()});
            if (rows == 1) {
                String userSelectQuery = String.format("SELECT %s FROM %s WHERE %s = ?", KEY_USER_ID, TABLE_USERS, KEY_USER_NAME);
                Cursor cursor = db.rawQuery(userSelectQuery, new String[]{user.getUserName()});
                try {
                    if (cursor.moveToFirst()) {
                        userId = cursor.getInt(0);
                        db.setTransactionSuccessful();
                    }
                } finally {
                    if (cursor != null || !cursor.isClosed())
                        cursor.close();
                }
            } else {
                userId = db.insertOrThrow(TABLE_USERS, null, values);
                db.setTransactionSuccessful();
            }
        } finally {
            db.endTransaction();
        }

        return userId;
    }

    public List<Post> getAllPosts() {
        List<Post> posts = new ArrayList<>();
        String POSTS_SELECT_QUERY = String.format("SELECT * FROM %s LEFT OUTER JOIN %s ON %s.%s = %s.%s",
                TABLE_POSTS, TABLE_USERS, TABLE_POSTS, KEY_POST_USER_ID_FK, TABLE_USERS, KEY_USER_ID
        );
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(POSTS_SELECT_QUERY, null);
        try {
            if (cursor.moveToFirst()) {
                do {
                    User user = new User();
                    user.setUserName(cursor.getString(cursor.getColumnIndex(KEY_USER_NAME)));
                    user.setProfilePictureUrl(cursor.getString(cursor.getColumnIndex(KEY_USER_PROFILE_PICTURE_URL)));
                    Post post = new Post();
                    post.setUser(user);
                    post.setText(cursor.getString(cursor.getColumnIndex(KEY_POST_TEXT)));
                    posts.add(post);
                } while (cursor.moveToNext());
            }
        } finally {
            if (cursor != null || !cursor.isClosed())
                cursor.close();
        }
        return posts;
    }

    public int updateUserProfileUrl(User user) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_USER_PROFILE_PICTURE_URL, user.getProfilePictureUrl());
        return db.update(TABLE_USERS, values, KEY_USER_NAME + "=?", new String[]{user.getUserName()});
    }

    public void deleteAllPostsAndUsers() {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        try {
            db.delete(TABLE_POSTS, null, null);
            db.delete(TABLE_USERS, null, null);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
        }
    }
}
