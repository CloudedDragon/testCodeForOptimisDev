package com.optimum.optimumme.fragments;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.optimum.optimumme.R;
import com.optimum.optimumme.activities.MainActivity;
import com.optimum.optimumme.activities.RepositoryListActicity;
import com.optimum.optimumme.application.MainApplication;
import com.optimum.optimumme.http.HttpManager;
import com.optimum.optimumme.http.gsonclass.GsonRepository;
import com.optimum.optimumme.http.listener.GetRepositoryListListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class GitHubFragment extends Fragment {

    private final static String TAG  = "GitHubFragment";

    private MainActivity mainActivity;
    private Toolbar toolbar;

    EditText inputRepoET;
    Button okBtn;
    String repositoryOwner;
    public GitHubFragment() {
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
        View view = inflater.inflate(R.layout.fragment_github, container, false);

        toolbar = (Toolbar) view.findViewById(R.id.toolbar);

        setupToolbar();
        inputRepoET = (EditText) view.findViewById(R.id.editText);
        okBtn = (Button) view.findViewById(R.id.okButton);

        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "inputRepoET: " + inputRepoET.getText());

                HttpManager httpManager = new HttpManager(MainApplication.getAppContext());

                repositoryOwner = inputRepoET.getText().toString().trim();

                if(repositoryOwner!=null && !repositoryOwner.equals("")){
                    httpManager.getRepostoryList(getActivity(), repositoryOwner, new GetRepositoryListListener() {
                        @Override
                        public void onResult(GsonRepository[] gsonRepositories) {


                            if(gsonRepositories== null || gsonRepositories.length ==0){
                                Toast.makeText(mainActivity, "Can't found the repositories of "+repositoryOwner, Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Intent intent = new Intent();
                                intent.setClass(mainActivity, RepositoryListActicity.class);
                                ArrayList<GsonRepository> gsonList =new ArrayList( Arrays.asList(gsonRepositories));
                                intent.putParcelableArrayListExtra("repo_list",gsonList);
                                startActivity(intent);
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.d(TAG, "error: " + error);
                            Toast.makeText(mainActivity, "Can't found the repositories of "+repositoryOwner, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mainActivity.setupNavigationDrawer(toolbar);
    }

    private void setupToolbar() {
        toolbar.setTitle(getString(R.string.github_fragment_title));
        mainActivity.setSupportActionBar(toolbar);
    }


}
