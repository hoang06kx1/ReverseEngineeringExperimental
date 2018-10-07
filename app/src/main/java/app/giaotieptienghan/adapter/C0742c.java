package app.giaotieptienghan.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import app.giaotieptienghan.R;
import app.giaotieptienghan.model.C0768b;
import app.giaotieptienghan.model.GrammaItem;
import app.giaotieptienghan.repository.EndlessloveDB1;

/* renamed from: com.example.english.a.c */
public class C0742c extends ArrayAdapter<GrammaItem> {
    /* renamed from: a */
    private Context f1871a;
    /* renamed from: b */
    private ArrayList<GrammaItem> f1872b;
    /* renamed from: c */
    private LayoutInflater f1873c;

    /* renamed from: com.example.english.a.c$a */
    private class C0741a {
        /* renamed from: b */
        private TextView f1868b;
        /* renamed from: c */
        private TextView f1869c;
        /* renamed from: d */
        private ImageView f1870d;

        private C0741a() {
        }
    }

    public C0742c(Context context, ArrayList<GrammaItem> arrayList) {
        super(context, 0, arrayList);
        this.f1871a = context;
        this.f1872b = arrayList;
        this.f1873c = LayoutInflater.from(context);
    }

    /* renamed from: a */
    private void m2906a(int i, int i2) {
        EndlessloveDB1 EndlessloveDB1 = new EndlessloveDB1(this.f1871a);
        try {
            EndlessloveDB1.mo2881a();
            EndlessloveDB1.mo2884b();
            EndlessloveDB1.mo2883a(i, i2);
            notifyDataSetChanged();
            if (this.f1872b.size() < C0768b.f1958b.size()) {
                this.f1871a.sendBroadcast(new Intent("com.fun.korean"));
            }
        } catch (Exception unused) {
            EndlessloveDB1.mo2885c();
        } catch (Throwable th) {
            EndlessloveDB1.mo2885c();
        }
    }

    /* renamed from: a */
    public GrammaItem getItem(int i) {
        return (GrammaItem) this.f1872b.get(i);
    }

    /* renamed from: a */
    public void mo2820a(ArrayList<GrammaItem> arrayList) {
        this.f1872b = arrayList;
    }

    public int getCount() {
        return this.f1872b.size();
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        C0741a c0741a;
        ImageView a;
        int i2;
        if (view == null) {
            view = this.f1873c.inflate(R.layout.gramma_adapter_item, null);
            c0741a = new C0741a();
            c0741a.f1868b = (TextView) view.findViewById(R.id.tvTitle);
            c0741a.f1869c = (TextView) view.findViewById(R.id.tvGenres);
            c0741a.f1870d = (ImageView) view.findViewById(R.id.ic_favorite);
            view.setTag(c0741a);
        } else {
            c0741a = (C0741a) view.getTag();
        }
        final GrammaItem a2 = getItem(i);
        if (a2.favorites == 1) {
            a = c0741a.f1870d;
            i2 = R.drawable.ic_action_favorite_active;
        } else {
            a = c0741a.f1870d;
            i2 = R.drawable.ic_action_favorite;
        }
        a.setImageResource(i2);
        c0741a.f1870d.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ImageView a;
                int i;
                if (a2.favorites == 0) {
                    a2.favorites = 1;
                    a = c0741a.f1870d;
                    i = R.drawable.ic_action_favorite_active;
                } else {
                    a2.favorites = 0;
                    a = c0741a.f1870d;
                    i = R.drawable.ic_action_favorite;
                }
                a.setImageResource(i);
                C0742c.this.m2906a(a2.favorites, a2.id);
            }
        });
        TextView b = c0741a.f1868b;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(i + 1);
        stringBuilder.append(". ");
        stringBuilder.append(a2.phrase);
        b.setText(stringBuilder.toString());
        c0741a.f1869c.setText(a2.description);
        return view;
    }
}
