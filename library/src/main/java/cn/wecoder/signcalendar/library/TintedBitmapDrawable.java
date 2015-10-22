package cn.wecoder.signcalendar.library;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;

/**
 * @Title: TintedBitmapDrawable
 * @Package cn.wecoder.signcalendar.library
 * @Description: 可染色的Drawable，需要注意的是底色必须为白色
 * @Author Jim
 * @Date 15/10/20
 * @Time 下午2:50
 * @Version
 */
public final class TintedBitmapDrawable extends BitmapDrawable {
    private int tint;
    private int alpha;

    public TintedBitmapDrawable(final Resources res, final Bitmap bitmap, final int tint) {
        super(res, bitmap);
        this.tint = tint;
        this.alpha = Color.alpha(tint);
    }

    public TintedBitmapDrawable(final Resources res, final int resId, final int tint) {
        super(res, BitmapFactory.decodeResource(res, resId));
        this.tint = tint;
        this.alpha = Color.alpha(tint);
    }

    public void setTint(final int tint) {
        this.tint = tint;
        this.alpha = Color.alpha(tint);
    }

    @Override
    public void draw(final Canvas canvas) {
        final Paint paint = getPaint();
        if (paint.getColorFilter() == null) {
            paint.setColorFilter(new LightingColorFilter(tint, 0));
            paint.setAlpha(alpha);
        }
        super.draw(canvas);
    }
}