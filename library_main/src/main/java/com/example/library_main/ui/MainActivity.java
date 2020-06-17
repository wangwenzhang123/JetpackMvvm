package com.example.library_main.ui;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.library_base.app.AppActivityKey;
import com.example.library_base.dialog.base.BaseDialog;
import com.example.library_base.ui.BaseActivity;
import com.example.library_base.ui.DataBindingConfig;
import com.example.library_main.R;
import com.example.library_main.model.MianModel;
@Route(path = AppActivityKey.MAIN_ACTIVITY)
public class MainActivity extends BaseActivity<MianModel> {
    @Override
    protected void initViewModel() {
        Bundle bundle=getIntent().getExtras();
        String title=bundle.getString("name");
        viewModel.title.setValue(title);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.activity_main1,viewModel);
    }

    @Override
    protected BaseDialog getDialog() {
        return null;
    }
}
