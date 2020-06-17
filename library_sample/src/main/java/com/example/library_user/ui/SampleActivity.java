package com.example.library_user.ui;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.library_base.app.AppActivityKey;
import com.example.library_base.dialog.base.BaseDialog;
import com.example.library_base.ui.BaseActivity;
import com.example.library_base.ui.DataBindingConfig;
import com.example.library_user.R;
import com.example.library_user.model.SecondModel;
@Route(path = AppActivityKey.SAMPLE_ACTIVITY)
public class SampleActivity extends BaseActivity<SecondModel> {
    @Override
    protected void initViewModel() {

    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.activity_login,viewModel);
    }

    @Override
    protected BaseDialog getDialog() {
        return null;
    }
}
