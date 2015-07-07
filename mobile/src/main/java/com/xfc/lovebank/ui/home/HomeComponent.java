package com.xfc.lovebank.ui.home;

import com.xfc.lovebank.ui.base.ActivityComponent;
import com.xfc.lovebank.ui.base.ActivityModule;
import com.xfc.lovebank.ui.base.UserComponent;
import com.xfc.lovebank.utils.ActivityScope;

import dagger.Component;

/**
 * @author zhangzf
 * @since 7/7/15 5:45 PM
 */
@ActivityScope
@Component(
        dependencies = {UserComponent.class},
        modules = {ActivityModule.class,HomeModule.class}
)

public interface HomeComponent extends ActivityComponent{
    void inject(HomeActivity homeActivity);
}
