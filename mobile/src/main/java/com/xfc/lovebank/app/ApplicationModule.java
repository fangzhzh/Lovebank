package com.xfc.lovebank.app;

import com.xfc.lovebank.BTApplication;
import com.xfc.lovebank.MainActivity;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author zhangzf
 * @since 7/3/15 6:37 PM
 */
@Module
public class ApplicationModule {
    private final BTApplication application;

    public ApplicationModule(BTApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    BTApplication provideApplication() {
        return this.application;
    }

}
