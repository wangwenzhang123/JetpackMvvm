package com.example.library_base.util;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

/**
 * Created by Administrator on 2018-04-08.
 */

public class DataSelectUtils {
    public static void showDatePickerDialog(Activity activity, int themeResId, final TextView tv, Calendar calendar) {
        final DatePickerDialog picker = new DatePickerDialog(activity, null,
                calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        picker.setCancelable(true);
        picker.setCanceledOnTouchOutside(true);
        picker.setButton(DialogInterface.BUTTON_POSITIVE, "确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DatePicker datePicker = picker.getDatePicker();
                        int monthOfYear = datePicker.getMonth();
                        if (monthOfYear < 9) {
                            tv.setText(datePicker.getYear() + "0" + (monthOfYear + 1));
                        } else {
                            tv.setText(datePicker.getYear() + "" + (monthOfYear + 1));
                        }
                    }
                });
        picker.setButton(DialogInterface.BUTTON_NEGATIVE, "取消",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
        picker.show();
    }


    public static void showEveryDayListFragmentDatePickerDialog(Activity activity, int themeResId, final TextView tv, Calendar calendar, final OnCompleteClick onCompleteClick) {
        final DatePickerDialog picker = new DatePickerDialog(activity, null,
                calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        picker.setCancelable(true);
        picker.setCanceledOnTouchOutside(true);
        picker.setButton(DialogInterface.BUTTON_POSITIVE, "确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DatePicker datePicker = picker.getDatePicker();
                        int monthOfYear = datePicker.getMonth()+1;
                        int year = datePicker.getYear();
                        int dayOfMonth = datePicker.getDayOfMonth();
                        String monthOfYearStr = monthOfYear + "";
                        String dayOfMonthStr = dayOfMonth + "";
                        if (monthOfYear <= 9) {
                            monthOfYearStr = "0" + monthOfYear;
                        }
                        if (dayOfMonth <=9) {
                            dayOfMonthStr = "0" + dayOfMonth;
                        }
                        tv.setText(year + "-" + (monthOfYearStr) + "-" + dayOfMonthStr);
                        onCompleteClick.onCompleteClick();
                    }
                });
        picker.setButton(DialogInterface.BUTTON_NEGATIVE, "取消",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
        picker.show();
    }

    public interface OnCompleteClick {
        void onCompleteClick();
    }
}
