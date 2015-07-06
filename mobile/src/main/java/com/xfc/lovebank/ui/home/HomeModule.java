package com.xfc.lovebank.ui.home;

import com.xfc.lovebank.BTApplication;
import com.xfc.lovebank.domain.interactor.HomeInterator;

import dagger.Module;
import dagger.Provides;

/**
 * @author zhangzf
 * @since 7/7/15 5:46 PM
 */
@Module
public class HomeModule {
    @Provides
    public HomePresenter provideHomePresenter(BTApplication application, HomeInterator interator) {
        return new HomePresenter(application, interator);
    }
}
