package com.imammuhtadi.weatheraround.screen;

import com.imammuhtadi.weatheraround.api.RestAPI;
import com.imammuhtadi.weatheraround.api.RetrofitService;
import com.imammuhtadi.weatheraround.model.DataResponse;

import java.util.HashMap;
import java.util.Map;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ADI on 21/11/2016.
 */

public class DashboardPresenterImpl implements DashboardPresenter{

    private DashboardView view;
    private Subscription subscription;
    private Map<String, String> options = new HashMap<>();

    public DashboardPresenterImpl(DashboardView view){
        this.view = view;
    }

    @Override
    public void loadWeather(){
        options.put("lat","10.67");
        options.put("lon", "107.78");
        options.put("appid", "8c19c1a85ca5ab8cb1da9c2afef49078");

        final RestAPI serviceRx = RetrofitService.createRetrofitClient();
        subscription = serviceRx.loadRxWeather(options)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DataResponse>() {
                    @Override
                    public void onCompleted() {
                        subscription.unsubscribe();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(DataResponse dataResponse) {
                        view.loadWeatherSuccess(dataResponse);
                    }
                });
    }

}
