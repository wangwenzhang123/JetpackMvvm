package com.example.library_base.net.exception;

import android.net.ParseException;

import com.google.gson.JsonParseException;

import org.json.JSONException;

import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.UnknownHostException;

import retrofit2.HttpException;

import static com.example.library_base.net.exception.KRetrofitConstants.Code;
import static com.example.library_base.net.exception.KRetrofitConstants.Code.ERROR_BAD_NETWORK;
import static com.example.library_base.net.exception.KRetrofitConstants.Code.ERROR_CONNECT;
import static com.example.library_base.net.exception.KRetrofitConstants.Code.ERROR_CONNECT_TIMEOUT;
import static com.example.library_base.net.exception.KRetrofitConstants.Code.ERROR_PARSE;
import static com.example.library_base.net.exception.KRetrofitConstants.Code.ERROR_UNKNOWN;
import static com.example.library_base.net.exception.KRetrofitConstants.Message;
import static com.example.library_base.net.exception.KRetrofitConstants.Message.MESSAGE_BAD_NETWORK;
import static com.example.library_base.net.exception.KRetrofitConstants.Message.MESSAGE_CONNECT;
import static com.example.library_base.net.exception.KRetrofitConstants.Message.MESSAGE_CONNECT_TIMEOUT;
import static com.example.library_base.net.exception.KRetrofitConstants.Message.MESSAGE_PARSE;
import static com.example.library_base.net.exception.KRetrofitConstants.Message.MESSAGE_UNKNOWN;


/**
 * description：统一处理异常信息
 * <p>
 * author： DS.Hu
 * <p>
 * time： 2018/7/22 15:29
 * <p>
 */
public class KExceptionHandler {

    /**
     * 异常处理
     *
     * @param e 异常对象
     * @return KRetrofitException
     */
    public static KRetrofitException handleException(Throwable e) {
        KRetrofitException httpException;
        if (e instanceof HttpException ) {
            httpException = new KRetrofitException(MESSAGE_BAD_NETWORK, ERROR_BAD_NETWORK);
        } else if (e instanceof ConnectException || e instanceof UnknownHostException) {
            httpException = new KRetrofitException(MESSAGE_CONNECT, ERROR_CONNECT);
        } else if (e instanceof InterruptedIOException) {
            httpException = new KRetrofitException(MESSAGE_CONNECT_TIMEOUT, ERROR_CONNECT_TIMEOUT);
        } else if (e instanceof JsonParseException || e instanceof JSONException || e instanceof ParseException) {
            httpException = new KRetrofitException(MESSAGE_PARSE, ERROR_PARSE);
        } else {
            httpException = new KRetrofitException(MESSAGE_UNKNOWN, ERROR_UNKNOWN);
        }
        return httpException;
    }
}
