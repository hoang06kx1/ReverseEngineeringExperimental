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

import app.giaotieptienghan.customview.SelectCategoryDialogFragment;
import app.giaotieptienghan.model.PhraseItem;
import app.giaotieptienghan.repository.AppPreference;
import app.giaotieptienghan.repository.EndlessLoveDB;

@SuppressLint("WrongConstant")
public class QuizDetail extends BaseAudioPlayActivity implements OnClickListener {
    /* renamed from: M */
    private ProgressBar progressBar1;
    /* renamed from: N */
    private TextView tvWord;
    /* renamed from: O */
    private TextView tvPoint;
    /* renamed from: P */
    private TextView tvAns1;
    /* renamed from: Q */
    private TextView tvAns2;
    /* renamed from: R */
    private TextView tvAns3;
    /* renamed from: S */
    private TextView tvAns4;
    /* renamed from: T */
    private TextView tvTime;
    /* renamed from: U */
    private String correctAnswer;
    /* renamed from: V */
    private ArrayList<Integer> selectedPhraseIndices;
    /* renamed from: W */
    private ArrayList<String> sampleAnswers;
    /* renamed from: X */
    private int currentScore = 0;
    /* renamed from: Y */
    private QuizCountdown quizCountdown;
    /* renamed from: Z */
    private AppPreference appPreference1;
    /* renamed from: aa */
    private ScrollView scrollMain;
    /* renamed from: ab */
    private RelativeLayout rlScore;
    /* renamed from: ac */
    private TextView tvScore;
    /* renamed from: ad */
    private TextView tvHighScore;
    /* renamed from: ae */
    private TextView btHome;
    /* renamed from: af */
    private TextView btRetry;

    private String currentPhraseVoice;

    private int categoryId = -1;

    /* renamed from: com.example.english.QuizDetail$1 */
    class NextQuizRunnable implements Runnable {
        NextQuizRunnable() {
        }

        public void run() {
            QuizDetail.this.currentScore = QuizDetail.this.currentScore + 1;
            QuizDetail.this.generateSampleAnswers();
            if (QuizDetail.this.quizCountdown != null) {
                QuizDetail.this.quizCountdown.start();
            }
        }
    }

    /* renamed from: com.example.english.QuizDetail$2 */
    class WrongAnswerRunnable implements Runnable {
        WrongAnswerRunnable() {
        }

        public void run() {
            QuizDetail.this.quizCountdown.onFinish();
        }
    }

    /* renamed from: com.example.english.QuizDetail$a */
    public class QuizCountdown extends CountDownTimer {
        /* renamed from: b */
        private int[] f1824b;

        public QuizCountdown(long j, long j2) {
            super(j, j2);
        }

