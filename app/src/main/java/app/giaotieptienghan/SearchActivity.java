package app.giaotieptienghan;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import java.util.ArrayList;

import app.giaotieptienghan.adapter.PhraseAdapter;
import app.giaotieptienghan.model.C0768b;
import app.giaotieptienghan.model.PhraseItem;
import app.giaotieptienghan.repository.EndlessLoveDB;

@SuppressLint("WrongConstant")
public class SearchActivity extends BaseAudioPlayActivity {
    /* renamed from: M */
    private ProgressBar f12159M;
    /* renamed from: N */
    private TextView f12160N;
    /* renamed from: O */
    private EditText f12161O;
    /* renamed from: P */
    private ArrayList<PhraseItem> f12162P;

    /* renamed from: com.example.english.SearchActivity$1 */
    class C07271 implements TextWatcher {
        C07271() {
        }

        public void afterTextChanged(Editable editable) {
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            if (SearchActivity.this.f12162P != null && SearchActivity.this.f12162P.size() > 0) {
                ArrayList arrayList = new ArrayList();
                if (charSequence == null || charSequence.length() == 0) {
                    arrayList = SearchActivity.this.f12162P;
                } else {
                    for (i2 = 0; i2 < SearchActivity.this.f12162P.size(); i2++) {
                        if (((PhraseItem) SearchActivity.this.f12162P.get(i2)).search.toLowerCase().contains(charSequence.toString().toLowerCase())) {
                            arrayList.add(SearchActivity.this.f12162P.get(i2));
                        }
                    }
                    SearchActivity.this.phraseAdapter.mo2831a(charSequence.toString().toLowerCase());
                }
                SearchActivity.this.phraseItems.clear();
                SearchActivity.this.phraseItems.addAll(arrayList);
                SearchActivity.this.phraseAdapter.mo2832a(arrayList);
                SearchActivity.this.phraseAdapter.notifyDataSetChanged();
            }
        }
    }

    /* renamed from: com.example.english.SearchActivity$a */
    private class C0728a extends AsyncTask<String, Void, ArrayList<PhraseItem>> {
        private C0728a() {
        }

        /* synthetic */ C0728a(SearchActivity searchActivity, C07271 c07271) {
            this();
        }

        /* renamed from: a */
        protected ArrayList<PhraseItem> doInBackground(String... strArr) {
            if (C0768b.phraseItems == null) {
                EndlessLoveDB endlessLoveDB = new EndlessLoveDB(SearchActivity.this);
                try {
                    endlessLoveDB.initDB();
                    endlessLoveDB.getReadableDB();
                    C0768b.phraseItems = endlessLoveDB.getPhrasesByCategoryId(-1);
                } catch (Exception e) {
                    e.printStackTrace();
                } catch (Throwable th) {
                    endlessLoveDB.closeDB();
                }
                endlessLoveDB.closeDB();
            }
            return C0768b.phraseItems;
        }

        /* renamed from: a */
        protected void onPostExecute(ArrayList<PhraseItem> arrayList) {
            super.onPostExecute(arrayList);
            if (arrayList != null && arrayList.size() > 0) {
                SearchActivity.this.phraseItems = (ArrayList) arrayList.clone();
                SearchActivity.this.f12162P = (ArrayList) arrayList.clone();
                SearchActivity.this.phraseAdapter = new PhraseAdapter(SearchActivity.this, arrayList);
                SearchActivity.this.listView.setAdapter(SearchActivity.this.phraseAdapter);
            }
            SearchActivity.this.f12159M.setVisibility(8);
        }

        protected void onPreExecute() {
            super.onPreExecute();
            SearchActivity.this.f12159M.setVisibility(0);
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.search_screen);
        try {
            this.f12005H = 2;
            setTitle("Tìm Kiếm...");
            mo10050r();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return false;
    }

    /* renamed from: r */
    public void mo10050r() {
        //mo10030k();
        if (this.listView == null) {
            this.listView = (ListView) findViewById(R.id.listView);
        }
        if (this.f12161O == null) {
            this.f12161O = (EditText) findViewById(R.id.editSearch);
        }
        this.f12161O.addTextChangedListener(new C07271());
        if (this.f12159M == null) {
            this.f12159M = (ProgressBar) findViewById(R.id.progressBar);
        }
        if (this.f12160N == null) {
            this.f12160N = (TextView) findViewById(R.id.empty);
        }
        this.listView.setOnItemClickListener(this);
        mo10031l();
        new C0728a(this, null).execute(new String[0]);
    }
}
