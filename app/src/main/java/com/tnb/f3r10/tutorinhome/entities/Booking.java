package com.tnb.f3r10.tutorinhome.entities;

/**
 * Created by f3r10 on 15/7/16.
 */
public class Booking {

    private String idTutor;
    private String studentEmail;
    private String day;
    private String startTime;
    private String numberHours;

    public String getIdTutor() {
        return idTutor;
    }

    public void setIdTutor(String idTutor) {
        this.idTutor = idTutor;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getNumberHours() {
        return numberHours;
    }

    public void setNumberHours(String numberHours) {
        this.numberHours = numberHours;
    }
}
