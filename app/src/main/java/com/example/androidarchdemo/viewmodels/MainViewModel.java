package com.example.androidarchdemo.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.example.androidarchdemo.entity.ResultPeople;
import com.example.androidarchdemo.paging.MySourceFactory;
import com.example.androidarchdemo.paging.NetworkStatus;
import com.example.androidarchdemo.paging.PeopleDataSource;
import com.example.androidarchdemo.repository.SwCharactersRepository;

import java.util.concurrent.Executors;

public class MainViewModel extends ViewModel {

    private LiveData<PagedList<ResultPeople>> pagedListMutableLiveData;
    private SwCharactersRepository swApiService;
    private MySourceFactory mySourceFactory;

    public MainViewModel(){
        init();
    }

    private void init() {
        swApiService = new SwCharactersRepository();
        if(pagedListMutableLiveData == null) {
            createFactory();
            pagedListMutableLiveData = new LivePagedListBuilder<>(mySourceFactory, createConfig())
                    .setFetchExecutor(Executors.newSingleThreadExecutor())
                    .build();
        }
    }


    private PagedList.Config createConfig(){
        return new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(10)
                .build();
    }

    private void createFactory(){
        mySourceFactory = new MySourceFactory(swApiService);

    }

    public LiveData<PagedList<ResultPeople>> getPagedListMutableLiveData(){
        return pagedListMutableLiveData;
    }

    public LiveData<NetworkStatus> isRefreshing(){
        return Transformations.switchMap(mySourceFactory.getPeopleDataSourceLiveData(), PeopleDataSource::getStatus);
    }



}
