package app.giaotieptienghan;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;

import app.giaotieptienghan.adapter.GrammarAdapter;
import app.giaotieptienghan.model.C0768b;
import app.giaotieptienghan.model.GrammaItem;
import app.giaotieptienghan.repository.EndlessloveDB1;

@SuppressLint("WrongConstant")
public class GrammaActivity extends BaseAudioPlayActivity implements OnItemClickListener {
    /* renamed from: M */
    private ProgressBar progressBar1;
    /* renamed from: N */
    private ListView listView1;
    /* renamed from: O */
    private GrammarAdapter grammarAdapter;
    /* renamed from: P */
    private C0709b f12067P;

    /* renamed from: com.example.english.GrammaActivity$a */
    private class GetGrammaItemTask extends AsyncTask<String, Void, ArrayList<GrammaItem>> {
        private GetGrammaItemTask() {
        }

        /* renamed from: a */
        protected ArrayList<GrammaItem> doInBackground(String... strArr) {
            EndlessloveDB1 EndlessloveDB1 = new EndlessloveDB1(GrammaActivity.this);
            try {
                EndlessloveDB1.mo2881a();
                EndlessloveDB1.mo2884b();
                C0768b.grammaItems = EndlessloveDB1.mo2882a(-1);
            } catch (Exception e) {
                e.printStackTrace();
            } catch (Throwable th) {
                EndlessloveDB1.mo2885c();
            }
            EndlessloveDB1.mo2885c();
            return C0768b.grammaItems;
        }

        /* renamed from: a */
        protected void onPostExecute(ArrayList<GrammaItem> arrayList) {
            super.onPostExecute(arrayList);
            if (arrayList != null) {
                if (GrammaActivity.this.grammarAdapter == null) {
                    GrammaActivity.this.grammarAdapter = new GrammarAdapter(GrammaActivity.this, arrayList);
                    GrammaActivity.this.listView1.setAdapter(GrammaActivity.this.grammarAdapter);
                } else {
                    GrammaActivity.this.grammarAdapter.mo2820a(C0768b.grammaItems);
                    GrammaActivity.this.grammarAdapter.notifyDataSetChanged();
                }
                GrammaActivity.this.progressBar1.setVisibility(8);
            }
        }

        protected void onPreExecute() {
            super.onPreExecute();
            GrammaActivity.this.progressBar1.setVisibility(0);
        }
    }

    /* renamed from: com.example.english.GrammaActivity$b */
    public class C0709b extends BroadcastReceiver {
        public void onReceive(Context context, Intent intent) {
            //C0769c.m2995b("broad", "ok");
            try {
                new GetGrammaItemTask().execute(new String[0]);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.gramma_screen);
        try {
            setTitle("Ngữ pháp");
            mo10050r();
        } catch (Exception unused) {
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.f12067P != null) {
            unregisterReceiver(this.f12067P);
        }
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        GrammaItem grammaItem = (GrammaItem) C0768b.grammaItems.get(i);
        Intent intent = new Intent(this, GrammaDetail.class);
        intent.setFlags(268435456);
        intent.putExtra("bundle_title", grammaItem.phrase);
        intent.putExtra("bundle_word_title", grammaItem.description);
        startActivity(intent);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != R.id.action_settings) {
            return super.onOptionsItemSelected(menuItem);
        }
        Intent intent = new Intent(this, FavoriteActivity.class);
        intent.setFlags(268435456);
        startActivity(intent);
        return true;
    }

    protected void onStop() {
        super.onStop();
    }

    /* renamed from: r */
    public void mo10050r() {
        if (this.f12067P == null) {
            this.f12067P = new C0709b();
            registerReceiver(this.f12067P, new IntentFilter("com.fun.korean"));
        }
        if (this.listView1 == null) {
            this.listView1 = (ListView) findViewById(R.id.listView);
        }
        if (this.progressBar1 == null) {
            this.progressBar1 = (ProgressBar) findViewById(R.id.progressBar);
        }
        this.listView1.setOnItemClickListener(this);
        if (C0768b.grammaItems == null) {
            new GetGrammaItemTask().execute(new String[0]);
            return;
        }
        this.grammarAdapter = new GrammarAdapter(this, C0768b.grammaItems);
        this.listView1.setAdapter(this.grammarAdapter);
    }
}
