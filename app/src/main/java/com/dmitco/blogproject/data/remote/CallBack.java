package com.dmitco.blogproject.data.remote;


public interface CallBack<T> {
    void onSuccess(T t);

    void onFail(ResponseException e);
}