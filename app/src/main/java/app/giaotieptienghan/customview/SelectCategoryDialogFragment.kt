package app.giaotieptienghan.customview

import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.RadioButton
import app.giaotieptienghan.QuizActivity1
import app.giaotieptienghan.R
import app.giaotieptienghan.model.CategoryItem
import app.giaotieptienghan.repository.AppPreference

class SelectCategoryDialogFragment: DialogFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.layout_select_category_fragment, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<ListView>(R.id.lv_category).adapter = CategoryAdapter(activity!!, (activity as QuizActivity1).categoryItems)
    }

    inner class CategoryAdapter(context: Context, items: ArrayList<CategoryItem>): ArrayAdapter<CategoryItem>(context, android.R.layout.activity_list_item, items) {
        private val appPreference = AppPreference(context)
        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            var mConvertView = convertView
            val category = getItem(position)
            if (mConvertView == null) {
                mConvertView = LayoutInflater.from(context).inflate(R.layout.item_select_category, parent, false)
            }
            mConvertView!!.findViewById<RadioButton>(R.id.rb_category).apply {
                text = category.vietnamese
                isChecked = appPreference.selectedPracticeId == category.id.toString()
                setOnClickListener {
                    appPreference.selectedPracticeId = category.id.toString()
                    notifyDataSetChanged()
                    (this@SelectCategoryDialogFragment.activity as QuizActivity1).initViews()
                    (this@SelectCategoryDialogFragment.activity as QuizActivity1).initBundle(category.id)
                    this@SelectCategoryDialogFragment.dismiss()
                }
            }
            return mConvertView
        }
    }
}
