package com.example.fox.app.data.repositories.usecases;


import com.example.fox.app.di.executors.ExecutionThread;
import com.example.fox.app.di.executors.PostExecutionThread;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

public abstract class BaseUseCase {

    protected Scheduler executionThread;
    protected Scheduler postExecutionThread;

    public BaseUseCase(Scheduler executionThread, Scheduler postExecutionThread) {
        this.executionThread = executionThread;
        this.postExecutionThread = postExecutionThread;
    }

    public BaseUseCase(ExecutionThread executionThread, PostExecutionThread postExecutionThread) {
        this.executionThread = Schedulers.from(executionThread);
        this.postExecutionThread = postExecutionThread.getScheduler();
    }

    public BaseUseCase(PostExecutionThread postExecutionThread) {
        this.executionThread = Schedulers.io();
        this.postExecutionThread = postExecutionThread.getScheduler();
    }
}
