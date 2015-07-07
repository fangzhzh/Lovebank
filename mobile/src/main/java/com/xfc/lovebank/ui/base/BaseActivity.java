package com.xfc.lovebank.ui.base;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.xfc.lovebank.BTApplication;

import java.util.List;


/**
 * @author zhangzf
 * @since 7/6/15 5:52 PM
 */
public abstract class BaseActivity extends ActionBarActivity {
    private UserComponent mComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initActivity(savedInstanceState);
    }

    private void initActivity(Bundle savedInstanceState) {
        mComponent = BTApplication.get().getComponent();
        onCreateComponent(mComponent);
        onCreateUI(savedInstanceState);
    }

    protected void onCreateComponent(UserComponent component) {

    }

    protected List<Object> getModules() {
        return null;
    }

    protected abstract void onCreateUI(Bundle bundle);
}
