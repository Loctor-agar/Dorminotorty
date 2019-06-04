package ng.com.obkm.bottomnavviewwithfragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import ng.com.obkm.bottomnavviewwithfragments.announcements.AnnouncementModel;
import ng.com.obkm.bottomnavviewwithfragments.models.User;
import ng.com.obkm.bottomnavviewwithfragments.models.UserResponse;
import ng.com.obkm.bottomnavviewwithfragments.services.AnnouncementService;
import ng.com.obkm.bottomnavviewwithfragments.services.ApiClient;
import ng.com.obkm.bottomnavviewwithfragments.services.UserClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText etUserName;
    private EditText etPassword;
    User user;
    private Button btn_sign_in;
    SharedPreferences mPrefs = getPreferences(MODE_PRIVATE);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        etUserName = (EditText) findViewById(R.id.etUserName);
        etPassword = (EditText) findViewById(R.id.etPassword);

        btn_sign_in = (Button) findViewById(R.id.btn_sign_in);
//        btn_sign_in.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(LoginActivity.this,MainActivity.class));
//            }
//        });

        btn_sign_in.setOnClickListener((view -> {
            user = new User(
                    etUserName.getText().toString(),
                    etPassword.getText().toString()
            );
            sendNetworkRequest(user);
        }));
    }

    private void sendNetworkRequest(User user) {
        UserClient client = ApiClient.getClientInstance().create(UserClient.class);
        Call<User> call = client.loginUser(user);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
//                Toast.makeText(LoginActivity.this, "Welcome, " + response.body().getUsername(), Toast.LENGTH_SHORT).show();
//                if (response.isSuccessful()) {
//                    Log.d("UserResponse", "ROle: " + response.body().getRole());
//                    Log.d("UserResponse", "ROle: " + response.body().getFirstName());
//                    Log.d("UserResponse", "ROle: " + response.body().getLastName());
//                    Log.d("UserResponse", "ROle: " + response.body().getAccessToken());
//                } else {
//                    Log.d("UserResponse", "Login not correct");
//                }
//                Log.d("UserResponse", "ROle: " + response.body().role);

                String username = response.body().getUsername();
                String firstName = response.body().getFirstName();
                String lastName = response.body().getLastName();
                String accessToken = response.body().getAccessToken();
                String refreshToken = response.body().getRefreshToken();
                String role = response.body().getRole();
                User loggedUser = new User(username, firstName, lastName, accessToken, refreshToken, role);
                SharedPreferences.Editor prefsEditor = mPrefs.edit();
                Gson gson = new Gson();
                String json = gson.toJson(loggedUser);
                prefsEditor.putString("UserData", json);
                prefsEditor.commit();

                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Something went wrong: " + t.getMessage().toString(), Toast.LENGTH_SHORT).show();
                Log.d("UserResponse", "ERROR: " + t.getMessage().toString());
            }
        });

    }
}
