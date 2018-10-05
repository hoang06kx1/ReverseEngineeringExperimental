package app.giaotieptienghan

import android.support.v4.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.GridView
import android.widget.ProgressBar

import java.util.ArrayList

import app.giaotieptienghan.adapter.HomeAdapter
import app.giaotieptienghan.model.CategoryItem
import app.giaotieptienghan.model.MainDB
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HomeFragment : Fragment(), OnItemClickListener {
    /* renamed from: V */
    private var gridView: GridView? = null
    /* renamed from: W */
    private var categoryItems: ArrayList<CategoryItem>? = null
    /* renamed from: Y */
    private var progressBar: ProgressBar? = null

    /* renamed from: b */
    private fun initView(view: View) {
        this.gridView = view.findViewById<View>(R.id.gridView) as GridView
        this.gridView!!.onItemClickListener = this
        if (this.progressBar == null) {
            this.progressBar = view.findViewById<View>(R.id.progressBar) as ProgressBar
        }
        categoryItems = ArrayList(MainDB.getInstance(activity!!)!!.CategoryDao().getAll())
        gridView!!.adapter = HomeAdapter(activity, categoryItems)
        gridView!!.visibility = View.VISIBLE
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val inflate = inflater.inflate(R.layout.home_screen, null)
        initView(inflate)
        return inflate
    }

    override fun onItemClick(adapterView: AdapterView<*>, view: View, i: Int, j: Long) {
        val stringBuilder = StringBuilder()
        stringBuilder.append("item ")
        stringBuilder.append(this.categoryItems!![i].id)
        //C0769c.m2995b("onitemclic", stringBuilder.toString());
        //Intent intent = new Intent(getActivity(), PhraseDetail.class);
        //intent.setFlags(268435456);
        //intent.putExtra("bundle_id", categoryItem.getId());
        //intent.putExtra("bundle_title", categoryItem.getVietnamese());
        //getActivity().startActivity(intent);
    }
}