package com.example.startscreen;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class DataBaseHandler extends SQLiteOpenHelper {
    String TableName="user";
    String username="username";
    String password="password";
    String matchesPlayed="matchesPlayed";
    String matchesWon="matchesWon";
    String matchesLost="matchesLost";
    String profilePicturePath="profilePicturePath";


    public DataBaseHandler(Context context) {
        super(context,"chess.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_USER_TABLE = "CREATE TABLE " + TableName + " (" +
                username + " TEXT, " +
                password + " TEXT, " +
                matchesPlayed + " INTEGER, " +
                matchesWon + " INTEGER, " +
                matchesLost + " INTEGER, " +
                profilePicturePath + " INTEGER)";
        sqLiteDatabase.execSQL(CREATE_USER_TABLE);
       Log.d("CREATE_USER_TABLE:","SUCCESS");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TableName);
            String CREATE_USER_TABLE = "CREATE TABLE " + TableName + " (" +
                    username + " TEXT, " +
                    password + " TEXT, " +
                    matchesPlayed + " INTEGER, " +
                    matchesWon + " INTEGER, " +
                    matchesLost + " INTEGER, " +
                    profilePicturePath + " INTEGER)";
            db.execSQL(CREATE_USER_TABLE);
            Log.d("CREATE_USER_TABLE:","SUCCESS1");
        }
    }

    void addUser(UserInfo ui)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(username,ui.getName());
        values.put(password,ui.getPswd());
        values.put(matchesPlayed,ui.getMatchesPlayed());
        values.put(matchesWon,ui.getMatchesWon());
        values.put(matchesLost,ui.getMatchesLost());
        values.put(profilePicturePath,-1);
        db.insert(TableName,null,values);
        db.close();
        Log.d("USERINFODB:","record added");
    }
    public String getUsername(String inputUsername) {
        String uname = "";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        try {
            String[] columns = {"username"};
            String selection = "username = ?";
            String[] selectionArgs = {inputUsername};
            cursor = db.query("user", columns, selection, selectionArgs, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                uname = cursor.getString(cursor.getColumnIndex("username"));
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }
        return uname;
    }

    public String getPassword(String username) {
        String password = "";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;

        try {
            String[] columns = {"password"};
            String selection = "username=?";
            String[] selectionArgs = {username};

            cursor = db.query("user", columns, selection, selectionArgs, null, null, null);

            if (cursor != null && cursor.moveToFirst()) {
                password = cursor.getString(cursor.getColumnIndex("password"));
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }

        return password;
    }
    public int getMatchesPlayedForUser(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        int matchesPlayed = 0;

        String[] columns = { "matchesPlayed" };
        String selection = "username = ?";
        String[] selectionArgs = { username };

        Cursor cursor = db.query(TableName, columns, selection, selectionArgs, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            matchesPlayed = cursor.getInt(cursor.getColumnIndex("matchesPlayed"));
            cursor.close();
        }

        db.close();
        return matchesPlayed;
    }
    public int getMatchesWonForUser(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        int matchesWon = 0;

        String[] columns = { "matchesWon" };
        String selection = "username = ?";
        String[] selectionArgs = { username };

        Cursor cursor = db.query(TableName, columns, selection, selectionArgs, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            matchesWon = cursor.getInt(cursor.getColumnIndex("matchesWon"));
            cursor.close();
        }

        db.close();
        return matchesWon;
    }
    public int getMatchesLostForUser(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        int matchesLost = 0;
        String[] columns = { "matchesLost" };
        String selection = "username = ?";
        String[] selectionArgs = { username };

        Cursor cursor = db.query(TableName, columns, selection, selectionArgs, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            matchesLost = cursor.getInt(cursor.getColumnIndex("matchesLost"));
            cursor.close();
        }

        db.close();
        return matchesLost;
    }
    public void updateMatchesPlayedForUser(String username, int newMatchesPlayed) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("matchesPlayed", newMatchesPlayed);

        String whereClause = "username = ?";
        String[] whereArgs = { username };

        db.update(TableName, values, whereClause, whereArgs);

        db.close();
    }
    public void updateMatchesWonForUser(String username, int newMatchesWon) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("matchesWon", newMatchesWon);

        String whereClause = "username = ?";
        String[] whereArgs = { username };

        db.update(TableName, values, whereClause, whereArgs);

        db.close();
    }
    public void updateMatchesLostForUser(String username, int newMatchesLost) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("matchesLost", newMatchesLost);

        String whereClause = "username = ?";
        String[] whereArgs = { username };

        db.update(TableName, values, whereClause, whereArgs);
        db.close();
    }

    public int updatePswd(String nstr,String pstr)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(password, pstr);
        String whereClause = username + " = ?";
        String[] whereArgs = {nstr};
        return db.update(TableName, values, whereClause, whereArgs);
    }
    public int updateUname(String OldUname,String newUname)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(username, newUname);
        String whereClause = username + " = ?";
        String[] whereArgs = {OldUname};
        return db.update(TableName, values, whereClause, whereArgs);
    }
    public void del() {
        SQLiteDatabase db = this.getWritableDatabase();
        String deleteAllQuery = "DELETE FROM " + TableName + ";";
        db.execSQL(deleteAllQuery);
        db.close();
    }
    public void updateImage(String username, int i) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        String imageData = Integer.toString(i);
        values.put("profilePicturePath", imageData);
        String whereClause = "username = ?";
        String[] whereArgs = { username };
        db.update(TableName, values, whereClause, whereArgs);
       // Log.d("UpdateImage:","Image Vlaue Updared");
        db.close();
    }
    public int getImage(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        int imageData = -1;
        String[] projection = { "profilePicturePath" };
        String selection = "username = ?";
        String[] selectionArgs = { username };
        Cursor cursor = db.query(TableName, projection, selection, selectionArgs, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            imageData = cursor.getInt(cursor.getColumnIndex("profilePicturePath"));
            cursor.close();
        }
        return imageData;
    }
}
