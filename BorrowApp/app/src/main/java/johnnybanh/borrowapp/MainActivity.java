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
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    ListView borrowedListView;
    Toolbar toolbar;
    Button searchButton;
    ImageButton homeToolBtn, profileToolBtn, watchingToolBtn;
    int myColor = Color.rgb(10,10,70);

   private ArrayList<BorrowedItems> borrowedItems;
    private BorrowedItemsAdapter borrowedItemsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchButton = (Button) findViewById(R.id.homeSearchButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, search.class);
                startActivity(intent);
            }
        });
        borrowedItems = new ArrayList<>();
        borrowedItems.add(new BorrowedItems("Bauer Vapor Skates", "01/02/2016", R.drawable.skates));
        borrowedItems.add(new BorrowedItems("Crock Pot Cooker", "12/25/2015", R.drawable.crockpot));
        borrowedItems.add(new BorrowedItems("iHome for iPhone 4, 4s", "01/11/2016", R.drawable.ihome));
        borrowedItems.add(new BorrowedItems("Warrior Hockey Gloves", "01/02/2016", R.drawable.gloves));

        borrowedListView = (ListView) findViewById(R.id.borrowingList);
        borrowedItemsAdapter = new BorrowedItemsAdapter(this, borrowedItems);

        borrowedListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Intent intent = new Intent(MainActivity.this, ItemInformation.class);
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
                Intent searchIntent = new Intent(MainActivity.this, post.class);
                startActivity(searchIntent);
            }
        });

        watchingToolBtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent searchIntent = new Intent(MainActivity.this, search.class);
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
