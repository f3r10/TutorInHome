package com.tnb.f3r10.tutorinhome.domain;

/**
 * Created by f3r10 on 12/7/16.
 */
public class Util {

    private final static String TUTORINHOME_ASSETS_TUTOR_URL = "http://tutorinhome.com/assets/images/tutors/";
    private final static String TUTORINHOME_DEFAULT_IMAGE_URL = "http://www.tutorinhome.com/assets/images/default_user.png";

    public String getAvatarUrl(String id){
        if(id.isEmpty()){
            return TUTORINHOME_DEFAULT_IMAGE_URL;
        }else{
            return TUTORINHOME_ASSETS_TUTOR_URL + id;
        }

    }
}
