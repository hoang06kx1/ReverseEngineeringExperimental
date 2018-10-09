package app.giaotieptienghan;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

import app.giaotieptienghan.adapter.PhraseAdapter;
import app.giaotieptienghan.model.PhraseItem;
import app.giaotieptienghan.repository.EndlessLoveDB;

@SuppressLint("WrongConstant")
public class FavoriteDetailActivity extends BaseAudioPlayActivity {
    /* renamed from: M */
    private TextView tvEmpty;

    /* renamed from: com.example.english.FavoriteDetailActivity$a */
    private class LoadFavoritePhrases extends AsyncTask<String, Void, ArrayList<PhraseItem>> {
        private LoadFavoritePhrases() {
        }

        /* renamed from: a */
        protected ArrayList<PhraseItem> doInBackground(String... strArr) {
            EndlessLoveDB db = new EndlessLoveDB(FavoriteDetailActivity.this);
            try {
                db.initDB();
                db.getReadableDB();
                FavoriteDetailActivity.this.phraseItems = db.mo2880e();
            } catch (Exception e) {
                e.printStackTrace();
            } catch (Throwable th) {
                db.closeDB();
            }
            db.closeDB();
            return FavoriteDetailActivity.this.phraseItems;
        }

        /* renamed from: a */
        protected void onPostExecute(ArrayList<PhraseItem> arrayList) {
            super.onPostExecute(arrayList);
            if (arrayList == null || arrayList.size() <= 0) {
                FavoriteDetailActivity.this.tvEmpty.setVisibility(0);
            } else {
                FavoriteDetailActivity.this.phraseAdapter = new PhraseAdapter(FavoriteDetailActivity.this, arrayList);
                FavoriteDetailActivity.this.listView.setAdapter(FavoriteDetailActivity.this.phraseAdapter);
                FavoriteDetailActivity.this.tvEmpty.setVisibility(8);
            }
            FavoriteDetailActivity.this.progressBar.setVisibility(8);
        }

        protected void onPreExecute() {
            super.onPreExecute();
            FavoriteDetailActivity.this.progressBar.setVisibility(0);
        }
    }

    /* renamed from: a */
    private void m16308a(Class cls) {
        if (this.phraseItems == null || this.phraseItems.size() < 4) {
            Toast.makeText(this, "It takes at least 4 sentences.", 0).show();
            return;
        }
        Intent intent = new Intent(this, cls);
        intent.setFlags(268435456);
        intent.putExtra("bundle_fav_id", "0");
        intent.putExtra("bundle_title", this.f12013n);
        startActivity(intent);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.detail_screen);
        try {
            this.f12005H = 2;
            setTitle(getString(R.string.menu_fav));
            mo10050r();
            mo10051s();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return false;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        try {
            Class cls;
            int itemId = menuItem.getItemId();
            if (itemId != R.id.action_quiz1) {
                if (itemId == R.id.action_settings) {
                    cls = QuizDetailSmal.class;
                }
                return super.onOptionsItemSelected(menuItem);
            }
            cls = QuizActivity1.class;
            m16308a(cls);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.onOptionsItemSelected(menuItem);
    }

    protected void onResume() {
        super.onResume();
    }

    /* renamed from: r */
    public void mo10050r() {
        if (this.listView == null) {
            this.listView = (ListView) findViewById(R.id.listView);
        }
        if (this.tvEmpty == null) {
            this.tvEmpty = (TextView) findViewById(R.id.empty);
        }
        //mo10030k();
        mo10031l();
    }

    /* renamed from: s */
    public void mo10051s() {
        this.listView.setOnItemClickListener(this);
        new LoadFavoritePhrases().execute(new String[0]);
    }
}
