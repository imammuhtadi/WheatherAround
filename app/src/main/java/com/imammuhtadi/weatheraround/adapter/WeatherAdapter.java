package com.imammuhtadi.weatheraround.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.imammuhtadi.weatheraround.R;
import com.imammuhtadi.weatheraround.model.DataResponse;
import com.imammuhtadi.weatheraround.model.Main;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder> {
    private static final String TAG = "CustomAdapter";

    private DataResponse dataResponse;

    public WeatherAdapter(DataResponse dataResponse) {
        this.dataResponse = dataResponse;
    }

    @Override
    public WeatherAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.weather, viewGroup, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(WeatherAdapter.ViewHolder viewHolder, final int position) {
        Main main = dataResponse.getMain();
        viewHolder.temp.setText(""+String.valueOf((int) main.getTemp())+" F");
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView temp;
        public ViewHolder(View view) {
            super(view);
            temp = (TextView)view.findViewById(R.id.temp);
        }
    }
}