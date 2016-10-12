package com.example.karthikeyantkr.weather;
/*
Weather
HomeWork 5

Karthikeyan TKR
Lakshmanan RM

*/


import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.Comparator;


public class Weather implements Parcelable, Comparator<Weather> {
    String time, temperature, dewpoint, clouds, iconUrl, windSpeed, windDirection, climateType;
    String humidity, feelsLike, maximumTemp, minimumTemp, pressure;
    //Bitmap thumbnail_bitmap;


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getDewpoint() {
        return dewpoint;
    }

    public void setDewpoint(String dewpoint) {
        this.dewpoint = dewpoint;
    }

    public String getClouds() {
        return clouds;
    }

    public void setClouds(String clouds) {
        this.clouds = clouds;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(String windDirection) {
        this.windDirection = windDirection;
    }

    public String getClimateType() {
        return climateType;
    }

    public void setClimateType(String climateType) {
        this.climateType = climateType;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getFeelsLike() {
        return feelsLike;
    }

    public void setFeelsLike(String feelsLike) {
        this.feelsLike = feelsLike;
    }

    public String getMaximumTemp() {
        return maximumTemp;
    }

    public void setMaximumTemp(String maximumTemp) {
        this.maximumTemp = maximumTemp;
    }

    public String getMinimumTemp() {
        return minimumTemp;
    }

    public void setMinimumTemp(String minimumTemp) {
        this.minimumTemp = minimumTemp;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public static Creator<Weather> getCREATOR() {
        return CREATOR;
    }

    public Weather(String temperature, String time, String dewpoint, String clouds, String iconUrl, String windSpeed, String windDirection, String climateType, String humidity, String feelsLike, String maximumTemp, String minimumTemp, String pressure) {
        this.temperature = temperature;
        this.time = time;
        this.dewpoint = dewpoint;
        this.clouds = clouds;
        this.iconUrl = iconUrl;
        this.windSpeed = windSpeed;

        this.windDirection = windDirection;
        this.climateType = climateType;
        this.humidity = humidity;
        this.feelsLike = feelsLike;
        this.maximumTemp = maximumTemp;
        this.minimumTemp = minimumTemp;
        this.pressure = pressure;
    }

    protected Weather(Parcel in) {
        time = in.readString();
        temperature = in.readString();
        dewpoint = in.readString();
        clouds = in.readString();
        iconUrl = in.readString();
        windSpeed = in.readString();
        windDirection = in.readString();
        climateType = in.readString();
        humidity = in.readString();
        feelsLike = in.readString();
        maximumTemp = in.readString();
        minimumTemp = in.readString();
        pressure = in.readString();
        //  thumbnail_bitmap = in.readParcelable(Bitmap.class.getClassLoader());
    }

    public static final Creator<Weather> CREATOR = new Creator<Weather>() {
        @Override
        public Weather createFromParcel(Parcel in) {
            return new Weather(in);
        }

        @Override
        public Weather[] newArray(int size) {
            return new Weather[size];
        }
    };

    @Override
    public int compare(Weather o1, Weather o2) {
        return 0;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(time);
        dest.writeString(temperature);
        dest.writeString(dewpoint);
        dest.writeString(clouds);
        dest.writeString(iconUrl);
        dest.writeString(windSpeed);
        dest.writeString(windDirection);
        dest.writeString(climateType);
        dest.writeString(humidity);
        dest.writeString(feelsLike);
        dest.writeString(maximumTemp);
        dest.writeString(minimumTemp);
        dest.writeString(pressure);
        //dest.writeParcelable(thumbnail_bitmap, flags);
    }
}
