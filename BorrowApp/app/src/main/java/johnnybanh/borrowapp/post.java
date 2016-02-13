package johnnybanh.borrowapp;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import com.firebase.client.Firebase;
import java.util.ArrayList;


public class post extends AppCompatActivity {

    Toolbar toolbar;
    ImageButton homeToolBtn, profileToolBtn, watchingToolBtn;
    Button postButton;
    private Firebase firebase;
    //EditText postItemName, postItemRate, postItemSpecifics, postItemCity, postItemState;
    CheckBox free, tools, sports, culinary, academics, clothing, electronics, homeGoods, misc;
    ArrayList<CheckBox> checklist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        Firebase.setAndroidContext(this);
        firebase = new Firebase("https://luminous-inferno-3787.firebaseio.com/");

        final EditText postItemName = (EditText) findViewById(R.id.postItemName);
        final EditText postItemRate = (EditText) findViewById(R.id.postItemRate);
        final EditText postItemSpecifics = (EditText) findViewById(R.id.postItemSpecifics);
        final EditText postItemCity = (EditText) findViewById(R.id.postItemCity);
        final EditText postItemState = (EditText) findViewById(R.id.postItemState);
        free = (CheckBox) findViewById(R.id.postItemFree);

        checklist = new ArrayList<CheckBox>();
        tools = (CheckBox) findViewById(R.id.toolsCheckbox);
        checklist.add(tools);
        sports = (CheckBox) findViewById(R.id.sportsCheckbox);
        checklist.add(sports);
        culinary = (CheckBox) findViewById(R.id.culinaryCheckbox);
        checklist.add(culinary);
        academics = (CheckBox) findViewById(R.id.academicsCheckbox);
        checklist.add(academics);
        clothing = (CheckBox) findViewById(R.id.clothingCheckbox);
        checklist.add(clothing);
        electronics = (CheckBox) findViewById(R.id.electronicsCheckbox);
        checklist.add(electronics);
        homeGoods = (CheckBox) findViewById(R.id.homeGoodsCheckbox);
        checklist.add(homeGoods);
        misc = (CheckBox) findViewById(R.id.miscCheckBox);
        checklist.add(misc);

        postButton = (Button) findViewById(R.id.postItemButton);

        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = postItemName.getText().toString();
                String rateTest = postItemRate.getText().toString();
                Log.i("Test", rateTest);
                double rate;

                if ((rateTest == "") || (free.isChecked()))
                    rate = 0.00;
                else
                    rate = Double.parseDouble(rateTest);

                String specifics = postItemSpecifics.getText().toString();
                String categories = "";

                for (CheckBox item : checklist){
                    if (item.isChecked())
                    {
                        categories += item.getText().toString()+ " ";
                    }
                }
                String city = postItemCity.getText().toString();
                String state = postItemState.getText().toString();

                PostItem postitem = new PostItem(name, rate, categories, specifics, city, state);
                firebase.child("ItemBank/").push().setValue(postitem);

                Intent intent = new Intent(post.this, MainActivity.class);
                startActivity(intent);
                Log.i("Test", "Item Name: " + name + " Item Rate: " + rate +  " Item Cat: " + categories.toString() + " Item Specs: " + specifics);
            }
        });


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        homeToolBtn = (ImageButton) toolbar.findViewById(R.id.home_button);
        profileToolBtn = (ImageButton) toolbar.findViewById(R.id.profile_button);
        watchingToolBtn = (ImageButton) toolbar.findViewById(R.id.watching_button);

        homeToolBtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent searchIntent = new Intent(post.this, MainActivity.class);
                startActivity(searchIntent);
            }
        });

        profileToolBtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent searchIntent = new Intent(post.this, post.class);
                startActivity(searchIntent);
            }
        });

        watchingToolBtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent searchIntent = new Intent(post.this, search.class);
                startActivity(searchIntent);
            }
        });



    }

    // this method determine if action bar item was selected. If true do action
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;

        }
        return super.onOptionsItemSelected(item);
    }
}
