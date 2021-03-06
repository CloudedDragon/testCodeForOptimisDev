package com.optimum.optimumme.fragments;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.optimum.optimumme.R;
import com.optimum.optimumme.activities.MainActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class TermsAndConditionsFragment extends Fragment {


    private MainActivity mainActivity;
    private Toolbar toolbar;


    public TermsAndConditionsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mainActivity = (MainActivity)activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_terms, container, false);

        toolbar = (Toolbar)view.findViewById(R.id.toolbar);

        setupToolbar();

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mainActivity.setupNavigationDrawer(toolbar);
    }

    private void setupToolbar(){
        toolbar.setTitle(getString(R.string.terms_fragment_title));
        mainActivity.setSupportActionBar(toolbar);
    }


}
