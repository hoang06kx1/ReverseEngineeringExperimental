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
    private final int[] drawables = new int[]{R.drawable.icon_luyen_tap, R.drawable.icon_game_luyen_nghe, R.drawable.icon_video_bai_giang, R.drawable.icon_bang_chu_cai, R.drawable.icon_chao_hoi, R.drawable.icon_hoi_thoai_thuong_dung, R.drawable.icon_so_dem, R.drawable.icon_ngay_gio, R.drawable.icon_phuong_huong_dia_diem, R.drawable.icon_phuong_tien_di_lai, R.drawable.icon_dia_diem_nghi_ngoi, R.drawable.icon_an_uong,
    R.drawable.icon_mua_sam, R.drawable.icon_mau_sac, R.drawable.icon_thanh_pho_tinh, R.drawable.icon_quoc_gia, R.drawable.icon_danh_lam_thang_canh, R.drawable.icon_gia_dinh, R.drawable.icon_hen_ho, R.drawable.icon_khan_cap, R.drawable.icon_dau_om,
    R.drawable.icon_cau_dong_am_khac_nghia, R.drawable.icon_am_nhac_hoi_hoa, R.drawable.icon_banh_keo_do_kho, R.drawable.icon_cung_hoang_dao, R.drawable.icon_rau_cu_qua, R.drawable.icon_cac_loai_gia_vi, R.drawable.icon_dong_tu_thuong_dung, R.drawable.icon_thi_truong_chung_khoan,
    R.drawable.icon_xuat_nhap_khau, R.drawable.icon_duong_bo, R.drawable.icon_hang_khong, R.drawable.icon_duong_sat, R.drawable.icon_cu_tru, R.drawable.icon_nghe_nghiep, R.drawable.icon_viec_lam, R.drawable.icon_hon_nhan, R.drawable.icon_nong_nghiep, R.drawable.icon_con_trung, R.drawable.icon_do_dung_trong_bep,
    R.drawable.icon_do_dung_tre_em, R.drawable.icon_phong_ngu, R.drawable.icon_phong_khach, R.drawable.icon_cam_xuc, R.drawable.icon_du_lich, R.drawable.ic_dong_vat, R.drawable.icon_thien_nhien, R.drawable.icon_cac_loai_hoa, R.drawable.icon_ngan_hang, R.drawable.icon_san_bay, R.drawable.icon_giao_duc, R.drawable.icon_truong_hoc, R.drawable.icon_my_pham, R.drawable.icon_hinh_dang, R.drawable.icon_van_phong, R.drawable.icon_nau_an};

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
        //Random random = new Random();
        //int nextInt = random.nextInt(this.colors.length);
        //if (this.currentColorIndex == nextInt) {
        //    this.currentColorIndex = random.nextInt(this.colors.length);
        //} else {
        //    this.currentColorIndex = nextInt;
        //}
        //((GradientDrawable) imageView.getBackground()).setColor(this.colors[this.currentColorIndex]);
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
