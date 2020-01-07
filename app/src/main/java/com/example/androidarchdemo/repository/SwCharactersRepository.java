package com.example.androidarchdemo.repository;

import com.example.androidarchdemo.base.App;
import com.example.androidarchdemo.entity.ResultPeople;
import com.example.androidarchdemo.entity.SwapiResponse;
import com.example.androidarchdemo.retrofit.SwApi;

import javax.inject.Inject;

import io.reactivex.Observable;


public class SwCharactersRepository {

    public static final String BASE_URL = "https://swapi.co/api/";

    @Inject
    SwApi swApi;

    public SwCharactersRepository(){
        App.getAppComponent().injectCharactersRepository(this);
    }

    public Observable<SwapiResponse> getAllPeople(int page){
        return swApi.getAllPeople(String.valueOf(page));
    }

    public Observable<ResultPeople> getConcretePeople(String pathId){
        return swApi.getConcretePeople(pathId);
    }
}
