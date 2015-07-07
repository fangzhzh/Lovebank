package com.xfc.lovebank.manager;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import com.xfc.lovebank.app.BBLoveBankApplication;

/**
 * @author zhangzf
 * @datetime 21 Nov 2014, 11:33 AM
 */
public class BBNotificationManager {
    NotificationManager mNotificationManger;
    private static BBNotificationManager _instance = null;
    private BBNotificationManager() {
        Context context = BBLoveBankApplication.getApplication().getApplicationContext();
        mNotificationManger = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

    }

    public static BBNotificationManager getInstance() {
        if (_instance == null) {
            _instance = new BBNotificationManager();
        }
        return _instance;
    }

    public void notify(int id, Notification notification) {
        mNotificationManger.notify(id, notification);
    }
}
