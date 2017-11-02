package com.example.alexanderdrumond.validationemail.network;

import android.os.AsyncTask;
import android.util.Log;

import com.example.alexanderdrumond.validationemail.models.Email;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class GetEmails extends AsyncTask<String, Void, Email>  {

    @Override
    protected Email doInBackground(String... params) {

        String email = params[0];

        Emails request = new EmailsInterceptor().get();

        Call<Email> call = request.getEmails(email);

        try {
            Response<Email> response = call.execute();
            if (200 == response.code() && response.isSuccessful()){
                Log.d("IS_WORKING", response.body().getIsValid());
                return response.body();

            }else{
                return null;
            }
        } catch (IOException e) {
            return null;
        }

    }
}
