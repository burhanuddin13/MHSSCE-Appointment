package com.mhssce.mhssceappointment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class AppointmentHelper extends SQLiteOpenHelper {

    private static final String NAME = "Appointment";
    private static final String TABLE = "MHSSCE";
    private static final int VERSION = 1;

    public AppointmentHelper(@Nullable Context context) {
        super(context, NAME, null, VERSION);
    }

    public void insertIntoTable(String Name, String Date, String Time){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues entry = new ContentValues();
        entry.put("Name", Name);
        entry.put("Date", Date);
        entry.put("Time", Time);
        db.insert(TABLE, null, entry);
        db.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
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

    public ArrayList<AppointmentData> readAppointments() {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] column = {"Name", "Date", "Time"};
        Cursor appCursor = db.query(TABLE, column, null, null,
                null, null, "Date ASC, Time ASC");
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
