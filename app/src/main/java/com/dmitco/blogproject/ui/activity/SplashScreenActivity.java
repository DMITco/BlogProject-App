package com.dmitco.blogproject.ui.activity;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.QuickContactBadge;
import android.widget.TextView;

import com.dmitco.blogproject.BuildConfig;
import com.dmitco.blogproject.R;
import com.dmitco.blogproject.application.Constants;
import com.dmitco.blogproject.application.G;
import com.dmitco.blogproject.utility.Utils;
import com.dmitco.blogproject.viewModel.SplashViewModel;
import com.dmitco.blogproject.viewModel.ViewModelProviderFactory;

public class SplashScreenActivity extends BaseActivity {

    SplashViewModel viewModel;
    TextView versionName_txt;
    TextView appName_txt;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        initializeViews();
        initialize(savedInstanceState);
    }

    @Override
    public void initializeViews() {
        appName_txt = findViewById(R.id.appName);
        versionName_txt = findViewById(R.id.versionNameTextView);
        progressBar = findViewById(R.id.progressBar);
    }

    @Override
    public void initialize(Bundle savedInstanceState) {

        initViewModel();
        setFonts();

        //show version
        versionName_txt.setText(String.format("%s %s", Utils.getString(R.string.versionName), BuildConfig.VERSION_NAME));
        initObservers();

    }

    private void initObservers() {

        Observer<Boolean> showProgress = new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                float alpha = aBoolean ? 100: 0;
                progressBar.setAlpha(alpha);
            }
        };
        Observer<String> navigateToMainScreen = new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Intent intent = new Intent(SplashScreenActivity.this,MainActivity.class);
                intent.putExtra(Constants.URL,s);
                startActivity(intent);
                finish();

            }
        };
        Observer<String> navigateToDownloadPage = new Observer<String>() {
            @Override
            public void onChanged(String s) {

                //todo : create new activity and navigate to for download updates
            }
        };
        Observer<String> showMessage = new Observer<String>() {
            @Override
            public void onChanged(String s) {
                showSnackBar(s, R.id.rootElement, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        viewModel.login();
                    }
                });
            }
        };

        viewModel.getShowProgress().observe(this, showProgress);
        viewModel.getShowMessage().observe(this, showMessage);
        viewModel.getNavigateToDownloadPage().observe(this, navigateToDownloadPage);
        viewModel.getNavigateToMainScreen().observe(this, navigateToMainScreen);

    }

    void initViewModel() {
        ViewModelProviderFactory factory = ViewModelProviderFactory.getInstance(getApplication());
        viewModel = new ViewModelProvider(this, factory).get(SplashViewModel.class);
    }

    void setFonts() {
        appName_txt.setTypeface(G.iranSans_bold);
        versionName_txt.setTypeface(G.iranSans_normal);
    }


}
