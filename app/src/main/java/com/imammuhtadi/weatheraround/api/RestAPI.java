package com.imammuhtadi.weatheraround.api;

/**
 * Created by ADI on 28/09/2016.
 */


import com.imammuhtadi.weatheraround.model.DataResponse;

import java.util.HashMap;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

public interface RestAPI{

    @GET("weather")
    Call<DataResponse> loadRetrofitWeather(@QueryMap Map<String, String> options);

    @GET("weather")
    Observable<DataResponse> loadRxWeather(@QueryMap Map<String, String> options);
}