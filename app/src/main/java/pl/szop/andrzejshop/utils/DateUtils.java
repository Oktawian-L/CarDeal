package pl.szop.andrzejshop.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtils {

    public static long dateToTimestamp(Date date) {
        return date.getTime();
    }

    public static Date timestampToDate(long timestamp){
        Timestamp stamp = new Timestamp(timestamp);
        Date date = new Date(stamp.getTime());
        return date;
    }

    public static Date createDate(int day, int month, int year){
        Calendar calendar = new GregorianCalendar(year, month, day);
        return calendar.getTime();
    }

    public static long createTimestamp(int day, int month, int year){
        return dateToTimestamp(createDate(day, month, year));
    }

    public static String toString(long timestamp){
        Date date = timestampToDate(timestamp);
        return toString(date);
    }

    public static String toString(Date date){
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return dateFormat.format(date);
    }
}
