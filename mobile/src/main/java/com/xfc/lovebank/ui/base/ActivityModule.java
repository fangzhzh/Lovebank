package com.xfc.lovebank.ui.base;

import android.app.Activity;
import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * @author zhangzf
 * @since 7/6/15 5:31 PM
 */
@Module
public class ActivityModule {
    private final Context context;

    public ActivityModule(Context context) {
        this.context = context;
    }

    @Provides
    public Context provideContext() {
        return context;
    }

    @Provides
    public Activity provideActivity() {
        return (Activity)context;
    }
}
