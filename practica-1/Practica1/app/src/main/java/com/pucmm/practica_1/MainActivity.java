package com.pucmm.practica_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    List<User> users = new ArrayList<>();
    RecyclerViewAdapter recyclerViewAdapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewAdapter = new RecyclerViewAdapter(MainActivity.this, users);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        getUsers();
    }

    private void getUsers() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://reqres.in/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UserService userService = retrofit.create(UserService.class);

        Call<Response> call = userService.getData();

        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                Toast.makeText(MainActivity.this, "onResponse", Toast.LENGTH_SHORT).show();

                if (response.body() != null) {
                    users.addAll(response.body().getData());
                    recyclerViewAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Toast.makeText(MainActivity.this, "onFailure", Toast.LENGTH_SHORT).show();
            }
        });
    }
}