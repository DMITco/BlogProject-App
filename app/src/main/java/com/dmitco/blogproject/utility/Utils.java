package com.dmitco.blogproject.utility;

import com.dmitco.blogproject.application.G;

public class Utils {

    public static String getString(int id){
        return G.getInstance().getResources().getString(id);
    }
}
