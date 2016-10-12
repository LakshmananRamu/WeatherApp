package com.example.karthikeyantkr.weather;
/*
Weather
HomeWork 5

Karthikeyan TKR
Lakshmanan RM

*/


import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText state, city;
    Button submit;
    String strState, strCity;
    ListView fav_list;
    HashMap<String, ArrayList<String>> favList = new HashMap<>();
    TextView favorites;

    FavAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setTitle("Weather App");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        assignViews();

        SharedPreferences prefs = getSharedPreferences("fav_list", MODE_PRIVATE);
        java.lang.reflect.Type type = new TypeToken<HashMap<String, ArrayList<String>>>() {
        }.getType();
        if (new Gson().fromJson(prefs.getString("my_list", null), type) != null) {
            favList = new Gson().fromJson(prefs.getString("my_list", null), type);
            if (!(favList.isEmpty())) {
                ArrayList<String> keys = new ArrayList<String>(favList.keySet());

                favorites.setText("Favorites");
                adapter = new FavAdapter(this, R.layout.main_list_view, favList, keys);
                fav_list.setAdapter(adapter);
                adapter.setNotifyOnChange(true);
                adapter.notifyDataSetChanged();
            }

        }

    }

    private void assignViews() {
        submit = (Button) findViewById(R.id.button);
        state = (EditText) findViewById(R.id.editTextState);
        city = (EditText) findViewById(R.id.editTextCity);
        fav_list = (ListView) findViewById(R.id.listView_main);
        favorites = (TextView) findViewById(R.id.textView_fav);


    }

    public void SubmitAction(View view) {
        strCity = city.getText().toString().trim();
        strState = state.getText().toString().trim();

        strCity.replace(" ", "_");

        if (strCity == null) {
            Toast.makeText(getApplicationContext(), "Please enter city", Toast.LENGTH_SHORT).show();
        } else if (strState == null) {
            Toast.makeText(getApplicationContext(), "Please enter state", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(MainActivity.this, HourlyActivity.class);
            String url = "http://api.wunderground.com/api/cc7069873113c6c7/hourly/q/" + strState + "/" + strCity.replace(" ", "_") + ".json";
            intent.putExtra("url", url);
            intent.putExtra("city", strCity);
            intent.putExtra("state", strState);
            intent.putExtra("map", favList);
            startActivity(intent);

        }
    }


}
