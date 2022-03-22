package com.mhssce.mhssceappointment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AppointmentAdapter extends RecyclerView.Adapter<AppointmentAdapter.ViewHolder> {

    private ArrayList<AppointmentData> appointmentData;
    private Context context;

    public AppointmentAdapter(ArrayList<AppointmentData> appointmentData, Context context) {
        this.appointmentData = appointmentData;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.appointment, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AppointmentData appData = appointmentData.get(position);
        holder.viewName.setText(appData.getName());
        holder.viewDate.setText(formatDate(appData.getDate()));
        holder.viewTime.setText(formatTime(appData.getTime()));
    }

    private String formatTime(String time) {
        String hour=time.substring(0,2), minute=time.substring(2);
        return hour+":"+minute;
    }

    private String formatDate(String date) {
        int year=Integer.parseInt(date.substring(0,4)),
                month=Integer.parseInt(date.substring(4,6)),
                day=Integer.parseInt(date.substring(6));
        return day+"-"+(month+1)+"-"+year;
    }

    @Override
    public int getItemCount() {
        return appointmentData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView viewName, viewDate, viewTime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            viewName = itemView.findViewById(R.id.viewName);
            viewDate = itemView.findViewById(R.id.viewDate);
            viewTime = itemView.findViewById(R.id.viewTime);
        }
    }
}
