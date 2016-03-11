package johnnybanh.borrowapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

import johnnybanh.borrowapp.itemData.Items;


public class MainActivity extends AppCompatActivity {

    ListView borrowedListView;
    TextView itemName, itemLocation;
    Toolbar toolbar;
    Button searchButton;
    ImageButton homeToolBtn, profileToolBtn, watchingToolBtn;
    Firebase firebase;
    int myColor = Color.rgb(10,10,70);

    private ArrayList<String> borrowedItems;
    private BorrowedItemsAdapter borrowedItemsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Firebase.setAndroidContext(this);
        firebase = new Firebase("https://luminous-inferno-3787.firebaseio.com/");
        searchButton = (Button) findViewById(R.id.homeSearchButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, search.class);
                startActivity(intent);
            }
        });

        itemName = (TextView) findViewById(R.id.borrowListItemName);

        itemLocation = (TextView) findViewById(R.id.borrowItemDate);

        borrowedItems = new ArrayList<>();

        borrowedListView = (ListView) findViewById(R.id.borrowingList);


        firebase.child("Database/BorrowList").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                borrowedItemsAdapter = new BorrowedItemsAdapter(MainActivity.this, firebase, dataSnapshot);
                borrowedListView.setAdapter(borrowedItemsAdapter);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        borrowedListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Items item = (Items) borrowedItemsAdapter.getItem(position);
                Intent intent = new Intent(MainActivity.this, ItemInformation.class);
                intent.putExtra("itemPic", R.drawable.turtle);
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

        borrowedListView.setAdapter(borrowedItemsAdapter);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        homeToolBtn = (ImageButton) toolbar.findViewById(R.id.home_button);
        homeToolBtn.setBackgroundColor(myColor);
        profileToolBtn = (ImageButton) toolbar.findViewById(R.id.profile_button);
        watchingToolBtn = (ImageButton) toolbar.findViewById(R.id.watching_button);

        homeToolBtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent searchIntent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(searchIntent);
            }
        });

        profileToolBtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent searchIntent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(searchIntent);
            }
        });

        watchingToolBtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent searchIntent = new Intent(MainActivity.this, WatchList.class);
                startActivity(searchIntent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    // this method determine if action bar item was selected. If true do action
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        //Handles Action Bar selection
        switch (item.getItemId()){

            case  R.id.post_action:
                startActivity(new Intent(this, post.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
