package com.example.karthikeyantkr.weather;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by KARTHIKEYAN T K R on 10/10/2016.
 */
public class WeatherAdapter extends ArrayAdapter<Weather> {
    List<Weather> mWeather;
    Context mContext;
    int mResource;
    int i = 0,max,min;
    String city, state;

    public WeatherAdapter(Context context, int resource, List<Weather> objects, String city, String state,int max,int min) {
        super(context, resource, objects);

        this.mContext = context;
        this.mWeather = objects;
        this.mResource = resource;
        this.city = city;
        this.state = state;
        this.max=max;
        this.min=min;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(mResource, parent, false);
        }
        final Weather weather = mWeather.get(position);
        TextView time = (TextView) convertView.findViewById(R.id.textviewTime);
        time.setText(weather.getTime());

        TextView cloud = (TextView) convertView.findViewById(R.id.textviewCloud);
        cloud.setText(weather.getClouds());

        TextView degree = (TextView) convertView.findViewById(R.id.textviewDegree);
        degree.setText(weather.getTemperature() + "°" + "F");

        ImageView thumb_url = (ImageView) convertView.findViewById(R.id.imageView);
        Picasso.with(mContext).load(weather.iconUrl).into(thumb_url);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DetailsActivity.class);
                intent.putExtra("com.example.karthikeyantkr.weather.Weather", weather);
                intent.putExtra("city", city);
                intent.putExtra("state", state);
                intent.putExtra("max", max);
                intent.putExtra("min", min);

                mContext.startActivity(intent);
            }
        });

        return convertView;
    }
}
