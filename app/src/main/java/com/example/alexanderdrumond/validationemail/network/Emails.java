package com.example.alexanderdrumond.validationemail.network;

import com.example.alexanderdrumond.validationemail.models.Email;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface Emails {

    @GET("validateemail/{email}")
    Call<Email> getEmails(@Path("email") String email);


}
