package app.giaotieptienghan;

import android.os.Bundle;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.lang.Character.UnicodeBlock;
import java.util.HashSet;
import java.util.Set;

public class GrammaDetail extends BaseAudioPlayActivity {
    /* renamed from: K */
    private TextView f2861K;
    /* renamed from: L */
    private String f2862L;
    /* renamed from: M */
    private String f2863M;

    class C08491 extends HashSet<UnicodeBlock> {
        C08491() {
            add(UnicodeBlock.HIRAGANA);
            add(UnicodeBlock.KATAKANA);
            add(UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS);
        }
    }

    /* renamed from: b */
    private String m4590b(String str) {
        Set c08491 = new C08491();
        StringBuilder stringBuilder = new StringBuilder();
        for (String str2 : str.split("\n")) {
            char[] toCharArray = str2.toCharArray();
            if (0 < toCharArray.length) {
                char c = toCharArray[0];
                if (c08491.contains(UnicodeBlock.of(c))) {
                    stringBuilder.append("<font color=#2092be>" + str2 + "</font><br>");
                } else if (String.valueOf(c).equals("①") || String.valueOf(c).equals("②") || String.valueOf(c).equals("③") || String.valueOf(c).equals("④") || String.valueOf(c).equals("⑤")) {
                    stringBuilder.append("<font color=#000000>" + str2 + "</font><br>");
                } else {
                    stringBuilder.append(str2 + "<br><br>");
                }
            }
        }
        return stringBuilder.toString();
    }

    public void onBackPressed() {
        super.onBackPressed();
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.gramma_detail_screen);
        try {
            Bundle extras = getIntent().getExtras();
            this.f2862L = extras.getString("bundle_title");
            this.f2863M = extras.getString("bundle_word_title");
            setTitle(this.f2862L);
            mo4107r();
        } catch (Exception e) {
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
    public void mo4107r() {
        initAds();
        this.f2861K = (TextView) findViewById(R.id.webView);
        this.f2861K.setText(Html.fromHtml(m4590b(this.f2863M)));
        this.f2861K.setLineSpacing(2.0f, 1.2f);
        this.f2861K.setMovementMethod(new ScrollingMovementMethod());
    }
}
