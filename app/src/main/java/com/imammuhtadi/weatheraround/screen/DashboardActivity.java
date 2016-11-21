package com.imammuhtadi.weatheraround.screen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.imammuhtadi.weatheraround.R;
import com.imammuhtadi.weatheraround.adapter.WeatherAdapter;
import com.imammuhtadi.weatheraround.model.DataResponse;

public class DashboardActivity extends AppCompatActivity implements DashboardView{

    private RecyclerView list;
    RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        init();

        DashboardPresenterImpl presenter = new DashboardPresenterImpl(this);
        presenter.loadWeather();
    }

    @Override
    public void loadWeatherSuccess(DataResponse dataResponse){
        WeatherAdapter weatherAdapter = new WeatherAdapter(dataResponse);
        list.setAdapter(weatherAdapter);
    }

    public void init(){
        list = (RecyclerView) findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(DashboardActivity.this);
        list.setLayoutManager(mLayoutManager);
    }
}
