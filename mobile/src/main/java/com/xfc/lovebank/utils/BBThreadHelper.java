package com.xfc.lovebank.utils;

/**
 * @author zhangzf
 * @datetime 12 Nov 2014, 4:47 PM
 */
public class BBThreadHelper {
    public static String getThreadInfo() {
        String s = String.format("[thread_id:%d name=%s] ", Thread.currentThread().getId(),
                Thread.currentThread().getName());
        return s;
    }
}
