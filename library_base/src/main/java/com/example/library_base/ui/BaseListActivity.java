package com.example.library_base.ui;

import com.example.library_base.base.model.BaseListModel;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

public abstract class BaseListActivity<M extends BaseListModel> extends BaseActivity<M> implements OnRefreshListener, OnLoadMoreListener {
    @Override
    public void onLoadMore(RefreshLayout refreshLayout) {
        viewModel.onLoadMore(refreshLayout);
    }

    @Override
    public void onRefresh(RefreshLayout refreshLayout) {
        viewModel.onRefresh(refreshLayout);
    }
}
