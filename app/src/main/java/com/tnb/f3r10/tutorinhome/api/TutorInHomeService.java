package com.tnb.f3r10.tutorinhome.api;

import com.tnb.f3r10.tutorinhome.entities.Booking;
import com.tnb.f3r10.tutorinhome.entities.Tutor;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by f3r10 on 12/7/16.
 */
public interface TutorInHomeService {

    @GET("tutors")
    Call<List<Tutor>> getTutors();

    @POST("reservations/android")
    Call<Booking> bookingTutor(@Body Booking booking);
}
