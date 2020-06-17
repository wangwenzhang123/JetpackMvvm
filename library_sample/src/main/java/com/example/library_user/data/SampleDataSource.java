package com.example.library_user.data;



import com.example.library_base.net.bean.BaseAppEntity;
import com.example.library_base.net.client.KRetrofitFactory;
import com.example.library_user.bean.ImageBean;
import com.example.library_user.service.SampleApi;

import java.util.List;

import io.reactivex.Observable;

;


/**
 * description：
 * <p>
 * author： DS.Hu
 * <p>
 * time： 2018/7/23 19:07
 * <p>
 */
public class SampleDataSource {

    public Observable<BaseAppEntity<List<ImageBean>>> sampleRequest(String code) {
        return KRetrofitFactory.createService(SampleApi.class)
                .sampleRequest(code);
    }

}
