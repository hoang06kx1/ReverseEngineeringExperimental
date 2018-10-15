package app.giaotieptienghan;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

import app.giaotieptienghan.model.C0768b;
import app.giaotieptienghan.model.PhraseItem;
import app.giaotieptienghan.repository.AppPreference;
import app.giaotieptienghan.repository.EndlessLoveDB;

@SuppressLint("WrongConstant")
public class QuizDetail extends BaseAudioPlayActivity implements OnClickListener {
    /* renamed from: M */
    private ProgressBar f12102M;
    /* renamed from: N */
    private TextView f12103N;
    /* renamed from: O */
    private TextView f12104O;
    /* renamed from: P */
    private TextView f12105P;
    /* renamed from: Q */
    private TextView f12106Q;
    /* renamed from: R */
    private TextView f12107R;
    /* renamed from: S */
    private TextView f12108S;
    /* renamed from: T */
    private TextView f12109T;
    /* renamed from: U */
    private String f12110U;
    /* renamed from: V */
    private ArrayList<Integer> f12111V;
    /* renamed from: W */
    private ArrayList<String> f12112W;
    /* renamed from: X */
    private int f12113X = 0;
    /* renamed from: Y */
    private C0721a f12114Y;
    /* renamed from: Z */
    private AppPreference f12115Z;
    /* renamed from: aa */
    private ScrollView f12116aa;
    /* renamed from: ab */
    private RelativeLayout f12117ab;
    /* renamed from: ac */
    private TextView f12118ac;
    /* renamed from: ad */
    private TextView f12119ad;
    /* renamed from: ae */
    private TextView f12120ae;
    /* renamed from: af */
    private TextView f12121af;

    /* renamed from: com.example.english.QuizDetail$1 */
    class C07191 implements Runnable {
        C07191() {
        }

        public void run() {
            QuizDetail.this.f12113X = QuizDetail.this.f12113X + 1;
            QuizDetail.this.m16364t();
            if (QuizDetail.this.f12114Y != null) {
                QuizDetail.this.f12114Y.start();
            }
        }
    }

    /* renamed from: com.example.english.QuizDetail$2 */
    class C07202 implements Runnable {
        C07202() {
        }

        public void run() {
            QuizDetail.this.f12114Y.onFinish();
        }
    }

    /* renamed from: com.example.english.QuizDetail$a */
    public class C0721a extends CountDownTimer {
        /* renamed from: b */
        private int[] f1824b;

        public C0721a(long j, long j2) {
            super(j, j2);
        }

        public void onFinish() {
            QuizDetail.this.f12109T.setText("Hết giờ");
            QuizDetail.this.m16366v();
            cancel();
        }

