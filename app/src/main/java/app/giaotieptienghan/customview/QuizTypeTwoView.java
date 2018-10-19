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
import app.giaotieptienghan.model.QuizAdapter.QuizInterface;
import app.giaotieptienghan.model.PhraseItem;
import app.giaotieptienghan.adapter.StaggeredAdapter.onStaggeredTextViewClick;
import app.giaotieptienghan.repository.AppPreference;
import app.giaotieptienghan.R;

/* renamed from: com.example.english.customview.c */
@SuppressLint("WrongConstant")
public class QuizTypeTwoView extends LinearLayout implements OnClickListener, onStaggeredTextViewClick {
    /* renamed from: a */
    private TextView tvKorean;
    /* renamed from: b */
    private TextView tvPinyin;
    /* renamed from: c */
    private TextView tvDel;
    /* renamed from: d */
    private TextView tvSug;
    /* renamed from: e */
    private ImageView btnPlay;
    /* renamed from: f */
    private String korean = null;
    /* renamed from: g */
    private FlowLayout flowLayout;
    /* renamed from: h */
    private EditText edtAnswer;
    /* renamed from: i */
    private LinearLayout linearLayout;
    /* renamed from: j */
    private StaggeredTextGridView staggeredTextGridView;
    /* renamed from: k */
    private StaggeredAdapter staggeredAdapter;
    /* renamed from: l */
    private QuizInterface quizInterface;
    /* renamed from: m */
    private int selectedWordCount = 0;
    /* renamed from: n */
    private List<String> listWords = new ArrayList();
    /* renamed from: o */
    private ArrayList<String> f7888o = new ArrayList();
    /* renamed from: p */
    private ArrayList<String> f7889p = new ArrayList();
    /* renamed from: q */
    private List<Integer> f7890q = new ArrayList();
    /* renamed from: r */
    private AppPreference appPreference;
    /* renamed from: s */
    private PhraseItem phraseItem;
    /* renamed from: t */
    private StringBuilder selectedWords = new StringBuilder();
    /* renamed from: u */
    private boolean f7894u;

    public QuizTypeTwoView(Context context) {
        super(context);
        init(context);
    }

    /* renamed from: a */
    private void init(Context context) {
        if (context instanceof QuizInterface) {
            this.quizInterface = (QuizInterface) context;
        }
        QuizTypeTwoView.inflate(getContext(), R.layout.quiz4, this);
        this.tvKorean = (TextView) findViewById(R.id.tvKorean);
        if (this.appPreference == null) {
            this.appPreference = new AppPreference(context);
        }
        this.tvPinyin = (TextView) findViewById(R.id.tvPinyin);
        if (!this.appPreference.mo2917j()) {
            this.tvPinyin.setVisibility(8);
        }
        this.flowLayout = (FlowLayout) findViewById(R.id.flowLayout);
        this.edtAnswer = (EditText) findViewById(R.id.editAnswer);
        this.edtAnswer.setInputType(0);
        this.linearLayout = (LinearLayout) findViewById(R.id.llFlowLayout);
        this.linearLayout.setLayoutParams(getLayoutParam());
        this.btnPlay = (ImageView) findViewById(R.id.btnPlay);
        this.btnPlay.setOnClickListener(this);
        this.tvDel = (TextView) findViewById(R.id.tvDel);
        this.tvSug = (TextView) findViewById(R.id.tvSug);
        this.tvDel.setOnClickListener(this);
        this.tvSug.setOnClickListener(this);
        this.staggeredTextGridView = (StaggeredTextGridView) findViewById(R.id.staggeredTextView);
        this.staggeredAdapter = new StaggeredAdapter(getContext(), this.f7888o);
        this.staggeredAdapter.setOnClick((onStaggeredTextViewClick) this);
        this.staggeredTextGridView.setmAdapter(this.staggeredAdapter);
    }

    /* renamed from: a */
    private void renderFirstCharacter(String str) {
        if (!Utils.isStringEmpty(str)) {
            str = String.valueOf(str.charAt(0));
            int b = m9510b(str);
            this.staggeredAdapter.renderViewWithAnimation(str, b, this.staggeredTextGridView.getTextViewAtIndex(b), true);
            if (this.quizInterface != null) {
                this.quizInterface.minusScore();
            }
        }
    }

