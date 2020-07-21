package appdynamics.challenge.ui.login;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class LoginViewModel extends ViewModel {

    enum RequestResult {
        UNABLE,
        NOTFOUND,
        SUCCESS,
        FAILURE
    }

    private MutableLiveData<RequestResult> loginResult = new MutableLiveData<>();

    public LoginViewModel() {

    }

    public LiveData<RequestResult> login() {
        return loginResult;
    }

    public void makeLoginRequest(String username, String password) {

        RequestBody formBody = new FormEncodingBuilder()
                .add("username", username)
                .add("password", password)
                .build();

        Request request = new Request.Builder()
                .url("http://192.168.1.133:8081/login")
                .post(formBody)
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

                Log.d("onFailure", "Unable to reach the server");
                loginResult.postValue(RequestResult.UNABLE);


            }

            /**
             * When the server recieves the request and provides a response to the user
             *
             * @param response - Information about the request made to the server. It contains body and code.
             * @throws IOException
             */
            @Override
            public void onResponse(Response response) throws IOException {


                if (!response.isSuccessful()) {

                    switch (response.code()) {
                        case 404:
                            Log.d("Error 404 ", "Not found");
                            loginResult.postValue(RequestResult.NOTFOUND);
                            break;
                        default:
                            break;

                    }
                    //Toast.makeText(LoginActivity.this, "Invalid request path", Toast.LENGTH_SHORT).show();


                    throw new IOException("Unexpected code " + response);

                } else {
                    // When the response is successful (code 200)

                    String result = response.body().string();
                    Log.d("onResponse Success", result);

                    if (result.contains("Approved")) { //If the response contains approved the user is registered and they can move forward.

                        loginResult.postValue(RequestResult.SUCCESS);

                    } else {

                        loginResult.postValue(RequestResult.FAILURE);


                    }

                }

            }

        });

    }


}
