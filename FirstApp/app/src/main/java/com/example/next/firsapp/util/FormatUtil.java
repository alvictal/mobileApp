package com.example.next.firsapp.util;

import android.util.Log;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatUtil {

    private static final String TAG = FormatUtil.class.getSimpleName();

    public static final String FORMAT_STRING_TO_DATE = "yyyy-MM-dd'T'HH:mm:ss";
    public static final String FORMAT_DATE_TO_STRING = "MM/dd/yyyy 'at' HH:mm";

    public static String formatRating(Double rating) {
        DecimalFormat decimalFormat = new DecimalFormat("#.#");
        return decimalFormat.format(rating);
    }

    public static Date formatDate(String value) {
        Date date = null;
        if (value == null) {
            return date;
        }

        if (value.length() > FORMAT_STRING_TO_DATE.length()) {
            value = value.substring(0, FORMAT_STRING_TO_DATE.length());
        }

        try {
            date = new SimpleDateFormat(FORMAT_STRING_TO_DATE).parse(value);
        } catch (ParseException e) {
            Log.e(TAG, "Error parsing date " + value, e);
        }

        return date;
    }

    public static String formatDate(Date value) {
        String date = null;
        if (value == null) {
            return date;
        }
        return new SimpleDateFormat(FORMAT_DATE_TO_STRING).format(value);
    }

}
