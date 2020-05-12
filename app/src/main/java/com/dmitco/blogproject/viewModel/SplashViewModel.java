package com.dmitco.blogproject.viewModel;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.dmitco.blogproject.BuildConfig;
import com.dmitco.blogproject.R;
import com.dmitco.blogproject.data.Repository;
import com.dmitco.blogproject.data.preference.Preference;
import com.dmitco.blogproject.data.remote.CallBack;
import com.dmitco.blogproject.data.remote.ResponseException;
import com.dmitco.blogproject.model.Login;
import com.dmitco.blogproject.model.ModelUserNamePassword;
import com.dmitco.blogproject.model.Version;
import com.dmitco.blogproject.utility.Utils;

public class SplashViewModel extends BaseViewModel {

    private MutableLiveData<String> navigateToMainScreen;
    private MutableLiveData<String> navigateToDownloadPage;

    public LiveData<String> getNavigateToMainScreen() {
        return navigateToMainScreen;
    }
    public LiveData<String> getNavigateToDownloadPage() {
        return navigateToDownloadPage;
    }


    public SplashViewModel(Application application, Repository repository) {
        super(application, repository);

        navigateToMainScreen = new MutableLiveData<>();
        navigateToDownloadPage = new MutableLiveData<>();

        login();
    }

    public void login(){

        setShowProgressValue(true);
        String userName = "";
        String password = "";
        ModelUserNamePassword userPass = Preference.getUserPass();
        if (userPass!= null){
            userName = userPass.getUserName();
            password = userPass.getPassword();
        }

        repository.login(userName, password, new CallBack<Login>() {
            @Override
            public void onSuccess(Login login) {
                setShowProgressValue(false);
                if (login!=null && login.getVersion()!= null){
                    Preference.setLogin(login);
                    Version version = login.getVersion();
                    if ( (version.getVersionCode() > BuildConfig.VERSION_CODE ) ){
                        if (version.isImportant()){
                           navigateToDownloadPage.setValue(version.getUrlVersion());
                        }else{
                            navigateToMainScreen.setValue(version.getUrlVersion());
                        }
                    }else {
                       navigateToMainScreen.setValue(null);
                    }
                }else {
                    setShowMessageValue(Utils.getString(R.string.defaultErrorMessage));
                }
            }

            @Override
            public void onFail(ResponseException e) {
                setShowProgressValue(false);
                String message = e.getMessage() != null ? e.getMessage() : Utils.getString(R.string.defaultErrorMessage);
                setShowMessageValue(message);
            }
        });
    }




}
