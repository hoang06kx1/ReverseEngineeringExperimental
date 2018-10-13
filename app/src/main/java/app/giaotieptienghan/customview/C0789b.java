package app.giaotieptienghan.customview;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import app.giaotieptienghan.model.QuizAdapter.QuizInterface;
import app.giaotieptienghan.model.PhraseItem;
import app.giaotieptienghan.repository.AppPreference;
import app.giaotieptienghan.R;

/* renamed from: com.example.english.customview.b */
public class C0789b extends LinearLayout {
    /* renamed from: a */
    private TextView f2050a;
    /* renamed from: b */
    private TextView f2051b;
    /* renamed from: c */
    private ImageView f2052c;
    /* renamed from: d */
    private QuizInterface f2053d;
    /* renamed from: e */
    private ImageView f2054e;
    /* renamed from: f */
    private PhraseItem f2055f;
    /* renamed from: g */
    private AppPreference f2056g;

    /* renamed from: com.example.english.customview.b$1 */
    class C07871 implements OnClickListener {
        C07871() {
        }

        public void onClick(View view) {
            final AnimationDrawable animationDrawable = (AnimationDrawable) C0789b.this.f2054e.getBackground();
            animationDrawable.start();
            if (C0789b.this.f2053d != null) {
                C0789b.this.f2053d.mo2841a(C0789b.this.f2055f, new OnCompletionListener() {
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        System.out.println("onCompletion");
                        animationDrawable.stop();
                        animationDrawable.selectDrawable(0);
                    }
                });
            }
        }
    }

    public C0789b(Context context) {
        super(context);
        m3125a(context);
    }

    /* renamed from: a */
    private void m3125a(Context context) {
        if (context instanceof QuizInterface) {
            this.f2053d = (QuizInterface) context;
        }
        C0789b.inflate(getContext(), R.layout.quiz3, this);
        if (this.f2056g == null) {
            this.f2056g = new AppPreference(context);
        }
        this.f2050a = (TextView) findViewById(R.id.tvEnglish);
        this.f2051b = (TextView) findViewById(R.id.tvPinyin);
        this.f2052c = (ImageView) findViewById(R.id.imgMicro);
        this.f2054e = (ImageView) findViewById(R.id.btnPlay);
        this.f2054e.setOnClickListener(new C07871());
    }

    @SuppressLint("WrongConstant")
    public void setData(final PhraseItem phraseItem) {
        this.f2055f = phraseItem;
        this.f2050a.setText(phraseItem.korean);
        this.f2051b.setText(phraseItem.pinyin);
        if (!this.f2056g.mo2917j()) {
            this.f2051b.setVisibility(8);
        }
        this.f2052c.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (C0789b.this.f2053d != null) {
                    C0789b.this.f2053d.mo2843a(phraseItem, false);
                }
            }
        });
    }
}

