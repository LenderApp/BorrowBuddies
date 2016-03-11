package johnnybanh.borrowapp;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

import johnnybanh.borrowapp.itemData.Items;

public class WatchList extends AppCompatActivity {


    Toolbar toolbar;
    ImageButton homeToolBtn, profileToolBtn, watchingToolBtn;
    ListView watchingListView;
    Firebase firebase;
    private WatchingItemsAdapter watchingItemsAdapter;
    int myColor = Color.rgb(10, 10, 70);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        firebase = new Firebase("https://luminous-inferno-3787.firebaseio.com/");
        setContentView(R.layout.activity_watch_list);

        watchingListView = (ListView) findViewById(R.id.watchingList);


        firebase.child("Database/WatchingList").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                watchingItemsAdapter = new WatchingItemsAdapter(WatchList.this, firebase, dataSnapshot);
                watchingListView.setAdapter(watchingItemsAdapter);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        watchingListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Items item = (Items) watchingItemsAdapter.getItem(position);
                Intent intent = new Intent(WatchList.this, ItemInformation.class);
                intent.putExtra("itemName", item.getName());
                intent.putExtra("itemLocation", item.getCity() + ", " + item.getState());
                String printRate = "";
                if (item.getRate() == 0.0) {
                    printRate = "Free";
                } else {
                    String amount = String.valueOf(item.getRate());
                    String decimalCheck = "";
                    for (int i = 0; i < amount.length(); i++) {
                        if (amount.charAt(i) == '.') {
                            decimalCheck = amount.substring(i, amount.length());
                        }
                    }

                    if (decimalCheck.length() < 3)
                        printRate = "$" + amount + "0";
                    else
                        printRate = "$" + amount;

                }
                intent.putExtra("itemRate", printRate);
                intent.putExtra("itemCategories", item.getCategories());
                intent.putExtra("itemSpecifics",  item.getSpecifics());
                startActivity(intent);
            }
        });

        watchingListView.setAdapter(watchingItemsAdapter);


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        homeToolBtn = (ImageButton) findViewById(R.id.home_button);
        profileToolBtn = (ImageButton) findViewById(R.id.profile_button);
        watchingToolBtn = (ImageButton) findViewById(R.id.watching_button);
        watchingToolBtn.setBackgroundColor(myColor);

        homeToolBtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent searchIntent = new Intent(WatchList.this, MainActivity.class);
                startActivity(searchIntent);
            }
        });

        profileToolBtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent searchIntent = new Intent(WatchList.this, MainActivity.class);
                startActivity(searchIntent);
            }
        });

        watchingToolBtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent searchIntent = new Intent(WatchList.this, WatchList.class);
                startActivity(searchIntent);
            }
        });

    }
}
