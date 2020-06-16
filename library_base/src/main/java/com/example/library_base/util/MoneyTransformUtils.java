package com.example.library_base.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class MoneyTransformUtils {
    public static int floatMoneyToInt(String str) throws NumFormatWithPointException {
        if (".".equals(str.substring(str.length() - 1, str.length()))) {
            throw new NumFormatWithPointException("最后一位不能为小数点");
        }
        if ("0".equals(str)) {
            return 0;
        }
        BigDecimal f = new BigDecimal(str);
        BigDecimal bd6 = new BigDecimal("100");
        // System.out.println("multiply:" + bd5.multiply(bd6));
        f = f.multiply(bd6);
        //(() f)
        int discountNum = f.intValue();
        return discountNum;
    }

    public static int salesFloatMoneyToInt(String str) throws NumFormatWithPointException {
        try {
            if (".".equals(str.substring(str.length() - 1, str.length()))) {
                throw new NumFormatWithPointException("最后一位不能为小数点");
            }
            if ("0".equals(str)) {
                return 0;
            }
        }catch (Exception e){
            throw new NumFormatWithPointException("非法输入");
        }
        BigDecimal bd6 = new BigDecimal("10000");
        BigDecimal f = new BigDecimal(str);
        int discountNum = f.multiply(bd6).intValue();
        return discountNum;
    }

    public static String IntMoneytoFloat(int i) {
        if (i == 0) {
            return "0";
        }
        BigDecimal b = new BigDecimal(i);
        BigDecimal discount = new BigDecimal(100);
        b = b.divide(discount);
        return String.valueOf(b);
    }

    public static String DoubleMoneytoFloat(double i) {
        if (i == 0) {
            return "0";
        }
        BigDecimal b = new BigDecimal(i);
        BigDecimal discount = new BigDecimal(100);
        b = b.divide(discount);
        b=b.setScale(2, BigDecimal.ROUND_HALF_UP);
        return String.valueOf(b);
    }

    public static String IntMoneyToString(int i){
        if (i == 0) {
            return "0.0";
        }
        BigDecimal b = new BigDecimal(i);
        BigDecimal discount = new BigDecimal(100);
        b = b.divide(discount);
        return round(Double.valueOf(String.valueOf(b)));
    }

    public static String round(double data) {
        DecimalFormat df = new DecimalFormat("#.0");
        String s = df.format(data);
        return s;
    }
    public static float IntMoneytoFloatTwo(int i) {
        if (i == 0) {
            return 0;
        }
        float num = (float) i;
        return num / 10000;
    }
    public static double IntMoneytoDoubleTwo(int i) {
        if (i == 0) {
            return 0;
        }
        double num = (double) i;
        return num / 10000;
    }
    public static int getIntCount(int i) {
        BigDecimal b = new BigDecimal(i);
        BigDecimal discount = new BigDecimal(10000);
        b = b.divide(discount);
        return b.intValue ();
    }

    public static int multiplyCount(String str) throws NumFormatWithPointException {
        if (".".equals(str.substring(str.length() - 1, str.length()))) {
            throw new NumFormatWithPointException("最后一位不能为小数点");
        }
        BigDecimal b = new BigDecimal(str);
        BigDecimal discount = new BigDecimal(10000);
        b = b.multiply (discount);
        return b.intValue ();
    }

    public static int addCount(int i) {
        BigDecimal b = new BigDecimal(i);
        BigDecimal discount = new BigDecimal(10000);
        b = b.add (discount);
        return b.intValue ();
    }

    public static int subtractCount(int i) {
        BigDecimal b = new BigDecimal(i);
        BigDecimal discount = new BigDecimal(10000);
        b = b.subtract (discount);
        return b.intValue ();
    }

    public static String getStock(String stock) {
        BigDecimal b = new BigDecimal(stock);
        BigDecimal discount = new BigDecimal(10000);
        b = b.divide(discount);
        return b.toString();
    }

}
