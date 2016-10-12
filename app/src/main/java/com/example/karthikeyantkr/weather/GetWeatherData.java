package com.example.karthikeyantkr.weather;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParserException;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

/*
Weather
HomeWork 5

Karthikeyan TKR
Lakshmanan RM

*/

public class GetWeatherData extends AsyncTask<String, Void, ArrayList<Weather>> {
    DataRetreiver activity;

    ArrayList<Weather> weatherlist = new ArrayList<>();

    @Override
    protected void onPostExecute(ArrayList<Weather> newsItems) {

        super.onPostExecute(newsItems);


        activity.setData(newsItems);
    }

    public GetWeatherData(DataRetreiver activity) {

        this.activity = activity;

    }

    @Override
    protected ArrayList<Weather> doInBackground(String... params) {
        String line;
        JSONObject jsonObject = null;
        try {
            URL url = new URL(params[0]);

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();

            InputStream in = new BufferedInputStream(con.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder sb = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
            reader.close();
            jsonObject = new JSONObject(sb.toString());

            return GetWeatherDetails(jsonObject);


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            Log.d("except","Json exception is : "+ e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    private ArrayList<Weather> GetWeatherDetails(JSONObject jsonObject) {

        try {
            Log.d("demo", "json==" + jsonObject.toString());
            if(!(jsonObject.isNull("hourly_forecast"))) {
                JSONArray hourly_data = jsonObject.getJSONArray("hourly_forecast");

                for (int i = 0; i < hourly_data.length(); i++) {
                    JSONObject hourly_data_ind = (JSONObject) hourly_data.get(i);
                    JSONObject hourly_data_time = (JSONObject) hourly_data_ind.getJSONObject("FCTTIME");
                    String time = hourly_data_time.getString("civil");
                    JSONObject hourly_data_temp = (JSONObject) hourly_data_ind.getJSONObject("temp");
                    String temp = hourly_data_temp.getString("english");
                    JSONObject hourly_data_dew = (JSONObject) hourly_data_ind.getJSONObject("dewpoint");
                    String dew = hourly_data_dew.getString("english");
                    String clouds = hourly_data_ind.getString("condition");

                    String icon_url = hourly_data_ind.getString("icon_url");
                    JSONObject hourly_data_windspeed = (JSONObject) hourly_data_ind.getJSONObject("wspd");
                    String windspeed = hourly_data_dew.getString("english");
                    JSONObject hourly_data_windirection = (JSONObject) hourly_data_ind.getJSONObject("wdir");
                    String windir = hourly_data_windirection.getString("dir");
                    String windegrees = hourly_data_windirection.getString("degrees");
                    String winddirection = windegrees + "°" + windir;
                    String climate_type = hourly_data_ind.getString("wx");

                    String humidity = hourly_data_ind.getString("humidity");
                    JSONObject hourly_data_feelslike = (JSONObject) hourly_data_ind.getJSONObject("feelslike");
                    String feelslike = hourly_data_feelslike.getString("english");

                    JSONObject hourly_data_pressure = (JSONObject) hourly_data_ind.getJSONObject("mslp");
                    String pressure = hourly_data_pressure.getString("metric");
                    weatherlist.add(new Weather(temp, time, dew, clouds, icon_url, windspeed, winddirection, climate_type, humidity, feelslike, "0", "0", pressure));

                }
            }
            else
            {
                String error = jsonObject.getJSONObject("error").getString("type");
                Log.d("except","Json exception is : "+ error);

            }
        } catch (JSONException e) {
            Log.d("except","Json exception is : "+ e.getMessage());
            e.printStackTrace();
        }
        return weatherlist;
    }

    interface DataRetreiver {
        void setData(ArrayList<Weather> newsList);
    }
}
