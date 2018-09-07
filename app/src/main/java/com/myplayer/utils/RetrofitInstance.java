package com.myplayer.utils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {


    private static Retrofit retrofit;

    private RetrofitInstance(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    public static Retrofit getRetrofitInstance()
    {
        if (retrofit!=null) return  retrofit;
        retrofit = new Retrofit.Builder()
            .baseUrl("https://raw.githubusercontent.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
        return retrofit;
    }
}
