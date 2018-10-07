package app.giaotieptienghan;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;

import app.giaotieptienghan.adapter.GrammarAdapter;
import app.giaotieptienghan.model.GrammaItem;
import app.giaotieptienghan.repository.EndlessloveDB1;

@SuppressLint("WrongConstant")
public class FavoriteActivity extends BaseAudioPlayActivity implements OnItemClickListener {
    /* renamed from: M */
    private ProgressBar progressBar1;
    /* renamed from: N */
    private ListView listView1;
    /* renamed from: O */
    private GrammarAdapter grammarAdapter;
    /* renamed from: P */
    private ArrayList<GrammaItem> grammaItems;

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
                FavoriteActivity.this.grammaItems = EndlessloveDB1.mo2882a(1);
            } catch (Exception e) {
                e.printStackTrace();
            } catch (Throwable th) {
                EndlessloveDB1.mo2885c();
            }
            EndlessloveDB1.mo2885c();
            return FavoriteActivity.this.grammaItems;
        }

        /* renamed from: a */
        protected void onPostExecute(ArrayList<GrammaItem> arrayList) {
            super.onPostExecute(arrayList);
            if (arrayList != null) {
                FavoriteActivity.this.grammarAdapter = new GrammarAdapter(FavoriteActivity.this, arrayList);
                FavoriteActivity.this.listView1.setAdapter(FavoriteActivity.this.grammarAdapter);
                FavoriteActivity.this.progressBar1.setVisibility(8);
            }
        }

        protected void onPreExecute() {
            super.onPreExecute();
            FavoriteActivity.this.progressBar1.setVisibility(0);
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
        GrammaItem grammaItem = (GrammaItem) this.grammaItems.get(i);
        Intent intent = new Intent(this, GrammaDetail.class);
        intent.setFlags(268435456);
        intent.putExtra("bundle_title", grammaItem.phrase);
        intent.putExtra("bundle_word_title", grammaItem.description);
        startActivity(intent);
    }

    /* renamed from: r */
    private void mo10050r() {
        if (this.listView1 == null) {
            this.listView1 = (ListView) findViewById(R.id.listView);
        }
        if (this.progressBar1 == null) {
            this.progressBar1 = (ProgressBar) findViewById(R.id.progressBar);
        }
        this.listView1.setOnItemClickListener(this);
        new LoadFavoriteGrammaItems().execute(new String[0]);
    }
}
