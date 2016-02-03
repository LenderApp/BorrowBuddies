package johnnybanh.borrowapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
    ImageButton homeToolBtn, profileToolBtn, watchingToolBtn;
    int myColor = Color.rgb(10,10,70);

    private ArrayList<BorrowedItems> borrowedItems;
    private BorrowedItemsAdapter borrowedItemsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        borrowedItems = new ArrayList<>();
        borrowedItems.add(new BorrowedItems("Bauer APX Pro Gloves 13in", "01/02/2016", R.drawable.cat));
        borrowedItems.add(new BorrowedItems("Bauer Pro Stick", "12/25/2015", R.drawable.ducks));
        borrowedItems.add(new BorrowedItems("Stanley Power Drill", "04/11/2014", R.drawable.turtle));
        borrowedItems.add(new BorrowedItems("Reebok size 9 Hockey Skates", "01/02/2016", R.drawable.cat));
        borrowedItems.add(new BorrowedItems("Sherwood Hockey stick", "05/02/2016", R.drawable.ducks));
        borrowedItems.add(new BorrowedItems("X-box One 2 Controller", "06/02/2016", R.drawable.turtle));
        borrowedItems.add(new BorrowedItems("Kitchen Aid Fry Pan", "08/02/2016", R.drawable.cat));
        borrowedItems.add(new BorrowedItems("CCM Vector Helmet Medium", "07/02/2016", R.drawable.ducks));
        borrowedItems.add(new BorrowedItems("Fuji 52cm Road Bicycle", "11/02/2016", R.drawable.turtle));

        borrowedListView = (ListView) findViewById(R.id.borrowingList);
        borrowedItemsAdapter = new BorrowedItemsAdapter(this, borrowedItems);
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
