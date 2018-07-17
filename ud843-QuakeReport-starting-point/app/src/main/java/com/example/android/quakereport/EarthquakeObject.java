package com.example.android.quakereport;

public class EarthquakeObject {

    private String location, magnitude, time;

    public EarthquakeObject(String magnitude, String time, String location) {
        this.magnitude = magnitude;
        this.location = location;
        this.time = time;
    }

    public String getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(String magnitude) {
        this.magnitude = magnitude;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "EarthquakeObject{" +
                "magnitude=" + magnitude +
                ", location='" + location + '\'' +
                ", time=" + time +
                '}';
    }
}
