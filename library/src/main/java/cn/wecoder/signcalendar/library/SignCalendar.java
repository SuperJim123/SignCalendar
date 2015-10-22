package cn.wecoder.signcalendar.library;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Title: SignCalendar
 * @Package cn.wecoder.signcalendar.library
 * @Description: 签到日历
 * @Author Jim
 * @Date 15/10/20
 * @Time 下午2:50
 * @Version
 */
public class SignCalendar extends LinearLayout {
    private Context mContext;
    private WrapContentViewPager datePager;
    private int pagerIndex;
    private int monthCount;
    private ImageView leftArrow;
    private ImageView rightArrow;
    private ArrayList<MonthSignData> mSignDatas;
    private TextView titleContent;
    private List<View> monthViews;
    private boolean showArrow = true;
    private Date today;

    public SignCalendar(Context context) {
        super(context);
    }

    public SignCalendar(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        setOrientation(LinearLayout.VERTICAL);
        initTitleView();
        addDivider();
        initWeekdays();
        initDatePager();
    }

    /**
     * 得到今日日期
     * @return
     */
    public Date getToday() {
        return today;
    }

    /**
     * 设置今日日期
     * @param today
     */
    public void setToday(Date today) {
        this.today = today;
    }

    /**
     * 初始化头部视图
     */
    private void initTitleView() {
        LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View titleContainer = mInflater.inflate(R.layout.calendar_title, null);
        titleContainer.setBackgroundColor(mContext.getResources().getColor(R.color.cal_title_background));

        titleContent = (TextView) titleContainer.findViewById(R.id.title_content);
        leftArrow = (ImageView) titleContainer.findViewById(R.id.left_arrow);
        rightArrow = (ImageView) titleContainer.findViewById(R.id.right_arrow);
        leftArrow.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                datePager.setCurrentItem(pagerIndex - 1);
            }
        });

        rightArrow.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                datePager.setCurrentItem(pagerIndex + 1);
            }
        });

        setArrowEnable(leftArrow, Direction.LEFT);
        setArrowEnable(rightArrow, Direction.RIGHT);

        titleContent.setTextColor(mContext.getResources().getColor(R.color.cal_title_text));
        titleContent.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.cal_title_text_size));

        LinearLayout.LayoutParams containerParams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        containerParams.height = (int) mContext.getResources().getDimension(R.dimen.cal_title_height);
        titleContainer.setLayoutParams(containerParams);
        addView(titleContainer);
    }

    /**
     * 初始化分割线视图
     */
    private void addDivider() {
        LinearLayout dividerContainer = new LinearLayout(getContext());
        dividerContainer.setBackgroundColor(mContext.getResources().getColor(R.color.cal_weekdays_background));

        ImageView divider = new ImageView(mContext);
        divider.setBackgroundColor(mContext.getResources().getColor(R.color.cal_divider));
        LinearLayout.LayoutParams dividerParams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        dividerParams.height = (int) mContext.getResources().getDimension(R.dimen.cal_divider_height);
        dividerParams.leftMargin = (int) mContext.getResources().getDimension(R.dimen.cal_divider_h_margin);
        dividerParams.rightMargin = (int) mContext.getResources().getDimension(R.dimen.cal_divider_h_margin);
        divider.setLayoutParams(dividerParams);

        dividerContainer.addView(divider);
        addView(dividerContainer);
    }

    /**
     * 初始化日期字符视图
     */
    private void initWeekdays() {
        LinearLayout weekdaysContainer = new LinearLayout(getContext());
        weekdaysContainer.setOrientation(LinearLayout.HORIZONTAL);
        weekdaysContainer.setBackgroundColor(mContext.getResources().getColor(R.color.cal_weekdays_background));
        LinearLayout.LayoutParams containerParams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        containerParams.height = (int) mContext.getResources().getDimension(R.dimen.cal_weekdays_height);
        containerParams.leftMargin = (int) mContext.getResources().getDimension(R.dimen.cal_weekdays_h_margin);
        containerParams.rightMargin = (int) mContext.getResources().getDimension(R.dimen.cal_weekdays_h_margin);
        weekdaysContainer.setLayoutParams(containerParams);

        String[] weekdaysStr = getResources().getStringArray(R.array.cal_weekdays);

        for (int i = 0; i < 7; i++) {
            TextView view = new TextView(getContext());
            view.setGravity(Gravity.CENTER);
            view.setText(weekdaysStr[i]);
            view.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.cal_weekdays_text_size));
            view.setTextColor(mContext.getResources().getColor(R.color.cal_weekdays_text));
            view.setLayoutParams(new LinearLayout.LayoutParams(0, -1, 1));
            weekdaysContainer.addView(view);
        }

        addView(weekdaysContainer);
    }

    /**
     * 初始化用于显示每月签到视图的View Pager
     */
    private void initDatePager() {
        LinearLayout dateContainer = new LinearLayout(mContext);
        LinearLayout.LayoutParams containerParams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        dateContainer.setLayoutParams(containerParams);
        dateContainer.setBackgroundColor(mContext.getResources().getColor(R.color.cal_pager_background));

        datePager = new WrapContentViewPager(mContext);

        LinearLayout.LayoutParams pagerParams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        pagerParams.leftMargin = (int) mContext.getResources().getDimension(R.dimen.cal_pager_h_margin);
        pagerParams.rightMargin = (int) mContext.getResources().getDimension(R.dimen.cal_pager_h_margin);
        datePager.setLayoutParams(pagerParams);
        dateContainer.addView(datePager);
        addView(dateContainer);
    }

    /**
     * 给签到日历设置签到数据
     * @param signDatas 签到数据
     */
    public void setSignDatas(ArrayList<MonthSignData> signDatas) {
        mSignDatas = signDatas;
        monthCount = signDatas.size();
        monthViews = new ArrayList<>();

        for (int i = 0; i < monthCount; i++) {
            MonthSignView monthSignView = new MonthSignView(mContext);
            monthSignView.setSignCalendar(this);
            monthSignView.setMonthSignData(signDatas.get(i));
            monthViews.add(monthSignView);
        }

        if(monthCount == 1) {
            showArrow = false;
            leftArrow.setVisibility(View.GONE);
            rightArrow.setVisibility(View.GONE);
        }

        pagerIndex = monthCount - 1;
        datePager.setChildViews(monthViews);
        datePager.setAdapter(new MyPagerAdapter(monthViews));
        datePager.setCurrentItem(pagerIndex);

        setArrowStateByPosition(pagerIndex);
        setTitleContent(pagerIndex);

        datePager.setOnPageChangeListener(new MyOnPageChangeListener());
    }

    /**
     * ViewPager适配器
     */
    private class MyPagerAdapter extends PagerAdapter {
        public List<View> mListViews;

        public MyPagerAdapter(List<View> mListViews) {
            this.mListViews = mListViews;
        }

        @Override
        public void destroyItem(View arg0, int arg1, Object arg2) {
            ((ViewPager) arg0).removeView(mListViews.get(arg1));
        }

        @Override
        public int getCount() {
            return mListViews.size();
        }

        @Override
        public Object instantiateItem(View arg0, int arg1) {
            ((ViewPager) arg0).addView(mListViews.get(arg1), 0);
            return mListViews.get(arg1);
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == (arg1);
        }
    }

    /**
     * ViewPager监听器，监听ViewPager的状态改变箭头和头部时间信息的显示
     */
    private class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            pagerIndex = position;
            setTitleContent(pagerIndex);
            setArrowStateByPosition(pagerIndex);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    private enum Direction {
        LEFT, RIGHT
    }

    /**
     * 设置箭头为可用
     */
    private void setArrowEnable(ImageView arrowImage, Direction direction) {
        if (direction == Direction.LEFT) {
            arrowImage.setClickable(true);
            Bitmap leftArrowBtp = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.cal_left_arrow);
            TintedBitmapDrawable leftArrowDrawable = new TintedBitmapDrawable(mContext.getResources(), leftArrowBtp, mContext.getResources().getColor(R.color.cal_arrow_enable));
            arrowImage.setImageDrawable(leftArrowDrawable);
        } else {
            arrowImage.setClickable(true);
            Bitmap rightArrowBtp = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.cal_right_arrow);
            TintedBitmapDrawable rightArrowDrawable = new TintedBitmapDrawable(mContext.getResources(), rightArrowBtp, mContext.getResources().getColor(R.color.cal_arrow_enable));
            arrowImage.setImageDrawable(rightArrowDrawable);
        }
    }

    /**
     * 设置箭头为不可用
     */
    private void setArrowUnable(ImageView arrowImage, Direction direction) {
        if (direction == Direction.LEFT) {
            arrowImage.setClickable(false);
            Bitmap leftArrowBtp = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.cal_left_arrow);
            TintedBitmapDrawable leftArrowDrawable = new TintedBitmapDrawable(mContext.getResources(), leftArrowBtp, mContext.getResources().getColor(R.color.cal_arrow_unable));
            arrowImage.setImageDrawable(leftArrowDrawable);
        } else {
            arrowImage.setClickable(false);
            Bitmap rightArrowBtp = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.cal_right_arrow);
            TintedBitmapDrawable rightArrowDrawable = new TintedBitmapDrawable(mContext.getResources(), rightArrowBtp, mContext.getResources().getColor(R.color.cal_arrow_unable));
            arrowImage.setImageDrawable(rightArrowDrawable);
        }
    }

    /**
     * 设置头部显示的时间信息
     */
    private void setTitleContent(int index) {
        MonthSignData monthSignData = mSignDatas.get(index);
        int year = monthSignData.getYear();
        int month = monthSignData.getMonth() + 1;

        titleContent.setText(String.format(getResources().getString(R.string.calendar_title), year, month));
    }

    private static final String INSTANCE_STATUS = "instance_status";
    private static final String STATUS_PAGER_INDEX = "status_pager_index";

    /**
     * 销毁时存储当前页面数值
     */
    @Override
    protected Parcelable onSaveInstanceState()
    {
        Bundle bundle = new Bundle();
        bundle.putParcelable(INSTANCE_STATUS, super.onSaveInstanceState());
        bundle.putInt(STATUS_PAGER_INDEX, pagerIndex);
        return bundle;
    }

    /**
     * 重建时恢复当前页面数值
     */
    @Override
    protected void onRestoreInstanceState(Parcelable state)
    {
        if (state instanceof Bundle)
        {
            Bundle bundle = (Bundle) state;
            pagerIndex = bundle.getInt(STATUS_PAGER_INDEX);
            datePager.setCurrentItem(pagerIndex);
            setTitleContent(pagerIndex);
            setArrowStateByPosition(pagerIndex);
            super.onRestoreInstanceState(bundle.getParcelable(INSTANCE_STATUS));
            return;
        }
        super.onRestoreInstanceState(state);
    }

    /**
     * 根据位置设置左右箭头状态
     */
    private void setArrowStateByPosition(int position) {
        if(showArrow) {
            if (position == 0) {
                setArrowUnable(leftArrow, Direction.LEFT);
                setArrowEnable(rightArrow, Direction.RIGHT);
            } else if (position == monthCount - 1) {
                setArrowEnable(leftArrow, Direction.LEFT);
                setArrowUnable(rightArrow, Direction.RIGHT);
            } else {
                setArrowEnable(leftArrow, Direction.LEFT);
                setArrowEnable(rightArrow, Direction.RIGHT);
            }
        }
    }
}
