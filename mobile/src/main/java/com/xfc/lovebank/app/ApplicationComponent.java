package com.xfc.lovebank.app;


import com.xfc.lovebank.BTApplication;
import com.xfc.lovebank.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author zhangzf
 * @since 7/3/15 6:38 PM
 */

@Singleton
@Component(modules = ApplicationModule.class)

public interface ApplicationComponent {
    BTApplication inject(BTApplication application);

    BTApplication app();

    void inject(MainActivity activity);
}
