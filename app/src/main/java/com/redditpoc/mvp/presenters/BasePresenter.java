package com.redditpoc.mvp.presenters;



import com.redditpoc.mvp.view.MvpView;
import com.redditpoc.mvp.view.Presenter;

import rx.subscriptions.CompositeSubscription;

public class BasePresenter<T extends MvpView> implements Presenter<T> {

    protected T mvpView;

    protected CompositeSubscription compositeSubscription;

    @Override
    public void attachMvpView(T mvpView) {
        this.mvpView = mvpView;
        this.compositeSubscription = new CompositeSubscription();
    }

    @Override
    public void detachMvpView() {
        this.mvpView = null;
        if (this.compositeSubscription != null) this.compositeSubscription.clear();
    }
}
