package com.dmitco.blogproject.data.preference;

import com.dmitco.blogproject.application.G;
import com.dmitco.blogproject.model.Login;
import com.dmitco.blogproject.model.ModelUserNamePassword;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Preference {

    private static final String USER_PASS = "USER_PASS";
    private static final String LOGIN = "LOGIN";


    public static void setUserPass(ModelUserNamePassword userPass) {
        try {
            if (userPass != null) {
                Gson gson   = new Gson();
                String json = gson.toJson(userPass, ModelUserNamePassword.class);
                G.sharedPref.edit().putString(USER_PASS, json).apply();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public static ModelUserNamePassword getUserPass() {
        ModelUserNamePassword model = null;
        try {
            Gson   gson   = new Gson();
            String json = G.sharedPref.getString(USER_PASS, "");
            model = gson.fromJson(json, new TypeToken<ModelUserNamePassword>() {}.getType());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return model;
    }

    public static void setLogin(Login login) {
        try {
            if (login != null) {
                Gson   gson   = new Gson();
                String json = gson.toJson(login, Login.class);
                G.sharedPref.edit().putString(LOGIN, json).apply();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public static Login getLogin() {
        Login model = null;
        try {
            Gson   gson   = new Gson();
            String json = G.sharedPref.getString(LOGIN, "");
            model = gson.fromJson(json, new TypeToken<Login>() {}.getType());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return model;
    }

    public static boolean isLogin() {
        return getUserPass() != null ;
    }

}
