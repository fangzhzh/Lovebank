package com.xfc.lovebank.ui.base;

import com.xfc.lovebank.utils.ActivityScope;

import dagger.Component;

/**
 * @author zhangzf
 * @since 7/6/15 5:32 PM
 */
@ActivityScope
@Component(
        dependencies = UserComponent.class,
        modules = ActivityModule.class

)
public interface ActivityComponent {
}
