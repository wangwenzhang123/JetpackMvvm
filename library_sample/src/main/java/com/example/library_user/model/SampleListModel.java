package com.example.library_user.model;

import android.util.Log;

import com.example.library_base.base.model.BaseListModel;
import com.example.library_base.base.view.RequestCallback;
import com.example.library_base.net.bean.BaseAppEntity;
import com.example.library_user.bean.ImageBean;
import com.example.library_user.data.SampleDataSource;

import java.util.List;

import io.reactivex.functions.Consumer;

public class SampleListModel extends BaseListModel<ImageBean> {
    private SampleDataSource sampleDataSource;
    public SampleListModel() {
        super();
        sampleDataSource=new SampleDataSource();
    }

    @Override
    public void onRefresh(RequestCallback<ImageBean> callback) {
        sampleDataSource.sampleRequest("1213")
                .compose(this.<BaseAppEntity<List<ImageBean>>>handleEverythingResult())
                .subscribe(new Consumer<BaseAppEntity<List<ImageBean>>>() {
                    @Override
                    public void accept(BaseAppEntity<List<ImageBean>> userBeanBaseAppEntity) throws Exception {
                        Log.e("onRefresh",userBeanBaseAppEntity.getCode()+"");
                        callback.onSuccess(userBeanBaseAppEntity.getContent());
                        Log.e("onLoadMore",mCurrentPage+"");
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e("onRefresh",throwable.getMessage()+"");
                        callback.onFail(throwable.getMessage());
                    }
                });
    }

    @Override
    public void onLoadMore(RequestCallback<ImageBean> callback) {
        sampleDataSource.sampleRequest("1213")
                .compose(this.<BaseAppEntity<List<ImageBean>>>handleEverythingResult())
                .subscribe(new Consumer<BaseAppEntity<List<ImageBean>>>() {
                    @Override
                    public void accept(BaseAppEntity<List<ImageBean>> userBeanBaseAppEntity) throws Exception {
                        //Log.e("onRefresh",userBeanBaseAppEntity.getCode()+"");
                        callback.onSuccess(userBeanBaseAppEntity.getContent());
                        Log.e("onLoadMore",mCurrentPage+"");
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e("onRefresh",throwable.getMessage()+"");
                        callback.onFail(throwable.getMessage());
                    }
                });
    }
}
