package app.giaotieptienghan.model;

import android.content.Context;
import android.media.MediaPlayer.OnCompletionListener;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import app.giaotieptienghan.customview.QuizTypeOne;
import app.giaotieptienghan.customview.QuizTypeThree;
import app.giaotieptienghan.customview.QuizTypeTwoView;

/* renamed from: com.example.english.a.g */
public class QuizAdapter extends PagerAdapter {
    /* renamed from: a */
    private LayoutInflater f7860a;
    /* renamed from: b */
    private Context f7861b;
    /* renamed from: c */
    private List<PhraseItem> f7862c;

    /* renamed from: com.example.english.a.g$a */
    public interface QuizInterface {
        /* renamed from: a */
        void mo2841a(PhraseItem phraseItem, OnCompletionListener onCompletionListener);

        /* renamed from: a */
        void mo2842a(PhraseItem phraseItem, String str, boolean z);

        /* renamed from: a */
        void mo2843a(PhraseItem phraseItem, boolean z);

        /* renamed from: t */
        void minusScore();
    }

    public QuizAdapter(Context context, List<PhraseItem> list) {
        this.f7861b = context;
        this.f7862c = list;
        m9486d();
    }

    /* renamed from: d */
    private void m9486d() {
        if (this.f7860a == null) {
            this.f7860a = LayoutInflater.from(this.f7861b);
        }
    }

    /* renamed from: a */
    @Override
    public int getCount() {
        return this.f7862c.size();
    }

    /* renamed from: a */
    public int mo753a(Object obj) {
        return -2;
    }

    /* renamed from: a */
    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        viewGroup.removeView((View) obj);
    }

    /* renamed from: a */
    public void mo8066a(List<PhraseItem> list) {
        this.f7862c = list;
    }

    /* renamed from: a */
    @Override
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    /* renamed from: b */
    @Override
    public View instantiateItem(ViewGroup viewGroup, int i) {
        View c0785a;
        switch (i % 3) {
            case 0:
                c0785a = new QuizTypeOne(this.f7861b);
                ((QuizTypeOne) c0785a).mo3007a(this.f7862c, i);
                break;
            case 1:
                c0785a = new QuizTypeTwoView(this.f7861b);
                ((QuizTypeTwoView) c0785a).setData((PhraseItem) this.f7862c.get(i));
                break;
            case 2:
                c0785a = new QuizTypeThree(this.f7861b);
                ((QuizTypeThree) c0785a).setData((PhraseItem) this.f7862c.get(i));
                break;
            default:
                c0785a = null;
                break;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("myview");
        stringBuilder.append(i);
        c0785a.setTag(stringBuilder.toString());
        viewGroup.addView(c0785a, -1, -1);
        return c0785a;
    }
}
