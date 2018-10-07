package app.giaotieptienghan;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import java.io.PrintStream;
import java.util.ArrayList;

import app.giaotieptienghan.adapter.PhraseAdapter;
import app.giaotieptienghan.model.CategoryItem;
import app.giaotieptienghan.model.PhraseItem;
import app.giaotieptienghan.repository.EndlessLoveDB;

@SuppressLint("WrongConstant")
public class PhraseDetailActivity extends BaseAudioPlayActivity implements OnItemClickListener {
    /* renamed from: M */
    private int phraseId;
    /* renamed from: N */
    private int f12073N = 0;
    /* renamed from: O */
    private String vietnamese;

    /* renamed from: com.example.english.PhraseDetailActivity$a */
    private class loadPhraseDetailActivity extends AsyncTask<String, Void, ArrayList<PhraseItem>> {
        private loadPhraseDetailActivity() {
        }

        /* renamed from: a */
        protected ArrayList<PhraseItem> doInBackground(String... strArr) {
            EndlessLoveDB db = new EndlessLoveDB(PhraseDetailActivity.this);
            try {
                db.initDB();
                db.getReadableDB();
                PhraseDetailActivity.this.phraseItems = db.mo2872a(PhraseDetailActivity.this.phraseId);
                if (PhraseDetailActivity.this.f12073N != 0) {
                    PhraseDetailActivity PhraseDetailActivity = PhraseDetailActivity.this;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(PhraseDetailActivity.this.phraseId);
                    stringBuilder.append("");
                    PhraseDetailActivity.vietnamese = ((CategoryItem) db.mo2873a(stringBuilder.toString()).get(0)).getVietnamese();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } catch (Throwable th) {
                db.closeDB();
            }
            db.closeDB();
            return PhraseDetailActivity.this.phraseItems;
        }

        /* renamed from: a */
        protected void onPostExecute(ArrayList<PhraseItem> arrayList) {
            super.onPostExecute(arrayList);
            if (arrayList != null) {
                PhraseDetailActivity.this.phraseAdapter = new PhraseAdapter(PhraseDetailActivity.this, arrayList);
                PhraseDetailActivity.this.listView.setAdapter(PhraseDetailActivity.this.phraseAdapter);
                PhraseDetailActivity.this.progressBar.setVisibility(8);
                if (PhraseDetailActivity.this.f12073N != 0) {
                    PhraseDetailActivity.this.setTitle(PhraseDetailActivity.this.vietnamese);
                    int a = PhraseDetailActivity.this.m16323a((ArrayList) arrayList, PhraseDetailActivity.this.f12073N);
                    if (a != -1) {
                        PhraseDetailActivity.this.phraseAdapter.mo2830a(a);
                        PhraseDetailActivity.this.phraseAdapter.notifyDataSetChanged();
                        PhraseDetailActivity.this.listView.smoothScrollToPosition(a);
                    }
                    PhraseDetailActivity.this.f12073N = 0;
                }
            }
        }

        protected void onPreExecute() {
            super.onPreExecute();
            PhraseDetailActivity.this.progressBar.setVisibility(0);
        }
    }

    /* renamed from: a */
    private int m16323a(ArrayList<PhraseItem> arrayList, int i) {
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            if (i == ((PhraseItem) arrayList.get(i2)).id) {
                return i2;
            }
        }
        return -1;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.detail_screen);
        try {
            bundle = getIntent().getExtras();
            this.phraseId = bundle.getInt("bundle_id");
            this.f12073N = bundle.getInt("bundle_phrase_id");
            PrintStream printStream = System.out;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("phraseId ");
            stringBuilder.append(this.f12073N);
            printStream.println(stringBuilder.toString());
            this.f12005H = 2;
            this.vietnamese = bundle.getString("bundle_title");
            if (Utils.isStringEmpty(this.vietnamese)) {
                this.vietnamese = getString(R.string.app_name);
            }
            setTitle(this.vietnamese);
            mo10050r();
            mo10051s();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.menu_detail, menu);
        return false;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        //Intent intent;
        //int itemId = menuItem.getItemId();
        //if (itemId != R.id.action_quiz1) {
        //    if (itemId == R.id.action_settings) {
        //        intent = new Intent(this, QuizDetailSmal.class);
        //    }
        //    return super.onOptionsItemSelected(menuItem);
        //}
        //intent = new Intent(this, QuizActivity1.class);
        //intent.setFlags(268435456);
        //intent.putExtra("bundle_title", this.vietnamese);
        //intent.putExtra("bundle_id", this.phraseId);
        //startActivity(intent);
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
        //mo10030k();
        mo10031l(); // init views
    }

    /* renamed from: s */
    public void mo10051s() {
        this.listView.setOnItemClickListener(this);
        new loadPhraseDetailActivity().execute(new String[0]);
    }
}
