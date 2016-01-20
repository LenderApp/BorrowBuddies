package johnnybanh.borrowapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;


// Array of options --> Array Adapter --> ListView

// List View: (views: user_items.xml)

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button postButton = (Button) findViewById(R.id.postButton);
        Button searchButton = (Button) findViewById(R.id.searchButton);

        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent postIntent = new Intent(MainActivity.this, post.class);
                startActivity(postIntent);
            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v){
                Intent searchIntent = new Intent(MainActivity.this, search.class);
                startActivity(searchIntent);
            }
        });

        populateListView();
    }

    private void populateListView(){
        // Create list of items
        String[] watchingtestItems = {"Bauer Supreme Hockey Gloves 13 in","CCM Vector Helmet Medium","Hockey Girdle",
                "Duralast Car Jack","Motor Oil Pan","KitchenAid Frying Pan"};

        String[] borrowingtestItems = {"Stanley: 4 mode Drill", "Fuji 52cm Road Bicycle", "Yamaha: Guitar",
                "CCM Hockey Sticks", "Reebok 3K Hockey Skates"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,                   //Context for Activity
                R.layout.user_items,    //Layout to use (create)
                watchingtestItems);     //Items to be populated

        //Configure the list
        ListView watchtestList = (ListView) findViewById(R.id.watchingList);
        watchtestList.setAdapter(adapter);

        //Build Adapter
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(
                this,                    //Context for Activity
                R.layout.user_items2,    //Layout to use (create)
                borrowingtestItems);     //Items to be populated

        //Configure the list
        ListView borrowtestList = (ListView) findViewById(R.id.borrowingList);
        borrowtestList.setAdapter(adapter2);



    }
}
