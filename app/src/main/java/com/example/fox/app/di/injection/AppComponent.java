package com.example.fox.app.di.injection;


import com.example.fox.app.ui.main.MainPresenter;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {AppModule.class})
@Singleton
public interface AppComponent {
    void runInject(MainPresenter mainPresenter);
}