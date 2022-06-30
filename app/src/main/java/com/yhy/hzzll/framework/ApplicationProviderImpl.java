package com.yhy.hzzll.framework;

import android.app.Application;

/**
 * @author stone
 * @date 17/3/29
 */
public class ApplicationProviderImpl implements ApplicationProvider {

    private static ApplicationProviderImpl sInstance;
    private Application app;

    private ApplicationProviderImpl() {
        //no instance
    }

    public static ApplicationProviderImpl get() {
        if(sInstance == null) sInstance = new ApplicationProviderImpl();
        return sInstance;
    }

    @Override
    public void init(Application context) {
        this.app = context;
    }

    public Application getApp() {
        return app;
    }
}
