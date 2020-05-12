package com.dmitco.blogproject.viewModel;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dmitco.blogproject.data.Repository;
import com.dmitco.blogproject.utility.Event;

public class BaseViewModel extends ViewModel {

    protected final Application application;
    protected final Repository repository;

    protected MutableLiveData<Event<Boolean>> showProgress;
    protected MutableLiveData<Event<String>>  showMessage;

    public BaseViewModel(Application application, Repository repository) {
        this.application = application;
        this.repository = repository;
        showMessage = new MutableLiveData<>();
        showProgress = new MutableLiveData<>();
    }
    public LiveData<Event<Boolean>> getShowProgress() {
        return showProgress;
    }
    public LiveData<Event<String>> getShowMessage() {
        return showMessage;
    }

    void setShowProgressValue(boolean value){
        showProgress.setValue(new Event<>(value));
    }
    void setShowMessageValue(String value){
        showMessage.setValue(new Event<>(value));
    }

}
