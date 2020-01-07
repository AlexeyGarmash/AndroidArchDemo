package com.example.androidarchdemo.retrofit;

import com.example.androidarchdemo.entity.ResultPeople;
import com.example.androidarchdemo.entity.SwapiResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface SwApi {

    @GET("people?")
    Observable<SwapiResponse> getAllPeople(@Query("page") String page);

    @GET("{id}")
    Observable<ResultPeople> getConcretePeople(@Path("id") String path);
}
