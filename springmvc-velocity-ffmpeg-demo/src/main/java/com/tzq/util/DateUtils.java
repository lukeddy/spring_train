package com.tzq.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateUtils {
    public static final String DATE_YYYY_MM_DD_HH_MM_SS="yyyy-MM-dd HH:mm:ss";
    public static final String DATE_YYYY_MM_DD="yyyy-MM-dd";
    public static final String DATE_HH_MM_SS="HH_MM_SS";
    public static final String DD_MM_YYYY_HH_MM_AA="dd/MM/yyyy, HH:MMaa";
    
	public static String getDate(String dateFormat){
		SimpleDateFormat format=new SimpleDateFormat(dateFormat==null?DATE_YYYY_MM_DD_HH_MM_SS:dateFormat);
		return format.format(Calendar.getInstance().getTime());
	}
}
