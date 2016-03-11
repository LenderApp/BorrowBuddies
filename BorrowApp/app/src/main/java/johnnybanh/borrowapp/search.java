package johnnybanh.borrowapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import johnnybanh.borrowapp.itemData.Items;

public class search extends AppCompatActivity {

    Toolbar toolbar;
    ImageButton homeToolBtn, profileToolBtn, watchingToolBtn;
    EditText searchItem, searchCity, searchState;
    Button searchButton;
    ListView searchReturnList;
    SearchAdapter searchadapter;
    private Firebase firebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Firebase.setAndroidContext(this);
        firebase = new Firebase("https://luminous-inferno-3787.firebaseio.com/");

        searchItem = (EditText) findViewById(R.id.searchItem);
        searchCity = (EditText) findViewById(R.id.searchCity);
        searchState = (EditText) findViewById(R.id.searchState);
        searchButton = (Button) findViewById(R.id.searchButton);
        searchReturnList = (ListView) findViewById(R.id.searchItemList);

        //triggers database search
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String inputItem = searchItem.getText().toString();
                final String inputCity = searchCity.getText().toString();
                final String inputState = searchState.getText().toString();

                firebase.child("Database/ItemBank").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        searchadapter = new SearchAdapter(search.this, firebase , dataSnapshot, inputItem, inputCity, inputState);
                        searchReturnList.setAdapter(searchadapter);
                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });

            }
        });

        searchReturnList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Items item = (Items) searchadapter.getItem(position);
                Intent intent = new Intent(search.this, ItemInformation.class);
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
                intent.putExtra("itemCategories",  item.getCategories());
                intent.putExtra("itemSpecifics",  item.getSpecifics());
                startActivity(intent);
            }
        });

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        homeToolBtn = (ImageButton) toolbar.findViewById(R.id.home_button);
        profileToolBtn = (ImageButton) toolbar.findViewById(R.id.profile_button);
        watchingToolBtn = (ImageButton) toolbar.findViewById(R.id.watching_button);

        homeToolBtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent searchIntent = new Intent(search.this, MainActivity.class);
                startActivity(searchIntent);
            }
        });

        profileToolBtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent searchIntent = new Intent(search.this, MainActivity.class);
                startActivity(searchIntent);
            }
        });

        watchingToolBtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent searchIntent = new Intent(search.this, WatchList.class);
                startActivity(searchIntent);
            }
        });

    }
}
