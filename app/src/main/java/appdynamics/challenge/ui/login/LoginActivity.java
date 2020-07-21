package appdynamics.challenge.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.squareup.okhttp.OkHttpClient;

import appdynamics.challenge.MainActivity;
import appdynamics.challenge.R;


public class LoginActivity extends AppCompatActivity {

    private String username = "";
    private String password = "";


    private static EditText usernameEditText;
    private static EditText passwordEditText;
    private static TextView invalid;
    private static Button loginButton;
    private LoginViewModel loginViewModel;
    public static final OkHttpClient client = new OkHttpClient();

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

        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);

        loginViewModel.login().observe(this, new Observer<LoginViewModel.RequestResult>() {
            @Override
            public void onChanged(@Nullable LoginViewModel.RequestResult res) {
                switch (res){
                    case UNABLE:
                        Toast.makeText(LoginActivity.this, "Unable to reach the server", Toast.LENGTH_SHORT).show();
                        break;

                    case FAILURE:
                        Toast.makeText(LoginActivity.this, "Incorrect username or password", Toast.LENGTH_SHORT).show();
                        invalid.setVisibility(View.VISIBLE);
                        break;

                    case SUCCESS:
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        break;

                    case NOTFOUND:
                        Toast.makeText(LoginActivity.this, "Error 404, Not Found", Toast.LENGTH_SHORT).show();
                        break;

                    default:
                        break;
                }
            }
        });

        /**
         * Login button to authenticate the user
         */
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                invalid.setVisibility(View.INVISIBLE);
                username = usernameEditText.getText().toString();
                password = passwordEditText.getText().toString();
                loginViewModel.makeLoginRequest(username,password);


            }
        });

    }





}
