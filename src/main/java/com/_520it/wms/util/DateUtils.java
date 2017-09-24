package com._520it.wms.util;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Helen MM on 2017/9/10.
 */
public class DateUtils {
    //处理日期的时分秒问题: 页面传的时间 时分秒都是0
    public static Date getEndDate(Date endDate){
        if(endDate == null){
            return endDate;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(endDate);
        calendar.set(Calendar.HOUR_OF_DAY,23);
        calendar.set(Calendar.MINUTE,59);
        calendar.set(Calendar.SECOND,59);
        return calendar.getTime();
    }
}
