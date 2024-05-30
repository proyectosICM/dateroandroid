package com.icm.dateroandorid.interfaces;

import com.icm.dateroandorid.models.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;


public interface JsonPlaceHolderApi {
    @GET("posts")
    Call<List<Post>> getPosts();}
