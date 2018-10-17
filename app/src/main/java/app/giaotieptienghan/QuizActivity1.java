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
import android.support.v4.content.ContextCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
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

import app.giaotieptienghan.customview.QuizTypeOne;
import app.giaotieptienghan.customview.CustomViewPager;
import app.giaotieptienghan.customview.QuizTypeTwoView;
import app.giaotieptienghan.customview.SelectCategoryDialogFragment;
import app.giaotieptienghan.model.QuizAdapter;
import app.giaotieptienghan.model.PhraseItem;
import app.giaotieptienghan.repository.EndlessLoveDB;

@SuppressLint("WrongConstant")
public class QuizActivity1 extends BaseAudioPlayActivity implements OnClickListener, QuizAdapter.QuizInterface {
    /* renamed from: M */
    private CustomViewPager viewPager;
    /* renamed from: N */
    private QuizAdapter quizAdapter;
    /* renamed from: O */
    private ProgressBar progressBar1;
    /* renamed from: P */
    private RelativeLayout llCheck;
    /* renamed from: Q */
    private RelativeLayout llCheckParent;
    /* renamed from: R */
    private TextView tvCheck;
    /* renamed from: S */
    private TextView tvContinue;
    /* renamed from: T */
    private TextView tvCorrect;
    /* renamed from: U */
    private TextView tvKoreanTest;
    /* renamed from: V */
    private TextView tvEnglishTest;
    /* renamed from: W */
    private TextView tvSkip;
    /* renamed from: X */
    private TextView tvScore;
    /* renamed from: Y */
    private TextView tvPlus;
    /* renamed from: Z */
    private TextView tvTitle;
    /* renamed from: aa */
    private TextView tvHigh;
    /* renamed from: ab */
    private LinearLayout f12090ab;
    /* renamed from: ac */
    private int currentScore = 0;
    /* renamed from: ad */
    private int currentBonusScore = 0;
    /* renamed from: ae */
    private boolean isAnswerCorrected;
    /* renamed from: af */
    private PhraseItem phraseItem;
    /* renamed from: ag */
    private int nextViewPagerPageIndex = 0;
    /* renamed from: ah */
    private final int f12096ah = 100;
    /* renamed from: ai */
    private String selectedAnswerText;
    /* renamed from: aj */
    private int categoryId;
    /* renamed from: ak */
    private String favorite_id;
    /* renamed from: al */
    private String plus_id;
    /* renamed from: am */
    private final String[] courageWords = new String[]{"Well Done!", "Nice Job!", "Excellent!", "Fantastic!", "Amazing!", "Awesome!", "Splendid!", "Unbelievable!", "Wonderful!"};

    /* renamed from: com.example.english.QuizActivity1$1 */
    class C07151 implements DialogInterface.OnClickListener {
        C07151() {
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            QuizActivity1.this.nextViewPagerPageIndex = 0;
            QuizActivity1.this.currentScore = 0;
            QuizActivity1.this.currentBonusScore = 0;
            QuizActivity1.this.tvScore.setText("0");
            QuizActivity1.this.tvPlus.setText("0");
            QuizActivity1.this.viewPager.setCurrentItem(0, true);
            TextView h = QuizActivity1.this.tvTitle;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(QuizActivity1.this.nextViewPagerPageIndex + 1);
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
            QuizActivity1.this.tvPlus.setVisibility(8);
        }

        public void onAnimationRepeat(Animation animation) {
        }

        public void onAnimationStart(Animation animation) {
            QuizActivity1.this.tvPlus.setVisibility(0);
        }
    }

    /* renamed from: com.example.english.QuizActivity1$a */
    private class getDataTask extends AsyncTask<String, Void, List<PhraseItem>> {
        private getDataTask() {
        }

        /* synthetic */ getDataTask(QuizActivity1 quizActivity1, C07151 c07151) {
            this();
        }

