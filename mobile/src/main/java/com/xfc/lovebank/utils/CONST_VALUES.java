package com.xfc.lovebank.utils;

/**
 * @author zhangzf
 * @datetime 12 Nov 2014, 4:14 PM
 */
public class CONST_VALUES {
    public interface PAGE_INDEX {
        public static final int PAGE_UTILS_INDEX = 0;
        public static final int PAGE_OVERVIEW_INDEX = PAGE_UTILS_INDEX +1;
        public static final int PAGE_STATISTICS_INDEX = PAGE_OVERVIEW_INDEX+1;
        public static final int PAGE_HISTORY_INDEX = PAGE_STATISTICS_INDEX+1;
    }

    public static final String title_identifier = "[fangzhzh daily log]";

    public interface SoftwareInfo {
        public static final String APP_PACKAGE_NAME = "com.xfc.lovebank";
        public static final String MACHINE_ID = "android"; //"android"
    }
}
