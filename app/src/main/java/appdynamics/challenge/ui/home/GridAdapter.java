package appdynamics.challenge.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import appdynamics.challenge.R;

public class GridAdapter extends BaseAdapter {

    private Context context;

    public GridAdapter(Context context){
        this.context = context;
    }

    @Override
    public int getCount() {
        return Item.ITEMS.length;
    }

    @Override
    public Item getItem(int position) {
        return Item.ITEMS[position];
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getId();
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.grid_item, viewGroup, false);
        }

        final Item item = getItem(position);

        // Set the TextView's contents
        TextView name = view.findViewById(R.id.ticket_name);
        name.setText(item.getName());

        TextView price = view.findViewById(R.id.ticket_price);
        price.setText(item.getPrice());


        return view;
    }

}
