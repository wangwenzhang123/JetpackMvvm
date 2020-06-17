package com.example.library_user.service;





import com.example.library_base.net.bean.BaseAppEntity;
import com.example.library_user.bean.ImageBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * description：
 * <p>
 * author： DS.Hu
 * <p>
 * time： 2018/7/23 19:08
 * <p>
 */
public interface SampleApi {

    @FormUrlEncoded
    @POST("picture/getBannerList")
    Observable<BaseAppEntity<List<ImageBean>>> sampleRequest(@Field("billnumber") String params);
}
