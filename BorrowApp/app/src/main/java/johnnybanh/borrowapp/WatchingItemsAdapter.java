package johnnybanh.borrowapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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
 * Created by johnnybanh on 2/17/16.
 */
public class WatchingItemsAdapter extends BaseAdapter {

    private Context context;
    private List<String> idList;
    private Map<String, Items> itemMap;
    private Firebase firebase;

    public WatchingItemsAdapter(Context context, Firebase firebase, DataSnapshot dataSnapshot) {
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

        View view = layoutInflater.inflate(R.layout.watching_list, parent, false);

        final ImageView watchImage = (ImageView) view.findViewById(R.id.watchingListImage);
        final TextView watchingName = (TextView) view.findViewById(R.id.watchingListItemName);
        final TextView watchingLocation = (TextView) view.findViewById(R.id.watchingLocation);
        final TextView watchingRate = (TextView) view.findViewById(R.id.watchingListItemRate);
        Button borrowButton = (Button) view.findViewById(R.id.watchingBorrowButton);
        Button unwatchButton = (Button) view.findViewById(R.id.unwatchButton);

        final Items item = (Items) getItem(position);

        watchImage.setImageResource(R.drawable.turtle);
        if (item.getName().length() > 20)
            watchingName.setText(item.getName().substring(0, 15) + "...");
        else
            watchingName.setText(item.getName());

        watchingLocation.setText("Location: " + item.getCity() + ", " + item.getState());

        if (item.getRate() == 0.0) {
            watchingRate.setText("Rate: Free");
        } else {
            String amount = String.valueOf(item.getRate());
            String decimalCheck = "";
            for (int i = 0; i < amount.length(); i++) {
                if (amount.charAt(i) == '.') {
                    decimalCheck = amount.substring(i, amount.length());
                }
            }

            if (decimalCheck.length() < 3)
                watchingRate.setText("Rate: $" + amount + "0");
            else
                watchingRate.setText("Rate: $" + amount);
        }

            borrowButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PostItem borrowedItem = new PostItem(item.getName(), item.getRate(), item.getCategories(), item.getSpecifics(), item.getCity(), item.getState());
                    firebase.child("Database/BorrowList").push().setValue(borrowedItem);
                    Toast.makeText(context, "Added to Borrow List!", Toast.LENGTH_SHORT).show();

                }
            });

            unwatchButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "Unwatched...", Toast.LENGTH_SHORT).show();
                }
            });

            return view;
        }
    }

