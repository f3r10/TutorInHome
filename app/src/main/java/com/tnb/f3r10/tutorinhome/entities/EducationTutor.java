package com.tnb.f3r10.tutorinhome.entities;

import org.parceler.Parcel;

/**
 * Created by f3r10 on 12/7/16.
 */

@Parcel(Parcel.Serialization.BEAN)
public class EducationTutor {
    public String institution;
    public String major;

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }
}
