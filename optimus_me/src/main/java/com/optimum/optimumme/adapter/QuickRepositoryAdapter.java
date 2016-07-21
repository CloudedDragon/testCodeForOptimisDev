package com.optimum.optimumme.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.optimum.optimumme.R;
import com.optimum.optimumme.http.gsonclass.GsonRepository;

import java.util.List;

/**
 * Created by Bruce_Lan on 2016/7/22.
 */
public class QuickRepositoryAdapter extends BaseQuickAdapter<GsonRepository> {
//    public QuickAdapter(Context context) {
//        super(context, R.layout.view_repo_item, DataServer.getSampleData(100));
//    }
//
//    public QuickAdapter(Context context, int dataSize) {
//        super(context, R.layout.tweet, DataServer.getSampleData(dataSize));
//    }


    public QuickRepositoryAdapter(Context context, List<GsonRepository> list) {
        super(context, R.layout.view_repo_item, list);
    }

    @Override
    protected void convert(BaseViewHolder helper, GsonRepository item) {

        String updateDate = "Updated on " +item.getUpdatedDate().substring(0,10);
        helper.setText(R.id.name, item.getFullName())
                .setText(R.id.description, item.getDescription())
                .setText(R.id.date, updateDate)
                .setText(R.id.lang, item.getLanguage())
                .setText(R.id.star_count_tv, item.getWatchersCount())
                .setText(R.id.fork_count_tv, item.getForksCount())
                .setOnClickListener(R.id.card_view, new OnItemChildClickListener());
    }


}