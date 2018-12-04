package com.example.fox.app.ui.main;

import com.example.fox.app.data.repositories.usecases.GetItemUseCase;
import com.example.fox.app.di.app.App;
import com.example.fox.app.data.model.Item;
import com.example.fox.app.ui.base.BaseViewModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MainPresenter extends BaseViewModel {

    private MainView mainView;

    @Inject
    public GetItemUseCase getItemUseCase;

    @Override
    protected void runInject() {
        App.getAppComponent().runInject(this);
    }

    MainPresenter(MainView mainView) {
        this.mainView = mainView;
    }


    void init () {
        getItemUseCase
                .getPosts()
                .subscribe(new Observer<List<Item>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        getCompositeDisposable().add(d);
                    }

                    @Override
                    public void onNext(List<Item> items) {
                        onFinished(items);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mainView.showError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    void onItemClicked(String title, String message) {
        if (mainView != null) {
            mainView.startInfoActivity(title, message);
        }
    }

    void onDestroy() {
        mainView = null;
    }

    void onFinished(List<Item> items) {
        if (mainView != null) {
            mainView.setItems(items);
            mainView.hideProgress();
        }
    }

}
