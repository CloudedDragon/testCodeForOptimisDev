package com.optimum.optimumme.fragments;


import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;

import com.optimum.optimumme.R;
import com.optimum.optimumme.activities.MainActivity;

//import org.eclipse.egit.github.core.Blob;
//import org.eclipse.egit.github.core.Commit;
//import org.eclipse.egit.github.core.Reference;
//import org.eclipse.egit.github.core.Repository;
//import org.eclipse.egit.github.core.RepositoryCommit;
//import org.eclipse.egit.github.core.Tree;
//import org.eclipse.egit.github.core.TreeEntry;
//import org.eclipse.egit.github.core.TypedResource;
//import org.eclipse.egit.github.core.client.GitHubClient;
//import org.eclipse.egit.github.core.service.CommitService;
//import org.eclipse.egit.github.core.service.DataService;
//import org.eclipse.egit.github.core.service.RepositoryService;
//
//import org.eclipse.egit.github.core.*;
//import org.eclipse.egit.github.core.client.GitHubClient;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class WorkoutsFragment extends Fragment {

    private final static String TAG = "WorkoutsFragment";
    private MainActivity mainActivity;
    private Toolbar toolbar;

    public WorkoutsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mainActivity = (MainActivity) activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_workouts, container, false);

        toolbar = (Toolbar) view.findViewById(R.id.toolbar);

        setupToolbar();

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mainActivity.setupNavigationDrawer(toolbar);
    }

    private void setupToolbar() {
        toolbar.setTitle(getString(R.string.workouts_fragment_title));
        mainActivity.setSupportActionBar(toolbar);
    }


}
