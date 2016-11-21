package com.imammuhtadi.weatheraround;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.imammuhtadi.weatheraround.api.RestAPI;
import com.imammuhtadi.weatheraround.api.RetrofitService;
import com.imammuhtadi.weatheraround.model.Clouds;
import com.imammuhtadi.weatheraround.model.DataResponse;
import com.imammuhtadi.weatheraround.model.Main;
import com.imammuhtadi.weatheraround.model.Wind;
import com.imammuhtadi.weatheraround.screen.DashboardActivity;

import java.util.HashMap;
import java.util.Map;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private Subscription subscription;
    Map<String, String> options = new HashMap<>();

    Button btRetrofit, btRx;
    TextView tv_speed, tv_deg, tv_hujan, tv_awan, tv_temp, tv_pressure, tv_humidity, tv_sea_level, tv_grnd_level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btRetrofit = (Button)findViewById(R.id.btRetrofit);
        btRx = (Button)findViewById(R.id.btRx);
        tv_speed = (TextView)findViewById(R.id.tv_speed);
        tv_deg = (TextView)findViewById(R.id.tv_deg);
        tv_hujan = (TextView)findViewById(R.id.tv_hujan);
        tv_awan = (TextView)findViewById(R.id.tv_awan);
        tv_temp = (TextView)findViewById(R.id.tv_temp);
        tv_pressure = (TextView)findViewById(R.id.tv_pressure);
        tv_humidity = (TextView)findViewById(R.id.tv_humidity);
        tv_sea_level = (TextView)findViewById(R.id.tv_sea_level);
        tv_grnd_level = (TextView)findViewById(R.id.tv_grnd_level);

        options.put("lat","10.67");
        options.put("lon", "107.78");
        options.put("appid", "8c19c1a85ca5ab8cb1da9c2afef49078");

        btRetrofit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                final RestAPI serviceRet = RetrofitService.createRetrofitClient();
                Call<DataResponse> getData = serviceRet.loadRetrofitWeather(options);
                getData.enqueue(new Callback<DataResponse>() {
                    @Override
                    public void onResponse(Call<DataResponse> call, Response<DataResponse> response) {
                        DataResponse resData = response.body();
                        Wind wind = resData.getWind();
                        Clouds clouds = resData.getClouds();
                        Main main = resData.getMain();
                        tv_speed.setText("Kecepatan Angin: "+String.valueOf(wind.getSpeed())+"");
                        tv_deg.setText("Arah Angin: derajat "+String.valueOf(wind.getDeg())+"");
                        tv_awan.setText("Awan: "+String.valueOf(clouds.getAll())+" %");
                        tv_temp.setText("Temperatur: "+String.valueOf(main.getTemp())+" F");
                        tv_pressure.setText("Tekanan: "+String.valueOf(main.getPressure())+"");
                        tv_humidity.setText("Humidity: "+String.valueOf(main.getHumidity())+"");
                        tv_sea_level.setText("Sea Level "+String.valueOf(main.getSeaLevel())+"");
                        tv_grnd_level.setText("Ground Level "+String.valueOf(main.getGrndLevel())+"");
                    }

                    @Override
                    public void onFailure(Call<DataResponse> call, Throwable t) {
                        Log.d("onFailure", t.toString());
                    }
                });
                 */
                Intent intent = new Intent(MainActivity.this, DashboardActivity.class);
                startActivity(intent);
            }
        });

        btRx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                                Wind wind = dataResponse.getWind();
                                Clouds clouds = dataResponse.getClouds();
                                Main main = dataResponse.getMain();
                                tv_speed.setText("Kecepatan Angin: "+String.valueOf(wind.getSpeed())+"");
                                tv_deg.setText("Arah Angin: derajat "+String.valueOf(wind.getDeg())+"");
                                tv_awan.setText("Awan: "+String.valueOf(clouds.getAll())+" %");
                                tv_temp.setText("Temperatur: "+String.valueOf(main.getTemp())+" F");
                                tv_pressure.setText("Tekanan: "+String.valueOf(main.getPressure())+"");
                                tv_humidity.setText("Humidity: "+String.valueOf(main.getHumidity())+"");
                                tv_sea_level.setText("Sea Level "+String.valueOf(main.getSeaLevel())+"");
                                tv_grnd_level.setText("Ground Level "+String.valueOf(main.getGrndLevel())+"");
                            }
                        });
            }
        });

    }
}
