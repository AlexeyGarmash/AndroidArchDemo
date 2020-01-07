package com.example.androidarchdemo.di.components;

import com.example.androidarchdemo.di.modules.RetrofitModule;
import com.example.androidarchdemo.repository.SwCharactersRepository;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {RetrofitModule.class})
@Singleton
public interface AppComponent {


    void injectCharactersRepository(SwCharactersRepository swCharactersRepository);

}
