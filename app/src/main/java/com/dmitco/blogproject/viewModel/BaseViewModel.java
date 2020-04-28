package com.dmitco.blogproject.viewModel;

import android.app.Application;

import androidx.lifecycle.ViewModel;

import com.dmitco.blogproject.data.Repository;

public class BaseViewModel extends ViewModel {

    protected final Application application;
    protected final Repository repository;

    public BaseViewModel(Application application, Repository repository) {
        this.application = application;
        this.repository = repository;
    }
}
