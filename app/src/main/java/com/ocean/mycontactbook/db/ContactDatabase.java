package com.ocean.mycontactbook.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ocean.mycontactbook.utility.Utility;

public class ContactDatabase {

    private final String DB_NAME = "my_contact_book";
    private final String TABLE_NAME = "personal_contact";
    private final int DB_VERSION = 1;

    // TABLE Cloumns name
    private final String ROW_ID = "rowId";
    private final String NAME = "name";
    private final String CONTACT_NUM = "contact_num";
    private final String EMAIL = "email";
    private final String IMAGE = "image";
    private final String ADDRESS = "address";

    // CREATE TABLE STUDENTS (rowId Integer AUTOINCREAMENT PRIMARY KEY, name text, email text, image text, address text, contactnum text);
    private String sqlQuery = "CREATE TABLE "+TABLE_NAME+" ("+ROW_ID+" "+"INTEGER PRIMARY KEY AUTOINCREMENT"+", "+ NAME +" text"+", "+ EMAIL +" text"+", "+IMAGE+" text"+"," + ADDRESS +" text " + "," + CONTACT_NUM +" text "+" )";
    MyDbHelper myDbHelper;
    SQLiteDatabase sqLiteDatabase;

    public ContactDatabase(Context context){
        myDbHelper =new MyDbHelper(context);
    }

    public ContactDatabase openDatabase(){
        sqLiteDatabase = myDbHelper.getWritableDatabase();
        return this;
    }

    public void insertData(Context context, String name, String email, String image, String address,String contact_num){
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, name);
        contentValues.put(EMAIL, email);
        contentValues.put(CONTACT_NUM,contact_num);
        contentValues.put(IMAGE, image);
        contentValues.put(ADDRESS, address);

        long insertedRow = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        if (insertedRow > 0){
            Utility.showLongToast(context, insertedRow+" data is successfully inserted");
        }else {
            Utility.showLongToast(context, "Something went wrong");
        }
    }

    public Cursor getAllData(){
        String[] colList = new String[]{ROW_ID, NAME, EMAIL, IMAGE, ADDRESS};
        return sqLiteDatabase.query(TABLE_NAME, colList, null, null, null, null, null);
    }

    public void deleteSingleRecord(Context context, String rowId){
        // DELETE * FROM STUDENT Where RowDI = 101;
        int deletedItems = sqLiteDatabase.delete(TABLE_NAME, ROW_ID+" = "+rowId, null);
        if (deletedItems > 0 ){
            Utility.showLongToast(context, deletedItems+" Record deleted");
        }else {
            Utility.showLongToast(context, "Something went wrong.");
        }
    }

    public void updateRecord(Context context, String name, String email, String image, String rowId, String address){
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, name);
        contentValues.put(EMAIL, email);
        contentValues.put(IMAGE, image);
        contentValues.put(ADDRESS, address);
        int updatedRow = sqLiteDatabase.update(TABLE_NAME, contentValues, ROW_ID+" = "+rowId, null);
        if (updatedRow > 0){
            Utility.showLongToast(context, updatedRow+" data is successfully updated");
        }else {
            Utility.showLongToast(context, "Something went wrong");
        }
    }

    public void deleteAllRecords(Context context){
        int deletedItems = sqLiteDatabase.delete(TABLE_NAME, null, null);
        if (deletedItems > 0 ){
            Utility.showLongToast(context, deletedItems+" Record deleted");
        }else {
            Utility.showLongToast(context, "Something went wrong.");
        }
    }


    class MyDbHelper extends SQLiteOpenHelper {

        public MyDbHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(sqlQuery);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}