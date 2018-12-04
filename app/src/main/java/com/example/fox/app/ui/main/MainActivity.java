package com.example.fox.app.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.fox.app.R;
import com.example.fox.app.data.model.Item;
import com.example.fox.app.ui.base.BaseActivity;
import com.example.fox.app.ui.info.InfoActivity;

import java.util.List;

public class MainActivity extends BaseActivity implements MainView {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.list);
        progressBar = findViewById(R.id.progress);
        presenter = new MainPresenter(this);
        init();
    }

    @Override
    public void init() {
        showProgress();
        presenter.init();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
        recyclerView.setVisibility(View.VISIBLE);
    }


    @Override
    public void setItems(List<Item> items) {
        recyclerView.setAdapter(new MainAdapter(items, presenter::onItemClicked));
    }

    @Override
    public void startInfoActivity(String title, String message) {
        Intent intent = new Intent(this, InfoActivity.class);
        intent.putExtra("GET_TITLE", title);
        intent.putExtra("GET_MESSAGE", message);
        startActivity(intent);
    }
}