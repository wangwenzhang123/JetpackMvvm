package com.example.library_base.util;

import android.text.TextUtils;
import android.util.Patterns;

import java.util.regex.Pattern;

/**
 * Created by Administrator on 2016/9/30.
 */

public class RegexUtils {

    /**
     * 验证身份证号码
     *
     * @param identify 待验证文本
     * @return {@code true}: 匹配<br>{@code false}: 不匹配
     */
    public static boolean isIdCard(String identify) {
        String regx = "^(\\d{15}$|^\\d{18}$|^\\d{17}(\\d|X|x))$";
        return identify.matches(regx);
    }

    /**
     * 验证邮箱
     *
     * @param email 待验证文本
     * @return {@code true}: 匹配<br>{@code false}: 不匹配
     */
    public static boolean isEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }


    public static boolean isUserName(String userName) {
        String regx = "[\\u4E00-\\u9FA5]{2,5}(?:·[\\u4E00-\\u9FA5]{2,5})*";
        return userName.matches(regx);
    }

    public static boolean validPhone(String phone) {
        return !TextUtils.isEmpty(phone) && phone.matches("^1[0-9]{10}");
    }
}
