package com.dmitco.blogproject.application;

import android.content.Context;
import android.graphics.Typeface;

import androidx.multidex.MultiDexApplication;

public class G extends MultiDexApplication {


    public static Context context;
    private static G mInstance;
    public static Typeface iranSans_normal;
    public static Typeface iranSans_bold;
    public static Typeface iranSans_light;
    public static Typeface iranSans_medium;
    public static Typeface iranSans_ultraLight;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        context = getApplicationContext();
        iranSans_normal = Typeface.createFromAsset(getAssets(),"fonts/IRANSansMobile(FaNum).ttf");
        iranSans_bold = Typeface.createFromAsset(getAssets(),"fonts/IRANSansMobile(FaNum)_Bold.ttf");
        iranSans_light = Typeface.createFromAsset(getAssets(),"fonts/IRANSansMobile(FaNum)_Light.ttf");
        iranSans_medium = Typeface.createFromAsset(getAssets(),"fonts/IRANSansMobile(FaNum)_Medium.ttf");
        iranSans_ultraLight = Typeface.createFromAsset(getAssets(),"fonts/IRANSansMobile(FaNum)_UltraLight.ttf");
    }
    public static G getInstance(){
        return mInstance;
    }
}
