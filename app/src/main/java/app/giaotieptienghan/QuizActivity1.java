package app.giaotieptienghan;

import android.annotation.SuppressLint;
import android.app.AlertDialog.Builder;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.view.Menu;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import app.giaotieptienghan.customview.CustomViewPager;
import app.giaotieptienghan.model.C2249g;
import app.giaotieptienghan.model.PhraseItem;
import app.giaotieptienghan.repository.EndlessLoveDB;

@SuppressLint("WrongConstant")
public class QuizActivity1 extends BaseAudioPlayActivity implements OnClickListener, C2249g.C0753a {
    /* renamed from: M */
    private CustomViewPager f12075M;
    /* renamed from: N */
    private C2249g f12076N;
    /* renamed from: O */
    private ProgressBar f12077O;
    /* renamed from: P */
    private RelativeLayout f12078P;
    /* renamed from: Q */
    private RelativeLayout f12079Q;
    /* renamed from: R */
    private TextView f12080R;
    /* renamed from: S */
    private TextView f12081S;
    /* renamed from: T */
    private TextView f12082T;
    /* renamed from: U */
    private TextView f12083U;
    /* renamed from: V */
    private TextView f12084V;
    /* renamed from: W */
    private TextView f12085W;
    /* renamed from: X */
    private TextView f12086X;
    /* renamed from: Y */
    private TextView f12087Y;
    /* renamed from: Z */
    private TextView f12088Z;
    /* renamed from: aa */
    private TextView f12089aa;
    /* renamed from: ab */
    private LinearLayout f12090ab;
    /* renamed from: ac */
    private int f12091ac = 0;
    /* renamed from: ad */
    private int f12092ad = 0;
    /* renamed from: ae */
    private boolean f12093ae;
    /* renamed from: af */
    private PhraseItem f12094af;
    /* renamed from: ag */
    private int f12095ag = 0;
    /* renamed from: ah */
    private final int f12096ah = 100;
    /* renamed from: ai */
    private String f12097ai;
    /* renamed from: aj */
    private int f12098aj;
    /* renamed from: ak */
    private String f12099ak;
    /* renamed from: al */
    private String f12100al;
    /* renamed from: am */
    private final String[] f12101am = new String[]{"Well Done!", "Nice Job!", "Excellent!", "Fantastic!", "Amazing!", "Awesome!", "Splendid!", "Unbelievable!", "Wonderful!"};

    /* renamed from: com.example.english.QuizActivity1$1 */
    class C07151 implements DialogInterface.OnClickListener {
        C07151() {
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            QuizActivity1.this.f12095ag = 0;
            QuizActivity1.this.f12091ac = 0;
            QuizActivity1.this.f12092ad = 0;
            QuizActivity1.this.f12086X.setText("0");
            QuizActivity1.this.f12087Y.setText("0");
            QuizActivity1.this.f12075M.setCurrentItem(0, true);
            TextView h = QuizActivity1.this.f12088Z;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(QuizActivity1.this.f12095ag + 1);
            stringBuilder.append("/");
            stringBuilder.append(QuizActivity1.this.phraseItems.size());
            h.setText(stringBuilder.toString());
        }
    }

    /* renamed from: com.example.english.QuizActivity1$2 */
    class C07162 implements DialogInterface.OnClickListener {
        C07162() {
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            QuizActivity1.this.finish();
        }
    }

    /* renamed from: com.example.english.QuizActivity1$3 */
    class C07173 implements AnimationListener {
        C07173() {
        }

        public void onAnimationEnd(Animation animation) {
            QuizActivity1.this.f12087Y.setVisibility(8);
        }

        public void onAnimationRepeat(Animation animation) {
        }

        public void onAnimationStart(Animation animation) {
            QuizActivity1.this.f12087Y.setVisibility(0);
        }
    }

    /* renamed from: com.example.english.QuizActivity1$a */
    private class C0718a extends AsyncTask<String, Void, List<PhraseItem>> {
        private C0718a() {
        }

