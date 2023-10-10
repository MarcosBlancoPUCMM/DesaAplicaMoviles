package com.pucmm.primerparcial;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    List<Product> products = new ArrayList<>();
    RecyclerViewAdapter recyclerViewAdapter;
    RecyclerView recyclerView;
    ProductViewModel productViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        productViewModel = new ViewModelProvider(this).get(ProductViewModel.class);

        recyclerViewAdapter = new RecyclerViewAdapter(MainActivity.this, products);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        LiveData<List<Product>> liveData = productViewModel.getProducts();
        liveData.observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                recyclerViewAdapter.updateProducts(products);
            }
        });

        getProducts();

        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(recyclerView);

        Button button = findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editText = findViewById(R.id.editTextText);
                getProductsByFilter(editText.toString());
            }
        });
    }

    private void getProducts() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://dummyjson.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Call<List<Product>> call = jsonPlaceHolderApi.getProducts();

        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                Toast.makeText(MainActivity.this, "onResponse", Toast.LENGTH_SHORT).show();

                products = response.body();
                for (Product product : products) {
                    productViewModel.insert(new Product(
                            product.id,
                            product.title,
                            product.description
                    ));
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "onFailure", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getProductsByFilter(String string) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://dummyjson.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Call<List<Product>> call = jsonPlaceHolderApi.getProductsByFilter(string);

        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                Toast.makeText(MainActivity.this, "onResponse", Toast.LENGTH_SHORT).show();

                products = response.body();

                if (products != null && !products.isEmpty()) {
                    productViewModel.updateProducts(products);
                } else {
                    getProducts();
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "onFailure", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showProductDetail(int id) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://dummyjson.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Call<ResponseBody> call = jsonPlaceHolderApi.getProductById(id);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Toast.makeText(MainActivity.this, "onResponse", Toast.LENGTH_SHORT).show();

                String responseString = response.body().toString();

                JsonParser parser = new JsonParser();
                JsonObject json = parser.parse(responseString).getAsJsonObject();

                String title = json.get("title").getAsString();
                String description = json.get("description").getAsString();
                double price = json.get("price").getAsDouble();
                double discountPercentage = json.get("discountPercentage").getAsDouble();
                double rating = json.get("rating").getAsDouble();
                int stock = json.get("stock").getAsInt();
                String brand = json.get("brand").getAsString();
                String category = json.get("category").getAsString();
                String thumbnail = json.get("thumbnail").getAsString();

                setContentView(R.layout.product_detail);

                TextView textView3 = findViewById(R.id.textView3);
                TextView textView4 = findViewById(R.id.textView4);
                textView3.setText(title);
                textView4.setText(description);

                TextView textView5 = findViewById(R.id.textView5);
                TextView textView6 = findViewById(R.id.textView6);
                textView5.setText(((int) price));
                textView6.setText((int) discountPercentage);

                TextView textView7 = findViewById(R.id.textView7);
                TextView textView8 = findViewById(R.id.textView8);
                textView7.setText((int) rating);
                textView8.setText(stock);

                TextView textView9 = findViewById(R.id.textView9);
                TextView textView10 = findViewById(R.id.textView10);
                textView9.setText(brand);
                textView10.setText(category);

                ImageView imageView = findViewById(R.id.imageView);
                Picasso.get().load(thumbnail).into(imageView);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(MainActivity.this, "onFailure", Toast.LENGTH_SHORT).show();
            }
        });
    }

    ItemTouchHelper.SimpleCallback callback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            Product product = recyclerViewAdapter.products.get(viewHolder.getAdapterPosition());
            showProductDetail(product.id);

            Toast.makeText(MainActivity.this, "Detail", Toast.LENGTH_SHORT).show();
        }
    };
}