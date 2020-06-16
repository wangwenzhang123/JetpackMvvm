package com.example.library_base.ui;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.library_base.base.model.BaseListModel;
import com.example.library_base.dialog.base.BaseDialog;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

public abstract   class BaseListFragment<M extends BaseListModel> extends BaseFragment<M> implements OnRefreshListener, OnLoadMoreListener {
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onLoadMore(RefreshLayout refreshLayout) {
        viewModel.onLoadMore(refreshLayout);
    }

    @Override
    public void onRefresh(RefreshLayout refreshLayout) {
        viewModel.onRefresh(refreshLayout);
    }
}
