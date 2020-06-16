package com.example.library_base.net.exception;

/**
 * description：异常Code
 * <p>
 * author： DS.Hu
 * <p>
 * time： 2018/7/22 14:17
 * <p>
 */
public class KRetrofitConstants {

    public static class Code {
        /**
         * 解析数据失败
         */
        public final static int ERROR_PARSE = -1000;
        /**
         * 网络问题
         */
        public final static int ERROR_BAD_NETWORK = -1001;
        /**
         * 连接错误
         */
        public final static int ERROR_CONNECT = -1002;
        /**
         * 连接超时
         */
        public final static int ERROR_CONNECT_TIMEOUT = -1003;
        /**
         * 未知错误
         */
        public final static int ERROR_UNKNOWN = -1004;
    }

    public static class Message {
        /**
         * 解析数据失败
         */
        public static final String MESSAGE_PARSE = "错误的数据格式";
        /**
         * 网络问题
         */
        public static final String MESSAGE_BAD_NETWORK = "网络异常，请检查您的网络状态";
        /**
         * 连接错误
         */
        public static final String MESSAGE_CONNECT = "网络连接异常，请检查您的网络状态";
        /**
         * 连接超时
         */
        public static final String MESSAGE_CONNECT_TIMEOUT = "网络连接超时，请检查您的网络状态，稍后重试";
        /**
         * 未知错误
         */
        public static final String MESSAGE_UNKNOWN = "网络异常，请检查您的网络状态";
    }
}
