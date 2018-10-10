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

import app.giaotieptienghan.model.PhraseItem;
import app.giaotieptienghan.repository.AppPreference;
import app.giaotieptienghan.repository.EndlessLoveDB;

@SuppressLint("WrongConstant")
public class QuizDetailSmal extends BaseAudioPlayActivity implements OnClickListener {
    /* renamed from: M */
    public ArrayList<PhraseItem> f12122M;
    /* renamed from: N */
    private ProgressBar f12123N;
    /* renamed from: O */
    private TextView f12124O;
    /* renamed from: P */
    private TextView f12125P;
    /* renamed from: Q */
    private TextView f12126Q;
    /* renamed from: R */
    private TextView f12127R;
    /* renamed from: S */
    private TextView f12128S;
    /* renamed from: T */
    private TextView f12129T;
    /* renamed from: U */
    private TextView f12130U;
    /* renamed from: V */
    private String f12131V;
    /* renamed from: W */
    private ArrayList<Integer> f12132W;
    /* renamed from: X */
    private ArrayList<String> f12133X;
    /* renamed from: Y */
    private int f12134Y = 0;
    /* renamed from: Z */
    private C0725a f12135Z;
    /* renamed from: aa */
    private AppPreference f12136aa;
    /* renamed from: ab */
    private ScrollView f12137ab;
    /* renamed from: ac */
    private int f12138ac;
    /* renamed from: ad */
    private String f12139ad;
    /* renamed from: ae */
    private String f12140ae;
    /* renamed from: af */
    private RelativeLayout f12141af;
    /* renamed from: ag */
    private TextView f12142ag;
    /* renamed from: ah */
    private TextView f12143ah;
    /* renamed from: ai */
    private TextView f12144ai;
    /* renamed from: aj */
    private TextView f12145aj;

    /* renamed from: com.example.english.QuizDetailSmal$1 */
    class C07231 implements Runnable {
        C07231() {
        }

        public void run() {
            QuizDetailSmal.this.f12134Y = QuizDetailSmal.this.f12134Y + 1;
            QuizDetailSmal.this.m16379t();
            if (QuizDetailSmal.this.f12135Z != null) {
                QuizDetailSmal.this.f12135Z.start();
            }
        }
    }

    /* renamed from: com.example.english.QuizDetailSmal$2 */
    class C07242 implements Runnable {
        C07242() {
        }

        public void run() {
            QuizDetailSmal.this.f12135Z.onFinish();
        }
    }

    /* renamed from: com.example.english.QuizDetailSmal$a */
    public class C0725a extends CountDownTimer {
        /* renamed from: b */
        private int[] f1829b;

        public C0725a(long j, long j2) {
            super(j, j2);
        }

        public void onFinish() {
            QuizDetailSmal.this.f12130U.setText("Time's up");
            QuizDetailSmal.this.m16381v();
            cancel();
        }

