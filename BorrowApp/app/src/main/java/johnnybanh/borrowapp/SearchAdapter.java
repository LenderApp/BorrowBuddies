package johnnybanh.borrowapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import johnnybanh.borrowapp.itemData.Items;

/**
 * Created by johnnybanh on 3/1/16.
 */
public class SearchAdapter extends BaseAdapter {

    private Context context;
    private List<String> idList;
    private Map<String, Items> itemMap;
    private Firebase firebase;
    public String itemCheck;
    public String cityCheck;
    public String stateCheck;

    public SearchAdapter(Context context, Firebase firebase, DataSnapshot dataSnapshot, String inputItem, String inputCity, String inputState) {
        itemCheck = inputItem;
        cityCheck = inputCity;
        stateCheck = inputState;
        this.context = context;
        this.firebase = firebase;
        initData(dataSnapshot);
    }

    private void initData(DataSnapshot dataSnapshot) {

        idList = new ArrayList<String>();
        itemMap = new HashMap<String, Items>();

        for (DataSnapshot data : dataSnapshot.getChildren()) {
            String id = data.getKey();
            Items item = data.getValue(Items.class);
            idList.add(id);
            itemMap.put(id, item);
        }
    }

    @Override
    public int getCount() {
        return idList.size();
    }

    @Override
    public Object getItem(int position) {
        String id = idList.get(position);
        return itemMap.get(id);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = layoutInflater.inflate(R.layout.search_list, parent, false);

        final TextView itemName = (TextView) view.findViewById(R.id.searchListItemName);
        final TextView itemLocation = (TextView) view.findViewById(R.id.searchLocation);
        final TextView itemRate = (TextView) view.findViewById(R.id.searchListItemRate);

        ImageView itemImage = (ImageView) view.findViewById(R.id.searchListImage);
        final Button watchButton = (Button) view.findViewById(R.id.searchwatchButton);
        final Button borrowButton = (Button) view.findViewById(R.id.searchBorrowButton);

        final Items item = (Items) getItem(position);

        itemImage.setImageResource(R.drawable.turtle);

        if (item.getName().length() > 20)
            itemName.setText(item.getName().substring(0, 15) + "...");
        else
            itemName.setText(item.getName());

        itemLocation.setText("Location: " + item.getCity() + ", " + item.getState());

        if (item.getRate() == 0.0) {
            itemRate.setText("Rate: Free");
        } else {
            String amount = String.valueOf(item.getRate());
            String decimalCheck = "";
            for (int i = 0; i < amount.length(); i++) {
                if (amount.charAt(i) == '.') {
                    decimalCheck = amount.substring(i, amount.length());
                }
            }

            if (decimalCheck.length() < 3)
                itemRate.setText("Rate: $" + amount + "0");
            else
                itemRate.setText("Rate: $" + amount);

        }

        watchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PostItem watchedItem = new PostItem(item.getName(), item.getRate(), item.getCategories(), item.getSpecifics(), item.getCity(), item.getState());
                firebase.child("Database/WatchingList").push().setValue(watchedItem);
                Toast.makeText(context, "Added to Watch List!", Toast.LENGTH_SHORT).show();
            }
        });

        borrowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PostItem borrowedItem = new PostItem(item.getName(), item.getRate(), item.getCategories(), item.getSpecifics(), item.getCity(), item.getState());
                firebase.child("Database/BorrowList").push().setValue(borrowedItem);
                Toast.makeText(context, "Added to Borrow List!", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

}
