package cn.wecoder.signcalendar.library;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Date;

/**
 * @Title: DateSignView
 * @Package cn.wecoder.signcalendar.library
 * @Description: 一天签到视图
 * @Author Jim
 * @Date 15/10/20
 * @Time 下午2:50
 * @Version
 */
public class DateSignView extends LinearLayout {
    private boolean signed;
    private Date date = new Date();
    private TextView mDayView;
    private TextView mSignView;
    private Context mContext;
    private SignCalendar signCalendar;

    public DateSignView(Context context) {
        this(context, null);
    }

    public DateSignView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        setOrientation(LinearLayout.VERTICAL);
    }

    /**
     * 设置签到日历
     * @param signCalendar 签到日历
     */
    public void setSignCalendar(SignCalendar signCalendar) {
        this.signCalendar = signCalendar;
    }

    /**
     * 得到日期
     * @return 日期
     */
    public Date getDate() {
        return date;
    }

    /**
     * 得到签到状态
     * @return 签到状态
     */
    public boolean isSigned() {
        return signed;
    }

    /**
     * 设置每日签到视图的数据
     * @param date 日期
     * @param signed 签到状态
     */
    public void setSignData(Date date, boolean signed) {
        this.date = date;
        this.signed = signed;
        generateChildViews();
    }

    /**
     * 生成视图
     */
    private void generateChildViews() {
        int day = date.getDate();
        TextView dayView = new TextView(mContext);
        TextView signView = new TextView(mContext);
        dayView.setGravity(Gravity.CENTER);
        signView.setGravity(Gravity.CENTER);
        dayView.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.cal_day_text_size));
        signView.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.cal_sign_text_size));
        Date nowDate = new Date();
        if(signCalendar.getToday() != null) {
            nowDate = signCalendar.getToday();
        }
        int result = DateUtil.compareDateDay(date, nowDate);
        if(result == 1) {
            dayView.setText(day + "");
            dayView.setTextColor(mContext.getResources().getColor(R.color.cal_unreach_day));
            signView.setText(R.string.cal_unreach);
            signView.setTextColor(mContext.getResources().getColor(R.color.cal_unreach_text));
        }else {
            if(signed) {
                if(result == 0) {
                    setBackgroundResource(R.drawable.cal_today_bg);
                    dayView.setText(day + "");
                    dayView.setTextColor(mContext.getResources().getColor(R.color.cal_today_day));
                    signView.setText(R.string.cal_signed);
                    signView.setTextColor(mContext.getResources().getColor(R.color.cal_today_text));
                }else if(result == -1) {
                    setBackgroundResource(R.drawable.cal_signed_bg);
                    dayView.setText(day + "");
                    dayView.setTextColor(mContext.getResources().getColor(R.color.cal_signed_day));
                    signView.setText(R.string.cal_signed);
                    signView.setTextColor(mContext.getResources().getColor(R.color.cal_signed_text));
                }
            }else {
                if(result == 0) {
                    setBackgroundResource(R.drawable.cal_today_bg);
                    dayView.setText(day + "");
                    dayView.setTextColor(mContext.getResources().getColor(R.color.cal_today_day));
                    signView.setText(R.string.cal_today);
                    signView.setTextColor(mContext.getResources().getColor(R.color.cal_today_text));
                }else if(result == -1) {
                    dayView.setText(day + "");
                    dayView.setTextColor(mContext.getResources().getColor(R.color.cal_unsigned_day));
                    signView.setText(R.string.cal_unsign);
                    signView.setTextColor(mContext.getResources().getColor(R.color.cal_unsigned_text));
                }
            }
        }
        mDayView = dayView;
        mSignView = signView;
        addView(mDayView);
        addView(mSignView);
    }
}
