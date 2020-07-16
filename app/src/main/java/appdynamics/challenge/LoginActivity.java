package appdynamics.challenge;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;


public class LoginActivity extends AppCompatActivity {

    private String username = "";
    private String password = "";


    private static EditText usernameEditText;
    private static EditText passwordEditText;
    private static TextView invalid;
    private static Button loginButton;
    private final OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        /**
         * Variables initialization
         */
        usernameEditText = (EditText) findViewById(R.id.username);
        passwordEditText = (EditText) findViewById(R.id.password);
        loginButton = (Button) findViewById(R.id.login);
        invalid = (TextView) findViewById(R.id.invalid);

        /**
         * Login button to authenticate the user
         */
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                 */

                invalid.setVisibility(View.INVISIBLE);
                username = usernameEditText.getText().toString();
                password = passwordEditText.getText().toString();


                RequestBody formBody = new FormEncodingBuilder()
                        .add("username", username)
                        .add("password", password)
                        .build();

                Request request = new Request.Builder()
                        .url("http://192.168.1.137:8081/login")
                        .post(formBody)
                        .build();


                /**
                 * Make a synchronous request to the server
                 */
                client.newCall(request).enqueue(new Callback() {

                    /**
                     * When the server is not available
                     * @param request - Request object to connect to the server
                     * @param e - Exception
                     */
                    @Override
                    public void onFailure(Request request, IOException e) {

                        Log.d("onFailure", "Unable to reach the server");

                        Thread thread = new Thread(){
                            public void run(){
                                runOnUiThread(new Runnable() {
                                    public void run() {
                                        Toast.makeText(LoginActivity.this, "Server not available", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        };
                        thread.start();


                    }

                    /**
                     * When the server recieves the request and provides a response to the user
                     * @param response - Information about the request made to the server. It contains body and code.
                     * @throws IOException
                     */
                    @Override
                    public void onResponse(Response response) throws IOException {

                        if (!response.isSuccessful()) {

                            switch (response.code()) {
                                case 404:
                                    Log.d("Error 404 ", "Not found");
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

                            if (result.contains("Approved")){ //If the response contains approved the user is registered and they can move forward.
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                            } else {

                                Thread thread = new Thread(){
                                    public void run(){
                                        runOnUiThread(new Runnable() {
                                            public void run() {
                                                Toast.makeText(LoginActivity.this, "Invalid user or password", Toast.LENGTH_SHORT).show();
                                                invalid.setVisibility(View.VISIBLE);
                                            }
                                        });
                                    }
                                };
                                thread.start();


                            }

                        }

                    }

                });



            }
        });

    }





}
