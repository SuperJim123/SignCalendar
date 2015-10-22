package cn.wecoder.signcalendar.library;

import java.util.Calendar;
import java.util.Date;

/**
 * @Title: DateUtil
 * @Package cn.wecoder.signcalendar.library
 * @Description: 日期处理帮助类
 * @Author Jim
 * @Date 15/10/20
 * @Time 下午2:50
 * @Version
 */
public class DateUtil {

    /**
     * 比较两个日期的大小
     * @param date1 第一个日期
     * @param date2 第二个日期
     * @return  1.同一天返回0；2.之前返回－1；3.之后返回1
    */
    public static int compareDateDay(Date date1, Date date2) {
        int year1 = date1.getYear() + 1900;
        int year2 = date2.getYear() + 1900;

        int month1 = date1.getMonth();
        int month2 = date2.getMonth();

        int day1 = date1.getDate();
        int day2 = date2.getDate();

        if(year1 > year2) {
            return 1;
        }else if(year1 < year2) {
            return -1;
        }

        if(month1 > month2) {
            return 1;
        }else if(month1 < month2) {
            return -1;
        }

        if(day1 > day2) {
            return 1;
        }else if(day1 < day2) {
            return -1;
        }

        return 0;
    }

    /**
     * 计算某年某月有多少天
     * @param year 年份
     * @param month 月份 0-11
     * @return
     */
    public static int getDateNum(int year, int month) {
        Calendar time = Calendar.getInstance();
        time.clear();
        time.set(Calendar.YEAR, year + 1900);
        time.set(Calendar.MONTH, month);
        return time.getActualMaximum(Calendar.DAY_OF_MONTH);
    }


}
