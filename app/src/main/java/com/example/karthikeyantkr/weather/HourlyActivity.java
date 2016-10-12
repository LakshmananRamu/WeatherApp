package com.example.karthikeyantkr.weather;

/*
Weather
HomeWork 5

Karthikeyan TKR
Lakshmanan RM

*/

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class HourlyActivity extends AppCompatActivity implements GetWeatherData.DataRetreiver {
    ArrayList<Weather> hourly_data_list;
    ListView hours;
    TextView citystate;
    String city, state;
    ProgressDialog progress;
    int min = 0, max = 0;

    HashMap<String, ArrayList<String>> favList = new HashMap<String, ArrayList<String>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hourly);

        String url = getIntent().getExtras().getString("url");
        city = getIntent().getExtras().getString("city");
        state = getIntent().getExtras().getString("state");

        favList = (HashMap<String, ArrayList<String>>) getIntent().getSerializableExtra("map");
        progress = new ProgressDialog(this);
        progress.setCancelable(false);
        progress.setTitle("Loading Hourly Data");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.show();

        new GetWeatherData(HourlyActivity.this).execute(url);

    }

    @Override
    public void setData(ArrayList<Weather> newsList) {
        progress.dismiss();
        hourly_data_list = newsList;
        hours = (ListView) findViewById(R.id.listView);
        citystate = (TextView) findViewById(R.id.textView_city_state);
        citystate.setText(city + ", " + state);


        if (hourly_data_list.size() == 0) {
            Intent intent = new Intent(HourlyActivity.this, MainActivity.class);
            startActivity(intent);
            Toast.makeText(getApplicationContext(), "No such City/State Exists", Toast.LENGTH_SHORT).show();
        } else {
            max = 0;
            min = Integer.parseInt(hourly_data_list.get(0).getTemperature());


            for (Weather weather : hourly_data_list) {

                if (max < Integer.parseInt(weather.getTemperature())) {
                    max = Integer.parseInt(weather.getTemperature());
                }

                if (min > Integer.parseInt(weather.getTemperature())) {
                    min = Integer.parseInt(weather.getTemperature());
                }
            }

        }

        if (favList.containsKey(city + "," + state)) {
            Log.d("demo", "demo");
            favList.remove(city + "," + state);
            String addKey = city + "," + state;
            ArrayList<String> addValue = new ArrayList<>();

            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy");
            String currentDateandTime = sdf.format(new Date());

            addValue.add(hourly_data_list.get(0).getTemperature());
            addValue.add(currentDateandTime);

            favList.put(addKey, addValue);

            SharedPreferences.Editor editor = getSharedPreferences("fav_list", MODE_PRIVATE).edit();
            editor.putString("my_list", new Gson().toJson(favList));
            editor.commit();
        }
        WeatherAdapter adapter = new WeatherAdapter(this, R.layout.list_view, hourly_data_list, city, state, max, min);
        hours.setAdapter(adapter);
        adapter.setNotifyOnChange(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        String addKey = city + "," + state;
        ArrayList<String> addValue = new ArrayList<>();

        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy");
        String currentDateandTime = sdf.format(new Date());

        addValue.add(hourly_data_list.get(0).getTemperature());
        addValue.add(currentDateandTime);


        // Toast.makeText(getApplicationContext(), "Cannot Add a City here", Toast.LENGTH_SHORT).show();
        if (favList.containsKey(city + "," + state)) {
            Log.d("demo", "demo");
            favList.remove(city + "," + state);
            String addKey1 = city + "," + state;
            ArrayList<String> addValue1 = new ArrayList<>();

            SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd/yy");
            String currentDateandTime1 = sdf1.format(new Date());

            addValue1.add(hourly_data_list.get(0).getTemperature());
            addValue1.add(currentDateandTime1);

            favList.put(addKey1, addValue1);

            SharedPreferences.Editor editor = getSharedPreferences("fav_list", MODE_PRIVATE).edit();
            editor.putString("my_list", new Gson().toJson(favList));
            editor.commit();
            Toast.makeText(getApplicationContext(), "Updated Favourites", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Added city to Favourites", Toast.LENGTH_SHORT).show();
        }
        favList.put(addKey, addValue);

        SharedPreferences.Editor editor = getSharedPreferences("fav_list", MODE_PRIVATE).edit();
        editor.putString("my_list", new Gson().toJson(favList));
        editor.commit();
        Intent intent = new Intent(HourlyActivity.this, MainActivity.class);
        startActivity(intent);

        return super.onOptionsItemSelected(item);
    }


}
