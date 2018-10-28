package com.example.user_pc.capstonestage2.Retrofit;

import com.example.user_pc.capstonestage2.Models.SchedulesResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("schedule")
    Call<SchedulesResponse> getSchedule();

}
