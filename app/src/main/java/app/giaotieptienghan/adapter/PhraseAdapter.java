package app.giaotieptienghan.adapter;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.res.ColorStateList;
import android.text.SpannableString;
import android.text.style.TextAppearanceSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.io.PrintStream;
import java.util.ArrayList;

import app.giaotieptienghan.R;
import app.giaotieptienghan.model.PhraseItem;

public class PhraseAdapter extends ArrayAdapter<PhraseItem> {
    /* renamed from: a */
    private Context context;
    /* renamed from: b */
    private ArrayList<PhraseItem> phraseItems;
    /* renamed from: c */
    private LayoutInflater inflater;
    /* renamed from: d */
    private int f1900d = -1;
    /* renamed from: e */
    private String f1901e = null;

    /* renamed from: com.example.english.a.e$a */
    private class PhraseViewHolder {
        /* renamed from: b */
        private TextView tvTitle;
        /* renamed from: c */
        private TextView tvGenres;
        /* renamed from: d */
        private TextView tvGenres1;
        /* renamed from: e */
        private ImageView imgFavorite;
        /* renamed from: f */
        private ImageView imgCopy;

        private PhraseViewHolder() {
        }

    }

    public PhraseAdapter(Context context, ArrayList<PhraseItem> arrayList) {
        super(context, 0, arrayList);
        this.context = context;
        this.phraseItems = arrayList;
        this.inflater = LayoutInflater.from(context);
    }

    /* renamed from: a */
    private void m2927a(int i, int i2) {
        try {
            C0764c c0764c = new C0764c(this.context);
            c0764c.mo2871a();
            c0764c.mo2875b();
            c0764c.mo2874a(i, i2);
            c0764c.mo2878c();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    private void m2928a(TextView textView, String str, String str2) {
        try {
            if (!(this.f1901e == null || this.f1901e.isEmpty())) {
                int indexOf = str2.toLowerCase().indexOf(this.f1901e.toLowerCase());
                PrintStream printStream = System.out;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("startPos ");
                stringBuilder.append(indexOf);
                printStream.println(stringBuilder.toString());
                int length = this.f1901e.length() + indexOf;
                if (indexOf != -1) {
                    try {
                        SpannableString spannableString = new SpannableString(str);
                        //spannableString.setSpan(new TextAppearanceSpan(null, 1, -1, new ColorStateList(new int[][]{new int[0]}, new int[]{-65536}), null), indexOf, length, 33);
                        spannableString.setSpan(new TextAppearanceSpan(null, 1, -1, new ColorStateList(new int[][]{new int[0]}, new int[]{-65536}), null), indexOf, length, 33);
                        textView.setText(spannableString);
                        return;
                    } catch (Exception e) {
                        e.printStackTrace();
                        return;
                    }
                }
            }
            textView.setText(str);
        } catch (Exception unused) {
            textView.setText(str);
        }
    }

    /* renamed from: a */
    public void mo2830a(int i) {
        this.f1900d = i;
    }

    /* renamed from: a */
    public void mo2831a(String str) {
        this.f1901e = str;
    }

    /* renamed from: a */
    public void mo2832a(ArrayList<PhraseItem> arrayList) {
        this.phraseItems = arrayList;
    }

    /* renamed from: b */
    public PhraseItem getItem(int i) {
        return (PhraseItem) this.phraseItems.get(i);
    }

    public int getCount() {
        return this.phraseItems.size();
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        PhraseViewHolder phraseViewHolder;
        final ImageView imgFavorite;
        int i2;
        if (view == null) {
            view = this.inflater.inflate(R.layout.detail_adapter_item, null);
            phraseViewHolder = new PhraseViewHolder();
            phraseViewHolder.tvTitle = (TextView) view.findViewById(R.id.tvTitle);
            phraseViewHolder.tvGenres = (TextView) view.findViewById(R.id.tvGenres);
            phraseViewHolder.tvGenres1 = (TextView) view.findViewById(R.id.tvGenres1);
            phraseViewHolder.imgFavorite = (ImageView) view.findViewById(R.id.favorite);
            phraseViewHolder.imgCopy = (ImageView) view.findViewById(R.id.imgCopy);
            view.setTag(phraseViewHolder);
        } else {
            phraseViewHolder = (PhraseViewHolder) view.getTag();
        }
        final PhraseItem b = getItem(i);
        view.setBackgroundColor(i == this.f1900d ? this.context.getResources().getColor(R.color.bg_solid) : 0);
        if (b.getFavorite() == 1) {
            i2 = R.drawable.ic_action_favorite_active;
        } else {
            i2 = R.drawable.ic_action_favorite;
        }
        imgFavorite = phraseViewHolder.imgFavorite;
        imgFavorite.setImageResource(i2);
        phraseViewHolder.imgFavorite.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ImageView imageView;
                int i;
                if (b.getFavorite() == 0) {
                    b.setFavorite(1);
                    imageView = imgFavorite;
                    i = R.drawable.ic_action_favorite_active;
                } else {
                    b.setFavorite(0);
                    imageView = imgFavorite;
                    i = R.drawable.ic_action_favorite;
                }
                imageView.setImageResource(i);
                PhraseAdapter.this.m2927a(b.getFavorite(), b.get_id());
            }
        });
        phraseViewHolder.imgCopy.setOnClickListener(new OnClickListener() {
            @SuppressLint("WrongConstant")
            public void onClick(View view) {
                try {
                    Context a = PhraseAdapter.this.context;
                    ((ClipboardManager) a.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("korean", b.getKorean()));
                    Toast.makeText(PhraseAdapter.this.context, "Copy to clipboard...", 0).show();
                } catch (Exception unused) {
                    Toast.makeText(PhraseAdapter.this.context, "Device not support copy to clipboard", 0).show();
                }
            }
        });
        TextView c = phraseViewHolder.tvTitle;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(i + 1);
        stringBuilder.append(". ");
        stringBuilder.append(b.getKorean());
        c.setText(stringBuilder.toString());
        phraseViewHolder.tvGenres.setText(b.getPinyin());
        m2928a(phraseViewHolder.tvGenres1, b.getVietnamese(), b.getSearch());
        return view;
    }
}
