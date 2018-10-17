package app.giaotieptienghan.adapter;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import app.giaotieptienghan.R;
import app.giaotieptienghan.model.MenuItem;

public class DrawerAdapter extends ArrayAdapter<MenuItem> {
    /* renamed from: a */
    private Context context;
    /* renamed from: b */
    private ArrayList<MenuItem> items;
    /* renamed from: c */
    private LayoutInflater inflater;
    /* renamed from: d */
    private final int[] colors = new int[]{-1499549, -10498817, -677838, -2735818, -10701220, -13730867, -1499549, -10498817, -677838, -2735818, -10701220};

    /* renamed from: com.example.english.a.f$a */
    private class ViewHolder {
        /* renamed from: b */
        private TextView textView;
        /* renamed from: c */
        private ImageView imageView;

        private ViewHolder() {
        }
    }

    public DrawerAdapter(Context context, ArrayList<MenuItem> arrayList) {
        super(context, 0, arrayList);
        this.context = context;
        this.items = arrayList;
        this.inflater = LayoutInflater.from(context);
    }

    /* renamed from: a */
    private void loadIcon(ImageView imageView, int i, int i2) {
        ((GradientDrawable) imageView.getBackground()).setColor(this.colors[i2]);
        imageView.setImageDrawable(this.context.getResources().getDrawable(i));
    }

    /* renamed from: a */
    public MenuItem getItem(int i) {
        return (MenuItem) this.items.get(i);
    }

    public int getCount() {
        return this.items.size();
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = this.inflater.inflate(R.layout.menu_adapter_item, null);
            viewHolder = new ViewHolder();
            viewHolder.textView = (TextView) view.findViewById(R.id.tvTitle);
            viewHolder.imageView = (ImageView) view.findViewById(R.id.icon);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        MenuItem item = getItem(i);
        view.findViewById(R.id.line).setVisibility(item.getName().equals("Update") ? View.VISIBLE : View.GONE);
        view.findViewById(R.id.tv_info).setVisibility(item.getName().equals("Update") ? View.VISIBLE : View.GONE);
        viewHolder.textView.setVisibility(item.getName().equals("Update") ? View.GONE : View.VISIBLE);
        viewHolder.imageView.setVisibility(item.getName().equals("Update") ? View.GONE : View.VISIBLE);
        if (!item.getName().equals("Update")) {
            viewHolder.textView.setText(item.getName());
            loadIcon(viewHolder.imageView, item.getImage(), i);
        }
        return view;
    }
}
