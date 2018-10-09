package app.giaotieptienghan.customview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import app.giaotieptienghan.Utils;
import app.giaotieptienghan.model.C2249g;
import app.giaotieptienghan.model.C2249g.C0753a;
import app.giaotieptienghan.model.PhraseItem;
import app.giaotieptienghan.repository.AppPreference;
import app.giaotieptienghan.R;
/* renamed from: com.example.english.customview.a */
@SuppressLint("WrongConstant")
public class C0785a extends LinearLayout implements OnClickListener {
    /* renamed from: a */
    private TextView f2032a;
    /* renamed from: b */
    private TextView f2033b;
    /* renamed from: c */
    private String f2034c;
    /* renamed from: d */
    private ArrayList<Integer> f2035d;
    /* renamed from: e */
    private ArrayList<String> f2036e;
    /* renamed from: f */
    private TextView f2037f;
    /* renamed from: g */
    private TextView f2038g;
    /* renamed from: h */
    private TextView f2039h;
    /* renamed from: i */
    private TextView f2040i;
    /* renamed from: j */
    private PhraseItem f2041j;
    /* renamed from: k */
    private AppPreference f2042k;
    /* renamed from: l */
    private C0753a f2043l;
    /* renamed from: m */
    private ImageView f2044m;

    public C0785a(Context context) {
        super(context);
        m3119a(context);
    }

    /* renamed from: a */
    private void m3119a(Context context) {
        if (context instanceof C0753a) {
            this.f2043l = (C0753a) context;
        }
        PrintStream printStream = System.out;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("listener ");
        stringBuilder.append(this.f2043l);
        printStream.println(stringBuilder.toString());
        C0785a.inflate(getContext(), R.layout.quiz1, this);
        this.f2032a = (TextView) findViewById(R.id.tvEnglish);
        this.f2033b = (TextView) findViewById(R.id.tvPinyin);
        this.f2037f = (TextView) findViewById(R.id.btnAns1);
        this.f2038g = (TextView) findViewById(R.id.btnAns2);
        this.f2039h = (TextView) findViewById(R.id.btnAns3);
        this.f2040i = (TextView) findViewById(R.id.btnAns4);
        this.f2044m = (ImageView) findViewById(R.id.btnPlay);
        this.f2044m.setOnClickListener(this);
        this.f2037f.setOnClickListener(this);
        this.f2038g.setOnClickListener(this);
        this.f2039h.setOnClickListener(this);
        this.f2040i.setOnClickListener(this);
        if (this.f2042k == null) {
            this.f2042k = new AppPreference(getContext());
        }
    }

    /* renamed from: a */
    private void m3120a(boolean z, TextView textView) {
        mo3006a();
        textView.setBackgroundResource(R.drawable.bg_phrase_answer_true_quiz);
        textView.setTextColor(Utils.m3036b(getContext(), R.color.white));
        if (this.f2043l != null) {
            String trim = textView.getText().toString().trim();
            this.f2043l.mo2842a(this.f2041j, trim, trim.contains(this.f2034c.trim()));
        }
    }

    /* renamed from: b */
    private void m3121b() {
        this.f2037f.setText("");
        this.f2038g.setText("");
        this.f2039h.setText("");
        this.f2040i.setText("");
        mo3006a();
    }

    /* renamed from: a */
    public void mo3006a() {
        System.out.println("setBGDefault");
        this.f2037f.setBackgroundResource(R.drawable.bg_phrase_answer);
        this.f2038g.setBackgroundResource(R.drawable.bg_phrase_answer);
        this.f2039h.setBackgroundResource(R.drawable.bg_phrase_answer);
        this.f2040i.setBackgroundResource(R.drawable.bg_phrase_answer);
        this.f2037f.setTextColor(Utils.m3036b(getContext(), R.color.quiz_text_color));
        this.f2038g.setTextColor(Utils.m3036b(getContext(), R.color.quiz_text_color));
        this.f2039h.setTextColor(Utils.m3036b(getContext(), R.color.quiz_text_color));
        this.f2040i.setTextColor(Utils.m3036b(getContext(), R.color.quiz_text_color));
    }

    /* renamed from: a */
    public void mo3007a(List<PhraseItem> list, int i) {
        m3121b();
        if (this.f2035d == null) {
            this.f2035d = new ArrayList();
        } else {
            this.f2035d.clear();
        }
        if (this.f2036e == null) {
            this.f2036e = new ArrayList();
        } else {
            this.f2036e.clear();
        }
        this.f2041j = (PhraseItem) list.get(i);
        this.f2032a.setText(this.f2041j.korean);
        this.f2033b.setText(this.f2041j.pinyin);
        if (!this.f2042k.mo2917j()) {
            this.f2033b.setVisibility(8);
        }
        this.f2034c = this.f2041j.vietnamese;
        this.f2036e.add(this.f2034c);
        Random random = new Random();
        while (this.f2036e.size() < 4) {
            int nextInt = random.nextInt(list.size());
            if (!this.f2035d.contains(Integer.valueOf(nextInt))) {
                this.f2035d.add(Integer.valueOf(nextInt));
                String str = ((PhraseItem) list.get(nextInt)).vietnamese;
                if (!str.equals(this.f2034c)) {
                    this.f2036e.add(str);
                }
            }
        }
        while (this.f2036e.size() > 0) {
            TextView textView = null;
            StringBuilder stringBuilder = null;
            String str2 = null;
            int nextInt2 = random.nextInt(this.f2036e.size());
            if (Utils.isStringEmpty(this.f2037f.getText().toString())) {
                textView = this.f2037f;
                stringBuilder = new StringBuilder();
                str2 = "A. ";
            } else if (Utils.isStringEmpty(this.f2038g.getText().toString())) {
                textView = this.f2038g;
                stringBuilder = new StringBuilder();
                str2 = "B. ";
            } else if (Utils.isStringEmpty(this.f2039h.getText().toString())) {
                textView = this.f2039h;
                stringBuilder = new StringBuilder();
                str2 = "C. ";
            } else if (Utils.isStringEmpty(this.f2040i.getText().toString())) {
                textView = this.f2040i;
                stringBuilder = new StringBuilder();
                str2 = "D. ";
            } else {
                this.f2036e.remove(nextInt2);
            }
            stringBuilder.append(str2);
            stringBuilder.append((String) this.f2036e.get(nextInt2));
            textView.setText(stringBuilder.toString());
            this.f2036e.remove(nextInt2);
        }
    }

    public void onClick(View view) {
        TextView textView = null;
        switch (view.getId()) {
            case R.id.btnAns1:
                textView = this.f2037f;
                break;
            case R.id.btnAns2:
                textView = this.f2038g;
                break;
            case R.id.btnAns3:
                textView = this.f2039h;
                break;
            case R.id.btnAns4:
                textView = this.f2040i;
                break;
            case R.id.btnPlay:
                final AnimationDrawable animationDrawable = (AnimationDrawable) this.f2044m.getBackground();
                animationDrawable.start();
                if (this.f2043l != null) {
                    this.f2043l.mo2841a(this.f2041j, new OnCompletionListener() {
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            System.out.println("onCompletion");
                            animationDrawable.stop();
                            animationDrawable.selectDrawable(0);
                        }
                    });
                    return;
                }
                break;
            default:
                return;
        }
        m3120a(false, textView);
    }
}
