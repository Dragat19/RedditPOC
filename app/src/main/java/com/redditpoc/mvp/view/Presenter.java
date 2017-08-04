package com.redditpoc.mvp.view;


public interface Presenter<T> {
    void attachMvpView(T mvpView);
    void detachMvpView();
}