        public void onFinish() {
            QuizDetail.this.tvTime.setText("Hết giờ");
            QuizDetail.this.showQuizScore();
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
                TextView e = QuizDetail.this.tvTime;
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
    private class GetDataTask extends AsyncTask<String, Void, ArrayList<PhraseItem>> {
        private GetDataTask() {
        }

        /* synthetic */ GetDataTask(QuizDetail quizDetail, NextQuizRunnable nextQuizRunnable) {
            this();
        }

        /* renamed from: a */
        protected ArrayList<PhraseItem> doInBackground(String... strArr) {
            EndlessLoveDB db = new EndlessLoveDB(QuizDetail.this);
            try {
                db.initDB();
                db.getReadableDB();
                if (QuizDetail.this.categoryItems.size() == 0) {
                    QuizDetail.this.categoryItems = db.getFavoriteCategories(null);
                }
                QuizDetail.this.phraseItems = db.getPhrasesByCategoryId(QuizDetail.this.categoryId);
            } catch (Exception e) {
                e.printStackTrace();
            } catch (Throwable th) {
                db.closeDB();
            }
            db.closeDB();
            return QuizDetail.this.phraseItems;
        }

        /* renamed from: a */
        protected void onPostExecute(ArrayList<PhraseItem> arrayList) {
            super.onPostExecute(arrayList);
            if (arrayList != null) {
                QuizDetail.this.generateSampleAnswers();
                QuizDetail.this.quizCountdown.start();
                QuizDetail.this.progressBar1.setVisibility(8);
                QuizDetail.this.scrollMain.setVisibility(0);
            }
        }

        protected void onPreExecute() {
            super.onPreExecute();
            QuizDetail.this.progressBar1.setVisibility(0);
            QuizDetail.this.scrollMain.setVisibility(8);
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
            stringBuilder.append(this.currentScore);
            textView.setText(stringBuilder.toString());
            resetSampleAnswers();
            if (this.selectedPhraseIndices == null) {
                this.selectedPhraseIndices = new ArrayList();
            } else {
                this.selectedPhraseIndices.clear();
            }
            if (this.sampleAnswers == null) {
                this.sampleAnswers = new ArrayList();
            } else {
                this.sampleAnswers.clear();
            }
            Random random = new Random();
            int nextInt = random.nextInt(this.phraseItems.size());
            PhraseItem phraseItem = (PhraseItem) this.phraseItems.get(nextInt);
            currentPhraseVoice = phraseItem.voice;
            this.selectedPhraseIndices.add(Integer.valueOf(nextInt));
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
                this.sampleAnswers.add(phraseItem.korean);
                while (this.sampleAnswers.size() < 4) {
                    nextInt = random.nextInt(this.phraseItems.size());
                    if (!this.selectedPhraseIndices.contains(Integer.valueOf(nextInt))) {
                        this.selectedPhraseIndices.add(Integer.valueOf(nextInt));
                        this.sampleAnswers.add(((PhraseItem) this.phraseItems.get(nextInt)).korean);
                    }
                }
                while (this.sampleAnswers.size() > 0) {
                    TextView textView3 = new TextView(this);
                    CharSequence charSequence = "";
                    nextInt = random.nextInt(this.sampleAnswers.size());
                    if (Utils.isStringEmpty(this.tvAns1.getText().toString())) {
                        textView3 = this.tvAns1;
                        charSequence = (CharSequence) this.sampleAnswers.get(nextInt);
                    } else if (Utils.isStringEmpty(this.tvAns2.getText().toString())) {
                        textView3 = this.tvAns2;
                        charSequence = (CharSequence) this.sampleAnswers.get(nextInt);
                    } else if (Utils.isStringEmpty(this.tvAns3.getText().toString())) {
                        textView3 = this.tvAns3;
                        charSequence = (CharSequence) this.sampleAnswers.get(nextInt);
                    } else if (Utils.isStringEmpty(this.tvAns4.getText().toString())) {
                        textView3 = this.tvAns4;
                        charSequence = (CharSequence) this.sampleAnswers.get(nextInt);
                    } else {
                        this.sampleAnswers.remove(nextInt);
                    }
                    textView3.setText(charSequence);
                    this.sampleAnswers.remove(nextInt);
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
    private void resetSampleAnswers() {
        this.tvAns1.setText("");
        this.tvAns2.setText("");
        this.tvAns3.setText("");
        this.tvAns4.setText("");
    }

    /* renamed from: v */
    private void showQuizScore() {
        this.rlScore.setVisibility(0);
        TextView textView = this.tvScore;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Điểm: ");
        stringBuilder.append(this.currentScore);
        textView.setText(stringBuilder.toString());
        textView = this.tvHighScore;
        stringBuilder = new StringBuilder();
        stringBuilder.append("Kỉ lục điểm: ");
        stringBuilder.append(this.appPreference1.getHighScore());
        textView.setText(stringBuilder.toString());
        if (this.appPreference1.getHighScore() < this.currentScore) {
            this.appPreference1.setHighScore(this.currentScore);
        }
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btnRetry) {
            this.rlScore.setVisibility(8);
            this.currentScore = 0;
            generateSampleAnswers();
            if (this.quizCountdown != null) {
                this.quizCountdown.start();
            }
        } else if (id == R.id.bt_replay) {
            if (this.appPreference1.isSoundOn()) {
                if (this.mediaPlayer != null && this.mediaPlayer.isPlaying()) {
                    resetMediaPlayer();
                }
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
        try {
            setTitle("Game luyện nghe");
            initViews();
        } catch (Exception unused) {
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        Resources resources;
        int i;
        getMenuInflater().inflate(R.menu.menu_search_2, menu);
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
        if (this.quizCountdown != null) {
            this.quizCountdown.cancel();
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
        } else if (menuItem.getItemId() == R.id.menu_category) {
            SelectCategoryDialogFragment selectCategoryDialogFragment = new SelectCategoryDialogFragment();
            selectCategoryDialogFragment.show(getSupportFragmentManager(), "category_dialog_fragment");
        }
        return super.onOptionsItemSelected(menuItem);
    }

    /* renamed from: r */
    public void initViews() {
        if (this.appPreference1 == null) {
            this.appPreference1 = new AppPreference(this);
        }
        this.scrollMain = (ScrollView) findViewById(R.id.slMain);
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
        this.btHome = (TextView) findViewById(R.id.btnHome);
        this.btRetry = (TextView) findViewById(R.id.btnRetry);
        this.rlScore.setOnClickListener(this);
        this.btRetry.setOnClickListener(this);
        this.btHome.setOnClickListener(this);
        this.tvAns1.setOnClickListener(this);
        this.tvAns2.setOnClickListener(this);
        this.tvAns3.setOnClickListener(this);
        this.tvAns4.setOnClickListener(this);
        findViewById(R.id.bt_replay).setOnClickListener(this);
        this.progressBar1 = (ProgressBar) findViewById(R.id.progressBar);
        layoutViews();
        this.quizCountdown = new QuizCountdown(15000, 1000);
        this.categoryId = Integer.valueOf(appPreference.getSelectedGameId());
        new GetDataTask(this, null).execute(new String[0]);
        return;
        //generateSampleAnswers();
        //this.quizCountdown.start();
    }
}
