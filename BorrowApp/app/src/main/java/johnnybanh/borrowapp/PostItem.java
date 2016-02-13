package johnnybanh.borrowapp;

/**
 * Created by johnnybanh on 2/12/16.
 */
public class PostItem {

    private String name;
    private double rate;
    private String categories;
    private String specifics;
    private String city;
    private String state;

    public  PostItem(String name, double rate, String categories, String specifics,
                     String city, String state){
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

    public void setCategories(String categories) {
        this.categories = categories;
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
}
