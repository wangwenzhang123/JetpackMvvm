package com.example.library_base.net.interceptor;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * description：处理cookie并生成加密串
 * <p>
 * author： DS.Hu
 * <p>
 * time： 2018/8/2 18:09
 * <p>
 */
public class ExCookieInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        String method = request.method();

        Map<String, String> params = new HashMap<>();
        if (method.equals("GET")) {
            HttpUrl httpUrl = request.url();
            Set<String> parameterNames = httpUrl.queryParameterNames();
            for (String key : parameterNames) {
                params.put(key, httpUrl.queryParameter(key));
            }

        } else if (method.equals("POST")) {
            RequestBody requestBody = request.body();
            if (requestBody instanceof FormBody) {
                FormBody formBody = (FormBody) requestBody;
                int size = formBody.size();
                for (int i = 0; i < size; i++) {
                    params.put(formBody.encodedName(i), formBody.encodedValue(i));
                }
            }
        }

        return chain.proceed(request);
    }
}
