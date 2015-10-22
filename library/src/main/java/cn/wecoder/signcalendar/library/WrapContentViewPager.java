package cn.wecoder.signcalendar.library;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.List;

/**
 * @Title: WrapContentViewPager
 * @Package cn.wecoder.signcalendar.library
 * @Description: 自适应高度的ViewPager
 * @Author Jim
 * @Date 15/10/20
 * @Time 下午2:50
 * @Version
 */
public class WrapContentViewPager extends ViewPager{
    private List<View> mChildViews;

    public WrapContentViewPager(Context context) {
        super(context);
    }

    public WrapContentViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setChildViews(List<View> views) {
        mChildViews = views;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int currentItem = getCurrentItem();
        //根据当前视图计算高度
        View child = mChildViews.get(currentItem);
        child.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
        int height = child.getMeasuredHeight();
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(height,
                MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