        /* renamed from: a */
        protected List<PhraseItem> doInBackground(String... strArr) {
            EndlessLoveDB db = new EndlessLoveDB(QuizActivity1.this);
            List<PhraseItem> arrayList = new ArrayList();
            try {
                db.initDB();
                db.getReadableDB();
                if (QuizActivity1.this.categoryItems.size() == 0) {
                    QuizActivity1.this.categoryItems = db.getFavoriteCategories(null);
                }
                ArrayList e = strArr[0] == null ? QuizActivity1.this.favorite_id != null ? db.getFavoritePhrases() : db.getPhrasesByCategoryId(QuizActivity1.this.categoryId) : "0".equals(QuizActivity1.this.favorite_id) ? db.getFavoriteGrammars() : db.getGrammarsByCategoryId(QuizActivity1.this.categoryId);
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
                QuizActivity1.this.phraseItems.clear();
                for (PhraseItem phraseItem : list) {
                    QuizActivity1.this.phraseItems.add(phraseItem);
                    QuizActivity1.this.phraseItems.add(phraseItem);
                    QuizActivity1.this.phraseItems.add(phraseItem);
                }
                Collections.shuffle(QuizActivity1.this.phraseItems, new Random(System.nanoTime()));
                QuizActivity1.this.quizAdapter.mo8066a(QuizActivity1.this.phraseItems);
                QuizActivity1.this.quizAdapter.notifyDataSetChanged();
                QuizActivity1.this.f12090ab.setVisibility(0);
                QuizActivity1.this.tvSkip.setVisibility(0);
                QuizActivity1.this.tvCheck.setVisibility(0);
                TextView h = QuizActivity1.this.tvTitle;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(QuizActivity1.this.nextViewPagerPageIndex + 1);
                stringBuilder.append("/");
                stringBuilder.append(QuizActivity1.this.phraseItems.size());
                h.setText(stringBuilder.toString());
            }
            QuizActivity1.this.progressBar1.setVisibility(8);
        }

        protected void onPreExecute() {
            super.onPreExecute();
        }
    }

    /* renamed from: a */
    private void completeQuiz(Context context) {
        if (this.appPreference.mo2897a(this.bundle_title) < this.currentScore) {
            this.appPreference.mo2899a(this.bundle_title, this.currentScore);
            TextView textView = this.tvHigh;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(this.currentScore);
            stringBuilder.append("");
            textView.setText(stringBuilder.toString());
        }
        Builder builder = new Builder(context, R.style.MyDialogTheme);
        builder.setTitle("Congratulations!!!");
        builder.setIcon(Utils.getDrawable(this, R.drawable.ic_launcher));
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("You have completed the quizAdapter with ");
        stringBuilder2.append(this.currentScore);
        stringBuilder2.append(" score! ");
        builder.setMessage(stringBuilder2.toString());
        builder.setCancelable(false);
        builder.setPositiveButton("Again", new C07151());
        builder.setNegativeButton("Cancel", new C07162());
        builder.create().show();
    }

