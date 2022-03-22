package com.mhssce.mhssceappointment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

//Program-specific Class to help with SQLite Database
public class AppointmentHelper extends SQLiteOpenHelper {

    private static final String NAME = "Appointment"; //Database Name
    private static final String TABLE = "MHSSCE"; //Table Name
    private static final int VERSION = 1; //Database Version

    //Constructor to initialise Database
    public AppointmentHelper(@Nullable Context context) { super(context, NAME, null, VERSION); }

    public void insertIntoTable(String Name, String Date, String Time){
        SQLiteDatabase db = this.getWritableDatabase(); //SQLite Database in Write Mode
        ContentValues entry = new ContentValues(); //Storing Entry as HashMap (Key-Value pair)
        entry.put("Name", Name); //Storing Name as Value "Name" Key
        entry.put("Date", Date); //Storing Date as Value "Date" Key
        entry.put("Time", Time); //Storing Time as Value "Time" Key
        db.insert(TABLE, null, entry); //Inserting Values in the Table of Database
        db.close();
    }

    //Function to Create Table in Database
    @Override
    public void onCreate(SQLiteDatabase db) {
        //Query to Create Table
        String CREATE = "CREATE TABLE "+ TABLE + "("+
                "SrNo INTEGER PRIMARY KEY AUTOINCREMENT, "+
                "Name TEXT,"+
                "Date TEXT,"+
                "Time TEXT)";
        db.execSQL(CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS MHSSCE");
        onCreate(db);
    }

    //Function to Read Values from Table
    public ArrayList<AppointmentData> readAppointments() {
        SQLiteDatabase db = this.getReadableDatabase(); //SQLite Database in Read Mode
        String[] column = {"Name", "Date", "Time"}; //Columns to be fetched
        //Fetching Data from Table and saving as Cursor
        Cursor appCursor = db.query(TABLE, column, null, null,
                null, null, "Date ASC, Time ASC");
        //Data will be sorted in Ascending Order of Date & Time
        ArrayList<AppointmentData> appointmentData = new ArrayList<>();
        if (appCursor.moveToFirst()) {
            do {
                appointmentData.add(new AppointmentData(appCursor.getString(0),
                        appCursor.getString(1), appCursor.getString(2), 0));
            } while (appCursor.moveToNext());
        }
        appCursor.close();
        return appointmentData;
    }
}
