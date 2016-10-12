package com.example.karthikeyantkr.weather;

/*
Weather
HomeWork 5

Karthikeyan TKR
Lakshmanan RM

*/

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by KARTHIKEYAN T K R on 10/11/2016.
 */
public class FavAdapter extends ArrayAdapter {
    HashMap<String, ArrayList<String>> favlist;
    Context mContext;
    int mResource;
    ArrayList<String> keys;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(mResource, parent, false);

        }
        String key = keys.get(position);

        ArrayList<String> list = favlist.get(key);
        final String city = key.substring(0, key.indexOf(","));
        final String state = key.substring(key.indexOf(",") + 1, key.length());
        TextView curloc = (TextView) convertView.findViewById(R.id.textView_main_curloc);
        TextView temp = (TextView) convertView.findViewById(R.id.textView_main_temp);
        TextView updated = (TextView) convertView.findViewById(R.id.textView_main_updated);
        curloc.setText(city + ", " + state);
        temp.setText(list.get(0) + "°" + "F");
        updated.setText("Updated on: " + list.get(1));
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, HourlyActivity.class);
                String url = "http://api.wunderground.com/api/cc7069873113c6c7/hourly/q/" + state.trim() + "/" + city.trim().replace(" ", "_") + ".json";
                intent.putExtra("url", url);
                intent.putExtra("city", city);
                intent.putExtra("state", state);
                intent.putExtra("map", favlist);
                mContext.startActivity(intent);
            }
        });

        convertView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (favlist.containsKey(city + "," + state)) {

                    favlist.remove(city + "," + state);
                    String addKey1 = city + "," + state;
                    ArrayList<String> addValue1 = new ArrayList<>();

                    SharedPreferences.Editor editor = mContext.getSharedPreferences("fav_list", mContext.MODE_PRIVATE).edit();
                    editor.putString("my_list", new Gson().toJson(favlist));
                    editor.commit();
                    Toast.makeText(mContext, "Deleted City from Favourites", Toast.LENGTH_SHORT).show();

                }
                Intent intent = new Intent(mContext, MainActivity.class);
                mContext.startActivity(intent);
                return true;
            }
        });

        return convertView;
    }

    public FavAdapter(Context context, int resource, HashMap<String, ArrayList<String>> favlist, ArrayList<String> keys) {
        super(context, resource, (List) keys);
        this.mContext = context;
        this.mResource = resource;
        this.favlist = favlist;
        this.keys = keys;

    }
}
