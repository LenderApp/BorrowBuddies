package johnnybanh.borrowapp.itemData;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by johnnybanh on 3/1/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Items {

    private String specifics;
    private double rate;
    private String name;
    private String city;
    private String state;
    private String categories;
    private int id;

    public Items() {

    }

    public Items(String name, double rate, String categories, String specifics, String city, String state) {

        this.name = name;
        this.rate = rate;
        this.categories = categories;
        this.specifics = specifics;
        this.city = city;
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String getCategories() {
        return categories;
    }

    public void setCatergories(String catergories) {
        this.categories = catergories;
    }

    public String getSpecifics() {
        return specifics;
    }

    public void setSpecifics(String specifics) {
        this.specifics = specifics;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
