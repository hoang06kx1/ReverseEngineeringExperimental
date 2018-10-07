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
public class GrammarAdapter extends ArrayAdapter<GrammaItem> {
    /* renamed from: a */
    private Context context;
    /* renamed from: b */
    private ArrayList<GrammaItem> items;
    /* renamed from: c */
    private LayoutInflater inflater;

    /* renamed from: com.example.english.a.c$a */
    private class Holder {
        /* renamed from: b */
        private TextView tvTitle;
        /* renamed from: c */
        private TextView tvGenres;
        /* renamed from: d */
        private ImageView icFavorite;

        private Holder() {
        }
    }

    public GrammarAdapter(Context context, ArrayList<GrammaItem> arrayList) {
        super(context, 0, arrayList);
        this.context = context;
        this.items = arrayList;
        this.inflater = LayoutInflater.from(context);
    }

    /* renamed from: a */
    private void m2906a(int i, int i2) {
        EndlessloveDB1 EndlessloveDB1 = new EndlessloveDB1(this.context);
        try {
            EndlessloveDB1.mo2881a();
            EndlessloveDB1.mo2884b();
            EndlessloveDB1.mo2883a(i, i2);
            notifyDataSetChanged();
            if (this.items.size() < C0768b.grammaItems.size()) {
                this.context.sendBroadcast(new Intent("com.fun.korean"));
            }
        } catch (Exception unused) {
            EndlessloveDB1.mo2885c();
        } catch (Throwable th) {
            EndlessloveDB1.mo2885c();
        }
    }

    /* renamed from: a */
    public GrammaItem getItem(int i) {
        return (GrammaItem) this.items.get(i);
    }

    /* renamed from: a */
    public void mo2820a(ArrayList<GrammaItem> arrayList) {
        this.items = arrayList;
    }

    public int getCount() {
        return this.items.size();
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        Holder holder;
        ImageView a;
        int i2;
        if (view == null) {
            view = this.inflater.inflate(R.layout.gramma_adapter_item, null);
            holder = new Holder();
            holder.tvTitle = (TextView) view.findViewById(R.id.tvTitle);
            holder.tvGenres = (TextView) view.findViewById(R.id.tvGenres);
            holder.icFavorite = (ImageView) view.findViewById(R.id.ic_favorite);
            view.setTag(holder);
        } else {
            holder = (Holder) view.getTag();
        }
        final GrammaItem a2 = getItem(i);
        if (a2.favorites == 1) {
            a = holder.icFavorite;
            i2 = R.drawable.ic_action_favorite_active;
        } else {
            a = holder.icFavorite;
            i2 = R.drawable.ic_action_favorite;
        }
        a.setImageResource(i2);
        holder.icFavorite.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ImageView a;
                int i;
                if (a2.favorites == 0) {
                    a2.favorites = 1;
                    a = holder.icFavorite;
                    i = R.drawable.ic_action_favorite_active;
                } else {
                    a2.favorites = 0;
                    a = holder.icFavorite;
                    i = R.drawable.ic_action_favorite;
                }
                a.setImageResource(i);
                GrammarAdapter.this.m2906a(a2.favorites, a2.id);
            }
        });
        TextView b = holder.tvTitle;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(i + 1);
        stringBuilder.append(". ");
        stringBuilder.append(a2.phrase);
        b.setText(stringBuilder.toString());
        holder.tvGenres.setText(a2.description);
        return view;
    }
}
