package app.giaotieptienghan;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.res.Resources;
import android.media.AudioAttributes.Builder;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaRecorder;
import android.media.SoundPool;
import android.media.SoundPool.OnLoadCompleteListener;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URLEncoder;
import java.util.ArrayList;

import app.giaotieptienghan.adapter.PhraseAdapter;
import app.giaotieptienghan.model.CategoryItem;
import app.giaotieptienghan.model.PhraseItem;
import app.giaotieptienghan.repository.AppPreference;

/* renamed from: com.example.english.b */
@SuppressLint("WrongConstant")
public class BaseAudioPlayActivity extends AppCompatActivity implements OnCompletionListener, OnClickListener, OnItemClickListener {
    /* renamed from: A */
    protected ImageView btPlayAll;
    /* renamed from: B */
    protected ImageView btSlow;
    /* renamed from: C */
    protected ImageView btVolume;
    /* renamed from: D */
    protected ArrayList<PhraseItem> phraseItems;
    /* category items */
    public ArrayList<CategoryItem> categoryItems = new ArrayList<>();
    /* renamed from: E */
    protected PhraseAdapter phraseAdapter;
    /* renamed from: F */
    protected final int f12003F;
    /* renamed from: G */
    protected final int f12004G;
    /* renamed from: H */
    protected int isPlaying;
    /* renamed from: I */
    protected int currentPlayIndex;
    /* renamed from: J */
    protected ListView listView;
    /* renamed from: K */
    protected ProgressBar progressBar;
    /* renamed from: L */
    protected AppPreference appPreference;
    /* renamed from: M */
    private String adID;
    /* renamed from: l */
    protected final String f12011l = ".mp3";
    /* renamed from: m */
    protected LinearLayout linearLayout;
    /* renamed from: n */
    protected String bundle_title;
    /* renamed from: o */
    protected MediaPlayer mediaPlayer;
    /* renamed from: p */
    protected MediaPlayer mediaPlayer1;
    /* renamed from: q */
    protected SoundPool soundPool;
    /* renamed from: r */
    protected MediaRecorder mediaRecorder = null;
    /* renamed from: s */
    protected String f12018s;
    /* renamed from: t */
    //protected boolean f12019t;
    /* renamed from: u */
    protected boolean f12020u;
    /* renamed from: v */
    protected boolean f12021v;
    /* renamed from: w */
    protected int playerDuration;
    /* renamed from: x */
    protected Handler handler;
    /* renamed from: y */
    protected ImageView btPlay;
    /* renamed from: z */
    protected ImageView btVoice;
    private AdView mAdView;

