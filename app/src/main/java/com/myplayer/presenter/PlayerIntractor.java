package com.myplayer.presenter;

import android.support.annotation.NonNull;
import android.util.Log;

import com.myplayer.model.MyPlayerList;
import com.myplayer.utils.RetrofitInstance;
import com.myplayer.webservice.APIRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlayerIntractor {

    ViewUpdate viewUpdate;
    String TAG = "PlayerIntractor_D";

    public PlayerIntractor(ViewUpdate viewUpdate) {
        this.viewUpdate = viewUpdate;
    }


    public void getSongList() {
        APIRequest apiRequest = RetrofitInstance.getRetrofitInstance().create(APIRequest.class);
        apiRequest.getPlayerList().enqueue(new Callback<MyPlayerList>() {
            @Override
            public void onResponse(@NonNull Call<MyPlayerList> call, @NonNull Response<MyPlayerList> response) {

                Log.d(TAG, "onResponse: " + response.body());
                if (response != null && response.code() == 200)
                    viewUpdate.songListUpdate(response.body().getSongs());
            }

            @Override
            public void onFailure(@NonNull Call<MyPlayerList> call, @NonNull Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });

    }
}
