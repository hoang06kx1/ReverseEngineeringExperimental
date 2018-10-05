package app.giaotieptienghan;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ProgressBar;

import java.util.ArrayList;

import app.giaotieptienghan.model.CategoryItem;

public class HomeFragment extends Fragment implements OnItemClickListener {
    /* renamed from: V */
    private GridView f10242V;
    /* renamed from: W */
    private C0745d f10243W;
    /* renamed from: X */
    private ArrayList<CategoryItem> f10244X;
    /* renamed from: Y */
    private ProgressBar f10245Y;

    /* renamed from: com.example.english.e$a */
    private class C0797a extends AsyncTask<String, Void, ArrayList<CategoryItem>> {
        private C0797a() {
        }

        /* renamed from: a */
        protected ArrayList<CategoryItem> doInBackground(String... strArr) {
            C0764c c0764c = new C0764c(C2848e.this.mo7206d());
            try {
                c0764c.mo2871a();
                c0764c.mo2875b();
                C2848e.this.f10244X = c0764c.mo2873a("");
            } catch (Exception e) {
                e.printStackTrace();
            } catch (Throwable th) {
                c0764c.mo2878c();
            }
            c0764c.mo2878c();
            return C2848e.this.f10244X;
        }

        /* renamed from: a */
        protected void onPostExecute(ArrayList<CategoryItem> arrayList) {
            super.onPostExecute(arrayList);
            if (!(C2848e.this.f10244X == null || C2848e.this.f10244X.size() <= 0 || C2848e.this.mo7206d() == null)) {
                C2848e.this.f10243W = new C0745d(C2848e.this.mo7206d(), C2848e.this.f10244X);
                C2848e.this.f10242V.setAdapter(C2848e.this.f10243W);
            }
            C2848e.this.f10245Y.setVisibility(8);
        }

        protected void onPreExecute() {
            super.onPreExecute();
            C2848e.this.f10245Y.setVisibility(0);
        }
    }

    /* renamed from: b */
    private void initView(View view) {
        this.f10242V = (GridView) view.findViewById(R.id.gridView);
        this.f10242V.setOnItemClickListener(this);
        if (this.f10245Y == null) {
            this.f10245Y = (ProgressBar) view.findViewById(R.id.progressBar);
        }
        new C0797a().execute(new String[0]);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.home_screen, null);
        initView(inflate);
        return inflate;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        CategoryItem categoryItem = (CategoryItem) this.f10244X.get(i);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("item ");
        stringBuilder.append(categoryItem.f2068id);
        C0769c.m2995b("onitemclic", stringBuilder.toString());
        Intent intent = new Intent(getActivity(), PhraseDetail.class);
        intent.setFlags(268435456);
        intent.putExtra("bundle_id", categoryItem.getF2068id());
        intent.putExtra("bundle_title", categoryItem.getVietnamese());
        getActivity().startActivity(intent);
    }
}