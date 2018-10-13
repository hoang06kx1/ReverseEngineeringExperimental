package app.giaotieptienghan.customview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.riontech.staggeredtextgridview.StaggeredTextGridView;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.apmem.tools.layouts.FlowLayout;

import app.giaotieptienghan.Utils;
import app.giaotieptienghan.adapter.StaggeredAdapter;
import app.giaotieptienghan.model.QuizAdapter;
import app.giaotieptienghan.model.QuizAdapter.C0753a;
import app.giaotieptienghan.model.PhraseItem;
import app.giaotieptienghan.adapter.StaggeredAdapter.onStaggeredTextViewClick;
import app.giaotieptienghan.repository.AppPreference;
import app.giaotieptienghan.R;

/* renamed from: com.example.english.customview.c */
@SuppressLint("WrongConstant")
public class QuizTypeTwoView extends LinearLayout implements OnClickListener, onStaggeredTextViewClick {
    /* renamed from: a */
    private TextView f7874a;
    /* renamed from: b */
    private TextView f7875b;
    /* renamed from: c */
    private TextView f7876c;
    /* renamed from: d */
    private TextView f7877d;
    /* renamed from: e */
    private ImageView f7878e;
    /* renamed from: f */
    private String f7879f = null;
    /* renamed from: g */
    private FlowLayout f7880g;
    /* renamed from: h */
    private EditText f7881h;
    /* renamed from: i */
    private LinearLayout f7882i;
    /* renamed from: j */
    private StaggeredTextGridView staggeredTextGridView;
    /* renamed from: k */
    private StaggeredAdapter staggeredAdapter;
    /* renamed from: l */
    private QuizAdapter.C0753a f7885l;
    /* renamed from: m */
    private int f7886m = 0;
    /* renamed from: n */
    private List<String> f7887n = new ArrayList();
    /* renamed from: o */
    private ArrayList<String> f7888o = new ArrayList();
    /* renamed from: p */
    private ArrayList<String> f7889p = new ArrayList();
    /* renamed from: q */
    private List<Integer> f7890q = new ArrayList();
    /* renamed from: r */
    private AppPreference f7891r;
    /* renamed from: s */
    private PhraseItem f7892s;
    /* renamed from: t */
    private StringBuilder f7893t = new StringBuilder();
    /* renamed from: u */
    private boolean f7894u;

    public QuizTypeTwoView(Context context) {
        super(context);
        m9507a(context);
    }

    /* renamed from: a */
    private void m9507a(Context context) {
        if (context instanceof C0753a) {
            this.f7885l = (C0753a) context;
        }
        QuizTypeTwoView.inflate(getContext(), R.layout.quiz4, this);
        this.f7874a = (TextView) findViewById(R.id.tvKorean);
        if (this.f7891r == null) {
            this.f7891r = new AppPreference(context);
        }
        this.f7875b = (TextView) findViewById(R.id.tvPinyin);
        if (!this.f7891r.mo2917j()) {
            this.f7875b.setVisibility(8);
        }
        this.f7880g = (FlowLayout) findViewById(R.id.flowLayout);
        this.f7881h = (EditText) findViewById(R.id.editAnswer);
        this.f7881h.setInputType(0);
        this.f7882i = (LinearLayout) findViewById(R.id.llFlowLayout);
        this.f7882i.setLayoutParams(getLayoutParam());
        this.f7878e = (ImageView) findViewById(R.id.btnPlay);
        this.f7878e.setOnClickListener(this);
        this.f7876c = (TextView) findViewById(R.id.tvDel);
        this.f7877d = (TextView) findViewById(R.id.tvSug);
        this.f7876c.setOnClickListener(this);
        this.f7877d.setOnClickListener(this);
        this.staggeredTextGridView = (StaggeredTextGridView) findViewById(R.id.staggeredTextView);
        this.staggeredAdapter = new StaggeredAdapter(getContext(), this.f7888o);
        this.staggeredAdapter.mo2848a((onStaggeredTextViewClick) this);
        this.staggeredTextGridView.setmAdapter(this.staggeredAdapter);
    }

    /* renamed from: a */
    private void m9508a(String str) {
        str = String.valueOf(str.charAt(0));
        int b = m9510b(str);
        this.staggeredAdapter.renderViewWithAnimation(str, b, this.staggeredTextGridView.mo7083a(b), true);
        if (this.f7885l != null) {
            this.f7885l.mo2844t();
        }
    }

    /* renamed from: a */
    private void m9509a(List<String> list) {
        String str = (String) list.get(0);
        int b = m9510b(str);
        this.staggeredAdapter.renderViewWithAnimation(str, b, this.staggeredTextGridView.mo7083a(b), true);
    }

