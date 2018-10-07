package app.giaotieptienghan;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class GrammaDetail extends BaseAudioPlayActivity {
    /* renamed from: M */
    private WebView f12068M;
    /* renamed from: N */
    private TextView f12069N;
    /* renamed from: O */
    private String f12070O;
    /* renamed from: P */
    private String f12071P;

    public void onBackPressed() {
        super.onBackPressed();
    }

    protected void onCreate(Bundle bundle) {
        try {
            super.onCreate(bundle);
            setContentView((int) R.layout.gramma_detail_screen);
            bundle = getIntent().getExtras();
            this.f12070O = bundle.getString("bundle_title");
            this.f12071P = bundle.getString("bundle_word_title");
            mo10050r();
        } catch (Exception unused) {
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return false;
    }

    protected void onDestroy() {
        super.onDestroy();
    }

    protected void onStop() {
        super.onStop();
    }

    /* renamed from: r */
    public void mo10050r() {
        this.f12068M = (WebView) findViewById(R.id.webView);
        this.f12068M.setScrollContainer(false);
        this.f12068M.loadDataWithBaseURL(null, this.f12071P, "text/html", "utf-8", null);
        this.f12069N = (TextView) findViewById(R.id.title);
        this.f12069N.setText(this.f12070O);
    }
}
