package com.dmitco.blogproject.ui.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dmitco.blogproject.R;


public class BaseFragment extends Fragment {

    public BaseFragment() {
        // Required empty public constructor
    }
    FragmentNavigation mfragmentNavigation;
    Context context;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        TextView textView = new TextView(getActivity());
        textView.setText(R.string.hello_blank_fragment);
        return textView;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragmentNavigation) {
             mfragmentNavigation = (FragmentNavigation) context;
        }
        this.context = context;
    }


    public interface FragmentNavigation {
        void pushFragment(Fragment fragment);

        void popFragment(int popDepth);
    }
}
