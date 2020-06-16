package com.example.library_base.appContext;

import android.app.Activity;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.multidex.MultiDexApplication;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.library_base.BuildConfig;
import com.example.library_base.config.BaseUrl;
import com.example.library_base.crash.CrashHandler;
import com.example.library_base.net.client.KRetrofitConfig;
import com.example.library_base.net.client.KRetrofitFactory;
import com.example.library_base.net.interceptor.ExCookieInterceptor;


/**
 * @name Base
 * @class describe
 * @anthor 王文章
 * @time 2018/12/24 14:29
 * @change
 */
public class AppContext extends MultiDexApplication implements ViewModelStoreOwner {
    public static Application appContext;
    private ViewModelProvider.Factory mFactory;
    private ViewModelStore mAppViewModelStore;
    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        appContext=this;
        ARouter.init(this);
        mAppViewModelStore = new ViewModelStore();
        KRetrofitConfig config = new KRetrofitConfig.Builder()
                .baseUrl(BaseUrl.BASEURL)
                .retryOnConnectionFailure(false)
                .setConnectTimeout(20)
                .setReadTimeout(20)
                .setWriteTimeout(20)
                .addInterceptor(new ExCookieInterceptor())
                .build();
        KRetrofitFactory.init(config);
        CrashHandler.getInstance().init(this);
    }

    public ViewModelProvider getAppViewModelProvider(Activity activity) {
        return new ViewModelProvider((AppContext) activity.getApplicationContext(),
                ((AppContext) activity.getApplicationContext()).getAppFactory(activity));
    }

    private ViewModelProvider.Factory getAppFactory(Activity activity) {
        Application application = checkApplication(activity);
        if (mFactory == null) {
            mFactory = ViewModelProvider.AndroidViewModelFactory.getInstance(application);
        }
        return mFactory;
    }
    private Application checkApplication(Activity activity) {
        Application application = activity.getApplication();
        if (application == null) {
            throw new IllegalStateException("Your activity/fragment is not yet attached to "
                    + "Application. You can't request ViewModel before onCreate call.");
        }
        return application;
    }

    @NonNull
    @Override
    public ViewModelStore getViewModelStore() {
        return mAppViewModelStore;
    }
}