        public void onTick(long j) {
            try {
                Object valueOf;
                StringBuilder stringBuilder;
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append(j);
                stringBuilder2.append("");
                this.f1829b = Utils.m3034a(j / 1000);
                TextView g = QuizDetailSmal.this.f12130U;
                StringBuilder stringBuilder3 = new StringBuilder();
                if (this.f1829b[0] >= 10) {
                    valueOf = Integer.valueOf(this.f1829b[0]);
                } else {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("0");
                    stringBuilder.append(this.f1829b[0]);
                    valueOf = stringBuilder.toString();
                }
                stringBuilder3.append(valueOf);
                stringBuilder3.append(":");
                if (this.f1829b[1] >= 10) {
                    valueOf = Integer.valueOf(this.f1829b[1]);
                } else {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("0");
                    stringBuilder.append(this.f1829b[1]);
                    valueOf = stringBuilder.toString();
                }
                stringBuilder3.append(valueOf);
                g.setText(stringBuilder3.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* renamed from: com.example.english.QuizDetailSmal$b */
    private class C0726b extends AsyncTask<String, Void, ArrayList<PhraseItem>> {
        private C0726b() {
        }

        /* synthetic */ C0726b(QuizDetailSmal quizDetailSmal, C07231 c07231) {
            this();
        }

        /* renamed from: a */
        protected ArrayList<PhraseItem> doInBackground(String... strArr) {
            EndlessLoveDB db = new EndlessLoveDB(QuizDetailSmal.this);
            try {
                QuizDetailSmal quizDetailSmal;
                ArrayList e;
                db.initDB();
                db.getReadableDB();
                if (strArr[0] == null) {
                    if (QuizDetailSmal.this.f12139ad != null) {
                        quizDetailSmal = QuizDetailSmal.this;
                        e = db.mo2880e();
                    } else {
                        quizDetailSmal = QuizDetailSmal.this;
                        e = db.mo2872a(QuizDetailSmal.this.f12138ac);
                    }
                } else if ("0".equals(QuizDetailSmal.this.f12139ad)) {
                    quizDetailSmal = QuizDetailSmal.this;
                    e = db.mo2879d();
                } else {
                    quizDetailSmal = QuizDetailSmal.this;
                    e = db.mo2876b(QuizDetailSmal.this.f12138ac);
                }
                quizDetailSmal.f12122M = e;
            } catch (Exception e2) {
                e2.printStackTrace();
            } catch (Throwable th) {
                db.closeDB();
            }
            db.closeDB();
            return QuizDetailSmal.this.f12122M;
        }

        /* renamed from: a */
        protected void onPostExecute(ArrayList<PhraseItem> arrayList) {
            super.onPostExecute(arrayList);
            if (arrayList != null && arrayList.size() > 0) {
                QuizDetailSmal.this.m16379t();
                QuizDetailSmal.this.f12135Z.start();
                QuizDetailSmal.this.f12123N.setVisibility(8);
                QuizDetailSmal.this.f12137ab.setVisibility(0);
            }
        }

        protected void onPreExecute() {
            super.onPreExecute();
            QuizDetailSmal.this.f12123N.setVisibility(0);
            QuizDetailSmal.this.f12137ab.setVisibility(8);
        }
    }

    /* renamed from: b */
    private void m16370b(String str) {
        try {
            if (this.f12131V.equalsIgnoreCase(str)) {
                this.handler.postDelayed(new C07231(), 100);
            } else {
                this.handler.postDelayed(new C07242(), 500);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: s */
    private void m16378s() {
        int b = Utils.getScreenHeight(this);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(11);
        double d = (double) b;
        b = (int) (0.0125d * d);
        layoutParams.rightMargin = b;
        layoutParams.topMargin = b;
        this.f12130U.setLayoutParams(layoutParams);
        layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(9);
        layoutParams.leftMargin = b;
        layoutParams.topMargin = b;
        this.f12125P.setLayoutParams(layoutParams);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams2.addRule(3, R.id.point);
        layoutParams2.addRule(14);
        int i = (int) (0.025d * d);
        layoutParams2.topMargin = i;
        this.f12124O.setLayoutParams(layoutParams2);
        layoutParams2 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams2.addRule(3, R.id.word);
        layoutParams2.addRule(14);
        int i2 = (int) (d * 0.02d);
        layoutParams2.setMargins(i, i, i, 0);
        this.f12126Q.setPadding(i2, i2, i2, i2);
        this.f12126Q.setGravity(1);
        this.f12126Q.setLayoutParams(layoutParams2);
        layoutParams2 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams2.addRule(3, R.id.btnAns1);
        layoutParams2.addRule(14);
        layoutParams2.setMargins(i, i, i, 0);
        this.f12127R.setPadding(i2, i2, i2, i2);
        this.f12127R.setGravity(1);
        this.f12127R.setLayoutParams(layoutParams2);
        layoutParams2 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams2.addRule(3, R.id.btnAns2);
        layoutParams2.addRule(14);
        layoutParams2.setMargins(i, i, i, 0);
        this.f12128S.setPadding(i2, i2, i2, i2);
        this.f12128S.setGravity(1);
        this.f12128S.setLayoutParams(layoutParams2);
        layoutParams2 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams2.addRule(3, R.id.btnAns3);
        layoutParams2.addRule(14);
        layoutParams2.setMargins(i, i, i, 0);
        this.f12129T.setPadding(i2, i2, i2, i2);
        this.f12129T.setGravity(1);
        this.f12129T.setLayoutParams(layoutParams2);
        if (Utils.getScreenHeight(this) >= 800) {
            layoutParams2 = new RelativeLayout.LayoutParams(-1, -2);
            layoutParams2.addRule(12);
        }
    }

    /* renamed from: t */
    private void m16379t() {
        try {
            TextView textView = this.f12125P;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Score: ");
            stringBuilder.append(this.f12134Y);
            textView.setText(stringBuilder.toString());
            m16380u();
            if (this.f12132W == null) {
                this.f12132W = new ArrayList();
            } else {
                this.f12132W.clear();
            }
            if (this.f12133X == null) {
                this.f12133X = new ArrayList();
            } else {
                this.f12133X.clear();
            }
            Random random = new Random();
            int nextInt = random.nextInt(this.f12122M.size());
            PhraseItem phraseItem = (PhraseItem) this.f12122M.get(nextInt);
            String str = phraseItem.voice;
            this.f12132W.add(Integer.valueOf(nextInt));
            if (Utils.isStringEmpty(phraseItem.korean)) {
                m16379t();
            } else {
                TextView textView2 = this.f12124O;
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append(phraseItem.vietnamese);
                stringBuilder2.append("\n");
                stringBuilder2.append(phraseItem.pinyin == null ? "" : phraseItem.pinyin);
                textView2.setText(stringBuilder2.toString());
                this.f12131V = phraseItem.korean;
                this.f12133X.add(phraseItem.korean);
                while (this.f12133X.size() < 4) {
                    nextInt = random.nextInt(this.f12122M.size());
                    if (!this.f12132W.contains(Integer.valueOf(nextInt))) {
                        this.f12132W.add(Integer.valueOf(nextInt));
                        this.f12133X.add(((PhraseItem) this.f12122M.get(nextInt)).korean);
                    }
                }
                while (this.f12133X.size() > 0) {
                    TextView textView3 = null;
                    CharSequence charSequence = null;
                    nextInt = random.nextInt(this.f12133X.size());
                    if (Utils.isStringEmpty(this.f12126Q.getText().toString())) {
                        textView3 = this.f12126Q;
                        charSequence = (CharSequence) this.f12133X.get(nextInt);
                    } else if (Utils.isStringEmpty(this.f12127R.getText().toString())) {
                        textView3 = this.f12127R;
                        charSequence = (CharSequence) this.f12133X.get(nextInt);
                    } else if (Utils.isStringEmpty(this.f12128S.getText().toString())) {
                        textView3 = this.f12128S;
                        charSequence = (CharSequence) this.f12133X.get(nextInt);
                    } else if (Utils.isStringEmpty(this.f12129T.getText().toString())) {
                        textView3 = this.f12129T;
                        charSequence = (CharSequence) this.f12133X.get(nextInt);
                    } else {
                        this.f12133X.remove(nextInt);
                    }
                    textView3.setText(charSequence);
                    this.f12133X.remove(nextInt);
                }
            }
            if (this.f12136aa.mo2916i()) {
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
    private void m16380u() {
        this.f12126Q.setText("");
        this.f12127R.setText("");
        this.f12128S.setText("");
        this.f12129T.setText("");
    }

    /* renamed from: v */
    private void m16381v() {
        this.f12141af.setVisibility(0);
        TextView textView = this.f12142ag;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Score: ");
        stringBuilder.append(this.f12134Y);
        textView.setText(stringBuilder.toString());
        textView = this.f12143ah;
        stringBuilder = new StringBuilder();
        stringBuilder.append("High Score ");
        stringBuilder.append(this.f12136aa.mo2896a());
        textView.setText(stringBuilder.toString());
        if (this.f12136aa.mo2896a() < this.f12134Y) {
            this.f12136aa.mo2898a(this.f12134Y);
        }
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btnRetry) {
            this.f12141af.setVisibility(8);
            this.f12134Y = 0;
            m16379t();
            if (this.f12135Z != null) {
                this.f12135Z.start();
            }
        } else if (id != R.id.rlScore) {
            TextView textView;
            switch (id) {
                case R.id.btnAns1:
                    textView = this.f12126Q;
                    break;
                case R.id.btnAns2:
                    textView = this.f12127R;
                    break;
                case R.id.btnAns3:
                    textView = this.f12128S;
                    break;
                case R.id.btnAns4:
                    textView = this.f12129T;
                    break;
                case R.id.btnHome:
                    finish();
                    return;
                default:
                    return;
            }
            m16370b(textView.getText().toString());
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.quiz_detail_screen);
        bundle = getIntent().getExtras();
        if (bundle != null) {
            try {
                this.f12138ac = bundle.getInt("bundle_id");
                this.f12139ad = bundle.getString("bundle_fav_id");
                this.f12140ae = bundle.getString("bundle_plus_id");
                this.f12013n = bundle.getString("bundle_title");
                setTitle(!Utils.isStringEmpty(this.f12013n) ? this.f12013n : "Quiz");
            } catch (Exception unused) {
            }
        }
        mo10050r();

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        Resources resources;
        int i;
        getMenuInflater().inflate(R.menu.menu_search, menu);
        MenuItem findItem = menu.findItem(R.id.action_volume);
        if (this.f12136aa.mo2916i()) {
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
        if (this.f12135Z != null) {
            this.f12135Z.cancel();
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.action_volume) {
            Resources resources;
            int i;
            if (this.f12136aa.mo2916i()) {
                this.f12136aa.mo2904b(false);
                resources = getResources();
                i = R.drawable.ic_action_volume_off;
            } else {
                this.f12136aa.mo2904b(true);
                resources = getResources();
                i = R.drawable.ic_action_volume_up;
            }
            menuItem.setIcon(resources.getDrawable(i));
        }
        return super.onOptionsItemSelected(menuItem);
    }

    /* renamed from: r */
    public void mo10050r() {
        if (this.f12136aa == null) {
            this.f12136aa = new AppPreference(this);
        }
        this.f12137ab = (ScrollView) findViewById(R.id.slMain);
        this.f12125P = (TextView) findViewById(R.id.point);
        this.f12130U = (TextView) findViewById(R.id.tvTime);
        this.f12124O = (TextView) findViewById(R.id.word);
        this.f12126Q = (TextView) findViewById(R.id.btnAns1);
        this.f12127R = (TextView) findViewById(R.id.btnAns2);
        this.f12128S = (TextView) findViewById(R.id.btnAns3);
        this.f12129T = (TextView) findViewById(R.id.btnAns4);
        this.f12141af = (RelativeLayout) findViewById(R.id.rlScore);
        this.f12142ag = (TextView) findViewById(R.id.tvScore);
        this.f12143ah = (TextView) findViewById(R.id.tvHighScore);
        this.f12144ai = (TextView) findViewById(R.id.btnHome);
        this.f12145aj = (TextView) findViewById(R.id.btnRetry);
        this.f12141af.setOnClickListener(this);
        this.f12145aj.setOnClickListener(this);
        this.f12144ai.setOnClickListener(this);
        this.f12126Q.setOnClickListener(this);
        this.f12127R.setOnClickListener(this);
        this.f12128S.setOnClickListener(this);
        this.f12129T.setOnClickListener(this);
        this.f12123N = (ProgressBar) findViewById(R.id.progressBar);
        m16378s();
        this.f12135Z = new C0725a(10000, 1000);
        if (this.f12122M == null) {
            new C0726b(this, null).execute(new String[]{this.f12140ae});
            return;
        }
        m16379t();
        this.f12135Z.start();
    }
}