    /* renamed from: a */
    private void renderFirstWord(List<String> list) {
        String str = (String) list.get(0);
        int b = m9510b(str);
        this.staggeredAdapter.renderViewWithAnimation(str, b, this.staggeredTextGridView.getTextViewAtIndex(b), true);
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
        for (int childCount = this.flowLayout.getChildCount() - 1; childCount >= 0; childCount--) {
            TextView textView = (TextView) this.flowLayout.getChildAt(childCount);
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
        for (String append : this.listWords) {
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
            if (i >= this.listWords.size()) {
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
    public void resetQuiz() {
        this.flowLayout.removeAllViews();
        if (this.listWords != null) {
            this.listWords.clear();
        }
        edtAnswer.setText("");
        this.selectedWordCount = 0;
        this.selectedWords = new StringBuilder();
        this.staggeredTextGridView.removeViews();
        this.staggeredTextGridView.resetRow();
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
            this.flowLayout.addView(textView, c0779a);
            this.listWords.add(str);
            if (this.selectedWordCount < this.f7889p.size() - 1) {
                this.selectedWordCount++;
                return;
            }
            StringBuilder stringBuilder;
            this.selectedWordCount++;
            for (String str2 : this.listWords) {
                stringBuilder = this.selectedWords;
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append(str2);
                stringBuilder2.append(" ");
                stringBuilder.append(stringBuilder2.toString());
            }
            if (this.quizInterface != null) {
                str = this.selectedWords.toString().trim();
                PrintStream printStream = System.out;
                stringBuilder = new StringBuilder();
                stringBuilder.append("ans ");
                stringBuilder.append(str);
                printStream.println(stringBuilder.toString());
                printStream = System.out;
                stringBuilder = new StringBuilder();
                stringBuilder.append("korean ");
                stringBuilder.append(this.korean);
                printStream.println(stringBuilder.toString());
                this.quizInterface.mo2842a(this.phraseItem, str, str.equals(this.korean.replace(",", "").replace(".", "").trim()));
                return;
            }
        }
        this.selectedWords.append(str);
        this.edtAnswer.setText(this.selectedWords.toString());
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
            final AnimationDrawable animationDrawable = (AnimationDrawable) this.btnPlay.getBackground();
            animationDrawable.start();
            if (this.quizInterface != null) {
                this.quizInterface.mo2841a(this.phraseItem, new OnCompletionListener() {
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
                    if (this.listWords.size() <= 0) {
                        list = this.f7889p;
                    } else if (this.korean.replace(",", "").replace(".", "").startsWith(getAllText())) {
                        list = getArrayList();
                        if (list.size() > 0) {
                            PrintStream printStream = System.out;
                            StringBuilder stringBuilder = new StringBuilder();
                            stringBuilder.append("list ");
                            stringBuilder.append(list.size());
                            printStream.println(stringBuilder.toString());
                            this.linearLayout.setBackgroundResource(R.drawable.bg_phrase_answer_true);
                        }
                    } else {
                        this.linearLayout.setBackgroundResource(R.drawable.bg_phrase_answer_false);
                        return;
                    }
                    renderFirstWord(list);
                    return;
                }
                obj = this.edtAnswer.getText().toString();
                if (Utils.isStringEmpty(obj)) {
                    obj = this.korean;
                } else if (this.korean.startsWith(obj)) {
                    obj = this.korean.replace(obj, "");
                } else {
                    Utils.animateTextView(this.edtAnswer);
                    return;
                }
                renderFirstCharacter(obj);
            }
        } else if (this.f7894u) {
            if (this.listWords.size() > 0) {
                obj = (String) this.listWords.get(this.listWords.size() - 1);
                PrintStream printStream2 = System.out;
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append("value ");
                stringBuilder2.append(obj);
                printStream2.println(stringBuilder2.toString());
                intValue = ((Integer) this.f7890q.get(this.f7890q.size() - 1)).intValue();
                a = this.staggeredTextGridView.getTextViewAtIndex(intValue);
                this.f7890q.remove(this.f7890q.size() - 1);
                this.flowLayout.removeView(this.flowLayout.getChildAt(m9512c(obj)));
                this.listWords.remove(this.listWords.size() - 1);
                this.selectedWordCount--;
                this.selectedWords = new StringBuilder();
                this.staggeredAdapter.renderView(obj, intValue, a);
                if (this.quizInterface != null) {
                    this.quizInterface.mo2842a(this.phraseItem, "", false);
                }
            }
        } else if (this.selectedWords.length() >= 1) {
            obj = String.valueOf(this.selectedWords.charAt(this.selectedWords.length() - 1));
            intValue = ((Integer) this.f7890q.get(this.f7890q.size() - 1)).intValue();
            a = this.staggeredTextGridView.getTextViewAtIndex(intValue);
            this.selectedWords.deleteCharAt(this.selectedWords.length() - 1);
            this.f7890q.remove(this.f7890q.size() - 1);
            if (!Utils.isStringEmpty(obj)) {
                this.staggeredAdapter.renderView(obj, intValue, a);
            }
            this.edtAnswer.setText(this.selectedWords.toString());
        }
    }

    public void setData(final PhraseItem phraseItem) {
        this.phraseItem = phraseItem;
        if (this.f7890q != null) {
            this.f7890q.clear();
        }
        String trim = phraseItem.korean.replace(",", "").trim();
        String obj = phraseItem.vietnamese;
        this.tvKorean.setText(obj);
        this.tvPinyin.setText(phraseItem.pinyin);
        this.korean = trim.replaceAll("\\(.*\\)", "").trim();
        if (this.f7888o == null) {
            this.f7888o = new ArrayList();
        } else {
            this.f7888o.clear();
        }
        int i = 0;
        int length;
        if (this.korean.length() >= 10) {
            this.flowLayout.setVisibility(0);
            String[] split = this.korean.split(" ");
            length = split.length;
            while (i < length) {
                this.f7888o.add(split[i].replace(".", ""));
                i++;
            }
            this.f7894u = true;
        } else {
            this.edtAnswer.setVisibility(0);
            for (length = 0; length < this.korean.length(); length++) {
                this.f7888o.add(String.valueOf(this.korean.charAt(length)));
            }
            this.f7894u = false;
            EditText editText = this.edtAnswer;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("How to write ");
            stringBuilder.append(obj);
            editText.setHint(stringBuilder.toString());
            this.edtAnswer.addTextChangedListener(new TextWatcher() {
                public void afterTextChanged(Editable editable) {
                }

                public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                }

                public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                    if (charSequence != null && QuizTypeTwoView.this.quizInterface != null) {
                        String trim = charSequence.toString().trim();
                        QuizTypeTwoView.this.quizInterface.mo2842a(phraseItem, trim, trim.equals(QuizTypeTwoView.this.korean));
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
        this.staggeredAdapter.setData(this.f7888o);
        resetQuiz();
    }
}
