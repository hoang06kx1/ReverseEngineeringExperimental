package app.giaotieptienghan;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.media.MediaPlayer;
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
    public ArrayList<PhraseItem> phraseItems1;
    /* renamed from: N */
    private ProgressBar progressBar1;
    /* renamed from: O */
    private TextView tvWord;
    /* renamed from: P */
    private TextView tvPoint;
    /* renamed from: Q */
    private TextView tvAns1;
    /* renamed from: R */
    private TextView tvAns2;
    /* renamed from: S */
    private TextView tvAns3;
    /* renamed from: T */
    private TextView tvAns4;
    /* renamed from: U */
    private TextView tvTime;
    /* renamed from: V */
    private String correctAnswer;
    /* renamed from: W */
    private ArrayList<Integer> f12132W;
    /* renamed from: X */
    private ArrayList<String> f12133X;
    /* renamed from: Y */
    private int f12134Y = 0;
    /* renamed from: Z */
    private QuizCountdownTimer quizCountDown;
    /* renamed from: aa */
    private AppPreference appPreference1;
    /* renamed from: ab */
    private ScrollView slMain;
    /* renamed from: ac */
    private int categoryId;
    /* renamed from: ad */
    private String favoriteId;
    /* renamed from: ae */
    private String plusId;
    /* renamed from: af */
    private RelativeLayout rlScore;
    /* renamed from: ag */
    private TextView tvScore;
    /* renamed from: ah */
    private TextView tvHighScore;
    /* renamed from: ai */
    private TextView tvHome;
    /* renamed from: aj */
    private TextView tvRetry;
    private String currentPhraseVoice;

    /* renamed from: com.example.english.QuizDetailSmal$1 */
    class NextQuizRunnable implements Runnable {
        NextQuizRunnable() {
        }

        public void run() {
            QuizDetailSmal.this.quizCountDown.cancel();
            if (QuizDetailSmal.this.appPreference1.isSoundOn()) {
                //if (QuizDetailSmal.this.mediaPlayer != null && QuizDetailSmal.this.mediaPlayer.isPlaying()) {
                //    resetMediaPlayer();
                //}
                int identifier = getResources().getIdentifier("success", "raw", getPackageName());
                if (identifier != 0) {
                    playAudio(identifier, new OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            QuizDetailSmal.this.f12134Y = QuizDetailSmal.this.f12134Y + 1;
                            QuizDetailSmal.this.generateSampleAnswers();
                            if (QuizDetailSmal.this.quizCountDown != null) {
                                QuizDetailSmal.this.quizCountDown.start();
                            }
                        }
                    });
                }
            }
        }
    }

    /* renamed from: com.example.english.QuizDetailSmal$2 */
    class WrongAnswerRunnable implements Runnable {
        WrongAnswerRunnable() {
        }

        public void run() {
            if (QuizDetailSmal.this.appPreference1.isSoundOn()) {
                //if (QuizDetailSmal.this.mediaPlayer != null && QuizDetailSmal.this.mediaPlayer.isPlaying()) {
                //    resetMediaPlayer();
                //}
                int identifier = getResources().getIdentifier("fail", "raw", getPackageName());
                if (identifier != 0) {
                    playAudio(identifier, (OnCompletionListener) QuizDetailSmal.this);
                }
            }
            QuizDetailSmal.this.quizCountDown.onFinish();
        }
    }

    /* renamed from: com.example.english.QuizDetailSmal$a */
    public class QuizCountdownTimer extends CountDownTimer {
        /* renamed from: b */
        private int[] f1829b;

        public QuizCountdownTimer(long j, long j2) {
            super(j, j2);
        }

        public void onFinish() {
            QuizDetailSmal.this.tvTime.setText("Hết giờ");
            QuizDetailSmal.this.showScore();
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
                TextView g = QuizDetailSmal.this.tvTime;
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
    private class GetDataTask extends AsyncTask<String, Void, ArrayList<PhraseItem>> {
        private GetDataTask() {
        }

        /* synthetic */ GetDataTask(QuizDetailSmal quizDetailSmal, NextQuizRunnable nextQuizRunnable) {
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
                    if (QuizDetailSmal.this.favoriteId != null) {
                        quizDetailSmal = QuizDetailSmal.this;
                        e = db.getFavoritePhrases();
                    } else {
                        quizDetailSmal = QuizDetailSmal.this;
                        e = db.getPhrasesByCategoryId(QuizDetailSmal.this.categoryId);
                    }
                } else if ("0".equals(QuizDetailSmal.this.favoriteId)) {
                    quizDetailSmal = QuizDetailSmal.this;
                    e = db.getFavoriteGrammars();
                } else {
                    quizDetailSmal = QuizDetailSmal.this;
                    e = db.getGrammarsByCategoryId(QuizDetailSmal.this.categoryId);
                }
                quizDetailSmal.phraseItems1 = e;
            } catch (Exception e2) {
                e2.printStackTrace();
            } catch (Throwable th) {
                db.closeDB();
            }
            db.closeDB();
            return QuizDetailSmal.this.phraseItems1;
        }

        /* renamed from: a */
        protected void onPostExecute(ArrayList<PhraseItem> arrayList) {
            super.onPostExecute(arrayList);
            if (arrayList != null && arrayList.size() > 0) {
                QuizDetailSmal.this.generateSampleAnswers();
                QuizDetailSmal.this.quizCountDown.start();
                QuizDetailSmal.this.progressBar1.setVisibility(8);
                QuizDetailSmal.this.slMain.setVisibility(0);
            }
        }

        protected void onPreExecute() {
            super.onPreExecute();
            QuizDetailSmal.this.progressBar1.setVisibility(0);
            QuizDetailSmal.this.slMain.setVisibility(8);
        }
    }

    /* renamed from: b */
    private void checkResult(String str) {
        try {
            if (this.correctAnswer.equalsIgnoreCase(str)) {
                this.handler.postDelayed(new NextQuizRunnable(), 100);
            } else {
                this.handler.postDelayed(new WrongAnswerRunnable(), 500);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: s */
    private void layoutViews() {
        int b = Utils.getScreenHeight(this);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(11);
        double d = (double) b;
        b = (int) (0.0125d * d);
        layoutParams.rightMargin = b;
        layoutParams.topMargin = b;
        this.tvTime.setLayoutParams(layoutParams);
        layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(9);
        layoutParams.leftMargin = b;
        layoutParams.topMargin = b;
        this.tvPoint.setLayoutParams(layoutParams);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams2.addRule(3, R.id.point);
        layoutParams2.addRule(14);
        int i = (int) (0.025d * d);
        layoutParams2.topMargin = i;
        this.tvWord.setLayoutParams(layoutParams2);
        layoutParams2 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams2.addRule(3, R.id.word);
        layoutParams2.addRule(14);
        int i2 = (int) (d * 0.02d);
        layoutParams2.setMargins(i, i, i, 0);
        this.tvAns1.setPadding(i2, i2, i2, i2);
        this.tvAns1.setGravity(1);
        this.tvAns1.setLayoutParams(layoutParams2);
        layoutParams2 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams2.addRule(3, R.id.btnAns1);
        layoutParams2.addRule(14);
        layoutParams2.setMargins(i, i, i, 0);
        this.tvAns2.setPadding(i2, i2, i2, i2);
        this.tvAns2.setGravity(1);
        this.tvAns2.setLayoutParams(layoutParams2);
        layoutParams2 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams2.addRule(3, R.id.btnAns2);
        layoutParams2.addRule(14);
        layoutParams2.setMargins(i, i, i, 0);
        this.tvAns3.setPadding(i2, i2, i2, i2);
        this.tvAns3.setGravity(1);
        this.tvAns3.setLayoutParams(layoutParams2);
        layoutParams2 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams2.addRule(3, R.id.btnAns3);
        layoutParams2.addRule(14);
        layoutParams2.setMargins(i, i, i, 0);
        this.tvAns4.setPadding(i2, i2, i2, i2);
        this.tvAns4.setGravity(1);
        this.tvAns4.setLayoutParams(layoutParams2);
        if (Utils.getScreenHeight(this) >= 800) {
            layoutParams2 = new RelativeLayout.LayoutParams(-1, -2);
            layoutParams2.addRule(12);
        }
    }

    /* renamed from: t */
    private void generateSampleAnswers() {
        try {
            TextView textView = this.tvPoint;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Điểm: ");
            stringBuilder.append(this.f12134Y);
            textView.setText(stringBuilder.toString());
            resetAnswers();
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
            int nextInt = random.nextInt(this.phraseItems1.size());
            PhraseItem phraseItem = (PhraseItem) this.phraseItems1.get(nextInt);
            currentPhraseVoice = phraseItem.voice;
            this.f12132W.add(Integer.valueOf(nextInt));
            if (Utils.isStringEmpty(phraseItem.korean)) {
                generateSampleAnswers();
            } else {
                TextView textView2 = this.tvWord;
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append(phraseItem.vietnamese);
                stringBuilder2.append("\n");
                stringBuilder2.append(phraseItem.pinyin == null ? "" : phraseItem.pinyin);
                textView2.setText(stringBuilder2.toString());
                this.correctAnswer = phraseItem.korean;
                this.f12133X.add(phraseItem.korean);
                while (this.f12133X.size() < 4) {
                    nextInt = random.nextInt(this.phraseItems1.size());
                    if (!this.f12132W.contains(Integer.valueOf(nextInt))) {
                        this.f12132W.add(Integer.valueOf(nextInt));
                        this.f12133X.add(((PhraseItem) this.phraseItems1.get(nextInt)).korean);
                    }
                }
                while (this.f12133X.size() > 0) {
                    TextView textView3 = null;
                    CharSequence charSequence = null;
                    nextInt = random.nextInt(this.f12133X.size());
                    if (Utils.isStringEmpty(this.tvAns1.getText().toString())) {
                        textView3 = this.tvAns1;
                        charSequence = (CharSequence) this.f12133X.get(nextInt);
                    } else if (Utils.isStringEmpty(this.tvAns2.getText().toString())) {
                        textView3 = this.tvAns2;
                        charSequence = (CharSequence) this.f12133X.get(nextInt);
                    } else if (Utils.isStringEmpty(this.tvAns3.getText().toString())) {
                        textView3 = this.tvAns3;
                        charSequence = (CharSequence) this.f12133X.get(nextInt);
                    } else if (Utils.isStringEmpty(this.tvAns4.getText().toString())) {
                        textView3 = this.tvAns4;
                        charSequence = (CharSequence) this.f12133X.get(nextInt);
                    } else {
                        this.f12133X.remove(nextInt);
                    }
                    textView3.setText(charSequence);
                    this.f12133X.remove(nextInt);
                }
            }
            if (this.appPreference1.isSoundOn()) {
                if (this.mediaPlayer != null && this.mediaPlayer.isPlaying()) {
                    resetMediaPlayer();
                }
                Resources resources = getResources();
                stringBuilder = new StringBuilder();
                stringBuilder.append(currentPhraseVoice);
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
    private void resetAnswers() {
        this.tvAns1.setText("");
        this.tvAns2.setText("");
        this.tvAns3.setText("");
        this.tvAns4.setText("");
    }

    /* renamed from: v */
    private void showScore() {
        this.rlScore.setVisibility(0);
        TextView textView = this.tvScore;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Điểm: ");
        stringBuilder.append(this.f12134Y);
        textView.setText(stringBuilder.toString());
        textView = this.tvHighScore;
        stringBuilder = new StringBuilder();
        stringBuilder.append("Kỉ lục điểm: ");
        stringBuilder.append(this.appPreference1.getHighScore());
        textView.setText(stringBuilder.toString());
        if (this.appPreference1.getHighScore() < this.f12134Y) {
            this.appPreference1.setHighScore(this.f12134Y);
        }
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btnRetry) {
            this.rlScore.setVisibility(8);
            this.f12134Y = 0;
            generateSampleAnswers();
            if (this.quizCountDown != null) {
                this.quizCountDown.start();
            }
        } else if (id == R.id.bt_replay) {
            if (this.appPreference1.isSoundOn()) {
                //if (this.mediaPlayer != null && this.mediaPlayer.isPlaying()) {
                //    resetMediaPlayer();
                //}
                Resources resources = getResources();
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(currentPhraseVoice);
                stringBuilder.append("_f");
                int identifier = resources.getIdentifier(stringBuilder.toString(), "raw", getPackageName());
                if (identifier != 0) {
                    playAudio(identifier, (OnCompletionListener) this);
                }
            }
        } else if (id != R.id.rlScore) {
            TextView textView;
            switch (id) {
                case R.id.btnAns1:
                    textView = this.tvAns1;
                    break;
                case R.id.btnAns2:
                    textView = this.tvAns2;
                    break;
                case R.id.btnAns3:
                    textView = this.tvAns3;
                    break;
                case R.id.btnAns4:
                    textView = this.tvAns4;
                    break;
                case R.id.btnHome:
                    finish();
                    return;
                default:
                    return;
            }
            checkResult(textView.getText().toString());
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.quiz_detail_screen);
        bundle = getIntent().getExtras();
        if (bundle != null) {
            try {
                this.categoryId = bundle.getInt("bundle_id");
                this.favoriteId = bundle.getString("bundle_fav_id");
                this.plusId = bundle.getString("bundle_plus_id");
                this.bundle_title = bundle.getString("bundle_title");
                setTitle(!Utils.isStringEmpty(this.bundle_title) ? this.bundle_title : "Game luyện nghe");
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
        if (this.appPreference1.isSoundOn()) {
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
        if (this.quizCountDown != null) {
            this.quizCountDown.cancel();
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.action_volume) {
            Resources resources;
            int i;
            if (this.appPreference1.isSoundOn()) {
                this.appPreference1.setSoundOn(false);
                resources = getResources();
                i = R.drawable.ic_action_volume_off;
            } else {
                this.appPreference1.setSoundOn(true);
                resources = getResources();
                i = R.drawable.ic_action_volume_up;
            }
            menuItem.setIcon(resources.getDrawable(i));
        }
        return super.onOptionsItemSelected(menuItem);
    }

    /* renamed from: r */
    public void mo10050r() {
        if (this.appPreference1 == null) {
            this.appPreference1 = new AppPreference(this);
        }
        initAds();
        this.slMain = (ScrollView) findViewById(R.id.slMain);
        this.tvPoint = (TextView) findViewById(R.id.point);
        this.tvTime = (TextView) findViewById(R.id.tvTime);
        this.tvWord = (TextView) findViewById(R.id.word);
        this.tvAns1 = (TextView) findViewById(R.id.btnAns1);
        this.tvAns2 = (TextView) findViewById(R.id.btnAns2);
        this.tvAns3 = (TextView) findViewById(R.id.btnAns3);
        this.tvAns4 = (TextView) findViewById(R.id.btnAns4);
        this.rlScore = (RelativeLayout) findViewById(R.id.rlScore);
        this.tvScore = (TextView) findViewById(R.id.tvScore);
        this.tvHighScore = (TextView) findViewById(R.id.tvHighScore);
        this.tvHome = (TextView) findViewById(R.id.btnHome);
        this.tvRetry = (TextView) findViewById(R.id.btnRetry);
        this.rlScore.setOnClickListener(this);
        this.tvRetry.setOnClickListener(this);
        this.tvHome.setOnClickListener(this);
        this.tvAns1.setOnClickListener(this);
        this.tvAns2.setOnClickListener(this);
        this.tvAns3.setOnClickListener(this);
        this.tvAns4.setOnClickListener(this);
        this.progressBar1 = (ProgressBar) findViewById(R.id.progressBar);
        findViewById(R.id.bt_replay).setOnClickListener(this);
        layoutViews();
        this.quizCountDown = new QuizCountdownTimer(30000, 1000);
        if (this.phraseItems1 == null) {
            new GetDataTask(this, null).execute(new String[]{this.plusId});
            return;
        }
        generateSampleAnswers();
        this.quizCountDown.start();
    }
}
