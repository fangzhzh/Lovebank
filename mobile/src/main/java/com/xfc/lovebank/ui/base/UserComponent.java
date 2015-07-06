package com.xfc.lovebank.ui.base;

import com.xfc.lovebank.app.ApplicationComponent;

import dagger.Component;

/**
 * @author zhangzf
 * @since 7/6/15 5:53 PM
 */
@UserScope
@Component(
        dependencies = ApplicationComponent.class,
        modules = UserModule.class
    )
public interface UserComponent extends ApplicationComponent {
}
