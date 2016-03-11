package johnnybanh.borrowapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;

public class ItemInformation extends AppCompatActivity {

    Toolbar toolbar;
    TextView itemName, itemLocation, itemRate, itemCategories, itemSpecifics;
    ImageView itemImage;
    Button watchButton, borrowButton;
    Firebase firebase;
    ImageButton homeToolBtn, profileToolBtn, watchingToolBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_information);
        Firebase.setAndroidContext(this);
        firebase = new Firebase("https://luminous-inferno-3787.firebaseio.com/");

        Intent intent = getIntent();
        int pic = intent.getIntExtra("itemImage", R.drawable.turtle );
        final String name = intent.getStringExtra("itemName");
        final String location = intent.getStringExtra("itemLocation");
        final String rate = intent.getStringExtra("itemRate");
        final String categories = intent.getStringExtra("itemCategories");
        final String specifics = intent.getStringExtra("itemSpecifics");

        itemImage = (ImageView) findViewById(R.id.itemInfoImage);
        itemName = (TextView) findViewById(R.id.itemInfoName);
        itemLocation = (TextView) findViewById(R.id.itemInfoLocation);
        itemRate = (TextView) findViewById (R.id.itemInfoRate);
        itemCategories = (TextView) findViewById (R.id.itemInfoCategories);
        itemSpecifics = (TextView) findViewById (R.id.itemInfoSpecifics);
        watchButton = (Button) findViewById(R.id.itemInfoWatchButton);
        borrowButton = (Button) findViewById(R.id.itemInfoBorrowButton);

        itemImage.setImageResource(pic);
        itemName.setText(name);
        itemLocation.setText("Location: "+location);
        itemRate.setText("Rate: "+rate);
        itemCategories.setText("Categories: "+categories);
        itemSpecifics.setText(specifics);

        watchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] parse = location.split(", ");
                String city = parse[0];
                String state = parse[1];
                double ratepost;
                if (rate.equals("Free")){
                    ratepost = 0.0;
                }
                else {
                    ratepost = Double.parseDouble(rate);
                }
                PostItem post = new PostItem(name, ratepost , categories, specifics, city, state);
                firebase.child("Database/WatchingList").push().setValue(post);

                Toast.makeText(ItemInformation.this, "Added to Watch List!", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(ItemInformation.this, MainActivity.class);
                startActivity(intent);
            }
        });

        borrowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] parse = location.split(", ");
                String city = parse[0];
                String state = parse[1];
                double ratepost;
                if (rate.equals("Free")){
                    ratepost = 0.0;
                }
                else {
                    ratepost = Double.parseDouble(rate);
                }
                PostItem post = new PostItem(name, ratepost , categories, specifics, city, state);
                firebase.child("Database/BorrowList").push().setValue(post);

                Toast.makeText(ItemInformation.this, "Added to Borrow List!", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(ItemInformation.this, MainActivity.class);
                startActivity(intent);
            }
        });
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        homeToolBtn = (ImageButton) toolbar.findViewById(R.id.home_button);
        profileToolBtn = (ImageButton) toolbar.findViewById(R.id.profile_button);
        watchingToolBtn = (ImageButton) toolbar.findViewById(R.id.watching_button);

        homeToolBtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent searchIntent = new Intent(ItemInformation.this, MainActivity.class);
                startActivity(searchIntent);
            }
        });

        profileToolBtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent searchIntent = new Intent(ItemInformation.this, MainActivity.class);
                startActivity(searchIntent);
            }
        });

        watchingToolBtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent searchIntent = new Intent(ItemInformation.this, search.class);
                startActivity(searchIntent);
            }
        });
    }

}
