package appdynamics.challenge.ui.detail;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import appdynamics.challenge.R;
import appdynamics.challenge.ui.home.Item;
import appdynamics.challenge.ui.mytickets.ValidatedTicket;

public class DetailActivity extends AppCompatActivity {

    private DetailViewModel detailViewModel;
    private String ticket;
    private String price;
    private Item.TICKET_TYPE type;
    private Item item; //Use this attribute to obtain the ticket price item.getPrice() to inject it into Network Requests

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        ticket = getIntent().getExtras().get("name").toString();
        price = getIntent().getExtras().get("price").toString();
        type = Item.TICKET_TYPE.valueOf(getIntent().getStringExtra("type"));

        for(Item it : Item.ITEMS ){
            if(it.getType() == type){
                item = it;
            }
        }


        getSupportActionBar().setTitle(ticket);

        TextView textView = (TextView) findViewById(R.id.details_title);
        textView.setText(ticket);

        TextView textViewPrice = (TextView) findViewById(R.id.details_price);
        textViewPrice.setText(price);

        detailViewModel = ViewModelProviders.of(this).get(DetailViewModel.class);

        detailViewModel.obtainTicket().observe(this, new Observer<Boolean>() {
                    @Override
                    public void onChanged(Boolean ticketPurchased) {
                        if(ticketPurchased){

                            detailViewModel.insertTicket(type);

                            Toast.makeText(DetailActivity.this, "Done!", Toast.LENGTH_SHORT).show();


                        } else {
                            Toast.makeText(DetailActivity.this, "Fail", Toast.LENGTH_SHORT).show();

                        }
                    }
                });


        Button buyAndValidateButton = (Button) findViewById(R.id.buyAndValidateButton);
        buyAndValidateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                detailViewModel.buyAndValidateTicket(type);

            }
        });

    }
}
