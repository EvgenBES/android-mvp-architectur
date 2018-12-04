package com.example.fox.app.di.injection;


import android.content.Context;

import com.example.fox.app.data.repositories.repository.ItemRepository;
import com.example.fox.app.data.repositories.repository.RepositoryImpl;
import com.example.fox.app.di.executors.PostExecutionThread;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private Context context;

    public AppModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return context;
    }


    @Provides
    public static ItemRepository provideItemRepository(RepositoryImpl Repository) {
        return Repository;
    }

    @Singleton
    @Provides
    public static PostExecutionThread provideUIThread(UIThread uiThread) {
        return uiThread;
    }

}
