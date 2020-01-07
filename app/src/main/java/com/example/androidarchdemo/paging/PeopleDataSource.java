package com.example.androidarchdemo.paging;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PageKeyedDataSource;

import com.example.androidarchdemo.entity.ResultPeople;
import com.example.androidarchdemo.entity.SwapiResponse;
import com.example.androidarchdemo.repository.SwCharactersRepository;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class PeopleDataSource extends PageKeyedDataSource<String, ResultPeople> {

    public static final String TAG = "PeopleDataSrcTAG";

    private MutableLiveData<NetworkStatus> status;
    private SwCharactersRepository swApiService;
    private int page;

    public PeopleDataSource(SwCharactersRepository swApiService){
        this.swApiService = swApiService;
        page = 1;
        status = new MutableLiveData<>();
    }

    /*@Override
    public void loadInitial(@NonNull LoadInitialParams params, @NonNull LoadInitialCallback<ResultPeople> callback) {
        status.postValue(NetworkStatus.LOADING);
        swApiService.getAllPeople(page=1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<SwapiResponse>() {
                    @Override
                    public void onNext(SwapiResponse swapiResponse) {
                        status.postValue(NetworkStatus.SUCCESS);
                        Log.i(TAG, "onNext in loadInitial " + page);
                        callback.onResult(swapiResponse.getResults(), 0);
                        page++;
                    }

                    @Override
                    public void onError(Throwable e) {
                        status.postValue(NetworkStatus.ERROR);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void loadRange(@NonNull LoadRangeParams params, @NonNull LoadRangeCallback<ResultPeople> callback) {
        status.postValue(NetworkStatus.LOADING);
        swApiService.getAllPeople(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<SwapiResponse>() {
                    @Override
                    public void onNext(SwapiResponse swapiResponse) {
                        status.postValue(NetworkStatus.SUCCESS);
                        Log.i(TAG, "onNext in loadRange " + page);
                        page++;
                        callback.onResult(swapiResponse.getResults());
                    }

                    @Override
                    public void onError(Throwable e) {
                        status.postValue(NetworkStatus.ERROR);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }*/

    public LiveData<NetworkStatus> getStatus() {
        return status;
    }


    public int getPage() {
        return page;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<String> params, @NonNull LoadInitialCallback<String, ResultPeople> callback) {
        status.postValue(NetworkStatus.LOADING);
        swApiService.getAllPeople(page=1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<SwapiResponse>() {
                    @Override
                    public void onNext(SwapiResponse swapiResponse) {
                        status.postValue(NetworkStatus.SUCCESS);
                        Log.i(TAG, "onNext in loadInitial " + page);
                        callback.onResult(swapiResponse.getResults(), null, swapiResponse.getNext());
                        page++;
                    }

                    @Override
                    public void onError(Throwable e) {
                        status.postValue(NetworkStatus.ERROR);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<String> params, @NonNull LoadCallback<String, ResultPeople> callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<String> params, @NonNull LoadCallback<String, ResultPeople> callback) {
        status.postValue(NetworkStatus.LOADING);
        swApiService.getAllPeople(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<SwapiResponse>() {
                    @Override
                    public void onNext(SwapiResponse swapiResponse) {
                        status.postValue(NetworkStatus.SUCCESS);
                        Log.i(TAG, "onNext in loadRange " + page);
                        page++;
                        callback.onResult(swapiResponse.getResults(), swapiResponse.getNext());
                    }

                    @Override
                    public void onError(Throwable e) {
                        status.postValue(NetworkStatus.ERROR);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
