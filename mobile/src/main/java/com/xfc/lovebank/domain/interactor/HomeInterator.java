package com.xfc.lovebank.domain.interactor;

import com.xfc.lovebank.utils.BBAppLogger;

import javax.inject.Inject;

/**
 * @author zhangzf
 * @since 7/7/15 6:40 PM
 */
public class HomeInterator extends AbstractInteractor {
    @Inject
    public HomeInterator() {
    }
    @Override
    protected String getId() {
        return "HomeInterator";
    }

    @Override
    protected void onExecute() {
        BBAppLogger.d("injected by HomeInterator:" + toString());

    }
}
