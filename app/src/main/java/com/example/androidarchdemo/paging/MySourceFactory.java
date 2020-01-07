package com.example.androidarchdemo.paging;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.example.androidarchdemo.entity.ResultPeople;
import com.example.androidarchdemo.repository.SwCharactersRepository;

public class MySourceFactory extends DataSource.Factory<String, ResultPeople> {

    private MutableLiveData<PeopleDataSource> peopleDataSourceMutableLiveData;
    private SwCharactersRepository swApiService;

    private PeopleDataSource peopleDataSource;


    public  MySourceFactory(SwCharactersRepository swApiService){
        this.swApiService = swApiService;
        peopleDataSourceMutableLiveData = new MutableLiveData<>();
    }

    @Override
    public DataSource<String, ResultPeople> create() {
        peopleDataSource = new PeopleDataSource(swApiService);
        peopleDataSourceMutableLiveData.postValue(peopleDataSource);
        return peopleDataSource;
    }

    public MutableLiveData<PeopleDataSource> getPeopleDataSourceLiveData(){
        return  peopleDataSourceMutableLiveData;
    }

}
