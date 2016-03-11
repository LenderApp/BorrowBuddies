package johnnybanh.borrowapp;

import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * Created by johnnybanh on 2/17/16.
 */
public class WatchingItems {

    private String name;
    private String city;
    private String state;
    private double rate;
    private int photoRes;

    public WatchingItems(String name, String city, String state, double rate){ //, int photoRes){
        this.name = name;
        this.city = city;
        this.state = state;
        this.rate = rate;
       // this.photoRes = photoRes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public int getPhotoRes() {
        return photoRes;
    }

    public void setPhotoRes(int photoRes) {
        this.photoRes = photoRes;
    }
}
