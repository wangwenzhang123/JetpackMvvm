package com.example.library_base.util;

import android.content.Context;


/**
 * Created by DS.Hu on 2016/9/30.
 */

public class AppUtils {




    /**
     * 获取App名称
     *
     * @param context 上下文
     * @return App名称
     */
    public static String getAppName(Context context) {
        return AppInfoUtils.getAppName(context, context.getPackageName());
    }

    /**
     * 获取App版本号
     *
     * @param context 上下文
     * @return App版本号
     */
    public static String getAppVersionName(Context context) {
        return AppInfoUtils.getAppVersionName(context);
    }


    /**
     * 获取App版本码
     *
     * @param context 上下文
     * @return App版本码
     */
    public static int getAppVersionCode(Context context) {
        return AppInfoUtils.getAppVersionCode(context, context.getPackageName());
    }


}
