package cn.wecoder.signcalendar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Date;

import cn.wecoder.signcalendar.library.MonthSignData;
import cn.wecoder.signcalendar.library.SignCalendar;

public class MainActivity extends AppCompatActivity {
    private SignCalendar signCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        signCalendar = (SignCalendar) findViewById(R.id.my_sign_calendar);
        ArrayList<MonthSignData> monthDatas = new ArrayList<>();

        MonthSignData monthData1 = new MonthSignData();
        monthData1.setYear(2015);
        monthData1.setMonth(7);
        ArrayList<Date> signDates1 = new ArrayList<>();
        Date date11= new Date(2015-1900, 7, 1);
        Date date12= new Date(2015-1900, 7, 19);
        Date date13= new Date(2015-1900, 7, 15);
        Date date14= new Date(2015-1900, 7, 16);
        signDates1.add(date11);
        signDates1.add(date12);
        signDates1.add(date13);
        signDates1.add(date14);
        monthData1.setSignDates(signDates1);
        monthDatas.add(monthData1);

        MonthSignData monthData2 = new MonthSignData();
        monthData2.setYear(2015);
        monthData2.setMonth(8);
        ArrayList<Date> signDates2 = new ArrayList<>();
        Date date21= new Date(2015-1900, 8, 1);
        Date date22= new Date(2015-1900, 8, 19);
        Date date23= new Date(2015-1900, 8, 15);
        Date date24= new Date(2015-1900, 8, 16);
        signDates2.add(date21);
        signDates2.add(date22);
        signDates2.add(date23);
        signDates2.add(date24);
        monthData2.setSignDates(signDates2);
        monthDatas.add(monthData2);

        MonthSignData monthData3 = new MonthSignData();
        monthData3.setYear(2015);
        monthData3.setMonth(9);
        ArrayList<Date> signDates3 = new ArrayList<>();
        Date date31= new Date(2015-1900, 9, 1);
        Date date32= new Date(2015-1900, 9, 19);
        Date date33= new Date(2015-1900, 9, 15);
        Date date34= new Date(2015-1900, 9, 16);
        Date date35= new Date(2015-1900, 9, 4);
        Date date36= new Date(2015-1900, 9, 10);
        signDates3.add(date31);
        signDates3.add(date32);
        signDates3.add(date33);
        signDates3.add(date34);
        signDates3.add(date35);
        signDates3.add(date36);
        monthData3.setSignDates(signDates3);
        monthDatas.add(monthData3);

        Date today = new Date(2015-1900, 9, 28);
        signCalendar.setToday(today);

        signCalendar.setSignDatas(monthDatas);
    }
}
