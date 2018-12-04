package com.example.fox.app.ui.main;

import com.example.fox.app.data.model.Item;

import java.util.List;

public interface MainView {

    void showProgress();

    void showError(String message);

    void hideProgress();

    void init();

    void setItems(List<Item> items);

    void startInfoActivity(String title, String message);
}