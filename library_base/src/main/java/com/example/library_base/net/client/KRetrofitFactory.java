package com.example.library_base.net.client;

import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * description：网络请求初始化
 * author： DS.Hu
 * time： 2018/7/5 14:04
 */
public class KRetrofitFactory {
    private static volatile boolean hasInit = false;
    /**
     * 网络请求实例
     */
    private static Retrofit mRetrofit;

    private KRetrofitFactory() {

    }

    public static void init(KRetrofitConfig config) {
        if (!hasInit) {
            hasInit = true;
            createRetrofit(config);
        }
    }

    private static void createRetrofit(KRetrofitConfig httpConfig) {
        if (httpConfig == null) {
            throw new ExceptionInInitializerError("KRetrofitConfig must not be null!");
        }
        OkHttpClient.Builder builder = UnsafeOkHttpClient.getUnsafeOkHttpClient()
                .connectTimeout(httpConfig.getConnectTimeout(), TimeUnit.SECONDS)
                .readTimeout(httpConfig.getReadTimeout(), TimeUnit.SECONDS)
                .writeTimeout(httpConfig.getWriteTimeout(), TimeUnit.SECONDS)
                .retryOnConnectionFailure(httpConfig.isRetryOnConnectionFailure());
        for (Interceptor interceptor : httpConfig.getInterceptors()) {
            builder.addInterceptor(interceptor);
        }
//        if (BuildConfig.DEBUG) {
//            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//            builder.addInterceptor(interceptor);
//        }

        mRetrofit = new Retrofit.Builder()
                .baseUrl(httpConfig.getBaseUrl())
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setLenient().create()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(builder.build())
                .build();
    }

    /**
     * 创建请求对象
     *
     * @param serviceClass class
     * @param <S>          class对象
     * @return class对象
     */
    public static <S> S createService(Class<S> serviceClass) {
        if (mRetrofit == null) {
            throw new ExceptionInInitializerError("KRetrofitFactory must be invoke init(config) first!");
        }
        return mRetrofit.create(serviceClass);
    }
}
