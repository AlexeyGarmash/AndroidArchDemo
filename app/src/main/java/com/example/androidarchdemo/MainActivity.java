package com.example.androidarchdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidarchdemo.adapters.PeopleAdapter;
import com.example.androidarchdemo.entity.ResultPeople;
import com.example.androidarchdemo.paging.NetworkStatus;
import com.example.androidarchdemo.viewmodels.MainViewModel;
import com.example.androidarchdemo.workers.MyWorker;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActitvity";
    MainViewModel mainViewModel;

    @BindView(R.id.rvPeople)
    RecyclerView recyclerView;

    @BindView(R.id.progress)
    ProgressBar progressBar;

    private PeopleAdapter peopleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        peopleAdapter = new PeopleAdapter(ResultPeople.DIFF_CALLBACK);
        mainViewModel.getPagedListMutableLiveData().observe(this, resultPeople -> {
            Log.i(TAG, "onChangedLiveData in loadInitial");
            peopleAdapter.submitList(resultPeople);
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(peopleAdapter);
        mainViewModel.isRefreshing().observe(this, this::resolveRefresh);


        createWorkandRun();
    }

    private void createWorkandRun(){
        OneTimeWorkRequest oneTimeWorkRequest = new OneTimeWorkRequest.Builder(MyWorker.class).build();
        WorkManager.getInstance().enqueue(oneTimeWorkRequest);
    }

    private void resolveRefresh(NetworkStatus status) {
        switch (status){
            case LOADING: dataLoading(true); break;
            case ERROR: dataError(); break;
            case SUCCESS: dataLoading(false); break;
        }
    }

    private void dataError() {
        dataLoading(false);
        Toast.makeText(this, "Error data", Toast.LENGTH_SHORT).show();
    }

    private void dataLoading(boolean refresh) {
        progressBar.setVisibility(refresh?View.VISIBLE:View.GONE);
    }

}
