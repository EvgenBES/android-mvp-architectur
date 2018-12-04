package com.example.fox.app.data.net;


import com.example.fox.app.data.model.PostResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface RestApi {

    @GET("posts")
    Observable<List<PostResponse>> getPosts();

}
