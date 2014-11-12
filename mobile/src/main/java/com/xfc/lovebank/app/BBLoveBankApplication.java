package com.xfc.lovebank.app;

import android.app.Application;
/**
 * @author zhangzf
 * @datetime 12 Nov 2014, 6:07 PM
 */
public class BBLoveBankApplication {
    private static Application __instance = null;

    public BBLoveBankApplication(Application application) {
        __instance = application;
    }

    /**
     * Singleton getter
     *
     * @return application instance
     */
    public static Application getApplication() {
        return __instance;
    }

}
