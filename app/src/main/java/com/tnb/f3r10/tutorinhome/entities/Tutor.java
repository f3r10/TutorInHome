package com.tnb.f3r10.tutorinhome.entities;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by f3r10 on 12/7/16.
 */

@Parcel(Parcel.Serialization.BEAN)
public class Tutor {

    @SerializedName("_id")
    public String id;
    public String name;
    @SerializedName("nick_name")
    public String nickName;
    public String ocupation;
    public String education;
    public String major;
    public int price;
    public String overview;
    @SerializedName("hours_worked")
    public int hoursWorked;
    public String phone;
    public String justification;
    public Ratings ratings;
    public List<String> interests = new ArrayList<String>();
    public String picture_uri;
    @SerializedName("professional_subjects")
    public List<String> professionalSubjects = new ArrayList<String>();
    @SerializedName("basic_subjects")
    public List<String> basicSubjects = new ArrayList<String>();
    public boolean isEnabled;
    @SerializedName("education_tutor")
    public List<EducationTutor> educationTutor = new ArrayList<EducationTutor>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getOcupation() {
        return ocupation;
    }

    public void setOcupation(String ocupation) {
        this.ocupation = ocupation;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public int getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(int hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getJustification() {
        return justification;
    }

    public void setJustification(String justification) {
        this.justification = justification;
    }

    public Ratings getRatings() {
        return ratings;
    }

    public void setRatings(Ratings ratings) {
        this.ratings = ratings;
    }

    public List<String> getInterests() {
        return interests;
    }

    public void setInterests(List<String> interests) {
        this.interests = interests;
    }

    public String getPicture_uri() {
        return picture_uri;
    }

    public void setPicture_uri(String picture_uri) {
        this.picture_uri = picture_uri;
    }

    public List<String> getProfessionalSubjects() {
        return professionalSubjects;
    }

    public void setProfessionalSubjects(List<String> professionalSubjects) {
        this.professionalSubjects = professionalSubjects;
    }

    public List<String> getBasicSubjects() {
        return basicSubjects;
    }

    public void setBasicSubjects(List<String> basicSubjects) {
        this.basicSubjects = basicSubjects;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public List<EducationTutor> getEducationTutor() {
        return educationTutor;
    }

    public void setEducationTutor(List<EducationTutor> educationTutor) {
        this.educationTutor = educationTutor;
    }
}
