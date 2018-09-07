package com.myplayer.webservice;

import com.myplayer.model.MyPlayerList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIRequest {
    @GET("/bhagyaAndroid/sampleJson/master/song.json")
    Call<MyPlayerList> getPlayerList();

}
