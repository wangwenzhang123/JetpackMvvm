package com.example.library_base.net.exception;

/**
 * description：异常对象
 * <p>
 * author： DS.Hu
 * <p>
 * time： 2018/7/22 14:17
 * <p>
 */
public class KRetrofitException extends Exception {

    private int code;

    public KRetrofitException(String message, int code) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
