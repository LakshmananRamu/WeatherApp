package com.example.karthikeyantkr.weather;

/*
Weather
HomeWork 5

Karthikeyan TKR
Lakshmanan RM

*/

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {
    TextView curloc, temp, cloud, max, min, feeslike, humidity, dew, pressure, winds, clouds;
    ImageView image;
    Weather weather;
    String city, state;
    int maxi=0,mini=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_details);
        assignviews();
        Bundle bundle = getIntent().getExtras();
        weather = bundle.getParcelable("com.example.karthikeyantkr.weather.Weather");
        city = getIntent().getExtras().getString("city");
        state = getIntent().getExtras().getString("state");
        maxi=getIntent().getExtras().getInt("max");
        mini=getIntent().getExtras().getInt("min");
        setdata();
    }

    private void setdata() {
        curloc.setText("Current Location:" + city + ", " + state + " (" + weather.getTime() + ")");
        Picasso.with(DetailsActivity.this).load(weather.iconUrl).into(image);
        temp.setText(weather.getTemperature() + "°" + "F");
        cloud.setText(weather.getClouds());
        feeslike.setText(weather.getFeelsLike() + " Fahrenheit");
        humidity.setText(weather.getHumidity() + "%");
        dew.setText(weather.getDewpoint() + " Fahrenheit");
        pressure.setText(weather.getPressure() + " hPa");
        clouds.setText(weather.getClouds());
        winds.setText(weather.getWindSpeed() + ", " + weather.getWindDirection());
    max.setText(""+maxi);
        min.setText(""+mini);
    }

    private void assignviews() {
        curloc = (TextView) findViewById(R.id.textViewCurLoc);
        image = (ImageView) findViewById(R.id.imageViewdimg);
        temp = (TextView) findViewById(R.id.textViewdDegree);
        cloud = (TextView) findViewById(R.id.textViewdCloudy);
        max = (TextView) findViewById(R.id.maxTemp);
        min = (TextView) findViewById(R.id.minTemp);

        feeslike = (TextView) findViewById(R.id.textView_dfeelslike);

        humidity = (TextView) findViewById(R.id.textView_dhumidity);

        dew = (TextView) findViewById(R.id.textView_ddew);

        pressure = (TextView) findViewById(R.id.textView_dpressure);

        clouds = (TextView) findViewById(R.id.textView_dcloud);

        winds = (TextView) findViewById(R.id.textView_dwinds);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Toast.makeText(getApplicationContext(), "Cannot Add a City here", Toast.LENGTH_SHORT).show();

        return super.onOptionsItemSelected(item);
    }

}
