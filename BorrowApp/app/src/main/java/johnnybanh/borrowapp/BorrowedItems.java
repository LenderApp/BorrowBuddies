package johnnybanh.borrowapp;

/**
 * Created by johnnybanh on 1/27/16.
 */
public class BorrowedItems {

    private String name;
    private String date;
    private int photoRes;

    public BorrowedItems(String name, String date, int photoRes) {
        this.name = name;
        this.date = date;
        this.photoRes = photoRes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getPhotoRes() {
        return photoRes;
    }

    public void setPhotoRes(int photoRes) {
        this.photoRes = photoRes;
    }
}
