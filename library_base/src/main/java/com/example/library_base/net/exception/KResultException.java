package com.example.library_base.net.exception;


/**
 * description：异常的统一处理
 * <p>
 * author： DS.Hu
 * <p>
 * time： 2018/7/23 18:58
 * <p>
 */

public class KResultException extends Exception {
    private String code;

    public KResultException(String code, String message) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
