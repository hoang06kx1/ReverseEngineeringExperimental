package app.giaotieptienghan;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;

import app.giaotieptienghan.adapter.C0742c;
import app.giaotieptienghan.model.GrammaItem;
import app.giaotieptienghan.repository.EndlessloveDB1;

@SuppressLint("WrongConstant")
public class FavoriteActivity extends BaseAudioPlayActivity implements OnItemClickListener {
    /* renamed from: M */
    private ProgressBar f12059M;
    /* renamed from: N */
    private ListView f12060N;
    /* renamed from: O */
    private C0742c f12061O;
    /* renamed from: P */
    private ArrayList<GrammaItem> f12062P;

    /* renamed from: com.example.english.FavoriteActivity$a */
    private class LoadFavoriteGrammaItems extends AsyncTask<String, Void, ArrayList<GrammaItem>> {
        private LoadFavoriteGrammaItems() {
        }

        /* renamed from: a */
        protected ArrayList<GrammaItem> doInBackground(String... strArr) {
            EndlessloveDB1 EndlessloveDB1 = new EndlessloveDB1(FavoriteActivity.this);
            try {
                EndlessloveDB1.mo2881a();
                EndlessloveDB1.mo2884b();
                FavoriteActivity.this.f12062P = EndlessloveDB1.mo2882a(1);
            } catch (Exception e) {
                e.printStackTrace();
            } catch (Throwable th) {
                EndlessloveDB1.mo2885c();
            }
            EndlessloveDB1.mo2885c();
            return FavoriteActivity.this.f12062P;
        }

        /* renamed from: a */
        protected void onPostExecute(ArrayList<GrammaItem> arrayList) {
            super.onPostExecute(arrayList);
            if (arrayList != null) {
                FavoriteActivity.this.f12061O = new C0742c(FavoriteActivity.this, arrayList);
                FavoriteActivity.this.f12060N.setAdapter(FavoriteActivity.this.f12061O);
                FavoriteActivity.this.f12059M.setVisibility(8);
            }
        }

        protected void onPreExecute() {
            super.onPreExecute();
            FavoriteActivity.this.f12059M.setVisibility(0);
        }
    }

    public void onBackPressed() {
        super.onBackPressed();
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.gramma_screen);
        try {
            setTitle("Ngữ pháp - yêu thích");
            mo10050r();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        GrammaItem grammaItem = (GrammaItem) this.f12062P.get(i);
        Intent intent = new Intent(this, GrammaDetail.class);
        intent.setFlags(268435456);
        intent.putExtra("bundle_title", grammaItem.phrase);
        intent.putExtra("bundle_word_title", grammaItem.description);
        startActivity(intent);
    }

    /* renamed from: r */
    public void mo10050r() {
        if (this.f12060N == null) {
            this.f12060N = (ListView) findViewById(R.id.listView);
        }
        if (this.f12059M == null) {
            this.f12059M = (ProgressBar) findViewById(R.id.progressBar);
        }
        this.f12060N.setOnItemClickListener(this);
        new LoadFavoriteGrammaItems().execute(new String[0]);
    }
}
