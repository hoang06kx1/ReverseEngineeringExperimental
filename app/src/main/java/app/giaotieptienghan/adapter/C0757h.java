package app.giaotieptienghan.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;
import app.giaotieptienghan.R;
import app.giaotieptienghan.Utils;

/* renamed from: com.example.english.a.h */
public class C0757h extends ArrayAdapter<String> {
    /* renamed from: a */
    private Context f1913a;
    /* renamed from: b */
    private List<String> f1914b;
    /* renamed from: c */
    private C0756b f1915c;
    /* renamed from: d */
    private String f1916d;
    /* renamed from: e */
    private int f1917e = -1;
    /* renamed from: f */
    private boolean f1918f;

    /* renamed from: com.example.english.a.h$a */
    private class C0755a {
        /* renamed from: b */
        private TextView f1912b;

        public C0755a(View view) {
            this.f1912b = (TextView) view.findViewById(R.id.txtWord);
        }
    }

    /* renamed from: com.example.english.a.h$b */
    public interface C0756b {
        /* renamed from: a */
        void mo2846a(String str, int i);
    }

    public C0757h(Context context, List<String> list) {
        super(context, 0, list);
        this.f1913a = context;
        this.f1914b = list;
    }

    /* renamed from: a */
    private void m2947a(TextView textView, Drawable drawable) {
        if (VERSION.SDK_INT >= 16) {
            textView.setBackground(drawable);
        } else {
            textView.setBackgroundDrawable(drawable);
        }
    }

    /* renamed from: a */
    private void m2948a(TextView textView, Drawable drawable, boolean z) {
        m2947a(textView, drawable);
        textView.setClickable(z);
    }

    /* renamed from: a */
    public String getItem(int i) {
        try {
            return (String) this.f1914b.get(i);
        } catch (Exception unused) {
            return "Sample";
        }
    }

    /* renamed from: a */
    public void mo2848a(C0756b c0756b) {
        this.f1915c = c0756b;
    }

    /* renamed from: a */
    public void mo2849a(String str, int i, TextView textView) {
        this.f1916d = str;
        this.f1917e = i;
        getView(i, textView, null);
    }

    /* renamed from: a */
    public void mo2850a(String str, int i, TextView textView, boolean z) {
        this.f1916d = str;
        this.f1917e = i;
        this.f1918f = z;
        getView(i, textView, null);
    }

    /* renamed from: a */
    public void mo2851a(List<String> list) {
        this.f1914b = list;
    }

    public int getCount() {
        return this.f1914b.size();
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(final int i, View view, ViewGroup viewGroup) {
        C0755a c0755a;
        if (view == null) {
            view = LayoutInflater.from(this.f1913a).inflate(R.layout.row_item_word, null);
            c0755a = new C0755a(view);
            view.setTag(c0755a);
        } else {
            c0755a = (C0755a) view.getTag();
        }
        Drawable a = ContextCompat.getDrawable(this.f1913a, R.drawable.selector_items);
        m2947a(c0755a.f1912b, a);
        try {
            String str = (String) this.f1914b.get(i);
            c0755a.f1912b.setText(str);
            if (str.equals(this.f1916d) && i == this.f1917e) {
                this.f1916d = null;
                m2948a(c0755a.f1912b, a, true);
                if (this.f1918f) {
                    this.f1918f = false;
                    Utils.m3032a(c0755a.f1912b);
                }
            }
            c0755a.f1912b.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    TextView textView = (TextView) view;
                    String charSequence = textView.getText().toString();
                    C0757h.this.m2948a(textView, ContextCompat.getDrawable(C0757h.this.f1913a, R.drawable.bg_item_click), false);
                    textView.setText("");
                    if (C0757h.this.f1915c != null) {
                        C0757h.this.f1915c.mo2846a(charSequence, i);
                    }
                }
            });
            return view;
        } catch (Exception e) {
            e.printStackTrace();
            return view;
        }
    }
}
