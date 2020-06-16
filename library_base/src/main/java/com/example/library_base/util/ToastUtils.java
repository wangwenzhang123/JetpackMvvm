package com.example.library_base.util;

import android.app.Activity;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.text.TextUtils;
import android.view.Gravity;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by DS.Hu on 2016/9/30.
 */

public class ToastUtils {
    private static Toast mToast;

    public static void showToast(Context mContext, String message, int duration) {
        if (TextUtils.isEmpty(message) || mContext == null) return;
        if (!isNotificationEnabled(mContext) && mContext instanceof Activity) {
            try {
                CustomToast.makeText(mContext, message, duration).setGravity(Gravity.BOTTOM, 0, 0).show();
            } catch (Exception e) {
            }
            return;
        }
        if (mToast == null) {
            mToast = Toast.makeText(mContext, message, duration);
        } else {
            mToast.setDuration(duration);
            mToast.setText(message);
        }
        mToast.setGravity(Gravity.BOTTOM, 0, 0);
        mToast.show();
    }

    public static void showToast(Context mContext, String message) {
        if (mContext == null) return;
        showToast(mContext, message, Toast.LENGTH_SHORT);
    }

    public static void showToast(Context mContext, int resId) {
        if (mContext == null) return;
        showToast(mContext, mContext.getString(resId));
    }

    public static void showToast(Context mContext, int resId, int duration) {
        if (mContext == null) return;
        showToast(mContext, mContext.getString(resId), duration);
    }

    public static void cancelToast() {
        if (mToast != null) {
            mToast.cancel();
            mToast = null;
        }
    }

    private static final String CHECK_OP_NO_THROW = "checkOpNoThrow";
    private static final String OP_POST_NOTIFICATION = "OP_POST_NOTIFICATION";

    /**
     * 用来判断是否开启通知权限
     */
    private static boolean isNotificationEnabled(Context context) {
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.KITKAT) {
            return true;
        }
        AppOpsManager mAppOps = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
        ApplicationInfo appInfo = context.getApplicationInfo();

        String pkg = context.getApplicationContext().getPackageName();

        int uid = appInfo.uid;

        Class appOpsClass = null; /* Context.APP_OPS_MANAGER */

        try {

            appOpsClass = Class.forName(AppOpsManager.class.getName());

            Method checkOpNoThrowMethod = appOpsClass.getMethod(CHECK_OP_NO_THROW, Integer.TYPE, Integer.TYPE, String.class);

            Field opPostNotificationValue = appOpsClass.getDeclaredField(OP_POST_NOTIFICATION);
            int value = (int) opPostNotificationValue.get(Integer.class);
            return ((int) checkOpNoThrowMethod.invoke(mAppOps, value, uid, pkg) == AppOpsManager.MODE_ALLOWED);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}
