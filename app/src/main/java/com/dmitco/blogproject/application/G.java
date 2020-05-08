package com.dmitco.blogproject.application;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.util.Log;

import androidx.core.content.res.ResourcesCompat;
import androidx.multidex.MultiDexApplication;

import com.dmitco.blogproject.R;

public class G extends MultiDexApplication {


    public static Context context;
    public static SharedPreferences sharedPref;
    private static final String PREFS_NAME   = "DMITcoPreference";
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
        sharedPref = context.getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        iranSans_normal = ResourcesCompat.getFont(context, R.font.iransans_fanum);
        iranSans_bold = ResourcesCompat.getFont(context, R.font.iransans_fanum_bold);
        iranSans_light = ResourcesCompat.getFont(context, R.font.iransans_fanum_light);
        iranSans_medium = ResourcesCompat.getFont(context, R.font.iransans_fanum_medium);
        iranSans_ultraLight = ResourcesCompat.getFont(context, R.font.iransans_fanum_ultra_light);
    }
    public static G getInstance(){
        return mInstance;
    }
    public static void log(String message){
        log("TAG",message);
    }
    public static void log(String tag,String message){
        if (!BuildVariants.DEBUG) return;

        Log.i(tag,"##########################");
        Log.i(tag, message);
        Log.i(tag,"##########################");
    }
}
