package com.ChangeBUG.utils;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;


@Component
public class TimeUtils {

    /**
     * 获取当前 时间 返回字符串
     */
    public String Get_Time() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        return dateFormat.format(date);
    }

    /**
     * 获取当前 日期 返回字符串
     */
    public String Get_Date() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }

    /**
     * Date 转 String
     */
    public String Date_Change_String(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(date);
    }

    /**
     * String 转 Date
     */
    public Date String_Change_Date(String s) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.parse(s);
    }

    /**
     * String 转 LocalDateTimeUtil
     */
    public LocalDateTime String_Change_LocalDateTimeUtil(String dateTime) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(dateTime, df);
    }

    /**
     * LocalDateTimeUtil 转 String
     */
    public String LocalDateTimeUtil_Change_String(LocalDateTime dateTime) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return df.format(dateTime);
    }

    /**
     * Date 转 LocalDateTimeUtil
     */
    public LocalDateTime Date_Change_LocalDateTimeUtil(Date dateTime) {
        return LocalDateTime.ofInstant(dateTime.toInstant(), ZoneId.systemDefault());
    }

    /**
     * Date 转 LocalDateTimeUtil
     */
    public Date LocalDateTimeUtil_Change_Date(LocalDateTime dateTime) {
        return Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

}