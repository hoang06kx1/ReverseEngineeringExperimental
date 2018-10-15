package app.giaotieptienghan

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.GridView
import android.widget.ProgressBar
import app.giaotieptienghan.adapter.HomeAdapter
import app.giaotieptienghan.model.CategoryItem
import java.util.*
import app.giaotieptienghan.repository.EndlessLoveDB


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
        val endlessLove = EndlessLoveDB(context)
        try {
            endlessLove.initDB()
            endlessLove.readableDB
            categoryItems = endlessLove.getFavoriteCategories("")
            categoryItems!!.add(0, CategoryItem(-1, "Practice", "Luyện tập", ""))
            categoryItems!!.add(1, CategoryItem(-2, "Listening Game", "Game luyện nghe", ""))
            categoryItems!!.add(2, CategoryItem(-3, "Learning Videos", "Video bài giảng"))
            gridView!!.adapter = HomeAdapter(activity, categoryItems)
            gridView!!.visibility = View.VISIBLE
        } catch (e: Exception) {
            e.printStackTrace()
        } catch (th: Throwable) {
            endlessLove.closeDB()
        }
        endlessLove.closeDB()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val inflate = inflater.inflate(R.layout.home_screen, null)
        initView(inflate)
        return inflate
    }

    @SuppressLint("WrongConstant")
    override fun onItemClick(adapterView: AdapterView<*>, view: View, i: Int, j: Long) {
        val categoryItem = this.categoryItems!!.get(i)
        if (categoryItem.id > 0) {
            val intent = Intent(activity, PhraseDetailActivity::class.java)
            intent.setFlags(268435456);
            intent.putExtra("bundle_id", categoryItem.id)
            intent.putExtra("bundle_title", categoryItem.vietnamese)
            activity!!.startActivity(intent)
        } else {
            when (categoryItem.id) {
                -1 -> {
                    val intent = Intent(getActivity(), QuizActivity1::class.java)
                    intent.setFlags(268435456);
                    activity!!.startActivity(intent)
                }
                -2 -> {
                    val intent = Intent(getActivity(), QuizDetail::class.java)
                    intent.setFlags(268435456);
                    activity!!.startActivity(intent)
                }
            }
        }
    }
}