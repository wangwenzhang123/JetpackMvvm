package com.example.myjetpactdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView textView=findViewById(R.id.text);
        Button btn=findViewById(R.id.btn);
        final FristViewModel fristViewModel= new ViewModelProvider(this).get(FristViewModel.class);
        fristViewModel.liveData.observe(this,new Observer<String> (){

            @Override
            public void onChanged(String s) {
                textView.setText(s);
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fristViewModel.liveData.setValue("你是我的小苹果");
            }
        });

    }
}
