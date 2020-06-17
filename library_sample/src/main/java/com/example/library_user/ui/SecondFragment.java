package com.example.library_user.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.library_base.dialog.base.BaseDialog;
import com.example.library_base.ui.BaseFragment;
import com.example.library_base.ui.DataBindingConfig;
import com.example.library_user.R;
import com.example.library_user.model.SecondModel;

public class SecondFragment extends BaseFragment<SecondModel> {
    String title;
    @Override
    protected BaseDialog getDialog() {
        return null;
    }

    @Override
    protected void initViewModel() {
        viewModel.title.setValue(title);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.fragment_second,viewModel);
    }

    @Override
    public void setArguments(@Nullable Bundle args) {
        super.setArguments(args);
        title=args.getString("name");

    }
}
