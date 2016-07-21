package com.optimum.optimumme.http.listener;

import com.optimum.optimumme.http.gsonclass.GsonCommitContainer;

/**
 * Created by Bruce_Lan on 2016/7/22.
 */
public interface GetCommitContainerListListener {

    void onResult(GsonCommitContainer[] list);
}
