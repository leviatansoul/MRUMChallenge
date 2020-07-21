package appdynamics.challenge.ui.detail;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.notbytes.barcode_reader.BarcodeReaderActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import appdynamics.challenge.R;
import appdynamics.challenge.ui.home.HomeViewModel;
import appdynamics.challenge.ui.home.Item;
import appdynamics.challenge.ui.login.LoginActivity;
import appdynamics.challenge.ui.login.LoginViewModel;
import appdynamics.challenge.ui.notifications.ValidatedTicket;

public class DetailActivity extends AppCompatActivity {

    private DetailViewModel detailViewModel;
    private String ticket;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        ticket = getIntent().getExtras().get("name").toString();

        getSupportActionBar().setTitle(ticket);

        TextView textView = (TextView) findViewById(R.id.details_title);
        textView.setText(ticket);

        detailViewModel = ViewModelProviders.of(this).get(DetailViewModel.class);

        detailViewModel.obtainTicket().observe(this, new Observer<Boolean>() {
                    @Override
                    public void onChanged(Boolean ticketPurchased) {
                        if(ticketPurchased){

                            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                            Date date = new Date();
                            String sDate = formatter.format(date);
                            ValidatedTicket.ticketsList.add(new ValidatedTicket(Item.ITEMS[0], sDate, ticket+" "+ ((int)(Math.random()*1000000))));

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

                detailViewModel.buyAndValidateTicket(ticket);

            }
        });

    }
}
