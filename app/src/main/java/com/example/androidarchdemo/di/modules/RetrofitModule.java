package com.example.androidarchdemo.di.modules;

import com.example.androidarchdemo.retrofit.SwApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.androidarchdemo.repository.SwCharactersRepository.BASE_URL;

@Module
public class RetrofitModule {

    @Provides
    @Singleton
    SwApi provideSwApi(Retrofit retrofit){
        return retrofit.create(SwApi.class);
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(String baseUrl, RxJava2CallAdapterFactory callAdapterFactory, GsonConverterFactory converterFactory){
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(callAdapterFactory)
                .addConverterFactory(converterFactory)
                .build();
    }

    @Provides
    @Singleton
    RxJava2CallAdapterFactory provideCallFactory(){
        return RxJava2CallAdapterFactory.create();
    }

    @Provides
    @Singleton
    GsonConverterFactory provideConverterFactory(){
        return GsonConverterFactory.create();
    }

    @Provides
    @Singleton
    String provideBaseUrl(){
        return BASE_URL;
    }
}
