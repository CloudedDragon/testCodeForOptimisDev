package com.optimum.optimumme.http;

import android.content.Context;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.ImageLoader;
import com.optimum.optimumme.application.MainApplication;
import com.optimum.optimumme.http.gsonclass.GsonCommitContainer;
import com.optimum.optimumme.http.gsonclass.GsonRepository;
import com.optimum.optimumme.http.listener.GetCommitContainerListListener;
import com.optimum.optimumme.http.listener.GetRepositoryListListener;

import net.eocbox.volleyeocbox_lib.toolbox.GsonRequest;
import net.eocbox.volleyeocbox_lib.toolbox.VolleySingleton;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Bruce_Lan on 2016/7/22.
 */
public class HttpManager {
    private final static String TAG = "HttpManager";

    // please input your user name and token here

    private final static String USER_NAME = "test";

    private final static String USER_TOKEN = "test";

    private static Context hmContext;

    public HttpManager() {

    }

    public ImageLoader getImageLoader(Context context) {
        return MainApplication.getVolleySingleton().getInstance(context).getImageLoader();
    }

    public HttpManager(Context context) {
        this.hmContext = hmContext;
    }

    public void init(Context context) {
        this.hmContext = context;
    }


    public void getRepostoryList(Context context, String owner, GetRepositoryListListener getRepositoryListListener, Response.ErrorListener errorListener) {

        String url = "https://" + USER_NAME + ":" + USER_TOKEN + "@api.github.com/users/" + owner + "/repos";

        Log.d(TAG, "getRepostoryList url:" + url);
        GsonRequest<GsonRepository[]> request = new GsonRequest<GsonRepository[]>(Request.Method.GET,
                url,
                GsonRepository[].class,
                createGetRepositoryListListener(getRepositoryListListener),
                errorListener);
        request.setTag("getRepostoryList");


        request.setRetryPolicy(new DefaultRetryPolicy(20000, 2,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton.getInstance(context).getRequestQueue().add(request);
    }

    private Response.Listener<GsonRepository[]> createGetRepositoryListListener(
            final GetRepositoryListListener getRepositoryListListener) {

        return new Response.Listener<GsonRepository[]>() {
            @Override
            public void onResponse(GsonRepository[] response) {
                Log.d(TAG, "response :" + response.toString());
                List<GsonRepository> gsonList = Arrays.asList(response);

                for (int i = 0; i < gsonList.size(); i++) {
                    Log.d(TAG, "gsonList.get(i) :" + gsonList.get(i).toString());
                }
                if (getRepositoryListListener != null)
                    getRepositoryListListener.onResult(response);
            }
        };
    }

    public void getCommitContainerList(Context context, String fullname, GetCommitContainerListListener getCommitContainerListListener, Response.ErrorListener errorListener) {

        String url = "https://" + USER_NAME + ":" + USER_TOKEN + "@api.github.com/repos/" + fullname + "/commits";

        Log.d(TAG, "getRepostoryList url:" + url);
        GsonRequest<GsonCommitContainer[]> request = new GsonRequest<GsonCommitContainer[]>(Request.Method.GET,
                url,
                GsonCommitContainer[].class,
                createGetCommitContainerListListener(getCommitContainerListListener),
                errorListener);
        request.setTag("getCommitContainerList");
        request.setRetryPolicy(new DefaultRetryPolicy(20000, 2,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton.getInstance(context).getRequestQueue().add(request);
    }

    private Response.Listener<GsonCommitContainer[]> createGetCommitContainerListListener(
            final GetCommitContainerListListener getCommitContainerListListener) {

        return new Response.Listener<GsonCommitContainer[]>() {
            @Override
            public void onResponse(GsonCommitContainer[] response) {
                Log.d(TAG, "response :" + response.toString());
                List<GsonCommitContainer> gsonList = Arrays.asList(response);

                for (int i = 0; i < gsonList.size(); i++) {
                    Log.d(TAG, "gsonList.get(i) :" + gsonList.get(i).toString());
                }
                if (getCommitContainerListListener != null)
                    getCommitContainerListListener.onResult(response);
            }
        };
    }

}
