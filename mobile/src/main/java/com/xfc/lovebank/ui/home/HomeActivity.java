package com.xfc.lovebank.ui.home;

import android.os.Bundle;

import com.xfc.lovebank.BTApplication;
import com.xfc.lovebank.R;
import com.xfc.lovebank.ui.base.*;
import com.xfc.lovebank.utils.BBAppLogger;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

import javax.inject.Inject;

/**
 * @author zhangzf
 * @since 7/6/15 8:01 PM
 */
@EActivity(R.layout.home_activity_layout)
public class HomeActivity extends BaseActivity {
    @Inject
    BTApplication application;

    @Inject
    HomePresenter   mPresenter;

    HomeComponent mComponent;

    @Override
    protected void onCreateUI(Bundle bundle) {

    }

    @AfterViews
    void afterView() {
        BBAppLogger.d("injected by activity:" + application.toString());
        mPresenter.printLog();
    }

    @Override
    protected void onCreateComponent(UserComponent component) {
        mComponent = DaggerHomeComponent.builder()
                .userComponent(component)
                .activityModule(new ActivityModule(this))
                .build();
        mComponent.inject(this);
    }


}
