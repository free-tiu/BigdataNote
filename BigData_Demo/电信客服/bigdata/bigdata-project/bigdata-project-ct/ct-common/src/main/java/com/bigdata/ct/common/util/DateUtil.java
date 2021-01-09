package com.bigdata.ct.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间工具类
 * try/catch快捷键 ctrl+atl+t
 */
public class DateUtil {

    /**
     *将指定日期按照指定的格式格式化为字符串
     * @return
     */
    public static String fomat( Date date,String format ) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return  sdf.format(date);
    }

    /**
     * 将日期字符串按照指定的格式解析为日期对象
     * @param dateString
     * @param format
     * @return
     * @throws ParseException
     */
    public static Date parse(String dateString,String format) {

        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = sdf.parse(dateString);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
