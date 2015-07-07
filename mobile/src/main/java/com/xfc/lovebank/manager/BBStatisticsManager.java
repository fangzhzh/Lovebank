package com.xfc.lovebank.manager;

/**
 * @author zhangzf
 * @datetime 20 Nov 2014, 12:32 PM
 */
public class BBStatisticsManager {
    private int pomoSessionCnt = 0;
    private long millionBegin;
    private long millionsUntilFinish;
    private static BBStatisticsManager __instance = null;

    public static BBStatisticsManager getInstance() {
        if (__instance == null) {
            __instance = new BBStatisticsManager();
        }
        return __instance;
    }

    public void increacePomoSession() {
        ++pomoSessionCnt;
    }

    public int getPomoSessionCnt() {
        return pomoSessionCnt;
    }



    public long getMillionBegin() {
        return millionBegin;
    }

    public void setMillionBegin(long millionBegin) {
        this.millionBegin = millionBegin;
    }

    public void setMillionUntilFinish(long millionsUntilFinish) {
        this.millionsUntilFinish = millionsUntilFinish;
    }

    public long getMillionsUntilFinish() {
        return millionsUntilFinish;
    }
}
