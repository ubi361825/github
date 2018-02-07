package com.example.im.pigeonapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by thomas on 2017/12/30.
 */


    public class DataBaseHelper extends SQLiteOpenHelper {

        private static final int DATABASE_VERSION = 1;
        private static final String DATABASE_NAME = "test1.db";
        private static final String TABLE_NAME = "test1";
        private static final String COLUMN_ID = "id";
        private static final String COLUMN_ACCOUNT = "account";
        private static final String COLUMN_PASSWORD = "password";
        private static final String COLUMN_NICKNAME = "nickname";
        private static final String COLUMN_NAME = "name";
        private static final String COLUMN_PHONE = "phone";
        private static final String COLUMN_EMAIL = "email";
        private static final String COLUMN_ADDRESS = "address";
        private static final String COLUMN_GENDER = "gender";
        private static final String COLUMN_BDATE = "bdate";
        private static final String COLUMN_STATUS = "status";
        SQLiteDatabase db;
        private static final String TABLE_CREATE = "create table test1 (id integer primary key not null , " +
                "account text not null , password text not null , nickname text not null , name text not null , phone not null , email text not null , address text , gender text not null , bdate text , status text not null);";

        public DataBaseHelper(Context context){
            super(context , DATABASE_NAME , null , DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(TABLE_CREATE);
            this.db = db;
        }

        public String searchPass(String account){
            db = this.getReadableDatabase();
            String query = "SELECT account, password from "+TABLE_NAME;
            Cursor cursor = db.rawQuery(query, null);
            String a, b;
            b="找不到密碼！";
            if(cursor.moveToFirst())
            {
                do{
                    a = cursor.getString(0);
                    b = cursor.getString(1);
                    if(a.equals(account))
                    {
                        b = cursor.getString(1);
                        break;
                    }
                }
                while (cursor.moveToNext());
            }
            return b;
        }

    public void insertContact(contact c){
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String query = "SELECT * FROM " + TABLE_NAME;

        Cursor cursor = db.rawQuery(query, null);
        int count = cursor.getCount();

        values.put(COLUMN_ID, count);
        values.put(COLUMN_ACCOUNT, c.getAccount());
        values.put(COLUMN_PASSWORD, c.getPassword());
        values.put(COLUMN_NICKNAME, c.getNickname());
        values.put(COLUMN_NAME, c.getName());
        values.put(COLUMN_PHONE, c.getPhone());
        values.put(COLUMN_EMAIL, c.getEmail());
        values.put(COLUMN_ADDRESS, c.getAddress());
        values.put(COLUMN_GENDER, c.getGender());
        values.put(COLUMN_BDATE, c.getBdate());
        values.put(COLUMN_STATUS, c.getStatus());

        db.insert(TABLE_NAME , null , values);
    }


    @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            String query = "DROP TABLE IF EXISTS" + TABLE_NAME;
            db.execSQL(query);
            this.onCreate(db);
        }


}
