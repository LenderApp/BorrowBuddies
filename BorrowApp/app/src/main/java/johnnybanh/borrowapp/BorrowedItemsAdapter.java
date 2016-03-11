package johnnybanh.borrowapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import johnnybanh.borrowapp.itemData.Items;

/**
 * Created by johnnybanh on 1/27/16.
 */
public class BorrowedItemsAdapter extends BaseAdapter {

    private Context context;
    private List<String> idList;
    private Map<String, Items> itemMap;
    private Firebase firebase;
    String id;

    public BorrowedItemsAdapter(Context context, Firebase firebase, DataSnapshot dataSnapshot) {
        this.context = context;
        this.firebase = firebase;
        initData(dataSnapshot);
    }

    private void initData(DataSnapshot dataSnapshot) {

        idList = new ArrayList<String>();
        itemMap = new HashMap<String, Items>();

        for (DataSnapshot data : dataSnapshot.getChildren()) {
            String id = data.getKey();
            Items item = data.getValue(Items.class);
            idList.add(id);
            itemMap.put(id, item);
        }
    }

    @Override
    public int getCount() {
        return idList.size();
    }

    @Override
    public Object getItem(int position) {
        id = idList.get(position);
        return itemMap.get(id);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = layoutInflater.inflate(R.layout.borrowing_list, parent, false);

        final ImageView borrowImage = (ImageView) view.findViewById(R.id.borrowListImage);
        final TextView borrowName = (TextView) view.findViewById(R.id.borrowListItemName);
        final TextView borrowLocation = (TextView) view.findViewById(R.id.borrowItemDate);

        final Items item = (Items) getItem(position);

        borrowImage.setImageResource(R.drawable.turtle);
        if (item.getName().length() > 20)
            borrowName.setText(item.getName().substring(0, 15) + "...");
        else
            borrowName.setText(item.getName());

        borrowLocation.setText("Location: " + item.getCity() + ", " + item.getState());

        Button returnButton = (Button) view.findViewById(R.id.returnedButton);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Item Returned!", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}