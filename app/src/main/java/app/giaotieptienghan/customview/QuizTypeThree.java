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
public class QuizTypeThree extends LinearLayout {
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
    private AppPreference appPreference;

    /* renamed from: com.example.english.customview.b$1 */
    class C07871 implements OnClickListener {
        C07871() {
        }

        public void onClick(View view) {
            final AnimationDrawable animationDrawable = (AnimationDrawable) QuizTypeThree.this.f2054e.getBackground();
            animationDrawable.start();
            if (QuizTypeThree.this.f2053d != null) {
                QuizTypeThree.this.f2053d.mo2841a(QuizTypeThree.this.f2055f, new OnCompletionListener() {
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        System.out.println("onCompletion");
                        animationDrawable.stop();
                        animationDrawable.selectDrawable(0);
                    }
                });
            }
        }
    }

    public QuizTypeThree(Context context) {
        super(context);
        initViews(context);
    }

    /* renamed from: a */
    private void initViews(Context context) {
        if (context instanceof QuizInterface) {
            this.f2053d = (QuizInterface) context;
        }
        QuizTypeThree.inflate(getContext(), R.layout.quiz3, this);
        if (this.appPreference == null) {
            this.appPreference = new AppPreference(context);
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
        if (!this.appPreference.mo2917j()) {
            this.f2051b.setVisibility(8);
        }
        this.f2052c.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (QuizTypeThree.this.f2053d != null) {
                    QuizTypeThree.this.f2053d.mo2843a(phraseItem, false);
                }
            }
        });
    }
}

