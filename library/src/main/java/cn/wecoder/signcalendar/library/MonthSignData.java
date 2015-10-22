package cn.wecoder.signcalendar.library;

import java.util.ArrayList;
import java.util.Date;

/**
 * @Title: MonthSignData
 * @Package cn.wecoder.signcalendar.library
 * @Description: 一个月签到数据
 * @Author Jim
 * @Date 15/10/20
 * @Time 下午2:50
 * @Version
 */
public class MonthSignData {
    private int mYear;
    private int mMonth;

    private ArrayList<Date> signDates;

    /**
     * 得到月签到数据的月份
     * @return 签到数据的月份
     */
    public int getMonth() {
        return mMonth;
    }

    /**
     * 设置月签到数据的月份
     */
    public void setMonth(int month) {
        mMonth = month;
    }

    /**
     * 得到月签到数据的年份
     * @return 签到数据的年份
     */
    public int getYear() {
        return mYear;
    }

    /**
     * 设置月签到数据的年份
     */
    public void setYear(int year) {
        mYear = year;
    }

    /**
     * 得到月签到数据的已经签到的日期
     */
    public ArrayList<Date> getSignDates() {
        return signDates;
    }

    /**
     * 设置月签到数据的已经签到的日期
     */
    public void setSignDates(ArrayList<Date> signDates) {
        this.signDates = signDates;
    }

    /**
     * 某一天的签到状态
     * @param date 日期
     * @return 签到状态
     */
    public boolean isDaySigned(Date date) {
        boolean result = false;
        int year = date.getYear() + 1900;
        int month = date.getMonth();

        if(year == mYear && month == mMonth) {
            for (Date mDate : signDates) {
                if(DateUtil.compareDateDay(mDate, date) == 0) {
                    return true;
                }
            }
        }
        return false;
    }
}
