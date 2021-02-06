package com.example.ezycommerce;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static Retrofit retrofit;
    private static String BASE_URL = "https://u73olh7vwg.execute-api.ap-northeast-2.amazonaws.com/staging/";

    public static Retrofit getRetrofit() {

        Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();



        if(retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }
}
