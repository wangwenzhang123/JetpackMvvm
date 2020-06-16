package com.example.library_base.permission;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

/**
 * Created by stiven on 2017/11/14 0014.
 */

public final class PermissionActivity  extends Activity implements ActivityCompat.OnRequestPermissionsResultCallback  {

    static final String KEY_INPUT_PERMISSIONS = "KEY_INPUT_PERMISSIONS";
    static final String KEY_PERMISSION_CODE = "KEY_PERMISSION_CODE";

    private static PermissionListener sListener;

    public static void setPermissionListener(PermissionListener listener){
        sListener = listener;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String[] permissions = intent.getStringArrayExtra(KEY_INPUT_PERMISSIONS);
        int requestCode = intent.getIntExtra(KEY_PERMISSION_CODE, 0);
        if (sListener != null) {
            ActivityCompat.requestPermissions(this, permissions, requestCode);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (sListener != null){
            sListener.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
        finish();
    }

    interface PermissionListener {
        void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults);
    }
}
