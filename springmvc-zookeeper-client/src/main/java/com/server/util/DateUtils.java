package com.server.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
    public static final String DATE_YYYY_MM_DD_HH_MM_SS="yyyy-MM-dd HH:mm:ss";
    public static final String DATE_YYYY_MM_DD="yyyy-MM-dd";
    public static final String DATE_HH_MM_SS="HH_MM_SS";
    public static final String DD_MM_YYYY_HH_MM_AA="dd/MM/yyyy, HH:MMaa";
    
	public static String getDate(String dateFormat){
		SimpleDateFormat format=new SimpleDateFormat(dateFormat==null?DATE_YYYY_MM_DD_HH_MM_SS:dateFormat);
		return format.format(Calendar.getInstance().getTime());
	}

    public static String formatDate(String dateFormat,long miniseconds){
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(miniseconds);
        return formatter.format(calendar.getTime());
    }
    public static String formatUTCMiniSecondsToLocalTime(String dateFormat,long utcMiniseconds){
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(utcMiniseconds*1000);
        return formatter.format(calendar.getTime());
    }
    public static long getDateMilisencods(String date){
        SimpleDateFormat format=new SimpleDateFormat(DATE_YYYY_MM_DD);
        try {
            Date tempDate=format.parse(date);
            return tempDate.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static long getDateUTCMilisencods(String date){
        SimpleDateFormat format=new SimpleDateFormat(DATE_YYYY_MM_DD);
        try {
            Date tempDate=format.parse(date);
            return tempDate.getTime()/1000;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
