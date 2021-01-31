package cn.spring.inter.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ExchangeConstants {

    public static final String FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMAT_T = "yyyy-MM-dd'T'HH:mm:ss";
    public static final String FORMAT_Z = "EEE MMM dd HH:mm:ss Z yyyy";

    /**
     *  处理时间格式 2019-11-28T06:52:09.724+0000 为 yyyy-MM-dd HH:mm:ss
     * */
    public static String dealDateFormat(String oldDate) {
        Date date1 = null;
        DateFormat df2 = null;
        try {
            DateFormat df = new SimpleDateFormat(ExchangeConstants.FORMAT_T);
            Date date = df.parse(oldDate);
            SimpleDateFormat df1 = new SimpleDateFormat (ExchangeConstants.FORMAT_Z, Locale.UK);
            date1 = df1.parse(date.toString());
            df2 = new SimpleDateFormat(ExchangeConstants.FORMAT);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return df2.format(date1);
    }
    /**
     *  处理String格式  为 Timestamp mysql识别的日期格式
     * */
    public static Timestamp returnTimestampForTemp(Object object){
        String oString = dealDateFormat(object.toString());
        Timestamp timestamp = Timestamp.valueOf(oString);
        return  timestamp;
    }
}
