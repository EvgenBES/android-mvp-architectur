package com.example.fox.app.data.repositories.usecases;


import com.example.fox.app.data.model.Item;
import com.example.fox.app.data.repositories.repository.ItemRepository;
import com.example.fox.app.di.executors.PostExecutionThread;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class GetItemUseCase extends BaseUseCase {

    private ItemRepository itemRepository;

    @Inject
    public GetItemUseCase(ItemRepository itemRepository,
                          PostExecutionThread postExecutionThread) {
        super(postExecutionThread);
        this.itemRepository = itemRepository;
    }


    public Observable<List<Item>> getPosts() {
        return itemRepository
                .getPosts()
                .subscribeOn(executionThread)
                .observeOn(postExecutionThread);
    }
}