    /* renamed from: b */
    private int m9510b(String str) {
        int i = 0;
        while (i < this.f7888o.size()) {
            PrintStream printStream = System.out;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("arrayList ");
            stringBuilder.append((String) this.f7888o.get(i));
            stringBuilder.append(" value ");
            stringBuilder.append(str);
            printStream.println(stringBuilder.toString());
            if (str.equals(this.f7888o.get(i)) && !this.f7890q.contains(Integer.valueOf(i))) {
                return i;
            }
            i++;
        }
        return -1;
    }

    /* renamed from: c */
    private int m9512c(String str) {
        for (int childCount = this.f7880g.getChildCount() - 1; childCount >= 0; childCount--) {
            TextView textView = (TextView) this.f7880g.getChildAt(childCount);
            PrintStream printStream = System.out;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("textView ");
            stringBuilder.append(textView.getText());
            stringBuilder.append(" value ");
            stringBuilder.append(str);
            printStream.println(stringBuilder.toString());
            if (str.equals(textView.getText())) {
                return childCount;
            }
        }
        return -1;
    }

    private String getAllText() {
        StringBuilder stringBuilder = new StringBuilder();
        for (String append : this.f7887n) {
            stringBuilder.append(append);
            stringBuilder.append(" ");
        }
        if (stringBuilder.length() > 0) {
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
        return stringBuilder.toString();
    }

    private List<String> getArrayList() {
        List<String> arrayList = new ArrayList();
        for (int i = 0; i < this.f7889p.size(); i++) {
            if (i >= this.f7887n.size()) {
                arrayList.add(this.f7889p.get(i));
            }
        }
        return arrayList;
    }

    private RelativeLayout.LayoutParams getLayoutParam() {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(13);
        layoutParams.addRule(3, R.id.tvPinyin);
        int a = (int) (((double) Utils.m3026a(getContext())) * 0.05d);
        PrintStream printStream = System.out;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("margin ");
        stringBuilder.append(a);
        printStream.println(stringBuilder.toString());
        layoutParams.setMargins(a, a, a, a);
        return layoutParams;
    }

    /* renamed from: a */
    public void mo8072a() {
        this.f7880g.removeAllViews();
        if (this.f7887n != null) {
            this.f7887n.clear();
        }
        this.f7886m = 0;
        this.f7893t = new StringBuilder();
        this.staggeredTextGridView.mo7085b();
        this.staggeredTextGridView.mo7084a();
        this.staggeredTextGridView.setmAdapter(this.staggeredAdapter);
    }

    /* renamed from: a */
    public void mo2846a(String str, int i) {
        this.f7890q.add(Integer.valueOf(i));
        if (this.f7894u) {
            TextView textView = new TextView(getContext());
            textView.setText(str);
            textView.setBackgroundResource(R.drawable.selector_items);
            textView.setGravity(17);
            FlowLayout.LayoutParams c0779a = new FlowLayout.LayoutParams(-2, -2);
            c0779a.rightMargin = 7;
            c0779a.bottomMargin = 7;
            textView.setLayoutParams(c0779a);
            textView.setPadding(20, 20, 20, 20);
            textView.setMinWidth(Utils.m3026a(getContext()) / 8);
            textView.setTextColor(Utils.getColor(getContext(), R.color.quiz_text_color));
            this.f7880g.addView(textView, c0779a);
            this.f7887n.add(str);
            if (this.f7886m < this.f7889p.size() - 1) {
                this.f7886m++;
                return;
            }
            StringBuilder stringBuilder;
            this.f7886m++;
            for (String str2 : this.f7887n) {
                stringBuilder = this.f7893t;
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append(str2);
                stringBuilder2.append(" ");
                stringBuilder.append(stringBuilder2.toString());
            }
            if (this.f7885l != null) {
                str = this.f7893t.toString().trim();
                PrintStream printStream = System.out;
                stringBuilder = new StringBuilder();
                stringBuilder.append("ans ");
                stringBuilder.append(str);
                printStream.println(stringBuilder.toString());
                printStream = System.out;
                stringBuilder = new StringBuilder();
                stringBuilder.append("korean ");
                stringBuilder.append(this.f7879f);
                printStream.println(stringBuilder.toString());
                this.f7885l.mo2842a(this.f7892s, str, str.equals(this.f7879f.replace(",", "").replace(".", "").trim()));
                return;
            }
        }
        this.f7893t.append(str);
        this.f7881h.setText(this.f7893t.toString());
    }

    /* JADX WARNING: Missing block: B:28:0x00a2, code:
            if (com.example.english.p033c.Utils.isStringEmpty(r7) == false) goto L_0x00a4;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onClick(View view) {
        int id = view.getId();
        String obj;
        int intValue;
        TextView a;
        if (id == R.id.btnPlay) {
            final AnimationDrawable animationDrawable = (AnimationDrawable) this.f7878e.getBackground();
            animationDrawable.start();
            if (this.f7885l != null) {
                this.f7885l.mo2841a(this.f7892s, new OnCompletionListener() {
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        System.out.println("onCompletion");
                        animationDrawable.stop();
                        animationDrawable.selectDrawable(0);
                    }
                });
            }
        } else if (id != R.id.tvDel) {
            if (id == R.id.tvSug) {
                if (this.f7894u) {
                    List list;
                    if (this.f7887n.size() <= 0) {
                        list = this.f7889p;
                    } else if (this.f7879f.replace(",", "").replace(".", "").startsWith(getAllText())) {
                        list = getArrayList();
                        if (list.size() > 0) {
                            PrintStream printStream = System.out;
                            StringBuilder stringBuilder = new StringBuilder();
                            stringBuilder.append("list ");
                            stringBuilder.append(list.size());
                            printStream.println(stringBuilder.toString());
                            this.f7882i.setBackgroundResource(R.drawable.bg_phrase_answer_true);
                        }
                    } else {
                        this.f7882i.setBackgroundResource(R.drawable.bg_phrase_answer_false);
                        return;
                    }
                    m9509a(list);
                    return;
                }
                obj = this.f7881h.getText().toString();
                if (Utils.isStringEmpty(obj)) {
                    obj = this.f7879f;
                } else if (this.f7879f.startsWith(obj)) {
                    obj = this.f7879f.replace(obj, "");
                } else {
                    Utils.animateTextView(this.f7881h);
                    return;
                }
                m9508a(obj);
            }
        } else if (this.f7894u) {
            if (this.f7887n.size() > 0) {
                obj = (String) this.f7887n.get(this.f7887n.size() - 1);
                PrintStream printStream2 = System.out;
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append("value ");
                stringBuilder2.append(obj);
                printStream2.println(stringBuilder2.toString());
                intValue = ((Integer) this.f7890q.get(this.f7890q.size() - 1)).intValue();
                a = this.staggeredTextGridView.mo7083a(intValue);
                this.f7890q.remove(this.f7890q.size() - 1);
                this.f7880g.removeView(this.f7880g.getChildAt(m9512c(obj)));
                this.f7887n.remove(this.f7887n.size() - 1);
                this.f7886m--;
                this.f7893t = new StringBuilder();
                this.staggeredAdapter.renderView(obj, intValue, a);
                if (this.f7885l != null) {
                    this.f7885l.mo2842a(this.f7892s, "", false);
                }
            }
        } else if (this.f7893t.length() >= 1) {
            obj = String.valueOf(this.f7893t.charAt(this.f7893t.length() - 1));
            intValue = ((Integer) this.f7890q.get(this.f7890q.size() - 1)).intValue();
            a = this.staggeredTextGridView.mo7083a(intValue);
            this.f7893t.deleteCharAt(this.f7893t.length() - 1);
            this.f7890q.remove(this.f7890q.size() - 1);
            if (!Utils.isStringEmpty(obj)) {
                this.staggeredAdapter.renderView(obj, intValue, a);
            }
            this.f7881h.setText(this.f7893t.toString());
        }
    }

    public void setData(final PhraseItem phraseItem) {
        this.f7892s = phraseItem;
        if (this.f7890q != null) {
            this.f7890q.clear();
        }
        String trim = phraseItem.korean.replace(",", "").trim();
        String obj = phraseItem.vietnamese;
        this.f7874a.setText(obj);
        this.f7875b.setText(phraseItem.pinyin);
        this.f7879f = trim.replaceAll("\\(.*\\)", "").trim();
        if (this.f7888o == null) {
            this.f7888o = new ArrayList();
        } else {
            this.f7888o.clear();
        }
        int i = 0;
        int length;
        if (this.f7879f.length() >= 10) {
            this.f7880g.setVisibility(0);
            String[] split = this.f7879f.split(" ");
            length = split.length;
            while (i < length) {
                this.f7888o.add(split[i].replace(".", ""));
                i++;
            }
            this.f7894u = true;
        } else {
            this.f7881h.setVisibility(0);
            for (length = 0; length < this.f7879f.length(); length++) {
                this.f7888o.add(String.valueOf(this.f7879f.charAt(length)));
            }
            this.f7894u = false;
            EditText editText = this.f7881h;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("How to write ");
            stringBuilder.append(obj);
            editText.setHint(stringBuilder.toString());
            this.f7881h.addTextChangedListener(new TextWatcher() {
                public void afterTextChanged(Editable editable) {
                }

                public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                }

                public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                    if (charSequence != null && QuizTypeTwoView.this.f7885l != null) {
                        String trim = charSequence.toString().trim();
                        QuizTypeTwoView.this.f7885l.mo2842a(phraseItem, trim, trim.equals(QuizTypeTwoView.this.f7879f));
                    }
                }
            });
        }
        PrintStream printStream = System.out;
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("size ");
        stringBuilder2.append(this.f7888o.size());
        printStream.println(stringBuilder2.toString());
        this.f7889p = (ArrayList) this.f7888o.clone();
        Collections.shuffle(this.f7888o, new Random(System.nanoTime()));
        this.staggeredAdapter.mo2851a(this.f7888o);
        mo8072a();
    }
}
