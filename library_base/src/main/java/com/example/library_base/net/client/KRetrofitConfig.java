package com.example.library_base.net.client;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Interceptor;

/**
 * description：网络请求初始化
 * author： DS.Hu
 * time： 2018/7/5 14:04
 */
public class KRetrofitConfig {
    /**
     * 连接超时
     */
    private int connectTimeout;
    /**
     * 读超时
     */
    private int readTimeout;
    /**
     * 写超时
     */
    private int writeTimeout;
    /**
     * 是否重试
     */
    private boolean retryOnConnectionFailure;
    /**
     * 拦截器
     */
    private List<Interceptor> interceptors;
    /**
     * base url
     */
    private String baseUrl;

    private KRetrofitConfig() {

    }

    private KRetrofitConfig(Builder builder) {
        this.connectTimeout = builder.connectTimeout;
        this.readTimeout = builder.readTimeout;
        this.writeTimeout = builder.writeTimeout;
        this.retryOnConnectionFailure = builder.retryOnConnectionFailure;
        this.interceptors = builder.interceptors;
        this.baseUrl = builder.baseUrl;
    }

    public int getConnectTimeout() {
        return connectTimeout;
    }

    public int getReadTimeout() {
        return readTimeout;
    }

    public int getWriteTimeout() {
        return writeTimeout;
    }

    public boolean isRetryOnConnectionFailure() {
        return retryOnConnectionFailure;
    }

    public List<Interceptor> getInterceptors() {
        return interceptors;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public static class Builder {
        /**
         * 连接超时
         */
        private int connectTimeout = 5;
        /**
         * 读超时
         */
        private int readTimeout = 10;
        /**
         * 写超时
         */
        private int writeTimeout = 10;
        /**
         * 是否重试
         */
        private boolean retryOnConnectionFailure;
        /**
         * 拦截器
         */
        private List<Interceptor> interceptors = new ArrayList<>();
        /**
         * base url
         */
        private String baseUrl;

        public Builder setConnectTimeout(int connectTimeout) {
            this.connectTimeout = connectTimeout;
            return this;
        }

        public Builder setReadTimeout(int readTimeout) {
            this.readTimeout = readTimeout;
            return this;
        }

        public Builder setWriteTimeout(int writeTimeout) {
            this.writeTimeout = writeTimeout;
            return this;
        }

        public Builder retryOnConnectionFailure(boolean retryOnConnectionFailure) {
            this.retryOnConnectionFailure = retryOnConnectionFailure;
            return this;
        }

        public Builder addInterceptor(Interceptor interceptor) {
            interceptors.add(interceptor);
            return this;
        }

        public Builder baseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
            return this;
        }

        public KRetrofitConfig build() {
            return new KRetrofitConfig(this);
        }
    }
}