    /* renamed from: a */
    private void displayResultBoard(String selectedAnswerText, boolean correct) {
        try {
            this.tvKoreanTest.setText(selectedAnswerText);
            this.llCheckParent.setVisibility(0);
            if (correct) {
                int identifier = getResources().getIdentifier("success", "raw", getPackageName());
                if (identifier != 0) {
                    playAudio(identifier, this);
                }
                this.currentBonusScore = 5;
                this.currentScore += 5;
                this.isAnswerCorrected = true;
            } else {
                int identifier = getResources().getIdentifier("fail", "raw", getPackageName());
                if (identifier != 0) {
                    playAudio(identifier, this);
                }
                this.isAnswerCorrected = false;
                this.currentBonusScore = -3;
                if (this.currentScore >= 3) {
                    this.currentScore -= 3;
                } else {
                    this.currentScore = 0;
                }
            }
            changeResultBoardColor(this.isAnswerCorrected);
            TextView textView = this.tvScore;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(this.currentScore);
            stringBuilder.append("");
            textView.setText(stringBuilder.toString());
            textView = this.tvPlus;
            stringBuilder = new StringBuilder();
            stringBuilder.append(this.currentBonusScore);
            stringBuilder.append("");
            textView.setText(stringBuilder.toString());
            Animation translateAnimation = new TranslateAnimation(0.0f, 0.0f, 0.0f, (-this.tvScore.getY()) + ((float) this.tvScore.getHeight()));
            translateAnimation.setDuration(500);
            translateAnimation.setAnimationListener(new C07173());
            this.tvPlus.startAnimation(translateAnimation);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: b */
    private void changeResultBoardColor(boolean correct) {
        RelativeLayout relativeLayout = this.llCheck;
        int i = R.color.green_check_layout_false;
        relativeLayout.setBackgroundColor(Utils.getColor(this, correct ? R.color.green_check_layout : R.color.green_check_layout_false));
        TextView textView = this.tvContinue;
        if (correct) {
            i = R.color.green_check_text;
        }
        textView.setTextColor(Utils.getColor(this, i));
        this.tvContinue.setBackgroundResource(correct ? R.drawable.bg_continue_true : R.drawable.bg_continue_false);
        this.tvCorrect.setTextColor(Utils.getColor(this, correct ? R.color.you_are_corect : R.color.you_are_wrong));
        this.tvCorrect.setText(correct ? this.courageWords[new Random().nextInt(this.courageWords.length)] : "You are wrong!");
        this.tvContinue.setText(correct ? "Continue" : "Try Again");
    }

    /* renamed from: c */
    private int getViewPagerPagePlusOffset(int i) {
        return this.viewPager.getCurrentItem() + i;
    }

    /* renamed from: u */
    private void m16348u() {
        String str = this.phraseItem.korean;
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
            playAudioByGoogleTranslate(phraseItem.korean, onCompletionListener, false);
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
        this.phraseItem = phraseItem;
        this.isAnswerCorrected = z;
        this.selectedAnswerText = str;
        if (!Utils.isStringEmpty(str)) {
            this.tvCheck.setEnabled(true);
            this.tvCheck.setTextColor(Utils.getColor(this, R.color.white));
            this.tvCheck.setBackgroundResource(R.drawable.bg_check_enable);
        }
    }

    /* renamed from: a */
    public void mo2843a(PhraseItem phraseItem, boolean z) {
        this.phraseItem = phraseItem;
        this.selectedAnswerText = phraseItem.korean;
        this.isAnswerCorrected = true;
        this.tvCheck.setEnabled(true);
        this.tvCheck.setTextColor(Utils.getColor(this, R.color.white));
        this.tvCheck.setBackgroundResource(R.drawable.bg_check_enable);
        m16348u();
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 100 && i2 == -1 && intent != null) {
            try {
                ArrayList stringArrayListExtra = intent.getStringArrayListExtra("android.speech.extra.RESULTS");
                TextView textView = this.tvEnglishTest;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Your answer : ");
                stringBuilder.append((String) stringArrayListExtra.get(0));
                textView.setText(stringBuilder.toString());
                this.tvEnglishTest.setVisibility(0);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @SuppressLint("ResourceType")
    @Override
    public void onClick(View view) {
        int id = view.getId();
        TextView textView;
        StringBuilder stringBuilder;
        if (id != R.id.tvSkip) {
            switch (id) {
                case R.id.tvCheck:
                    displayResultBoard(this.selectedAnswerText, this.isAnswerCorrected);
                    return;
                case R.id.tvContinue:
                    if (!this.isAnswerCorrected) {
                        StringBuilder stringBuilder2 = new StringBuilder();
                        stringBuilder2.append("getCurrentItem ");
                        stringBuilder2.append(this.viewPager.getCurrentItem());
                        CustomViewPager customViewPager = this.viewPager;
                        stringBuilder2 = new StringBuilder();
                        stringBuilder2.append("myview");
                        stringBuilder2.append(this.viewPager.getCurrentItem());
                        View findViewWithTag = customViewPager.findViewWithTag(stringBuilder2.toString());
                        if (findViewWithTag != null) {
                            if (findViewWithTag instanceof QuizTypeOne) {
                                ((QuizTypeOne) findViewWithTag).resetQuiz();
                            } else if (findViewWithTag instanceof QuizTypeTwoView) {
                                ((QuizTypeTwoView) findViewWithTag).resetQuiz();
                            }
                        }
                        this.llCheckParent.setVisibility(8);
                        this.tvCheck.setBackgroundResource(R.drawable.bg_phrase_answer);
                        this.tvCheck.setEnabled(false);
                        this.tvCheck.setTextColor(ContextCompat.getColor(this, R.color.quiz_text_color));
                        this.tvEnglishTest.setVisibility(8);
                        break;
                    }
                    this.nextViewPagerPageIndex = getViewPagerPagePlusOffset(1);
                    if (this.nextViewPagerPageIndex != this.phraseItems.size()) {
                        this.viewPager.setCurrentItem(getViewPagerPagePlusOffset(1), true);
                        textView = this.tvTitle;
                        stringBuilder = new StringBuilder();
                        stringBuilder.append(1 + this.nextViewPagerPageIndex);
                        stringBuilder.append("/");
                        stringBuilder.append(this.phraseItems.size());
                        textView.setText(stringBuilder.toString());
                        this.llCheckParent.setVisibility(8);
                        this.tvCheck.setBackgroundResource(R.drawable.bg_phrase_answer);
                        this.tvCheck.setEnabled(false);
                        this.tvCheck.setTextColor(ContextCompat.getColor(this, R.color.quiz_text_color));
                        this.tvEnglishTest.setVisibility(8);
                    } else {
                        completeQuiz((Context) this);
                        this.llCheckParent.setVisibility(8);
                        this.tvCheck.setBackgroundResource(R.drawable.bg_phrase_answer);
                        this.tvCheck.setEnabled(false);
                        this.tvCheck.setTextColor(ContextCompat.getColor(this, R.color.quiz_text_color));
                        this.tvEnglishTest.setVisibility(8);
                    }
                    break;
                default:
                    return;
            }
        } else {
            this.nextViewPagerPageIndex = getViewPagerPagePlusOffset(1);
            if (this.nextViewPagerPageIndex != this.phraseItems.size()) {
                this.viewPager.setCurrentItem(getViewPagerPagePlusOffset(1), true);
                textView = this.tvTitle;
                stringBuilder = new StringBuilder();
                stringBuilder.append(1 + this.nextViewPagerPageIndex);
                stringBuilder.append("/");
                stringBuilder.append(this.phraseItems.size());
                textView.setText(stringBuilder.toString());
                this.llCheckParent.setVisibility(8);
                this.tvCheck.setBackgroundResource(R.drawable.bg_phrase_answer);
                this.tvCheck.setEnabled(false);
                this.tvCheck.setTextColor(ContextCompat.getColor(this, R.color.quiz_text_color));
                this.tvEnglishTest.setVisibility(8);
            } else {
                completeQuiz((Context) this);
                this.llCheckParent.setVisibility(8);
                this.tvCheck.setBackgroundResource(R.drawable.bg_phrase_answer);
                this.tvCheck.setEnabled(false);
                this.tvCheck.setTextColor(ContextCompat.getColor(this, R.color.quiz_text_color));
                this.tvEnglishTest.setVisibility(8);
            }
        }
    }

    protected void onCreate(Bundle bundle) {
        try {
            super.onCreate(bundle);
            setContentView(R.layout.quiz_activity);
            initViews();
            initBundle(this.categoryId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_quiz_1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.menu_category) {
            SelectCategoryDialogFragment selectCategoryDialogFragment = new SelectCategoryDialogFragment();
            selectCategoryDialogFragment.show(getSupportFragmentManager(), "category_dialog_fragment");
        }
        return super.onOptionsItemSelected(menuItem);
    }

    /* renamed from: r */
    @SuppressLint("ResourceType")
    public void initViews() {
        //mo10030k();
        this.f12090ab = (LinearLayout) findViewById(R.id.view);
        this.progressBar1 = (ProgressBar) findViewById(R.id.progressBar);
        this.viewPager = (CustomViewPager) findViewById(R.id.pager);
        this.viewPager.setPagingEnabled(false);
        this.phraseItems = new ArrayList();
        this.quizAdapter = new QuizAdapter(this, this.phraseItems);
        this.viewPager.setAdapter(this.quizAdapter);
        this.viewPager.setOffscreenPageLimit(1);
        this.tvScore = (TextView) findViewById(R.id.tvScore);
        this.tvHigh = (TextView) findViewById(R.id.tvHigh);
        this.tvPlus = (TextView) findViewById(R.id.tvPlus);
        this.tvTitle = (TextView) findViewById(R.id.tvTitle);
        this.llCheck = (RelativeLayout) findViewById(R.id.llCheck);
        this.llCheckParent = (RelativeLayout) findViewById(R.id.llCheckParent);
        this.llCheckParent.setOnClickListener(this);
        this.tvCheck = (TextView) findViewById(R.id.tvCheck);
        this.tvCheck.setVisibility(8);
        this.tvCheck.setBackgroundResource(R.drawable.bg_phrase_answer);
        this.tvCheck.setEnabled(false);
        this.tvCheck.setTextColor(Utils.getColor(this, R.color.quiz_text_color));
        this.tvSkip = (TextView) findViewById(R.id.tvSkip);
        this.tvSkip.setVisibility(8);
        this.tvContinue = (TextView) findViewById(R.id.tvContinue);
        this.tvCorrect = (TextView) findViewById(R.id.tvCorrect);
        this.tvKoreanTest = (TextView) findViewById(R.id.tvKoreanTest);
        this.tvEnglishTest = (TextView) findViewById(R.id.tvEnglishTest);
        int screenHeight = Utils.getScreenHeight(this);
        if (((double) getResources().getConfiguration().fontScale) >= 1.1d || screenHeight <= 860) {
            if (VERSION.SDK_INT < 23) {
                this.tvKoreanTest.setTextAppearance(this, 16973894);
                this.tvEnglishTest.setTextAppearance(this, 16973894);
            } else {
                this.tvKoreanTest.setTextAppearance(16973894);
                this.tvEnglishTest.setTextAppearance(16973894);
            }
        }
        this.tvKoreanTest.setTextColor(-1);
        this.tvEnglishTest.setTextColor(-1);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, screenHeight / 4);
        layoutParams.addRule(12);
        this.llCheck.setLayoutParams(layoutParams);
    }

    /* renamed from: s */
    public void initBundle(int categoryId) {
        this.categoryId = categoryId;
        this.tvCheck.setOnClickListener(this);
        this.tvSkip.setOnClickListener(this);
        this.tvContinue.setOnClickListener(this);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.categoryId = extras.getInt("bundle_id");
            this.favorite_id = extras.getString("bundle_fav_id");
            this.plus_id = extras.getString("bundle_plus_id");
            this.bundle_title = extras.getString("bundle_title");
            setTitle(!Utils.isStringEmpty(this.bundle_title) ? this.bundle_title : "Game luyện nghe");
            TextView textView = this.tvHigh;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(this.appPreference.mo2897a(this.bundle_title));
            stringBuilder.append("");
            textView.setText(stringBuilder.toString());
        } else {
            this.categoryId = Integer.valueOf(appPreference.getSelectedPracticeId());
        }
        getData(null, this.plus_id);
    }

    public void getData(String categoryId, String grammarId) {
        if (categoryId != null) {
            this.categoryId = Integer.valueOf(categoryId);
            new getDataTask(this, null).execute(new String[]{null});
        } else {
            new getDataTask(this, null).execute(new String[]{grammarId});
        }
    }

    /* renamed from: t */
    public void mo2844t() {
        this.currentScore--;
        TextView textView = this.tvScore;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.currentScore);
        stringBuilder.append("");
        textView.setText(stringBuilder.toString());
        this.tvPlus.setText("1");
    }
}
