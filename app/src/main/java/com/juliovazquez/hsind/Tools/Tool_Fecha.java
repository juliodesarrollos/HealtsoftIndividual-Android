package com.juliovazquez.hsind.Tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Tool_Fecha {

    static SimpleDateFormat sdfDateTime  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    static SimpleDateFormat sdfDate  = new SimpleDateFormat("yyyy-MM-dd");

    public static String getStringCurrentDate () {
        Date now = new Date();
        String strDate = sdfDate.format(now);
        return strDate;
    }

    public static Date getDateFromString (String date) {
        try {
            return sdfDate.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Date getDateTimeFromString (String date) {
        try {
            return sdfDateTime.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Date getCurrentDate () {
        return new Date();
    }

    public static Date getCurrentDateTime () {
        return getDateTimeFromString(sdfDateTime.format(new Date()));
    }
}

