package johnnybanh.borrowapp;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageButton;

public class search extends AppCompatActivity {

    Toolbar toolbar;
    ImageButton homeToolBtn, profileToolBtn, watchingToolBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

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
                Intent searchIntent = new Intent(search.this, post.class);
                startActivity(searchIntent);
            }
        });

        watchingToolBtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent searchIntent = new Intent(search.this, search.class);
                startActivity(searchIntent);
            }
        });

        /*toolbar.inflateMenu(R.menu.menu_toolbar);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.profile_action:
                        startActivity(new Intent(search.this, search.class));
                        return true;

                    case R.id.home_action:
                        startActivity(new Intent(search.this, MainActivity.class));
                        return true;

                    case R.id.watching_action:
                        startActivity(new Intent(search.this, search.class));
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

    public void showSoftKeyboard(View view){
        if(view.requestFocus()){
            InputMethodManager imm =(InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
        }
    }
}
