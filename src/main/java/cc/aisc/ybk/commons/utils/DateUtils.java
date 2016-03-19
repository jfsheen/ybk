package cc.aisc.ybk.commons.utils;

import cc.aisc.ybk.commons.costant.Constant;
import org.joda.time.DateTime;

import java.util.Date;

/**
 * Created by sjf on 16-3-15.
 */
public abstract class DateUtils  {

    public static Date parse(String str){
        return new DateTime(str).toDate();
    }

    public static Date parse(Long num){
        return new DateTime(num).toDate();
    }

    public static String formatDate(Date date) {
        if(null != date) {
            DateTime dt = new DateTime(date);
            return dt.toString(Constant.DATE_TIME_FORMATTER);
        }
        return "date cannot be null.";
    }


}
