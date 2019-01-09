package hotel.hotelapp;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;

import entities.User;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView id,userName,firstName,lastName;
    Button btnLogout, buttonShowDepartments, buttonDeregister;
    private static final String TAG = LoginActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(SharedPrefManager.getInstance(this).isLoggedIn()){
            id = findViewById(R.id.textViewId);
            userName = findViewById(R.id.textViewUsername);
            firstName = findViewById(R.id.textViewFirstName);
            lastName = findViewById(R.id.textViewLastName);
            btnLogout = findViewById(R.id.buttonLogout);
            buttonShowDepartments = findViewById(R.id.buttonShowDepartments);
            buttonDeregister = findViewById(R.id.buttonDeregister);

            User user = SharedPrefManager.getInstance(this).getUser();

            id.setText(String.valueOf(user.getId()));
            firstName.setText(user.getFirstName());
            lastName.setText(user.getLastName());
            userName.setText(user.getUserName());

            btnLogout.setOnClickListener(this);
            buttonShowDepartments.setOnClickListener(this);
            buttonDeregister.setOnClickListener(this);
        }
        else{
            Intent  intent = new Intent(MainActivity.this,LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }
    public void onClick(View view){
        if(view.equals(btnLogout)){
            SharedPrefManager.getInstance(getApplicationContext()).logout();
        }

        if(view.equals(buttonShowDepartments)){
            SharedPrefManager.getInstance(getApplicationContext()).showDepartments();
        }

        if(view.equals(buttonDeregister)){
            deregisterUser();
        }

    }

    private void deregisterUser() {

        User user = SharedPrefManager.getInstance(this).getUser();

        Log.i(TAG, "TO BE DEREGISTERED ->> " + user.getId());

        Log.i(TAG, URLs.URL_DEREGISTER + user.getId());

        StringRequest stringRequest = new StringRequest(Request.Method.DELETE, URLs.URL_DEREGISTER + user.getId(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.i(TAG, response);
                        Toast.makeText(getApplicationContext(), "You have been successfully DEREGISTERED", Toast.LENGTH_LONG).show();


                        //starting the profile activity
                        finish();
                        SharedPrefManager.getInstance(getApplicationContext()).deregisterUser();
                        startActivity(new Intent(getApplicationContext(), LoginActivity.class));

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }
}