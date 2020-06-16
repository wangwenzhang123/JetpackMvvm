package com.example.library_user.ui;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.library_base.dialog.base.BaseDialog;
import com.example.library_base.ui.BaseListFragment;
import com.example.library_base.ui.DataBindingConfig;
import com.example.library_user.BR;
import com.example.library_user.R;
import com.example.library_user.adapter.SampleAdapter;
import com.example.library_user.bean.ImageBean;
import com.example.library_user.dialog.SampleDialog;
import com.example.library_user.model.SampleListModel;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

public class SampleListFragement extends BaseListFragment<SampleListModel> {
    @Override
    protected BaseDialog getDialog() {
        return new SampleDialog(getActivity());
    }

    @Override
    protected void initViewModel() {

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SmartRefreshLayout refreshLayout=getBinding().getRoot().findViewById(R.id.smart);
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setOnLoadMoreListener(this);
        refreshLayout.autoRefresh();
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.fragment_sample,viewModel)
                .addBindingParam(BR.adapter, new SampleAdapter(getContext()));
    }

}
