package com.example.library_base.crash;

import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Looper;


import com.example.library_base.BuildConfig;
import com.example.library_base.util.ToastUtils;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * description：app异常退出，设置是否重启App，默认不重启
 * <p>
 * author： DS.Hu
 * <p>
 * time： 2018/8/6 19:58
 * <p>
 */
public class CrashHandler implements Thread.UncaughtExceptionHandler {
    private Thread.UncaughtExceptionHandler mDefaultHandler;
    private static CrashHandler mCrashHandler;
    private Application mApplication;
    //管理activity生命周期，crash时关闭所有栈中的页面
    private CrashActivityLifecycle activityLifecycle = new CrashActivityLifecycle();
    private boolean isRestartApp;

    private CrashHandler() {

    }

    public static CrashHandler getInstance() {
        if (mCrashHandler == null) {
            synchronized (CrashHandler.class) {
                if (mCrashHandler == null) {
                    mCrashHandler = new CrashHandler();
                }
            }
        }
        return mCrashHandler;
    }

    public void init(Application application) {
        init(application, false);
    }

    public void init(Application application, boolean isRestartApp) {
        this.isRestartApp = isRestartApp;
        initCrashHandler(application);
    }

    private void initCrashHandler(Application application) {
        mApplication = application;
        mApplication.registerActivityLifecycleCallbacks(activityLifecycle);
        Thread.setDefaultUncaughtExceptionHandler(this);
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        if (!handleException(ex) && mDefaultHandler != null) {
            //未处理的异常，交于系统
            mDefaultHandler.uncaughtException(thread, ex);
        } else {
            ex.printStackTrace();
            //等待3秒，为了是给Toast提示的时间
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(BuildConfig.DEBUG) {
                int maxStackTraceSize = 131071;
                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw);
                ex.printStackTrace(pw);
                String stackTraceString = sw.toString();

                if (stackTraceString.length() > maxStackTraceSize) {
                    String disclaimer = " [stack trace too large]";
                    stackTraceString = stackTraceString.substring(0, maxStackTraceSize - disclaimer.length()) + disclaimer;
                }

                Intent intent = new Intent(mApplication, ExceptionActivity.class);
                intent.putExtra("message", stackTraceString);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                mApplication.startActivity(intent);
            }else {
                //重新启动app
                try {
                    restartApp();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //关闭所有页面并杀掉进程
                activityLifecycle.finishActivities();
            }
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(10);
        }
    }


    private boolean handleException(final Throwable ex) {
        if (ex == null) {
            return false;
        }
        //处理异常，可以在此处上报异常等
        //目前只提示即将重启
        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                ToastUtils.showToast(mApplication, "程序出现异常，即将重新启动！");
                Looper.loop();
            }
        }).start();
        return true;
    }


    private String getAppSplash(Context mContext) throws Exception {
        //获取当前App的启动页
        PackageManager packageManager = mContext.getPackageManager();
        Intent intent = packageManager.getLaunchIntentForPackage(mContext.getPackageName());
        ComponentName componentName = null;
        if (intent != null) {
            componentName = intent.getComponent();
        }
        String splashName = null;
        if (componentName != null) {
            splashName = componentName.getClassName();
        }

        return splashName;
    }

    private void restartApp() throws Exception {
        if (!isRestartApp) {
            return;
        }
        //利用系统时钟进行重启任务
        AlarmManager mgr = (AlarmManager) mApplication.getSystemService(Context.ALARM_SERVICE);
        String launchClass = getAppSplash(mApplication);
        Intent intent = new Intent();
        intent.setClassName(mApplication.getPackageName(), launchClass);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent restartIntent = PendingIntent.getActivity(mApplication, 0, intent, PendingIntent.FLAG_ONE_SHOT);
        if (mgr != null) {
            mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 1000, restartIntent); // x秒钟后重启应用
        }
    }
}
