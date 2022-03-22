package com.mhssce.mhssceappointment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity
        implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    ImageButton btnDate, btnTime;
    TextView txtDate, txtTime;
    Button btnBook, btnView;
    EditText edtName;
    ImageView AII;

    private String Date, Time, Name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE); //Saves Users Preferences wrt device
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        final boolean isDarkModeOn = sharedPreferences.getBoolean("isDarkModeOn", false); //Boolean to store Dark Mode state

        txtDate=(TextView) findViewById(R.id.txtDate);
        btnDate=(ImageButton) findViewById(R.id.btnDate);
        btnDate.setOnClickListener(v -> showDatePickerDialog());

        txtTime=(TextView) findViewById(R.id.txtTime);
        btnTime=(ImageButton) findViewById(R.id.btnTime);
        btnTime.setOnClickListener(v -> showTimePickerDialog());

        edtName=(EditText) findViewById(R.id.edtName);
        btnBook=(Button) findViewById(R.id.btnBook);
        btnBook.setOnClickListener(v -> insertIntoTable());

        btnView=(Button) findViewById(R.id.btnView);
        btnView.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, ViewAppointment.class)));

        AII = (ImageView) findViewById(R.id.AII); //References AII Logo
        AII.setOnClickListener(v -> { //When "AII" is clicked
            if(isDarkModeOn){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                editor.putBoolean("isDarkModeOn", false);
                editor.apply();
            }
            else{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                editor.putBoolean("isDarkModeOn", true);
                editor.apply();
            }
        });

        if (isDarkModeOn)
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        else
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
    }

    private void insertIntoTable() {
        Name=edtName.getText().toString().trim();
        if(Name.isEmpty()){
            edtName.setError("Please Enter Faculty Name");
            edtName.requestFocus();
        }
        else if(Date.isEmpty()){
            txtDate.setError("Please Select Date");
            txtDate.requestFocus();
        }
        else if(Time.isEmpty()){
            txtTime.setError("Please Select Time");
            txtTime.requestFocus();
        }
        else {
            AppointmentHelper app = new AppointmentHelper(this);
            app.insertIntoTable(Name, Date, Time);
            Toast.makeText(MainActivity.this, R.string.success, Toast.LENGTH_SHORT).show();
            txtDate.setText("");
            txtTime.setText("");
            edtName.setText("");
        }
    }

    private void showDatePickerDialog(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    private void showTimePickerDialog() {
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, this,
                Calendar.getInstance().get(Calendar.HOUR_OF_DAY),
                Calendar.getInstance().get(Calendar.MINUTE), true);
        timePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String Day, Month;
        if (dayOfMonth>9) Day=""+dayOfMonth;
        else Day="0"+dayOfMonth;
        if (month>9) Month=""+month;
        else Month="0"+month;
        Date=year+Month+Day;
        String display="Date: "+dayOfMonth+"-"+(month+1)+"-"+year;
        txtDate.setText(display);
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        String Hour, Minute;
        if (hourOfDay>9) Hour=""+hourOfDay;
        else Hour="0"+hourOfDay;
        if (minute>9) Minute=""+minute;
        else Minute="0"+minute;
        Time=Hour+Minute;
        String display="Time:    "+Hour+":"+Minute;
        txtTime.setText(display);
    }
}