        public void onTick(long j) {
            try {
                Object valueOf;
                StringBuilder stringBuilder;
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append(j);
                stringBuilder2.append("");
                //C0769c.m2995b("Secorn", stringBuilder2.toString());
                this.f1824b = Utils.m3034a(j / 1000);
                TextView e = QuizDetail.this.f12109T;
                StringBuilder stringBuilder3 = new StringBuilder();
                if (this.f1824b[0] >= 10) {
                    valueOf = Integer.valueOf(this.f1824b[0]);
                } else {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("0");
                    stringBuilder.append(this.f1824b[0]);
                    valueOf = stringBuilder.toString();
                }
                stringBuilder3.append(valueOf);
                stringBuilder3.append(":");
                if (this.f1824b[1] >= 10) {
                    valueOf = Integer.valueOf(this.f1824b[1]);
                } else {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("0");
                    stringBuilder.append(this.f1824b[1]);
                    valueOf = stringBuilder.toString();
                }
                stringBuilder3.append(valueOf);
                e.setText(stringBuilder3.toString());
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    /* renamed from: com.example.english.QuizDetail$b */
    private class C0722b extends AsyncTask<String, Void, ArrayList<PhraseItem>> {
        private C0722b() {
        }

        /* synthetic */ C0722b(QuizDetail quizDetail, C07191 c07191) {
            this();
        }

        /* renamed from: a */
        protected ArrayList<PhraseItem> doInBackground(String... strArr) {
            if (C0768b.f1957a == null) {
                EndlessLoveDB db = new EndlessLoveDB(QuizDetail.this);
                try {
                    db.initDB();
                    db.getReadableDB();
                    C0768b.f1957a = db.getPhrasesByCategoryId(-1);
                } catch (Exception e) {
                    e.printStackTrace();
                } catch (Throwable th) {
                    db.closeDB();
                }
                db.closeDB();
            }
            return C0768b.f1957a;
        }

        /* renamed from: a */
        protected void onPostExecute(ArrayList<PhraseItem> arrayList) {
            super.onPostExecute(arrayList);
            if (arrayList != null) {
                QuizDetail.this.m16364t();
                QuizDetail.this.f12114Y.start();
                QuizDetail.this.f12102M.setVisibility(8);
                QuizDetail.this.f12116aa.setVisibility(0);
            }
        }

        protected void onPreExecute() {
            super.onPreExecute();
            QuizDetail.this.f12102M.setVisibility(0);
            QuizDetail.this.f12116aa.setVisibility(8);
        }
    }

    /* renamed from: b */
    private void m16357b(String str) {
        try {
            if (this.f12110U.equalsIgnoreCase(str)) {
                this.handler.postDelayed(new C07191(), 100);
            } else {
                this.handler.postDelayed(new C07202(), 500);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: s */
    private void m16363s() {
        int b = Utils.getScreenHeight(this);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(11);
        double d = (double) b;
        b = (int) (0.0125d * d);
        layoutParams.rightMargin = b;
        layoutParams.topMargin = b;
        this.f12109T.setLayoutParams(layoutParams);
        layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(9);
        layoutParams.leftMargin = b;
        layoutParams.topMargin = b;
        this.f12104O.setLayoutParams(layoutParams);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams2.addRule(3, R.id.point);
        layoutParams2.addRule(14);
        int i = (int) (0.025d * d);
        layoutParams2.topMargin = i;
        this.f12103N.setLayoutParams(layoutParams2);
        layoutParams2 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams2.addRule(3, R.id.word);
        layoutParams2.addRule(14);
        int i2 = (int) (d * 0.02d);
        layoutParams2.setMargins(i, i, i, 0);
        this.f12105P.setPadding(i2, i2, i2, i2);
        this.f12105P.setGravity(1);
        this.f12105P.setLayoutParams(layoutParams2);
        layoutParams2 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams2.addRule(3, R.id.btnAns1);
        layoutParams2.addRule(14);
        layoutParams2.setMargins(i, i, i, 0);
        this.f12106Q.setPadding(i2, i2, i2, i2);
        this.f12106Q.setGravity(1);
        this.f12106Q.setLayoutParams(layoutParams2);
        layoutParams2 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams2.addRule(3, R.id.btnAns2);
        layoutParams2.addRule(14);
        layoutParams2.setMargins(i, i, i, 0);
        this.f12107R.setPadding(i2, i2, i2, i2);
        this.f12107R.setGravity(1);
        this.f12107R.setLayoutParams(layoutParams2);
        layoutParams2 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams2.addRule(3, R.id.btnAns3);
        layoutParams2.addRule(14);
        layoutParams2.setMargins(i, i, i, 0);
        this.f12108S.setPadding(i2, i2, i2, i2);
        this.f12108S.setGravity(1);
        this.f12108S.setLayoutParams(layoutParams2);
        if (Utils.getScreenHeight(this) >= 800) {
            layoutParams2 = new RelativeLayout.LayoutParams(-1, -2);
            layoutParams2.addRule(12);
        }
    }

    /* renamed from: t */
    private void m16364t() {
        try {
            TextView textView = this.f12104O;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Điểm: ");
            stringBuilder.append(this.f12113X);
            textView.setText(stringBuilder.toString());
            m16365u();
            if (this.f12111V == null) {
                this.f12111V = new ArrayList();
            } else {
                this.f12111V.clear();
            }
            if (this.f12112W == null) {
                this.f12112W = new ArrayList();
            } else {
                this.f12112W.clear();
            }
            Random random = new Random();
            int nextInt = random.nextInt(C0768b.f1957a.size());
            PhraseItem phraseItem = (PhraseItem) C0768b.f1957a.get(nextInt);
            String str = phraseItem.voice;
            this.f12111V.add(Integer.valueOf(nextInt));
            if (Utils.isStringEmpty(phraseItem.korean)) {
                m16364t();
            } else {
                TextView textView2 = this.f12103N;
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append(phraseItem.vietnamese);
                stringBuilder2.append("\n");
                stringBuilder2.append(phraseItem.pinyin == null ? "" : phraseItem.pinyin);
                textView2.setText(stringBuilder2.toString());
                this.f12110U = phraseItem.korean;
                this.f12112W.add(phraseItem.korean);
                while (this.f12112W.size() < 4) {
                    nextInt = random.nextInt(C0768b.f1957a.size());
                    if (!this.f12111V.contains(Integer.valueOf(nextInt))) {
                        this.f12111V.add(Integer.valueOf(nextInt));
                        this.f12112W.add(((PhraseItem) C0768b.f1957a.get(nextInt)).korean);
                    }
                }
                while (this.f12112W.size() > 0) {
                    TextView textView3 = new TextView(this);
                    CharSequence charSequence = "";
                    nextInt = random.nextInt(this.f12112W.size());
                    if (Utils.isStringEmpty(this.f12105P.getText().toString())) {
                        textView3 = this.f12105P;
                        charSequence = (CharSequence) this.f12112W.get(nextInt);
                    } else if (Utils.isStringEmpty(this.f12106Q.getText().toString())) {
                        textView3 = this.f12106Q;
                        charSequence = (CharSequence) this.f12112W.get(nextInt);
                    } else if (Utils.isStringEmpty(this.f12107R.getText().toString())) {
                        textView3 = this.f12107R;
                        charSequence = (CharSequence) this.f12112W.get(nextInt);
                    } else if (Utils.isStringEmpty(this.f12108S.getText().toString())) {
                        textView3 = this.f12108S;
                        charSequence = (CharSequence) this.f12112W.get(nextInt);
                    } else {
                        this.f12112W.remove(nextInt);
                    }
                    textView3.setText(charSequence);
                    this.f12112W.remove(nextInt);
                }
            }
            if (this.f12115Z.mo2916i()) {
                if (this.mediaPlayer != null && this.mediaPlayer.isPlaying()) {
                    resetMediaPlayer();
                }
                Resources resources = getResources();
                stringBuilder = new StringBuilder();
                stringBuilder.append(str);
                stringBuilder.append("_f");
                int identifier = resources.getIdentifier(stringBuilder.toString(), "raw", getPackageName());
                if (identifier != 0) {
                    playAudio(identifier, (OnCompletionListener) this);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: u */
    private void m16365u() {
        this.f12105P.setText("");
        this.f12106Q.setText("");
        this.f12107R.setText("");
        this.f12108S.setText("");
    }

    /* renamed from: v */
    private void m16366v() {
        this.f12117ab.setVisibility(0);
        TextView textView = this.f12118ac;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Điểm: ");
        stringBuilder.append(this.f12113X);
        textView.setText(stringBuilder.toString());
        textView = this.f12119ad;
        stringBuilder = new StringBuilder();
        stringBuilder.append("Kỉ lục điểm ");
        stringBuilder.append(this.f12115Z.mo2896a());
        textView.setText(stringBuilder.toString());
        if (this.f12115Z.mo2896a() < this.f12113X) {
            this.f12115Z.mo2898a(this.f12113X);
        }
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btnRetry) {
            this.f12117ab.setVisibility(8);
            this.f12113X = 0;
            m16364t();
            if (this.f12114Y != null) {
                this.f12114Y.start();
            }
        } else if (id != R.id.rlScore) {
            TextView textView;
            switch (id) {
                case R.id.btnAns1:
                    textView = this.f12105P;
                    break;
                case R.id.btnAns2:
                    textView = this.f12106Q;
                    break;
                case R.id.btnAns3:
                    textView = this.f12107R;
                    break;
                case R.id.btnAns4:
                    textView = this.f12108S;
                    break;
                case R.id.btnHome:
                    finish();
                    return;
                default:
                    return;
            }
            m16357b(textView.getText().toString());
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.quiz_detail_screen);
        try {
            setTitle("Quiz");
            mo10050r();
        } catch (Exception unused) {
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        Resources resources;
        int i;
        getMenuInflater().inflate(R.menu.menu_search, menu);
        MenuItem findItem = menu.findItem(R.id.action_volume);
        if (this.f12115Z.mo2916i()) {
            resources = getResources();
            i = R.drawable.ic_action_volume_up;
        } else {
            resources = getResources();
            i = R.drawable.ic_action_volume_off;
        }
        findItem.setIcon(resources.getDrawable(i));
        return true;
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.f12114Y != null) {
            this.f12114Y.cancel();
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.action_volume) {
            Resources resources;
            int i;
            if (this.f12115Z.mo2916i()) {
                this.f12115Z.mo2904b(false);
                resources = getResources();
                i = R.drawable.ic_action_volume_off;
            } else {
                this.f12115Z.mo2904b(true);
                resources = getResources();
                i = R.drawable.ic_action_volume_up;
            }
            menuItem.setIcon(resources.getDrawable(i));
        }
        return super.onOptionsItemSelected(menuItem);
    }

    /* renamed from: r */
    public void mo10050r() {
        if (this.f12115Z == null) {
            this.f12115Z = new AppPreference(this);
        }
        this.f12116aa = (ScrollView) findViewById(R.id.slMain);
        this.f12104O = (TextView) findViewById(R.id.point);
        this.f12109T = (TextView) findViewById(R.id.tvTime);
        this.f12103N = (TextView) findViewById(R.id.word);
        this.f12105P = (TextView) findViewById(R.id.btnAns1);
        this.f12106Q = (TextView) findViewById(R.id.btnAns2);
        this.f12107R = (TextView) findViewById(R.id.btnAns3);
        this.f12108S = (TextView) findViewById(R.id.btnAns4);
        this.f12117ab = (RelativeLayout) findViewById(R.id.rlScore);
        this.f12118ac = (TextView) findViewById(R.id.tvScore);
        this.f12119ad = (TextView) findViewById(R.id.tvHighScore);
        this.f12120ae = (TextView) findViewById(R.id.btnHome);
        this.f12121af = (TextView) findViewById(R.id.btnRetry);
        this.f12117ab.setOnClickListener(this);
        this.f12121af.setOnClickListener(this);
        this.f12120ae.setOnClickListener(this);
        this.f12105P.setOnClickListener(this);
        this.f12106Q.setOnClickListener(this);
        this.f12107R.setOnClickListener(this);
        this.f12108S.setOnClickListener(this);
        this.f12102M = (ProgressBar) findViewById(R.id.progressBar);
        m16363s();
        this.f12114Y = new C0721a(10000, 1000);
        if (C0768b.f1957a == null) {
            new C0722b(this, null).execute(new String[0]);
            return;
        }
        m16364t();
        this.f12114Y.start();
    }
}
