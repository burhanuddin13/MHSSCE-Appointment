package com.mhssce.mhssceappointment;

public class AppointmentData {

    private String Name, Date, Time;
    private int id;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AppointmentData(String name, String date, String time, int id) {
        Name = name;
        Date = date;
        Time = time;
        this.id = id;
    }
}
