package johnnybanh.borrowapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by johnnybanh on 1/27/16.
 */
public class BorrowedItemsAdapter extends ArrayAdapter<BorrowedItems> {

    private Context context;
    private ArrayList<BorrowedItems> borrowedItems;

    public BorrowedItemsAdapter(Context context, ArrayList<BorrowedItems> borrowedItems){
        super(context, R.layout.borrowing_list, borrowedItems);
        this.context = context;
        this.borrowedItems = borrowedItems;

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
       // return super.getView(position, convertView, parent);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.borrowing_list, parent, false);

        ImageView imageView = (ImageView) view.findViewById(R.id.borrowListImage);
        imageView.setImageResource(borrowedItems.get(position).getPhotoRes());

        TextView itemName = (TextView) view.findViewById(R.id.borrowListItemName);
        if (borrowedItems.get(position).getName().toString().length() > 20){

            itemName.setText(borrowedItems.get(position).getName().substring(0,15) + "...");
        }
        else {
            itemName.setText(borrowedItems.get(position).getName());
        }

        TextView itemDate = (TextView) view.findViewById(R.id.borrowItemDate);
        itemDate.setText("Date Borrowed: " + borrowedItems.get(position).getDate());

        Button returnButton = (Button) view.findViewById(R.id.returnedButton);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                borrowedItems.remove(position);
                BorrowedItemsAdapter.this.notifyDataSetChanged();
            }
        });

        return view;
    }
}
