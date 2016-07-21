package com.optimum.optimumme.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.optimum.optimumme.R;
import com.optimum.optimumme.adapter.QuickRepositoryAdapter;
import com.optimum.optimumme.http.gsonclass.GsonRepository;

import java.util.ArrayList;

/**
 * Created by Bruce_Lan on 2016/7/22.
 */
public class RepositoryListActicity extends AppCompatActivity {
    private final static String TAG = "RepositoryListActicity";
    private RecyclerView mRecyclerView;
    private QuickRepositoryAdapter mQuickAdapter;
    AppCompatActivity appCompatActivity;
    ArrayList<GsonRepository> gsonList ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_repo_list);
        appCompatActivity = this;
        Intent intent = this.getIntent();

        gsonList = intent.getParcelableArrayListExtra("repo_list");

        mRecyclerView = (RecyclerView) findViewById(R.id.rv_list);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        initAdapter();
    }

    private void initAdapter() {
        mQuickAdapter = new QuickRepositoryAdapter(this,gsonList);
//        mQuickAdapter.openLoadAnimation();
        mQuickAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);

        mQuickAdapter.setOnRecyclerViewItemChildClickListener(new BaseQuickAdapter.OnRecyclerViewItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                String content = null;
                GsonRepository repository = (GsonRepository) adapter.getItem(position);
                switch (view.getId()) {
                    case R.id.card_view:
                        content = "name:" + repository.getFullName();
                        Intent intent = new Intent();
                        intent.setClass(appCompatActivity, CalendarActicity.class);
                        intent.putExtra("fullname",repository.getFullName());
                        startActivity(intent);

                        Log.d(TAG,"onItemChildClick "+content);
                        break;
                }
//                Toast.makeText(AnimationUseActivity.this, content, Toast.LENGTH_LONG).show();
            }
        });
        mRecyclerView.setAdapter(mQuickAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
