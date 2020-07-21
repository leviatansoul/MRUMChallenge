package appdynamics.challenge.ui.notifications;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import appdynamics.challenge.R;
import appdynamics.challenge.ui.home.Item;

public class ValidatedTicketAdapter extends BaseAdapter {

    private Context context;

    public ValidatedTicketAdapter(Context context){
        this.context = context;
    }

    @Override
    public int getCount() {
        return ValidatedTicket.ticketsList.size();
    }

    @Override
    public ValidatedTicket getItem(int position) {
        Log.d("Position", ""+position);
        return ValidatedTicket.ticketsList.get(position+1);
    }

    @Override
    public long getItemId(int position) {
        return Long.parseLong(""+position);
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.validated_ticket_list_item, viewGroup, false);
        }

        if(!ValidatedTicket.ticketsList.isEmpty()){
            Log.d("position", ""+position);
            final ValidatedTicket ticket = ValidatedTicket.ticketsList.get(position);

            // Set the TextView's contents
            TextView code = view.findViewById(R.id.ticket_code);
            code.setText(ticket.getCode());

            TextView date = view.findViewById(R.id.ticket_date);
            date.setText(ticket.getDate());
        }



        return view;
    }

}
