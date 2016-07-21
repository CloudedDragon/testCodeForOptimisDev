package com.optimum.optimumme.http.listener;

import com.optimum.optimumme.http.gsonclass.GsonCommitContainer;
import com.optimum.optimumme.http.gsonclass.GsonRepository;

/**
 * Created by Bruce_Lan on 2016/7/22.
 */
public interface GetRepositoryListListener {

    void onResult(GsonRepository[] list);
}
