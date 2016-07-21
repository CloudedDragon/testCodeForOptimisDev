package com.optimum.optimumme.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.optimum.optimumme.R;
import com.optimum.optimumme.http.gsonclass.GsonCommitContainer;
import com.optimum.optimumme.http.gsonclass.GsonRepository;

import java.util.List;

/**
 * Created by Bruce_Lan on 2016/7/22.
 */
public class QuickCommitAdapter extends BaseQuickAdapter<GsonCommitContainer> {

    public QuickCommitAdapter(Context context, List<GsonCommitContainer> list) {
        super(context, R.layout.view_commit_item, list);
    }

    @Override
    protected void convert(BaseViewHolder helper, GsonCommitContainer item) {
        String commitInfo = item.getGsonCommit().getGsonCommitAuthor().getName() +" committed on " +item.getGsonCommit().getGsonCommitAuthor().getDate().substring(0,10);
        helper.setText(R.id.message, item.getGsonCommit().getMessage())
                .setText(R.id.commit_info, commitInfo);
    }

}