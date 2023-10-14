package com.pucmm.practica_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        int userId = intent.getIntExtra("user_id", -1);

        getUserById(userId);
    }

    private void getUserById(int id) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://reqres.in/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UserService userService = retrofit.create(UserService.class);

        Call<ResponseBody> call = userService.getUserById(id);

        call.enqueue(new Callback<ResponseBody>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Toast.makeText(MainActivity2.this, "onResponse", Toast.LENGTH_SHORT).show();

                try {
                    JSONObject jsonResponse = new JSONObject(response.body().string());

                    JSONObject data = jsonResponse.getJSONObject("data");

                    String email = data.getString("email");
                    String firstName = data.getString("first_name");
                    String lastName = data.getString("last_name");
                    String avatar = data.getString("avatar");

                    TextView textView2 = findViewById(R.id.textView2);
                    textView2.setText(firstName + " " + lastName);

                    TextView textView3 = findViewById(R.id.textView3);
                    textView3.setText(email);

                    ImageView imageView = findViewById(R.id.imageView);
                    Picasso.get().load(avatar).into(imageView);
                } catch (JSONException | IOException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(MainActivity2.this, "onFailure", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}