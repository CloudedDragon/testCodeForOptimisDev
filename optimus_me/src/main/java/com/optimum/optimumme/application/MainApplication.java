package com.optimum.optimumme.application;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import net.eocbox.volleyeocbox_lib.toolbox.VolleySingleton;

/**
 * Created by Bruce_Lan on 16/7/21.
 */
public class MainApplication extends MultiDexApplication {

    public static Context CONTEXT;
    private static Application gInstance;
    public static VolleySingleton volleySingleton;
    @Override
    public void onCreate() {
        super.onCreate();
        // for demo.
        CONTEXT = this;
        gInstance = this;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public static synchronized Application getInstance() {
        return gInstance;
    }
    public static Context getAppContext() {
        return gInstance.getApplicationContext();
    }

    public static VolleySingleton getVolleySingleton() {
        if (volleySingleton == null) {
            volleySingleton = VolleySingleton.getInstance(CONTEXT);
        }

        return volleySingleton;
    }


}
