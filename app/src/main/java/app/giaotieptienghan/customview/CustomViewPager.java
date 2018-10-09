package app.giaotieptienghan.customview;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class CustomViewPager extends ViewPager {
    /* renamed from: d */
    private boolean f7867d = true;

    public CustomViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return this.f7867d ? super.onInterceptTouchEvent(motionEvent) : false;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return this.f7867d ? super.onTouchEvent(motionEvent) : false;
    }

    public void setPagingEnabled(boolean z) {
        this.f7867d = z;
    }
}
