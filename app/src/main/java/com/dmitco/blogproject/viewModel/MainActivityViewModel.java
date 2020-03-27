package com.dmitco.blogproject.viewModel;

import android.app.Application;

import androidx.lifecycle.ViewModel;

import com.dmitco.blogproject.data.Repository;

public class MainActivityViewModel extends BaseViewModel {

    public MainActivityViewModel(Application application, Repository repository) {
        super(application, repository);
    }
}
