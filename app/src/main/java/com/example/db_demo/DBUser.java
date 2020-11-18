package com.example.db_demo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBUser extends SQLiteOpenHelper {

    public DBUser(@Nullable Context context,
                  @Nullable String name,
                  @Nullable SQLiteDatabase.CursorFactory factory,
                  int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlQuery = "CREATE TABLE USERS (id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT)";
        db.execSQL(sqlQuery);
    }

    public void addUser(User user){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name",user.getName());
        database.insert("USERS",null,values);
    }

    public ArrayList<User> getAll(){
        SQLiteDatabase database = this.getReadableDatabase();

        ArrayList<User> users = new ArrayList<>();
        String sql = "select * from users";
        Cursor cursor = database.rawQuery(sql,null);

        if(cursor.moveToFirst()){
            do {
                User user = new User();
                user.setId(cursor.getString(0));
                user.setName(cursor.getString(1));
                users.add(user);
            }while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return users;
    }

    public void removeUser(String id){
        SQLiteDatabase database = this.getWritableDatabase();
        database.delete("USERS", "id = ?",new String[]{id});
        database.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
