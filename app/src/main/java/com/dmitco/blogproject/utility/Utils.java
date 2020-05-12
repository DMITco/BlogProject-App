package com.dmitco.blogproject.utility;

import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dmitco.blogproject.R;
import com.dmitco.blogproject.application.G;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class Utils {

    public static String getString(int id){
        return G.getInstance().getResources().getString(id);
    }
    public static void showSnackBar(String message,View layout, View.OnClickListener listener) {

        boolean hasAction = listener != null;
        Snackbar snackbar = Snackbar.make(layout, message, hasAction?Snackbar.LENGTH_INDEFINITE:Snackbar.LENGTH_LONG);

        View     sbView    = snackbar.getView();
        TextView txtMessage  = sbView.findViewById(R.id.snackbar_text);

        if (hasAction){
            snackbar.setAction("تلاش مجدد", listener);
            TextView txtAction = sbView.findViewById(R.id.snackbar_action);
            snackbar.setActionTextColor(G.getInstance().getResources().getColor(R.color.red));
            txtAction.setTypeface(G.iranSans_ultraLight);
        }

        txtMessage.setTypeface(G.iranSans_ultraLight);
        txtMessage.setTextColor(G.getInstance().getResources().getColor(R.color.white));
        txtMessage.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
        snackbar.show();
    }

    public static int getRandomColor(String id) {
        ArrayList<String> colorList = new ArrayList<>();
        colorList.add("#E56555");
        colorList.add("#F28C48");
        colorList.add("#5094D2");
        colorList.add("#8E85EE");
        colorList.add("#F2749A");
        colorList.add("#E58544");
        colorList.add("#76C74D");
        colorList.add("#5FBED5");
        int i = id != null ? id.hashCode() : 0;
        if (i > 0 && i < colorList.size()) {
            return Color.parseColor(colorList.get(i));
        }
        return Color.parseColor(colorList.get(Math.abs(i % colorList.size())));
    }
}
