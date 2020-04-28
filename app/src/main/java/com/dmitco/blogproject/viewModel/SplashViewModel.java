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


    private MutableLiveData<Boolean> showProgress;
    private MutableLiveData<String> navigateToMainScreen;
    private MutableLiveData<String> navigateToDownloadPage;
    private MutableLiveData<String>  showMessage;

    public LiveData<Boolean> getShowProgress() {
        return showProgress;
    }

    public LiveData<String> getNavigateToMainScreen() {
        return navigateToMainScreen;
    }

    public LiveData<String> getNavigateToDownloadPage() {
        return navigateToDownloadPage;
    }

    public LiveData<String> getShowMessage() {
        return showMessage;
    }

    public SplashViewModel(Application application, Repository repository) {
        super(application, repository);
        showProgress = new MutableLiveData<>();
        navigateToMainScreen = new MutableLiveData<>();
        navigateToDownloadPage = new MutableLiveData<>();
        showMessage = new MutableLiveData<>();
        login();
    }

    public void login(){

        showProgress.setValue(true);
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
                showProgress.setValue(false);
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
                    showMessage.setValue(Utils.getString(R.string.defaultErrorMessage));
                }
            }

            @Override
            public void onFail(ResponseException e) {
                showProgress.setValue(false);
                String message = e.getMessage() != null ? e.getMessage() : Utils.getString(R.string.defaultErrorMessage);
                showMessage.setValue(message);
            }
        });
    }




}
