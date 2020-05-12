package com.dmitco.blogproject.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public abstract void initializeViews();
    public abstract void initialize(Bundle savedInstanceState);


}
