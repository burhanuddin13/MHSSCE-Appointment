package com.mhssce.mhssceappointment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class ViewAppointment extends AppCompatActivity {

    ArrayList<AppointmentData> appointmentData;
    AppointmentAdapter appointmentAdapter;
    AppointmentHelper dbHandler;
    RecyclerView relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_appointment);

        appointmentData = new ArrayList<>();
        dbHandler = new AppointmentHelper(ViewAppointment.this);

        appointmentData = dbHandler.readAppointments();
        appointmentAdapter = new AppointmentAdapter(appointmentData, ViewAppointment.this);
        relativeLayout =(RecyclerView) findViewById(R.id.relativeLayout);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ViewAppointment.this, RecyclerView.VERTICAL, false);
        relativeLayout.setLayoutManager(linearLayoutManager);
        relativeLayout.setAdapter(appointmentAdapter);
    }
}