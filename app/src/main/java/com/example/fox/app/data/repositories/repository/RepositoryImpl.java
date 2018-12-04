package com.example.fox.app.data.repositories.repository;

import com.example.fox.app.data.model.Item;
import com.example.fox.app.data.model.PostResponse;
import com.example.fox.app.data.net.RestService;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class RepositoryImpl implements ItemRepository {

    private RestService restService;

    @Inject
    public RepositoryImpl(RestService restService) {
        this.restService = restService;
    }


    @Override
    public Observable<List<Item>> getPosts() {
        return restService
                .getPosts()
                .map(new Function<List<PostResponse>, List<Item>>() {
                    @Override
                    public List<Item> apply(List<PostResponse> postResponses) throws Exception {
                        List<Item> itemPostList = new ArrayList<>();
                        for (PostResponse post : postResponses) {
                            itemPostList.add(new Item(post.getTitle(), post.getBody(), ""));
                        }
                        return itemPostList;
                    }
                });
    }
}

