package com.project.management.projectmanagement.common;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Util class for different type of operation
 *
 * @author Abdullah Al Masum
 * @version 1.0
 * @since 05-06-2020
 */
public class Util {

    /**
     * Date formatter for date without time zone.
     * The format used is <tt>mm/dd/yyyy</tt>.
     */
    public static final DateFormat DATA_FORMAT_MM_DD_YYYY = new SimpleDateFormat("MM/dd/yyyy");

    /**
     * Constructor.
     */
    private Util() {
    }

    /**
     * This method used convert string to date
     *
     * @param str String date value
     * @return date Date
     */
    public static Date strToDt(String str) {
        Date dt = null;
        try {
            if (str != null && str.length() > 7) {
                dt = DATA_FORMAT_MM_DD_YYYY.parse(str);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dt;
    }

    /**
     * This method used convert date to string
     *
     * @param dt date
     * @return string Date
     */
    public static String dtToStr(Date dt) {
        String str = null;
        if (dt != null) {
            str = DATA_FORMAT_MM_DD_YYYY.format(dt);
        }
        return str;
    }

    /**
     * Change date by adding month
     *
     * @param date     Date
     * @param addMonth Integer
     * @return date
     */
    public static Date addMonth(Date date, Integer addMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        calendar.add(Calendar.MONTH, addMonth);
        return calendar.getTime();
    }

    /**
     * get default future last date.
     *
     * @return Date
     */
    public static Date getDefaultFirstDate() {
        return Util.strToDt("01/01/2000");
    }

    /**
     * get default future last date.
     *
     * @return Date
     */
    public static Date getDefaultFutureDate() {
        return Util.strToDt("01/01/2900");
    }

}