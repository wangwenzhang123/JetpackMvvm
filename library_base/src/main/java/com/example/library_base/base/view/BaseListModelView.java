package com.example.library_base.base.view;

import com.scwang.smartrefresh.layout.api.RefreshLayout;

public interface BaseListModelView <Model>{

    /**
     * 分页数据起始页码
     *
     * @return index
     */
    int getFirstPageIndex();

    /**
     * 下拉刷新
     *
     * @param refreshLayout 刷新控件
     */
    void onRefresh(RefreshLayout refreshLayout);

    /**
     * 加载更多
     *
     * @param refreshLayout 刷新控件
     */
    void onLoadMore(RefreshLayout refreshLayout);

    /**
     * 刷新数据
     *
     * @param callback 结果回调
     */
    void onRefresh(RequestCallback<Model> callback);

    /**
     * 加载更多
     *
     * @param callback 结果回调
     */
    void onLoadMore(RequestCallback<Model> callback);
}
