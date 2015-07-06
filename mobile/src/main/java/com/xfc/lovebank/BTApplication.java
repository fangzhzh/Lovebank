package com.xfc.lovebank;

import android.app.Application;

import com.xfc.lovebank.app.ApplicationComponent;
import com.xfc.lovebank.app.ApplicationModule;
import com.xfc.lovebank.app.DaggerApplicationComponent;
import com.xfc.lovebank.ui.base.DaggerUserComponent;
import com.xfc.lovebank.ui.base.UserComponent;

import org.androidannotations.annotations.EApplication;

/**
 * @author zhangzf
 * @datetime 12 Nov 2014, 6:29 PM
 */
@EApplication
public class BTApplication extends Application {

    private ApplicationComponent mApplicationComponent;
    private UserComponent mUserComponent;
    private static Application mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        prepareInject();
    }

    private void prepareInject() {
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();

        mUserComponent = DaggerUserComponent.builder()
                .applicationComponent(mApplicationComponent)
                .build();
        mUserComponent.inject(this);
    }

    public static BTApplication get() {
        return (BTApplication)mInstance;
    }

    public UserComponent getComponent() {
        return mUserComponent;
    }
}