    public BaseAudioPlayActivity() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Environment.getExternalStorageDirectory().getAbsolutePath());
        stringBuilder.append("/Korean/");
        this.f12018s = stringBuilder.toString();
        //this.f12019t = true;
        this.f12020u = true;
        this.f12021v = false;
        this.playerDuration = 0;
        this.f12003F = 1;
        this.f12004G = 2;
        this.currentPlayIndex = 0;
    }

    /* renamed from: b */
    private void playMp3Audio(String str) {
        try {
            this.mediaPlayer = new MediaPlayer();
            MediaPlayer mediaPlayer = this.mediaPlayer;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(this.f12018s);
            stringBuilder.append(str);
            stringBuilder.append(".mp3");
            mediaPlayer.setDataSource(stringBuilder.toString());
            this.mediaPlayer.setOnCompletionListener(this);
            this.mediaPlayer.prepare();
            this.mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* renamed from: c */
    @SuppressLint("WrongConstant")
    private void recording(String str) {
        try {
            System.out.println("startRecording");
            File file = new File(this.f12018s);
            if (!file.exists()) {
                file.mkdir();
            }
            this.mediaRecorder = new MediaRecorder();
            this.mediaRecorder.setAudioSource(1);
            this.mediaRecorder.setOutputFormat(1);
            MediaRecorder mediaRecorder = this.mediaRecorder;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(this.f12018s);
            stringBuilder.append(str);
            stringBuilder.append(".mp3");
            mediaRecorder.setOutputFile(stringBuilder.toString());
            this.mediaRecorder.setAudioEncoder(1);
            this.mediaRecorder.prepare();
            this.mediaRecorder.start();
            this.f12021v = true;
        } catch (Exception e) {
            e.printStackTrace();
            this.mediaRecorder = null;
            Toast.makeText(this, getString(R.string.not_micrphone), 0).show();
        }
    }

    ///* renamed from: r */
    //private void initViews() {
    //    if (C0768b.f1962f == null) {
    //        C0768b.f1962f = new C1483h(this);
    //        C0768b.f1962f.mo4664a(this.adID);
    //        final C1456a s = initBundle();
    //        C0768b.f1962f.mo4661a(s.mo4549a());
    //        C0768b.f1962f.mo4660a(new C1447a() {
    //            /* renamed from: b */
    //            public void mo2803b() {
    //                super.mo2803b();
    //                C0768b.f1962f.mo4661a(s.mo4549a());
    //            }
    //        });
    //        return;
    //    }
    //    if (C0768b.f1961e >= this.appPreference.mo2915h() + 1 && !C0768b.f1962f.mo4666a()) {
    //        C0768b.f1962f.mo4661a(initBundle().mo4549a());
    //    }
    //}
    //
    ///* renamed from: s */
    //private C1456a initBundle() {
    //    return new C1456a().mo4550b("B3EEABB8EE11C2BE770B684D95219ECB").mo4550b("EE542AF7FF4B2C8FDB8049C8DB5F0B11");
    //}
    //
    ///* renamed from: t */
    //private void minusScore() {
    //    if (C0768b.f1962f.mo4666a()) {
    //        C0768b.f1962f.mo4667b();
    //    }
    //}

    /* renamed from: u */
    private void playNextPharase() {
        this.currentPlayIndex++;
        if (this.currentPlayIndex >= this.phraseItems.size()) {
            this.currentPlayIndex = 0;
        }
        Resources resources = getResources();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(((PhraseItem) this.phraseItems.get(this.currentPlayIndex)).voice);
        stringBuilder.append("_f");
        int identifier = resources.getIdentifier(stringBuilder.toString(), "raw", getPackageName());
        if (identifier != 0) {
            playAudio(identifier, (OnCompletionListener) this);
        } else {
            playAudioByGoogleTranslate(((PhraseItem) this.phraseItems.get(this.currentPlayIndex)).korean, this, true);
        }
        if (this.phraseAdapter != null) {
            this.phraseAdapter.setCurrentPlaySoundIndex(this.currentPlayIndex);
            this.phraseAdapter.notifyDataSetChanged();
            if (this.listView.getLastVisiblePosition() < this.currentPlayIndex + 1) {
                this.listView.setSelection(this.currentPlayIndex);
            }
        }
    }

    /* renamed from: v */
    @SuppressLint("WrongConstant")
    private void initMediaPlayer() {
        if (this.mediaPlayer != null) {
            if (VERSION.SDK_INT >= 21) {
                System.out.println("LOLLIPOP");
                Builder builder = new Builder();
                builder.setUsage(1).setContentType(2);
                this.mediaPlayer.setAudioAttributes(builder.build());
                return;
            }
            this.mediaPlayer.setAudioStreamType(3);
        }
    }

    /* renamed from: x */
    private void resetMediaRecorder() {
        if (this.mediaRecorder != null) {
            try {
                this.mediaRecorder.stop();
                this.mediaRecorder.release();
                this.mediaRecorder = null;
                //this.f12019t ^= 1;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* renamed from: a */
    protected final void playAudio(int i, OnCompletionListener onCompletionListener) {
        try {
            if (this.mediaPlayer != null && this.mediaPlayer.isPlaying()) {
                resetMediaPlayer();
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("android.resource://");
            stringBuilder.append(getPackageName());
            stringBuilder.append("/raw/");
            stringBuilder.append(i);
            this.mediaPlayer = MediaPlayer.create(this, Uri.parse(stringBuilder.toString()));
            if (this.mediaPlayer != null) {
                initMediaPlayer();
                this.mediaPlayer.setOnCompletionListener(onCompletionListener);
                this.mediaPlayer.start();
                this.playerDuration = this.mediaPlayer.getDuration();
                PrintStream printStream = System.out;
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append("cTime ");
                stringBuilder2.append(this.playerDuration);
                printStream.println(stringBuilder2.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    protected void playCurrentAudio(String str) {
        if (this.phraseItems != null && this.phraseItems.size() > 0) {
            str = ((PhraseItem) this.phraseItems.get(this.currentPlayIndex)).voice;
        }
        if (!Utils.isStringEmpty(str)) {
            Resources resources = getResources();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append("_f");
            int identifier = resources.getIdentifier(stringBuilder.toString(), "raw", getPackageName());
            if (identifier != 0) {
                playAudio(identifier, (OnCompletionListener) this);
            } else {
                playAudioByGoogleTranslate(((PhraseItem) this.phraseItems.get(this.currentPlayIndex)).korean, this, true);
            }
            if (this.phraseAdapter != null) {
                this.phraseAdapter.setCurrentPlaySoundIndex(this.currentPlayIndex);
                this.phraseAdapter.notifyDataSetChanged();
            }
        }
    }

    /* renamed from: a */
    @SuppressLint("WrongConstant")
    protected final void playAudioByGoogleTranslate(String str, OnCompletionListener onCompletionListener, boolean z) {
        try {
            if (Utils.m3038c(this)) {
                if (this.mediaPlayer != null && this.mediaPlayer.isPlaying()) {
                    resetMediaPlayer();
                }
                this.mediaPlayer = new MediaPlayer();
                initMediaPlayer();
                if (BuildConfig.FLAVOR.contains("kr")) {
                    this.mediaPlayer.setDataSource(String.format("https://translate.google.com/translate_tts?ie=UTF-8&q=%s&tl=%s&client=tw-ob", new Object[]{URLEncoder.encode(str, "UTF-8"), "ko"}));
                } else if  (BuildConfig.FLAVOR.contains("jp")) {
                    this.mediaPlayer.setDataSource(String.format("https://translate.google.com/translate_tts?ie=UTF-8&q=%s&tl=%s&client=tw-ob", new Object[]{URLEncoder.encode(str, "UTF-8"), "jp"}));
                }
                this.mediaPlayer.prepare();
                this.mediaPlayer.setOnCompletionListener(onCompletionListener);
                this.mediaPlayer.start();
                return;
            }
            Toast.makeText(this, getString(R.string.no_internet), 0).show();
        } catch (IOException e) {
            e.printStackTrace();
            if (z) {
                playNextPharase();
            }
        }
    }

    /* renamed from: a */
    protected void recordingOrStop(boolean z, String str) {
        if (z) {
            recording(str);
        } else {
            resetMediaRecorder();
        }
    }

    @TargetApi(21)
    /* renamed from: b */
    protected void playSoundSlowly(int i) {
        final float streamMaxVolume = (float) ((AudioManager) getSystemService("audio")).getStreamMaxVolume(3);
        if (VERSION.SDK_INT >= 21) {
            this.soundPool = new SoundPool.Builder().setMaxStreams(25).setAudioAttributes(new Builder().setUsage(14).setContentType(4).build()).build();
        } else {
            this.soundPool = new SoundPool(10, 3, 0);
        }
        i = this.soundPool.load(this, i, 1);
        this.soundPool.setOnLoadCompleteListener(new OnLoadCompleteListener() {

            /* renamed from: com.example.english.b$2$1 */
            class C07601 implements Runnable {
                C07601() {
                }

                public void run() {
                    if (BaseAudioPlayActivity.this.btSlow != null) {
                        BaseAudioPlayActivity.this.btSlow.setImageResource(R.drawable.ic_slow);
                    }
                }
            }

            public void onLoadComplete(SoundPool soundPool, int i, int i2) {
                if (BaseAudioPlayActivity.this.soundPool != null) {
                    BaseAudioPlayActivity.this.soundPool.play(i, streamMaxVolume, streamMaxVolume, 1, 0, 0.7f);
                }
                if (BaseAudioPlayActivity.this.handler != null) {
                    BaseAudioPlayActivity.this.handler.postDelayed(new C07601(), (long) ((BaseAudioPlayActivity.this.playerDuration * 10) / 7));
                }
            }
        });
    }

    /* renamed from: b */
    protected void playMp3AudioOrStop(boolean z, String str) {
        if (z) {
            playMp3Audio(str);
        } else {
            resetMediaPlayer();
        }
    }

    /* renamed from: l */
    protected void initBaseViews() {
        if (this.progressBar == null) {
            this.progressBar = (ProgressBar) findViewById(R.id.progressBar);
        }
        this.btPlay = (ImageView) findViewById(R.id.btnPlay);
        this.btVoice = (ImageView) findViewById(R.id.btnVoice);
        this.btPlayAll = (ImageView) findViewById(R.id.btnPlayAll);
        this.btSlow = (ImageView) findViewById(R.id.btnSlow);
        this.btVolume = (ImageView) findViewById(R.id.btnVolume);
        this.btPlay.setOnClickListener(this);
        this.btVoice.setOnClickListener(this);
        this.btPlayAll.setOnClickListener(this);
        this.btSlow.setOnClickListener(this);
        this.btVolume.setOnClickListener(this);
    }

    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out);
        PrintStream printStream = System.out;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onBackPressed ");
        stringBuilder.append(this.handler);
        printStream.println(stringBuilder.toString());
    }

    public void onClick(View view) {
        StringBuilder stringBuilder;
        switch (view.getId()) {
            case R.id.btnPlay:
                if (this.phraseItems != null && this.phraseItems.size() > 0) {
                    this.isPlaying = 2;
                    resetSoundPool();
                    if (this.mediaPlayer != null && this.mediaPlayer.isPlaying()) {
                        resetMediaPlayer();
                    }
                    this.btPlay.setImageResource(R.drawable.ic_pause_footer);
                    stringBuilder = new StringBuilder();
                    stringBuilder.append(this.f12018s);
                    stringBuilder.append(((PhraseItem) this.phraseItems.get(this.currentPlayIndex)).voice);
                    stringBuilder.append(".mp3");
                    if (!new File(stringBuilder.toString()).exists()) {
                        Toast.makeText(this, "Chọn ghi âm trước để có thể nghe lại...", 0).show();
                        break;
                    } else {
                        playMp3AudioOrStop(this.f12020u, ((PhraseItem) this.phraseItems.get(this.currentPlayIndex)).voice);
                        return;
                    }
                }
            case R.id.btnPlayAll:
                resetSoundPool();
                if (this.mediaPlayer != null && this.mediaPlayer.isPlaying()) {
                    resetMediaPlayer();
                }
                if (this.phraseItems != null && this.phraseItems.size() > 0) {
                    if (this.isPlaying != 1) {
                        this.btPlayAll.setImageResource(R.drawable.ic_pause_footer);
                        playCurrentAudio(null);
                        this.isPlaying = 1;
                        return;
                    }
                    this.isPlaying = 2;
                    return;
                }
            case R.id.btnSlow:
                if (this.mediaPlayer != null && this.mediaPlayer.isPlaying()) {
                    resetMediaPlayer();
                }
                this.btSlow.setImageResource(R.drawable.ic_slow1);
                resetSoundPool();
                if (this.phraseItems != null && this.phraseItems.size() > 0) {
                    Resources resources = getResources();
                    stringBuilder = new StringBuilder();
                    stringBuilder.append(((PhraseItem) this.phraseItems.get(this.currentPlayIndex)).voice);
                    stringBuilder.append("_f");
                    int identifier = resources.getIdentifier(stringBuilder.toString(), "raw", getPackageName());
                    if (identifier != 0) {
                        playSoundSlowly(identifier);
                        if (this.phraseAdapter != null) {
                            this.phraseAdapter.setCurrentPlaySoundIndex(this.currentPlayIndex);
                            this.phraseAdapter.notifyDataSetChanged();
                            return;
                        }
                    }
                }
                break;
            case R.id.btnVoice:
                resetSoundPool();
                resetMediaPlayer();
                if (this.phraseItems != null && this.phraseItems.size() > 0) {
                    PhraseItem phraseItem = (PhraseItem) this.phraseItems.get(this.currentPlayIndex);
                    Intent intent = new Intent(this, RecordActivity.class);
                    intent.addFlags(268435456);
                    intent.putExtra("korean", phraseItem.korean);
                    intent.putExtra("korean1", phraseItem.pinyin);
                    intent.putExtra("english", phraseItem.vietnamese);
                    intent.putExtra("audio", phraseItem.voice);
                    startActivity(intent);
                    return;
                }
            case R.id.btnVolume:
                this.isPlaying = 2;
                resetSoundPool();
                if (this.mediaPlayer != null && this.mediaPlayer.isPlaying()) {
                    resetMediaPlayer();
                }
                playCurrentAudio(null);
                this.btVolume.setImageResource(R.drawable.ic_volume1);
                break;
            default:
                return;
        }
    }

    public void onCompletion(MediaPlayer mediaPlayer) {
        try {
            if (this.isPlaying == 1) {
                if (this.mediaPlayer != null) {
                    this.mediaPlayer.reset();
                    this.mediaPlayer.release();
                    this.mediaPlayer.setOnCompletionListener(null);
                    this.mediaPlayer = null;
                }
                playNextPharase();
                return;
            }
            if (this.isPlaying == 2) {
                resetMediaPlayer();
            }
        } catch (Exception e) {
            e.printStackTrace();
            resetMediaPlayer();
        }
    }

    protected void onCreate(Bundle bundle) {
        try {
            super.onCreate(bundle);
            this.isPlaying = 2;
            if (this.handler == null) {
                this.handler = new Handler();
            }
            //mo9998g().mo1324a(true);
            //mo9998g().mo1329b(true);
            overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
            if (this.appPreference == null) {
                this.appPreference = new AppPreference(this);
            }
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            //mo10033n();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void initAds() {
        // ad init
        mAdView = findViewById(R.id.llAds);
        if (mAdView != null) {
            mAdView.setVisibility(View.GONE);
            AdRequest adRequest = (BuildConfig.DEBUG) ? new AdRequest.Builder().addTestDevice("D9E46D2AE14D4064F48C60B07D4218FC").build() : new AdRequest.Builder().build();
            mAdView.loadAd(adRequest);
            mAdView.setAdListener(new AdListener() {
                @Override
                public void onAdLoaded() {
                    if (mAdView != null) {
                        mAdView.setVisibility(View.VISIBLE);
                    }
                }

                @Override
                public void onAdFailedToLoad(int i) {
                    if (mAdView != null) {
                        mAdView.setVisibility(View.GONE);
                    }
                }
            });
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    protected void onDestroy() {
        super.onDestroy();
        resetMediaPlayer();
        resetSoundPool();
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        this.isPlaying = 2;
        if (this.currentPlayIndex != i) {
            this.f12021v = false;
            this.currentPlayIndex = i;
            this.phraseAdapter.setCurrentPlaySoundIndex(i);
            this.phraseAdapter.notifyDataSetChanged();
        }
        if (this.mediaPlayer != null && this.mediaPlayer.isPlaying()) {
            resetMediaPlayer();
        }
        playCurrentAudio(null);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == 16908332) {
            finish();
            overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out);
        }
        return super.onOptionsItemSelected(menuItem);
    }

    protected void onPause() {
        super.onPause();
        //mo10034o();
        resetMediaPlayer();
        resetSoundPool();
        if (this.mediaRecorder != null) {
            this.mediaRecorder.release();
            this.mediaRecorder = null;
        }
        if (this.mediaPlayer1 != null) {
            this.mediaPlayer1.release();
            this.mediaPlayer1 = null;
        }
    }

    protected void onResume() {
        super.onResume();
        //mo10032m();

    }

    protected void onStop() {
        super.onStop();
        resetMediaPlayer();
        resetSoundPool();
    }

    /* renamed from: p */
    protected final void resetMediaPlayer() {
        if (this.mediaPlayer != null) {
            this.mediaPlayer.reset();
            this.mediaPlayer.release();
            this.mediaPlayer.setOnCompletionListener(null);
            this.mediaPlayer = null;
            if (this.btPlay != null) {
                this.btPlay.setImageResource(R.drawable.ic_play_footer);
            }
            if (this.btPlayAll != null) {
                this.btPlayAll.setImageResource(R.drawable.ic_play_all);
            }
            if (this.btVolume != null) {
                this.btVolume.setImageResource(R.drawable.ic_volume);
            }
        }
    }

    /* renamed from: q */
    protected final void resetSoundPool() {
        if (this.soundPool != null) {
            this.soundPool.release();
            this.soundPool = null;
        }
    }
}
