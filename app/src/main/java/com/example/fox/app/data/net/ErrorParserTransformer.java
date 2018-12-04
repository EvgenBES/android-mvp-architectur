package com.example.fox.app.data.net;


import com.example.fox.app.data.model.Error;
import com.example.fox.app.data.model.ErrorType;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.functions.Function;
import retrofit2.HttpException;

public class ErrorParserTransformer<S> {

    public <T, E extends Throwable> ObservableTransformer<T, T> parseHttpError() {

        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {

                return upstream
                        .onErrorResumeNext(new Function<Throwable, ObservableSource<T>>() {
                            @Override
                            public ObservableSource<T> apply(Throwable throwable) throws Exception {

                                Error error;
                                if (throwable instanceof HttpException) {
                                    HttpException httpException = (HttpException) throwable;

                                    error = new Error("Error try again", ErrorType.UNEXPECTED_ERROR);
                                    try {
                                        if (httpException.code() == 404) {
                                            error = new Error("Error 404 Not Found",
                                                    ErrorType.SERVER_ERROR);
                                        } else if (httpException.response().errorBody().string().contains("valid")) {
                                            error = new Error("Error validation",
                                                    ErrorType.VALID_ERROR);
                                        }
                                    } catch (IOException e) {
                                        error = new Error("Error try again",
                                                ErrorType.VALID_ERROR);

                                    }

                                } else if (throwable instanceof SocketTimeoutException) {
                                    error = new Error("Internet is not available",
                                            ErrorType.INTERNET_IS_NOT_AVAILABLE);
                                } else if (throwable instanceof UnknownHostException) {
                                    error = new Error("Internet is not available",
                                            ErrorType.INTERNET_IS_NOT_AVAILABLE);
                                } else {
                                    error = new Error("Unexpected error",
                                            ErrorType.UNEXPECTED_ERROR);
                                }

                                return Observable.error(error);
                            }
                        });
            }
        };
    }
}
