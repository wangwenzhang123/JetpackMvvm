package com.example.library_base.crash;

import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

/**
 * description：
 * <p>
 * author： DS.Hu
 * <p>
 * time： 2018/8/23 12:42
 * <p>
 */
public class ExceptionActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new AlertDialog.Builder(this)
                .setMessage(getIntent().getStringExtra("message"))
                .setCancelable(false)
                .create()
                .show();
    }
}