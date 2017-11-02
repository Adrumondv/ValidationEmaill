package com.example.alexanderdrumond.validationemail.network;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EmailsInterceptor {
    public static final String BASE_URL = "https://pozzad-email-validator.p.mashape.com/emailvalidator/";
    public Emails get() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS);
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();
                Request request = originalRequest.newBuilder()
                        .header("X-Mashape-Key", "ZRJEDC0SYFmshYNenkBV0Gd1XLO3p1nMFSSjsn2L3jz587RzjH")
                        .header("Accept", "application/json")
                        .build();
                Response response = chain.proceed(request);
                int retryCount = 0;
                while (!response.isSuccessful() && retryCount < 3) {
                    retryCount++;
                    response = chain.proceed(request);
                }
                return response;
            }
        });
        OkHttpClient client = httpClient.build();
        Retrofit interceptor = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        Emails request = interceptor.create(Emails.class);
        return request;
    }
}