        /* synthetic */ C0718a(QuizActivity1 quizActivity1, C07151 c07151) {
            this();
        }

        /* renamed from: a */
        protected List<PhraseItem> doInBackground(String... strArr) {
            EndlessLoveDB db = new EndlessLoveDB(QuizActivity1.this);
            List<PhraseItem> arrayList = new ArrayList();
            try {
                db.initDB();
                db.getReadableDB();
                ArrayList e = strArr[0] == null ? QuizActivity1.this.f12099ak != null ? db.mo2880e() : db.mo2872a(QuizActivity1.this.f12098aj) : "0".equals(QuizActivity1.this.f12099ak) ? db.mo2879d() : db.mo2876b(QuizActivity1.this.f12098aj);
                arrayList = e;
            } catch (Exception e2) {
                e2.printStackTrace();
            } catch (Throwable th) {
                db.closeDB();
            }
            db.closeDB();
            return arrayList;
        }

        /* renamed from: a */
        protected void onPostExecute(List<PhraseItem> list) {
            super.onPostExecute(list);
            if (list != null && list.size() > 0) {
                for (PhraseItem phraseItem : list) {
                    QuizActivity1.this.phraseItems.add(phraseItem);
                    QuizActivity1.this.phraseItems.add(phraseItem);
                    QuizActivity1.this.phraseItems.add(phraseItem);
                }
                Collections.shuffle(QuizActivity1.this.phraseItems, new Random(System.nanoTime()));
                QuizActivity1.this.f12076N.mo8066a(QuizActivity1.this.phraseItems);
                QuizActivity1.this.f12076N.notifyDataSetChanged();
                QuizActivity1.this.f12090ab.setVisibility(0);
                QuizActivity1.this.f12085W.setVisibility(0);
                QuizActivity1.this.f12080R.setVisibility(0);
                TextView h = QuizActivity1.this.f12088Z;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(QuizActivity1.this.f12095ag + 1);
                stringBuilder.append("/");
                stringBuilder.append(QuizActivity1.this.phraseItems.size());
                h.setText(stringBuilder.toString());
            }
            QuizActivity1.this.f12077O.setVisibility(8);
        }

        protected void onPreExecute() {
            super.onPreExecute();
        }
    }

    /* renamed from: a */
    private void m16331a(Context context) {
        if (this.appPreference.mo2897a(this.f12013n) < this.f12091ac) {
            this.appPreference.mo2899a(this.f12013n, this.f12091ac);
            TextView textView = this.f12089aa;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(this.f12091ac);
            stringBuilder.append("");
            textView.setText(stringBuilder.toString());
        }
        Builder builder = new Builder(context, R.style.MyDialogTheme);
        builder.setTitle("Congratulations!!!");
        builder.setIcon(Utils.m3037c(this, R.drawable.ic_launcher));
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("You have completed the quiz with ");
        stringBuilder2.append(this.f12091ac);
        stringBuilder2.append(" score! ");
        builder.setMessage(stringBuilder2.toString());
        builder.setCancelable(false);
        builder.setPositiveButton("Again", new C07151());
        builder.setNegativeButton("Cancel", new C07162());
        builder.create().show();
    }

