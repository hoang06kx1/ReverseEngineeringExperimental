package app.giaotieptienghan;

import android.os.Bundle;
import android.view.Menu;
import android.webkit.WebView;
import android.widget.TextView;

public class GrammaDetail extends BaseAudioPlayActivity {
    /* renamed from: M */
    private WebView webView;
    /* renamed from: N */
    private TextView tvTitle;
    /* renamed from: O */
    private String title;
    /* renamed from: P */
    private String word_title;

    public void onBackPressed() {
        super.onBackPressed();
    }

    protected void onCreate(Bundle bundle) {
        try {
            super.onCreate(bundle);
            setContentView((int) R.layout.gramma_detail_screen);
            bundle = getIntent().getExtras();
            this.title = bundle.getString("bundle_title");
            this.word_title = bundle.getString("bundle_word_title");
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
        this.webView = (WebView) findViewById(R.id.webView);
        this.webView.setScrollContainer(false);
        this.webView.loadDataWithBaseURL(null, this.word_title, "text/html", "utf-8", null);
        this.tvTitle = (TextView) findViewById(R.id.title);
        this.tvTitle.setText(this.title);
    }
}
