package com.example.library_base.net.base;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;


import com.example.library_base.net.bean.ExBaseEntity;
import com.example.library_base.net.exception.KExceptionHandler;
import com.example.library_base.net.exception.KRetrofitException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * description：观察者基类
 * <p>
 * author： DS.Hu
 * <p>
 * time： 2018/7/22 10:54
 * <p>
 */
public abstract class ExBaseObserver<T extends ExBaseEntity> implements Observer<T> {

    @Override
    public void onSubscribe(Disposable d) {
        onStart();
    }

    @Override
    public void onNext(T t) {
        if (t.isSuccessful()) {
            onSuccess(t);
        } else if (TextUtils.equals(t.getCode(), getExpireLoginCode())) {
            onExpireLogin();
        } else {
            onCodeError(t);
        }
    }

    @Override
    public void onError(Throwable e) {
        onFailure(KExceptionHandler.handleException(e));
    }

    @Override
    public void onComplete() {
    }

    /**
     * 是否是在主线程中
     *
     * @return true 主线程
     */
    public boolean isMainThread() {
        return Looper.getMainLooper() == Looper.myLooper();
    }

    /**
     * 运行在主线程
     *
     * @param runnable runnable
     */
    public void runOnUiThread(Runnable runnable) {
        Handler mainHandler = new Handler(Looper.getMainLooper());
        mainHandler.post(runnable);
    }

    /**
     * 开始请求
     */
    public void onStart() {

    }

    /**
     * 请求成功
     *
     * @param t 数据对象
     */
    public abstract void onSuccess(T t);

    /**
     * 请求成功，但是不是成功Code
     *
     * @param t 数据对象
     */
    public abstract void onCodeError(T t);

    /**
     * 登录态失效Code
     */
    public abstract String getExpireLoginCode();

    /**
     * 请求失败，网络异常等
     *
     * @param e exception
     */
    public abstract void onFailure(KRetrofitException e);

    /**
     * 登录态失效
     */
    public abstract void onExpireLogin();
}
