package appdynamics.challenge.ui.detail;

import android.util.Log;
import android.widget.ListView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;

import appdynamics.challenge.ui.login.LoginActivity;
import appdynamics.challenge.ui.login.LoginViewModel;

public class DetailViewModel extends ViewModel {

    private MutableLiveData<Boolean> ticketProcessed =  new MutableLiveData<>();

    public DetailViewModel() {

    }

    public LiveData<Boolean> obtainTicket() {
        return ticketProcessed;
    }


    public void buyAndValidateTicket(final String ticket) {

        RequestBody formBody = new FormEncodingBuilder()
                .add("ticket", ticket)
                .build();

        Request request = new Request.Builder()
                .url("http://192.168.1.133:8081/buyTicket/normal")
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

                ticketProcessed.postValue(false);

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
                    ticketProcessed.postValue(true);

                } else {
                    ticketProcessed.postValue(false);
                }

            }

        });

    }

}
