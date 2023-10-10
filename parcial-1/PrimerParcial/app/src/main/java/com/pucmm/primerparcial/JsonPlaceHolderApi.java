package com.pucmm.primerparcial;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JsonPlaceHolderApi {

    @GET("products")
    Call<List<Product>> getProducts();

    @GET("products/{id}")
    Call<ResponseBody> getProductById(@Path("id") int id);

    @GET("products/search")
    Call<List<Product>> getProductsByFilter(@Query("q") String string);
}