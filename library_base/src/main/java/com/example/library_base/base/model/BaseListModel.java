package com.example.library_base.base.model;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableInt;
import androidx.lifecycle.MutableLiveData;

import com.example.library_base.base.view.BaseListModelView;
import com.example.library_base.base.view.RequestCallback;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import java.util.List;

public abstract class BaseListModel<T>extends BaseModel implements BaseListModelView <T>{
    public int mCurrentPage;
    ObservableBoolean isFinshRefrash=new ObservableBoolean();
    ObservableBoolean isShowEmpty=new ObservableBoolean();
    {
        isFinshRefrash.set(false);
        isShowEmpty.set(false);
    }
    public MutableLiveData<List<T>> list=new MutableLiveData<>();

    public int getFirstPageIndex(){
        return 1;
    }

    @Override
    public void onRefresh(RefreshLayout refreshLayout) {
        onLoadData(refreshLayout,true,true);
    }

    @Override
    public void onLoadMore(RefreshLayout refreshLayout) {
        onLoadData(refreshLayout,false,false);
    }
    class Callback implements RequestCallback<T> {
        private RefreshLayout refreshLayout;
        private boolean isRefresh;
        private boolean isFirstLoad;

        Callback(boolean isRefresh) {
            this.isRefresh = isRefresh;
        }

        Callback(boolean isRefresh, boolean isFirstLoad) {
            this.isRefresh = isRefresh;
            this.isFirstLoad = isFirstLoad;
        }

        public Callback(RefreshLayout refreshLayout, boolean isRefresh, boolean isFirstLoad) {
            this.refreshLayout = refreshLayout;
            this.isRefresh = isRefresh;
            this.isFirstLoad = isFirstLoad;
        }

        @Override
        public void onStart() {
        }

        @Override
        public void onSuccess(List<T> dataSources) {
            isFirstLoad = false;
            List<T> tList=list.getValue();
            if (isRefresh) {
                refreshLayout.finishRefresh();
                list.setValue(dataSources);
            } else {
                if (dataSources != null && tList!= null){
                    tList.addAll(dataSources);
                    list.setValue(tList);
                    refreshLayout.finishLoadMore();
                }
            }
            boolean isEmptyResult = dataSources == null || dataSources.size() == 0;
            if (isEmptyResult && mCurrentPage > getFirstPageIndex()) {
                mCurrentPage--;
                refreshLayout.finishLoadMoreWithNoMoreData();
            }

        }

        @Override
        public void onFail(String message) {
            isFirstLoad = false;
            if (isRefresh) {
                refreshLayout.finishRefresh();
            } else {
                refreshLayout.finishLoadMore();
            }
            if (mCurrentPage > getFirstPageIndex()) {
                mCurrentPage--;
            }
        }
    }
    private void onLoadData(RefreshLayout refreshLayout, boolean isRefresh, boolean isFirstLoad) {
        if (isRefresh && isFirstLoad) {
            isShowLoadingData.setValue(true);
        }
        Callback callback = new Callback(refreshLayout, isRefresh, isFirstLoad);
        if (isRefresh) {
            mCurrentPage = getFirstPageIndex();
            refreshLayout.setNoMoreData(false);
            onRefresh(callback);
        } else {
            mCurrentPage++;
            onLoadMore(callback);
        }
    }
}
