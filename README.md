# SignCalendar
一个签到日历控件，可以设置多个月，支持左右滑动

# How to use

    //存储多个月份的签到数据
    ArrayList<MonthSignData> monthDatas = new ArrayList<>();
    //一个月份的签到数据
    MonthSignData monthData = new MonthSignData();
    Date date = new Date();
    monthData.setYear(date.getYear() + 1900);
    monthData.setMonth(date.getMonth());
    //一个月内的签到天数
    ArrayList<Date> signDates = new ArrayList<>();
    monthData.setSignDates(signDates);
    monthDatas.add(monthData);
    //给签到日历设置今天是哪一天
    signCalendar.setToday(date);
    //给签到日历设置数据
    signCalendar.setSignDatas(monthDatas);


# Snapshot

 ![image](https://github.com/SuperJim123/SignCalendar/raw/master/snapshot.jpg)
