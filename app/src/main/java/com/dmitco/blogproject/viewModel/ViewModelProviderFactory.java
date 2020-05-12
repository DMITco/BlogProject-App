package com.dmitco.blogproject.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.dmitco.blogproject.data.Repository;
import com.dmitco.blogproject.data.remote.RemoteRepository;


public class ViewModelProviderFactory extends ViewModelProvider.NewInstanceFactory {

    private static volatile ViewModelProviderFactory INSTANCE;
    private final Application application;
    private final Repository repository;

    public static ViewModelProviderFactory getInstance(Application application){
        if (INSTANCE==null){
            synchronized (ViewModelProviderFactory.class){
                if (INSTANCE==null){
                    RemoteRepository remoteRepository = RemoteRepository.getInstance();
                    Repository repository = Repository.getInstance(remoteRepository);
                    INSTANCE = new ViewModelProviderFactory(application,repository);
                }
            }
        }
        return INSTANCE;
    }


    private ViewModelProviderFactory(Application application, Repository repository){
        this.application = application;
        this.repository =  repository;
    }


    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MainActivityViewModel.class)){
            return (T) new MainActivityViewModel(application,repository);
        }else if (modelClass.isAssignableFrom(SplashViewModel.class)){
            return (T) new SplashViewModel(application,repository);
        }else if (modelClass.isAssignableFrom(HomeViewModel.class)){
            return (T) new HomeViewModel(application,repository);
        }

        throw new IllegalArgumentException("UnKnown ViewModel class :"+modelClass.getName());
    }
}
