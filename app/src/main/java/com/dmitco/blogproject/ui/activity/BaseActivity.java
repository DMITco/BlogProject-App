package com.dmitco.blogproject.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dmitco.blogproject.R;
import com.dmitco.blogproject.application.G;
import com.google.android.material.snackbar.Snackbar;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public abstract void initializeViews();
    public abstract void initialize();


    public void showSnackBar(String message, int viewGroupId, View.OnClickListener listener) {

        boolean hasAction = listener != null;
        final ViewGroup layout   = findViewById(viewGroupId);
        Snackbar snackbar = Snackbar.make(layout, message, hasAction?Snackbar.LENGTH_INDEFINITE:Snackbar.LENGTH_LONG);

        View     sbView    = snackbar.getView();
        TextView txtMessage  = sbView.findViewById(R.id.snackbar_text);

        if (hasAction){
            snackbar.setAction("تلاش مجدد", listener);
            TextView txtAction = sbView.findViewById(R.id.snackbar_action);
            snackbar.setActionTextColor(getResources().getColor(R.color.red));
            txtAction.setTypeface(G.iranSans_ultraLight);
        }

        txtMessage.setTypeface(G.iranSans_ultraLight);
        txtMessage.setTextColor(getResources().getColor(R.color.white));
        txtMessage.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
        snackbar.show();
    }


}
