package com.example.library_user.model;

import androidx.lifecycle.MutableLiveData;

import com.example.library_base.base.model.BaseModel;

public class SecondModel extends BaseModel {
    public MutableLiveData<String> title=new MutableLiveData<>();
    {
        title.setValue("我是SecondFragment");
    }
}
