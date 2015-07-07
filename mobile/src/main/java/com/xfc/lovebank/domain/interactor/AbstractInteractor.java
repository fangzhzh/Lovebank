package com.xfc.lovebank.domain.interactor;

import com.xfc.lovebank.utils.BBAppLogger;

import org.androidannotations.api.BackgroundExecutor;

/**
 * @author zhangzf
 * @since 7/7/15 6:38 PM
 */
public abstract class AbstractInteractor implements Runnable {
    @Override
    public void run() {
        try {
            onExecute();
        } catch (Exception e) {
            BBAppLogger.e(e);
        }
    }

    public void execute() {
        BackgroundExecutor.execute(this, getId(), "");
    }

    protected abstract String getId();

    protected abstract void onExecute();
}
