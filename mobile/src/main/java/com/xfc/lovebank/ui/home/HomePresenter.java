package com.xfc.lovebank.ui.home;

import com.xfc.lovebank.BTApplication;
import com.xfc.lovebank.domain.interactor.HomeInterator;
import com.xfc.lovebank.utils.BBAppLogger;

import javax.inject.Inject;

/**
 * @author zhangzf
 * @since 7/7/15 5:41 PM
 */
public class HomePresenter {
    private BTApplication application;
    private HomeInterator interator;

    public HomePresenter(BTApplication application,
                         HomeInterator interator) {
        this.application = application;
        this.interator = interator;
    }

    public void printLog() {
        BBAppLogger.d("injected by presenter:" + application.toString());
        interator.execute();
    }
}
