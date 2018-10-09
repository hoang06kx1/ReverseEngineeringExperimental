package com.riontech.staggeredtextgridview;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import java.io.PrintStream;
import app.giaotieptienghan.R;

@SuppressLint("WrongConstant")
public class StaggeredTextGridView extends ScrollView {
    /* renamed from: a */
    private BaseAdapter f7084a;
    /* renamed from: b */
    private int f7085b;
    /* renamed from: c */
    private int f7086c;
    /* renamed from: d */
    private LinearLayout f7087d;
    /* renamed from: e */
    private LinearLayout f7088e;
    /* renamed from: f */
    private Context f7089f;
    public StaggeredTextGridView(Context context) {
        super(context);
        this.f7089f = context;
        m8225c();
    }

    public StaggeredTextGridView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f7089f = context;
        m8225c();
    }

    @SuppressLint({"NewApi"})
    public StaggeredTextGridView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f7089f = context;
        m8225c();
    }

    @SuppressLint({"NewApi"})
    public StaggeredTextGridView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.f7089f = context;
        m8225c();
    }

    /* renamed from: a */
    private void m8222a(final TextView textView) {
        textView.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
            @SuppressLint({"NewApi"})
            public void onGlobalLayout() {
                int width = textView.getWidth();
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -1);
                layoutParams.width = width;
                layoutParams.gravity = 17;
                layoutParams.rightMargin = 7;
                textView.setLayoutParams(layoutParams);
                textView.setMaxLines(1);
                System.out.println("resetchild");
                if (VERSION.SDK_INT < 11) {
                    textView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                } else {
                    textView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            }
        });
    }

    /* renamed from: a */
    private void m8223a(TextView textView, int i) {
        this.f7087d.addView(textView);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(i, -1);
        layoutParams.gravity = 17;
        layoutParams.rightMargin = 7;
        textView.setLayoutParams(layoutParams);
        textView.setMaxLines(1);
        m8224b(i);
    }

    /* renamed from: b */
    private void m8224b(int i) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.f7087d.getLayoutParams();
        this.f7086c = (this.f7086c + i) + 7;
        PrintStream printStream = System.out;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("mRowWidth ");
        stringBuilder.append(this.f7086c);
        printStream.println(stringBuilder.toString());
        layoutParams.gravity = 1;
        this.f7087d.setLayoutParams(layoutParams);
    }

    /* renamed from: c */
    private void m8225c() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) this.f7089f).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        this.f7085b = (int) (((double) displayMetrics.widthPixels) * 0.8d);
        setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        this.f7088e = new LinearLayout(this.f7089f);
        this.f7088e.setOrientation(1);
        LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 17;
        this.f7088e.setLayoutParams(layoutParams);
        this.f7088e.setGravity(17);
        addView(this.f7088e);
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x008b A:{SYNTHETIC} */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0084  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* renamed from: d */
    private void m8226d() {
        for (int i = 0; i < this.f7084a.getCount(); i++) {
            int paddingEnd;
            int paddingStart;
            TextView textView = (TextView) this.f7084a.getView(i, null, this);
            PrintStream printStream = System.out;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("generateSpannableTextGridView ");
            stringBuilder.append(textView);
            printStream.println(stringBuilder.toString());
            if (VERSION.SDK_INT >= 21) {
                paddingEnd = textView.getPaddingEnd();
                paddingStart = textView.getPaddingStart();
            } else {
                paddingEnd = textView.getPaddingLeft();
                paddingStart = textView.getPaddingRight();
            }
            paddingEnd += paddingStart;
            paddingEnd = (int) (textView.getPaint().measureText((String) this.f7084a.getItem(i)) + ((float) paddingEnd));
            paddingStart = this.f7085b / 8;
            if (paddingEnd < paddingStart) {
                paddingEnd = paddingStart;
            }
            if (i != 0) {
                if (this.f7086c + paddingEnd >= this.f7085b) {
                    m8227e();
                    this.f7088e.addView(this.f7087d);
                }
                m8223a(textView, paddingEnd);
                if (i != this.f7084a.getCount() - 1) {
                    this.f7088e.addView(this.f7087d);
                }
            }
            this.f7087d = getRow();
            m8223a(textView, paddingEnd);
            if (i != this.f7084a.getCount() - 1) {
            }
        }
    }

    /* renamed from: e */
    private void m8227e() {
        for (int i = 0; i < this.f7087d.getChildCount(); i++) {
            m8222a((TextView) this.f7087d.getChildAt(i));
        }
        this.f7086c = 0;
    }

    @SuppressLint({"InflateParams"})
    private LinearLayout getRow() {
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(getContext()).inflate(R.layout.row_item_spanneble, null);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(this.f7085b, this.f7085b / 8);
        layoutParams.gravity = 17;
        layoutParams.topMargin = 7;
        linearLayout.setLayoutParams(layoutParams);
        linearLayout.setOrientation(0);
        return linearLayout;
    }

    /* renamed from: a */
    public TextView mo7083a(int i) {
        for (int i2 = 0; i2 < this.f7088e.getChildCount(); i2++) {
            LinearLayout linearLayout = (LinearLayout) this.f7088e.getChildAt(i2);
            if (i < linearLayout.getChildCount()) {
                return (TextView) linearLayout.getChildAt(i);
            }
            i -= linearLayout.getChildCount();
        }
        return null;
    }

    /* renamed from: a */
    public void mo7084a() {
        this.f7086c = 0;
        this.f7087d = null;
    }

    /* renamed from: b */
    public void mo7085b() {
        this.f7088e.removeAllViews();
    }

    public int getChildsCount() {
        return this.f7088e != null ? this.f7088e.getChildCount() + 1 : 1;
    }

    public void setmAdapter(BaseAdapter baseAdapter) {
        this.f7084a = baseAdapter;
        m8226d();
    }
}
