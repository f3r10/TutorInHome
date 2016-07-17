package com.tnb.f3r10.tutorinhome.entities;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

/**
 * Created by f3r10 on 12/7/16.
 */

@Parcel(Parcel.Serialization.BEAN)
public class Ratings {

    public float number;
    @SerializedName("comments_number")
    public int commentsNumber;

    public float getNumber() {
        return number;
    }

    public void setNumber(float number) {
        this.number = number;
    }

    public int getCommentsNumber() {
        return commentsNumber;
    }

    public void setCommentsNumber(int commentsNumber) {
        this.commentsNumber = commentsNumber;
    }
}
