package com.example.library_main.model;

import androidx.lifecycle.MutableLiveData;

import com.example.library_base.base.model.BaseModel;

public class MianModel extends BaseModel {
   public MutableLiveData<String> title=new MutableLiveData<>();
    {
        title.setValue("我是主模块");
    }
}
