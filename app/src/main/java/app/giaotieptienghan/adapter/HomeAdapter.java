package app.giaotieptienghan.adapter;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ScreenUtils;

import java.util.ArrayList;
import java.util.Random;

import app.giaotieptienghan.R;
import app.giaotieptienghan.model.CategoryItem;

/* renamed from: com.example.english.a.d */
public class HomeAdapter extends ArrayAdapter<CategoryItem> {
    /* renamed from: a */
    private Context context;
    /* renamed from: b */
    private ArrayList<CategoryItem> listCategories;
    /* renamed from: c */
    private LayoutInflater layout;
    /* renamed from: d */
    private int screenWidth;
    /* renamed from: e */
    private LayoutParams itemLayoutParams;
    /* renamed from: f */
    private LinearLayout.LayoutParams imageViewLayoutParams;
    /* renamed from: g */
    private int currentColorIndex;
    /* renamed from: h */
    private final int[] colors = new int[]{-677838, -2735818, -10498817, -4449208, -10701220, -13730867};
    /* renamed from: i */
    private final int[] drawables = new int[]{R.drawable.ic_abc, R.drawable.ic_salutation, R.drawable.ic_comments16, R.drawable.ic_number, R.drawable.ic_three6, R.drawable.ic_bag2, R.drawable.ic_train15, R.drawable.ic_person193, R.drawable.ic_plate7, R.drawable.ic_bag2, R.drawable.ic_paint41, R.drawable.ic_town, R.drawable.ic_world8, R.drawable.ic_small35, R.drawable.ic_family4, R.drawable.ic_love4, R.drawable.ic_first12, R.drawable.ic_stethoscope2, R.drawable.ic_binoculars8, R.drawable.ic_music, R.drawable.ic_keo, R.drawable.ic_cunghd, R.drawable.ic_vegetable, R.drawable.ic_giavi, R.drawable.ic_common_verb, R.drawable.ic_ck, R.drawable.ic_xnk, R.drawable.ic_road, R.drawable.ic_plan, R.drawable.ic_rail, R.drawable.ic_town, R.drawable.ic_work, R.drawable.ic_work, R.drawable.ic_family4, R.drawable.ic_nature, R.drawable.ic_bug, R.drawable.ic_cook, R.drawable.ic_closthers, R.drawable.ic_person193, R.drawable.ic_holiday, R.drawable.ic_emotion, R.drawable.ic_map49, R.drawable.ic_animal, R.drawable.ic_nature, R.drawable.ic_flower, R.drawable.ic_ck, R.drawable.ic_xnk, R.drawable.ic_town, R.drawable.ic_town, R.drawable.ic_heart203, R.drawable.ic_cunghd, R.drawable.ic_town, R.drawable.ic_giavi, R.drawable.ic_emotion};

    /* renamed from: com.example.english.a.d$a */
    private class ViewHolder {
        /* renamed from: b */
        private TextView textView;
        /* renamed from: c */
        private ImageView imageView;

        private ViewHolder() {
        }
    }

    public HomeAdapter(Context context, ArrayList<CategoryItem> arrayList) {
        super(context, 0, arrayList);
        try {
            this.context = context;
            this.listCategories = arrayList;
            this.screenWidth = ScreenUtils.getScreenWidth();
            this.itemLayoutParams = new LayoutParams((this.screenWidth / 3) - 12, (this.screenWidth / 3) - 12);
            this.imageViewLayoutParams = new LinearLayout.LayoutParams(this.screenWidth / 6, this.screenWidth / 6);
            this.imageViewLayoutParams.gravity = 1;
            this.layout = LayoutInflater.from(context);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    private void setBgColor(ImageView imageView, int i) {
        Random random = new Random();
        int nextInt = random.nextInt(this.colors.length);
        if (this.currentColorIndex == nextInt) {
            this.currentColorIndex = random.nextInt(this.colors.length);
        } else {
            this.currentColorIndex = nextInt;
        }
        ((GradientDrawable) imageView.getBackground()).setColor(this.colors[this.currentColorIndex]);
        imageView.setImageDrawable(this.context.getResources().getDrawable(this.drawables[i]));
    }

    /* renamed from: a */
    public CategoryItem getItem(int i) {
        return (CategoryItem) this.listCategories.get(i);
    }

    public int getCount() {
        return this.listCategories.size();
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = this.layout.inflate(R.layout.home_adapter_item, null);
            viewHolder = new ViewHolder();
            viewHolder.textView = (TextView) view.findViewById(R.id.tvTitle);
            viewHolder.imageView = (ImageView) view.findViewById(R.id.imgView);
            view.setLayoutParams(this.itemLayoutParams);
            viewHolder.imageView.setLayoutParams(this.imageViewLayoutParams);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.textView.setText(getItem(i).getVietnamese());
        setBgColor(viewHolder.imageView, i);
        return view;
    }
}
