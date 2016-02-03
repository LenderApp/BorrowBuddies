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

import java.util.ArrayList;

public class post extends AppCompatActivity {
  /*  ArrayList<String> postItemNameList = new ArrayList<String>();
    ArrayList<String> postItemTypeList = new ArrayList<String>();
    ArrayList<String> postItemSpecificsList = new ArrayList<String>();
    ArrayList<Double> postItemRateList = new ArrayList<Double>();

    EditText itemName = (EditText) findViewById(R.id.postItemName);
    EditText itemType = (EditText) findViewById(R.id.postItemType);
    EditText itemSpecifics = (EditText) findViewById(R.id.postItemSpecifics);
    EditText itemRate = (EditText) findViewById(R.id.rateTextbox);
    CheckBox itemSolid = (CheckBox) findViewById(R.id.solidCheckbox);*/
    Toolbar toolbar;
    ImageButton homeToolBtn, profileToolBtn, watchingToolBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);


        Button post = (Button) findViewById(R.id.postItemButton);
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*postItemNameList.add(itemName.toString());
                postItemTypeList.add(itemType.toString());
                postItemSpecificsList.add(itemSpecifics.toString());
                double rate = Double.parseDouble(itemRate.toString());
                postItemRateList.add(rate);

                Log.i("list", postItemNameList.get(0));
                Log.i("list", postItemTypeList.get(0));
                Log.i("list", postItemSpecificsList.get(0));
                Log.i("list", postItemRateList.get(0).toString());*/

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

       /* toolbar.inflateMenu(R.menu.menu_toolbar);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.profile_action:
                        startActivity(new Intent(post.this, search.class));
                        return true;

                    case R.id.home_action:
                        startActivity(new Intent(post.this, MainActivity.class));
                        return true;

                    case R.id.watching_action:
                        startActivity(new Intent(post.this, search.class));
                        return true;
                }
                return false;
            }
        });*/
    }

    // this method determine if action bar item was selected. If true do action
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        //Handles Action Bar selection
        switch (item.getItemId()){

            case R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;

            /*case R.id.action_search:
                startActivity(new Intent(this, search.class));
                return true;

            case  R.id.post_action:
                startActivity(new Intent(this, post.class));
                return true;*/
        }
        return super.onOptionsItemSelected(item);
    }
}
