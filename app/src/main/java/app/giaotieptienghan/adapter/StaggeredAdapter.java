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
public class StaggeredAdapter extends ArrayAdapter<String> {
    /* renamed from: a */
    private Context context;
    /* renamed from: b */
    private List<String> items;
    /* renamed from: c */
    private onStaggeredTextViewClick onStaggeredTextViewClick;
    /* renamed from: d */
    private String word;
    /* renamed from: e */
    private int index = -1;
    /* renamed from: f */
    private boolean shouldAnimate;

    /* renamed from: com.example.english.a.h$a */
    private class ViewHolder {
        /* renamed from: b */
        private TextView textView;

        public ViewHolder(View view) {
            this.textView = (TextView) view.findViewById(R.id.txtWord);
        }
    }

    /* renamed from: com.example.english.a.h$b */
    public interface onStaggeredTextViewClick {
        /* renamed from: a */
        void mo2846a(String str, int i);
    }

    public StaggeredAdapter(Context context, List<String> list) {
        super(context, 0, list);
        this.context = context;
        this.items = list;
    }

    /* renamed from: a */
    private void setBackground(TextView textView, Drawable drawable) {
        if (VERSION.SDK_INT >= 16) {
            textView.setBackground(drawable);
        } else {
            textView.setBackgroundDrawable(drawable);
        }
    }

    /* renamed from: a */
    private void setBackground(TextView textView, Drawable drawable, boolean z) {
        setBackground(textView, drawable);
        textView.setClickable(z);
    }

    /* renamed from: a */
    public String getItem(int i) {
        try {
            return (String) this.items.get(i);
        } catch (Exception unused) {
            return "Sample";
        }
    }

    /* renamed from: a */
    public void setOnClick(onStaggeredTextViewClick onStaggeredTextViewClick) {
        this.onStaggeredTextViewClick = onStaggeredTextViewClick;
    }

    /* renamed from: a */
    public void renderView(String str, int i, TextView textView) {
        this.word = str;
        this.index = i;
        getView(i, textView, null);
    }

    /* renamed from: a */
    public void renderViewWithAnimation(String str, int i, TextView textView, boolean z) {
        this.word = str;
        this.index = i;
        this.shouldAnimate = z;
        getView(i, textView, null);
    }

    /* renamed from: a */
    public void mo2851a(List<String> list) {
        this.items = list;
    }

    public int getCount() {
        return this.items.size();
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = LayoutInflater.from(this.context).inflate(R.layout.row_item_word, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        Drawable a = ContextCompat.getDrawable(this.context, R.drawable.selector_items);
        setBackground(viewHolder.textView, a);
        try {
            String str = (String) this.items.get(i);
            viewHolder.textView.setText(str);
            if (str.equals(this.word) && i == this.index) {
                this.word = null;
                setBackground(viewHolder.textView, a, true);
                if (this.shouldAnimate) {
                    this.shouldAnimate = false;
                    Utils.animateTextView(viewHolder.textView);
                }
            }
            viewHolder.textView.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    TextView textView = (TextView) view;
                    String charSequence = textView.getText().toString();
                    StaggeredAdapter.this.setBackground(textView, ContextCompat.getDrawable(StaggeredAdapter.this.context, R.drawable.bg_item_click), false);
                    textView.setText("");
                    if (StaggeredAdapter.this.onStaggeredTextViewClick != null) {
                        StaggeredAdapter.this.onStaggeredTextViewClick.mo2846a(charSequence, i);
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
