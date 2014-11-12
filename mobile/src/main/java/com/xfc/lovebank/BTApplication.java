package com.xfc.lovebank;

import android.app.Application;
import com.xfc.lovebank.app.BBLoveBankApplication;

/**
 * @author zhangzf
 * @datetime 12 Nov 2014, 6:29 PM
 */
public class BTApplication extends Application {

    private BBLoveBankApplication application;

    @Override
    public void onCreate() {
        super.onCreate();

        application = new BBLoveBankApplication(this);
    }
}