    /* renamed from: a */
    private void m16332a(String str, boolean z) {
        try {
            this.f12083U.setText(str);
            this.f12079Q.setVisibility(0);
            if (z) {
                this.f12092ad = 5;
                this.f12091ac += 5;
                this.f12093ae = true;
            } else {
                this.f12093ae = false;
                this.f12092ad = -3;
                if (this.f12091ac >= 3) {
                    this.f12091ac -= 3;
                } else {
                    this.f12091ac = 0;
                }
            }
            m16335b(this.f12093ae);
            TextView textView = this.f12086X;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(this.f12091ac);
            stringBuilder.append("");
            textView.setText(stringBuilder.toString());
            textView = this.f12087Y;
            stringBuilder = new StringBuilder();
            stringBuilder.append(this.f12092ad);
            stringBuilder.append("");
            textView.setText(stringBuilder.toString());
            Animation translateAnimation = new TranslateAnimation(0.0f, 0.0f, 0.0f, (-this.f12086X.getY()) + ((float) this.f12086X.getHeight()));
            translateAnimation.setDuration(500);
            translateAnimation.setAnimationListener(new C07173());
            this.f12087Y.startAnimation(translateAnimation);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: b */
    private void m16335b(boolean z) {
        RelativeLayout relativeLayout = this.f12078P;
        int i = R.color.green_check_layout_false;
        relativeLayout.setBackgroundColor(Utils.m3036b(this, z ? R.color.green_check_layout : R.color.green_check_layout_false));
        TextView textView = this.f12081S;
        if (z) {
            i = R.color.green_check_text;
        }
        textView.setTextColor(Utils.m3036b(this, i));
        this.f12081S.setBackgroundResource(z ? R.drawable.bg_continue_true : R.drawable.bg_continue_false);
        this.f12082T.setTextColor(Utils.m3036b(this, z ? R.color.you_are_corect : R.color.you_are_wrong));
        this.f12082T.setText(z ? this.f12101am[new Random().nextInt(this.f12101am.length)] : "You are wrong!");
        this.f12081S.setText(z ? "Continue" : "Try Again");
    }

    /* renamed from: c */
    private int m16336c(int i) {
        return this.f12075M.getCurrentItem() + i;
    }

    /* renamed from: u */
    private void m16348u() {
        String str = this.f12094af.korean;
        Intent intent = new Intent("android.speech.action.RECOGNIZE_SPEECH");
        intent.putExtra("android.speech.extra.LANGUAGE_MODEL", "free_form");
        intent.putExtra("android.speech.extra.LANGUAGE", "ko-KR");
        intent.putExtra("android.speech.extra.PROMPT", String.format(getString(R.string.speech_prompt), new Object[]{str}));
        try {
            startActivityForResult(intent, 100);
        } catch (ActivityNotFoundException unused) {
            Toast.makeText(getApplicationContext(), getString(R.string.speech_not_supported), 0).show();
        }
    }

    /* renamed from: a */
    public void mo2841a(PhraseItem phraseItem, OnCompletionListener onCompletionListener) {
        if (Utils.isStringEmpty(phraseItem.voice)) {
            mo10026a(phraseItem.korean, onCompletionListener, false);
        } else {
            Resources resources = getResources();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(phraseItem.voice);
            stringBuilder.append("_f");
            int identifier = resources.getIdentifier(stringBuilder.toString(), "raw", getPackageName());
            if (identifier != 0) {
                playAudio(identifier, onCompletionListener);
            }
        }
    }

    /* renamed from: a */
    public void mo2842a(PhraseItem phraseItem, String str, boolean z) {
        this.f12094af = phraseItem;
        this.f12093ae = z;
        this.f12097ai = str;
        if (!Utils.isStringEmpty(str)) {
            this.f12080R.setEnabled(true);
            this.f12080R.setTextColor(Utils.m3036b(this, R.color.white));
            this.f12080R.setBackgroundResource(R.drawable.bg_check_enable);
        }
    }

    /* renamed from: a */
    public void mo2843a(PhraseItem phraseItem, boolean z) {
        this.f12094af = phraseItem;
        this.f12097ai = phraseItem.korean;
        this.f12093ae = true;
        this.f12080R.setEnabled(true);
        this.f12080R.setTextColor(Utils.m3036b(this, R.color.white));
        this.f12080R.setBackgroundResource(R.drawable.bg_check_enable);
        m16348u();
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 100 && i2 == -1 && intent != null) {
            try {
                ArrayList stringArrayListExtra = intent.getStringArrayListExtra("android.speech.extra.RESULTS");
                TextView textView = this.f12084V;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Your answer : ");
                stringBuilder.append((String) stringArrayListExtra.get(0));
                textView.setText(stringBuilder.toString());
                this.f12084V.setVisibility(0);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:24:0x00e9 in {3, 8, 9, 10, 15, 17, 19, 22, 23} preds:[]
        	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.computeDominators(BlockProcessor.java:238)
        	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.processBlocksTree(BlockProcessor.java:48)
        	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.visit(BlockProcessor.java:38)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
        	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
        	at java.util.ArrayList.forEach(Unknown Source)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
        	at jadx.core.ProcessClass.process(ProcessClass.java:32)
        	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:293)
        	at jadx.api.JavaClass.decompile(JavaClass.java:62)
        	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:201)
        */
    public void onClick(android.view.View r8) {
        /*
        r7 = this;
        r8 = r8.getId();
        r0 = 2131165413; // 0x7f0700e5 float:1.7945042E38 double:1.052935616E-314;
        r1 = 2130968671; // 0x7f04005f float:1.7546002E38 double:1.0528384127E-314;
        r2 = 0;
        r3 = 2131099743; // 0x7f06005f float:1.7811848E38 double:1.052903171E-314;
        r4 = 8;
        r5 = 1;
        if (r8 == r0) goto L_0x00c5;
    L_0x0013:
        switch(r8) {
            case 2131165391: goto L_0x00bd;
            case 2131165392: goto L_0x0017;
            default: goto L_0x0016;
        };
    L_0x0016:
        return;
    L_0x0017:
        r8 = r7.f12093ae;
        if (r8 == 0) goto L_0x005b;
    L_0x001b:
        r8 = r7.m16336c(r5);
        r7.f12095ag = r8;
        r8 = r7.f12095ag;
        r0 = r7.phraseItems;
        r0 = r0.size();
        if (r8 != r0) goto L_0x002f;
    L_0x002b:
        r7.m16331a(r7);
        goto L_0x009f;
    L_0x002f:
        r8 = r7.f12075M;
        r0 = r7.m16336c(r5);
        r8.mo837a(r0, r5);
        r8 = r7.f12088Z;
        r0 = new java.lang.StringBuilder;
        r0.<init>();
    L_0x003f:
        r6 = r7.f12095ag;
        r6 = r6 + r5;
        r0.append(r6);
        r5 = "/";
        r0.append(r5);
        r5 = r7.phraseItems;
        r5 = r5.size();
        r0.append(r5);
        r0 = r0.toString();
        r8.setText(r0);
        goto L_0x009f;
    L_0x005b:
        r8 = java.lang.System.out;
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r5 = "getCurrentItem ";
        r0.append(r5);
        r5 = r7.f12075M;
        r5 = r5.getCurrentItem();
        r0.append(r5);
        r0 = r0.toString();
        r8.println(r0);
        r8 = r7.f12075M;
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r5 = "myview";
        r0.append(r5);
        r5 = r7.f12075M;
        r5 = r5.getCurrentItem();
        r0.append(r5);
        r0 = r0.toString();
        r8 = r8.findViewWithTag(r0);
        if (r8 == 0) goto L_0x009f;
    L_0x0096:
        r0 = r8 instanceof com.example.english.customview.C0785a;
        if (r0 == 0) goto L_0x009f;
    L_0x009a:
        r8 = (com.example.english.customview.C0785a) r8;
        r8.mo3006a();
    L_0x009f:
        r8 = r7.f12079Q;
        r8.setVisibility(r4);
        r8 = r7.f12080R;
        r8.setBackgroundResource(r3);
        r8 = r7.f12080R;
        r8.setEnabled(r2);
        r8 = r7.f12080R;
        r0 = com.example.english.p033c.Utils.m3036b(r7, r1);
        r8.setTextColor(r0);
        r8 = r7.f12084V;
        r8.setVisibility(r4);
        return;
    L_0x00bd:
        r8 = r7.f12097ai;
        r0 = r7.f12093ae;
        r7.m16332a(r8, r0);
        return;
    L_0x00c5:
        r8 = r7.m16336c(r5);
        r7.f12095ag = r8;
        r8 = r7.f12095ag;
        r0 = r7.phraseItems;
        r0 = r0.size();
        if (r8 != r0) goto L_0x00d7;
    L_0x00d5:
        goto L_0x002b;
    L_0x00d7:
        r8 = r7.f12075M;
        r0 = r7.m16336c(r5);
        r8.mo837a(r0, r5);
        r8 = r7.f12088Z;
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        goto L_0x003f;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.english.QuizActivity1.onClick(android.view.View):void");
    }

    protected void onCreate(Bundle bundle) {
        try {
            super.onCreate(bundle);
            setContentView((int) R.layout.quiz_activity);
            mo10050r();
            mo10051s();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return false;
    }

    /* renamed from: r */
    @SuppressLint("ResourceType")
    public void mo10050r() {
        //mo10030k();
        this.f12090ab = (LinearLayout) findViewById(R.id.view);
        this.f12077O = (ProgressBar) findViewById(R.id.progressBar);
        this.f12075M = (CustomViewPager) findViewById(R.id.pager);
        this.f12075M.setPagingEnabled(false);
        this.phraseItems = new ArrayList();
        this.f12076N = new C2249g(this, this.phraseItems);
        this.f12075M.setAdapter(this.f12076N);
        this.f12075M.setOffscreenPageLimit(1);
        this.f12086X = (TextView) findViewById(R.id.tvScore);
        this.f12089aa = (TextView) findViewById(R.id.tvHigh);
        this.f12087Y = (TextView) findViewById(R.id.tvPlus);
        this.f12088Z = (TextView) findViewById(R.id.tvTitle);
        this.f12078P = (RelativeLayout) findViewById(R.id.llCheck);
        this.f12079Q = (RelativeLayout) findViewById(R.id.llCheckParent);
        this.f12079Q.setOnClickListener(this);
        this.f12080R = (TextView) findViewById(R.id.tvCheck);
        this.f12080R.setVisibility(8);
        this.f12080R.setBackgroundResource(R.drawable.bg_phrase_answer);
        this.f12080R.setEnabled(false);
        this.f12080R.setTextColor(Utils.m3036b(this, R.color.quiz_text_color));
        this.f12085W = (TextView) findViewById(R.id.tvSkip);
        this.f12085W.setVisibility(8);
        this.f12081S = (TextView) findViewById(R.id.tvContinue);
        this.f12082T = (TextView) findViewById(R.id.tvCorrect);
        this.f12083U = (TextView) findViewById(R.id.tvKoreanTest);
        this.f12084V = (TextView) findViewById(R.id.tvEnglishTest);
        int b = Utils.m3035b(this);
        if (((double) getResources().getConfiguration().fontScale) >= 1.1d || b <= 860) {
            if (VERSION.SDK_INT < 23) {
                this.f12083U.setTextAppearance(this, 16973894);
                this.f12084V.setTextAppearance(this, 16973894);
            } else {
                this.f12083U.setTextAppearance(16973894);
                this.f12084V.setTextAppearance(16973894);
            }
        }
        this.f12083U.setTextColor(-1);
        this.f12084V.setTextColor(-1);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, b / 4);
        layoutParams.addRule(12);
        this.f12078P.setLayoutParams(layoutParams);
    }

    /* renamed from: s */
    public void mo10051s() {
        this.f12080R.setOnClickListener(this);
        this.f12085W.setOnClickListener(this);
        this.f12081S.setOnClickListener(this);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.f12098aj = extras.getInt("bundle_id");
            this.f12099ak = extras.getString("bundle_fav_id");
            this.f12100al = extras.getString("bundle_plus_id");
            this.f12013n = extras.getString("bundle_title");
            setTitle(!Utils.isStringEmpty(this.f12013n) ? this.f12013n : "Quiz");
            TextView textView = this.f12089aa;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(this.appPreference.mo2897a(this.f12013n));
            stringBuilder.append("");
            textView.setText(stringBuilder.toString());
        }
        new C0718a(this, null).execute(new String[]{this.f12100al});
    }

    /* renamed from: t */
    public void mo2844t() {
        this.f12091ac--;
        TextView textView = this.f12086X;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.f12091ac);
        stringBuilder.append("");
        textView.setText(stringBuilder.toString());
        this.f12087Y.setText("1");
    }
}
