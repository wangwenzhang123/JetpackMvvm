package com.example.library_base.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by DS.Hu on 2017/8/28.
 */

class CustomToast {

    private WindowManager manger;
    private long time = 2000L;
    private static View contentView;
    private WindowManager.LayoutParams mParams;
    private static Timer timer;
    private Toast toast;
    private static Toast oldToast;
    public static final int LENGTH_SHORT = 0;
    public static final int LENGTH_LONG = 1;
    private static Handler handler;
    private CharSequence text;

    private CustomToast(Context context, CharSequence text, int duration) {
        manger = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);

        this.text = text;

        setDuration(duration);

        if (oldToast == null) {
            toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
            contentView = toast.getView();

            mParams = new WindowManager.LayoutParams();
            mParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
            mParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
            mParams.format = PixelFormat.TRANSLUCENT;
            mParams.windowAnimations = android.R.style.Animation_Toast;
//            mParams.type = WindowManager.LayoutParams.TYPE_TOAST;
            mParams.setTitle("Toast");
            mParams.flags = WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                    | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                    | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE;
            mParams.gravity = Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM;
            mParams.y = 200;
        }
        if (handler == null) {
            handler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    CustomToast.this.cancel();
                }
            };
        }
    }

    /**
     * Set the location at which the notification should appear on the screen.
     *
     * @param gravity
     * @param xOffset
     * @param yOffset
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public CustomToast setGravity(int gravity, int xOffset, int yOffset) {
        // We can resolve the Gravity here by using the Locale for getting
        // the layout direction
        final int finalGravity;
        if (Build.VERSION.SDK_INT >= 14) {
            final Configuration config = contentView.getContext().getResources().getConfiguration();
            finalGravity = Gravity.getAbsoluteGravity(gravity, config.getLayoutDirection());
        } else {
            finalGravity = gravity;
        }
        mParams.gravity = finalGravity;
        if ((finalGravity & Gravity.HORIZONTAL_GRAVITY_MASK) == Gravity.FILL_HORIZONTAL) {
            mParams.horizontalWeight = 1.0f;
        }
        if ((finalGravity & Gravity.VERTICAL_GRAVITY_MASK) == Gravity.FILL_VERTICAL) {
            mParams.verticalWeight = 1.0f;
        }
        mParams.y = yOffset;
        mParams.x = xOffset;
        return this;
    }

    public CustomToast setDuration(long durationMillis) {
        if (durationMillis < 0) {
            time = 0;
        }
        if (durationMillis == Toast.LENGTH_SHORT) {
            time = 2000;
        } else if (durationMillis == Toast.LENGTH_LONG) {
            time = 3500;
        } else {
            time = durationMillis;
        }
        return this;
    }

    public static CustomToast makeText(Context context, String text, int duration) {
        return new CustomToast(context, text, duration);
    }

    public static CustomToast makeText(Context context, int resId, int duration) {
        return makeText(context, context.getText(resId).toString(), duration);
    }

    public void show() {
        if (oldToast == null) {
            oldToast = toast;
            manger.addView(contentView, mParams);
            timer = new Timer();
        } else {
            timer.cancel();
            oldToast.setText(text);
        }
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(1);
            }
        }, time);
    }

    public void cancel() {
        try {
            manger.removeView(contentView);
        } catch (IllegalArgumentException e) {
            //这边由于上下文被销毁后removeView可能会抛出IllegalArgumentException
            //暂时这么处理，因为EToast2是轻量级的，不想和Context上下文的生命周期绑定在一块儿
            //其实如果真的想这么做，可以参考博文2的第一种实现方式，添加一个空的fragment来做生命周期绑定
        }
        timer.cancel();
        oldToast.cancel();
        timer = null;
        toast = null;
        oldToast = null;
        contentView = null;
        handler = null;
    }

    public void setText(CharSequence s) {
        toast.setText(s);
    }
}
