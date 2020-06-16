package com.example.library_base.base.view;

import java.util.List;

/**
 * description：
 * <p>
 * author： DS.Hu
 * <p>
 * time： 2018/9/10 16:15
 * <p>
 */
public interface RequestCallback<Model> {

    /**
     * 请求开始
     */
    void onStart();

    /**
     * 请求成功
     */
    void onSuccess(List<Model> dataSources);

    /**
     * 请求失败
     */
    void onFail(String message);
}
