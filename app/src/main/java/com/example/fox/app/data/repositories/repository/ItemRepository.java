package com.example.fox.app.data.repositories.repository;

import com.example.fox.app.data.model.Item;

import java.util.List;

import io.reactivex.Observable;

public interface ItemRepository {

    Observable<List<Item>> getPosts();

}
