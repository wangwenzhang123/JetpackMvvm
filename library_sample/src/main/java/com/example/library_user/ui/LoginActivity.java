package com.example.library_user.ui;

import com.example.library_base.dialog.base.BaseDialog;
import com.example.library_base.ui.BaseActivity;
import com.example.library_base.ui.DataBindingConfig;
import com.example.library_user.R;
import com.example.library_user.model.SecondModel;

public class LoginActivity extends BaseActivity<SecondModel> {

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
