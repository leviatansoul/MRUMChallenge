package appdynamics.challenge.ui.scan;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import appdynamics.challenge.UserData;
import appdynamics.challenge.ui.home.Item;
import appdynamics.challenge.ui.login.LoginActivity;
import appdynamics.challenge.ui.mytickets.ValidatedTicket;

public class ScanViewModel extends ViewModel {

    private MutableLiveData<Boolean> ticketValidated =  new MutableLiveData<>();

    public LiveData<Boolean> validateTicket() {
        return ticketValidated;
    }

    private MutableLiveData<String> mText;

    public ScanViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Insert a QR code");
    }

    public LiveData<String> getText() {
        return mText;
    }

    public void validateTicket(final String barcode_url) {

        RequestBody formBody = new FormEncodingBuilder()
                .build();

        Request request = new Request.Builder()
                .url(UserData.URL_SERVER +barcode_url)
                .build();


        /**
         * Make a synchronous request to the server
         */
        LoginActivity.client.newCall(request).enqueue(new Callback() {

            /**
             * When the server is not available
             *
             * @param request - Request object to connect to the server
             * @param e       - Exception
             */
            @Override
            public void onFailure(Request request, IOException e) {

                ticketValidated.postValue(false);

            }

            /**
             * When the server recieves the request and provides a response to the user
             *
             * @param response - Information about the request made to the server. It contains body and code.
             * @throws IOException
             */
            @Override
            public void onResponse(Response response) throws IOException {


                if (response.isSuccessful()) {
                    ticketValidated.postValue(true);
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                    Date date = new Date();
                    String sDate = formatter.format(date);
                    if(barcode_url.equals("/bus/validateTicket/normal")){
                        ValidatedTicket.ticketsList.add(new ValidatedTicket(Item.ITEMS[0], sDate, Item.ITEMS[0].getName()+" "+ ((int)(Math.random()*1000000))));
                    } else if(barcode_url.equals("/bus/validateTicket/reduced")){
                        ValidatedTicket.ticketsList.add(new ValidatedTicket(Item.ITEMS[0], sDate, Item.ITEMS[1].getName()+" "+ ((int)(Math.random()*1000000))));
                    }

                } else {
                    ticketValidated.postValue(false);
                }

            }

        });

    }

}