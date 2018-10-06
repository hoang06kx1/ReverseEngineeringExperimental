package app.giaotieptienghan;

import android.annotation.SuppressLint;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.io.File;
import java.io.PrintStream;

public class RecordActivity extends BaseAudioPlayActivity implements OnClickListener {
    /* renamed from: M */
    private TextView tvKorean;
    /* renamed from: N */
    private TextView tvKorean1;
    /* renamed from: O */
    private TextView tvEng;
    /* renamed from: P */
    private ImageView imgMicro;
    /* renamed from: Q */
    private ImageView btRecord;
    /* renamed from: R */
    private ImageView imgPlay;
    /* renamed from: S */
    private String koreanString;
    /* renamed from: T */
    private String korean1String;
    /* renamed from: U */
    private String englishString;
    /* renamed from: V */
    private String audioString;
    /* renamed from: W */
    private boolean f12156W = false;
    /* renamed from: X */
    private boolean f12157X = false;
    /* renamed from: Y */
    private String[] permissionStrings = new String[]{"android.permission.RECORD_AUDIO", "android.permission.WRITE_EXTERNAL_STORAGE"};

    /* renamed from: r */
    private void initView() {
        //mo10030k(); ads
        this.tvKorean = (TextView) findViewById(R.id.tvKorean);
        this.tvKorean1 = (TextView) findViewById(R.id.tvKorean1);
        this.tvEng = (TextView) findViewById(R.id.tvEng);
        this.imgMicro = (ImageView) findViewById(R.id.imgMicro);
        this.imgMicro.setOnClickListener(this);
        this.imgMicro.setBackgroundResource(R.drawable.record_loader);
        this.btRecord = (ImageView) findViewById(R.id.record);
        this.btRecord.setOnClickListener(this);
        this.imgPlay = (ImageView) findViewById(R.id.play);
        this.imgPlay.setOnClickListener(this);
        this.tvKorean.setText(this.koreanString);
        this.tvKorean1.setText(this.korean1String);
        this.tvEng.setText(this.englishString);
        if (VERSION.SDK_INT >= 23) {
            requestPermissions(this.permissionStrings, 200);
        }
    }

    @SuppressLint("WrongConstant")
    public void onClick(View view) {
        int id = view.getId();
        AnimationDrawable animationDrawable;
        StringBuilder stringBuilder;
        if (id == R.id.play) {
            if (this.f12021v) {
                animationDrawable = (AnimationDrawable) this.imgMicro.getBackground();
                animationDrawable.stop();
                animationDrawable.selectDrawable(0);
                this.f12021v = false;
                mo10027a(this.f12021v, this.audioString);
                this.imgMicro.setBackgroundResource(R.drawable.record_loader);
                this.btRecord.setImageResource(R.drawable.ic_voice);
            }
            this.imgPlay.setImageResource(R.drawable.ic_pause_footer);
            stringBuilder = new StringBuilder();
            stringBuilder.append(this.f12018s);
            stringBuilder.append(this.audioString);
            stringBuilder.append(".mp3");
            if (new File(stringBuilder.toString()).exists()) {
                playMp3AudioOrStop(this.f12020u, this.audioString);
                return;
            }
            Toast.makeText(this, "Chọn ghi âm trước để có thể nghe lại...", 0).show();
            mo10025a(this.audioString);
        } else if (id == R.id.record) {
            PrintStream printStream = System.out;
            stringBuilder = new StringBuilder();
            stringBuilder.append("record ");
            stringBuilder.append(this.f12021v);
            printStream.println(stringBuilder.toString());
            animationDrawable = (AnimationDrawable) this.imgMicro.getBackground();
            if (this.f12021v) {
                animationDrawable.stop();
                animationDrawable.selectDrawable(0);
                this.f12021v = false;
                this.imgMicro.setBackgroundResource(R.drawable.record_loader);
                mo10027a(this.f12021v, this.audioString);
                this.btRecord.setImageResource(R.drawable.ic_voice);
                return;
            }
            animationDrawable.start();
            this.f12021v = true;
            mo10027a(this.f12021v, this.audioString);
            this.btRecord.setImageResource(R.drawable.ic_pause_footer);
        }
    }

    public void onCompletion(MediaPlayer mediaPlayer) {
        super.onCompletion(mediaPlayer);
        this.imgPlay.setImageResource(R.drawable.ic_play_footer);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        bundle = getIntent().getExtras();
        if (bundle != null) {
            setContentView((int) R.layout.record_dialog);
            this.koreanString = bundle.getString("korean");
            this.korean1String = bundle.getString("korean1");
            this.englishString = bundle.getString("english");
            this.audioString = bundle.getString("audio");
            initView();
        }
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (i == 200) {
            boolean z = false;
            try {
                this.f12156W = iArr[0] == 0;
                if (iArr[1] == 0) {
                    z = true;
                }
                this.f12157X = z;
            } catch (Exception unused) {
                if (!this.f12156W) {
                    finish();
                }
                if (!this.f12157X) {
                    finish();
                }
            }
        }
    }
}
