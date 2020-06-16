package com.example.library_base.net.bean;

/**
 * description：网络响应解析基础类
 * <p>
 * author： DS.Hu
 * <p>
 * time： 2018/7/20 17:01
 * <p>
 */
public interface ExBaseEntity {
    /**
     * 成功请求
     *
     * @return boolean
     */
    boolean isSuccessful();

    /**
     * 错误信息
     *
     * @return message
     */
    String getMessage();

    /**
     * 请求返回的Code
     *
     * @return code
     */
    String getCode();

    /**
     * 是否登录过期
     */
    boolean isExpireLogin();
}
