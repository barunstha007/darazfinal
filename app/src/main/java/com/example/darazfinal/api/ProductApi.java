package com.example.darazfinal.api;

import com.example.darazfinal.model.Item;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ProductApi {
    @GET("product/list")
    Call<List<Item>> getProduct();
}
