package com.pucmm.practica_1;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface UserService {

    @GET("users")
    Call<Response> getData();

    @GET("users/{id}")
    Call<ResponseBody> getUserById(@Path("id") int id);
}
