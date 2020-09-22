package com.gengms.msarchitecture.test.page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.gengms.common.utils.toastShort
import com.gengms.msarchitecture.R
import com.gengms.msarchitecture.test.adapter.MyMultiAdapter
import com.gengms.msarchitecture.test.bean.TestBean
import com.gengms.recyclerview.decoration.MsDividerItemDecoration
import com.gengms.recyclerview.selectable.SelectState_SELECTING
import kotlinx.android.synthetic.main.fragment_item_decoration.*
import kotlinx.android.synthetic.main.fragment_item_decoration.recycler
import kotlinx.android.synthetic.main.fragment_select.*

class MultiAdapterFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_item_decoration, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val itemDecoration = MsDividerItemDecoration.Builder()
            .setColor(R.color.colorPrimary)
            .setSpace(resources.getDimensionPixelSize(R.dimen.recycler_horizontal_space), 0)
            .create()
        recycler.addItemDecoration(itemDecoration)
        recycler.layoutManager = LinearLayoutManager(requireContext())
        val adapter = MyMultiAdapter(getSingleDataList())
        recycler.adapter = adapter
        adapter.setOnItemClickListener { _, position ->
            toastShort("clicked$position")
        }
        adapter.setOnSelectStateChangeListener {
            if (it == SelectState_SELECTING) {
                layout_confirm.visibility = View.VISIBLE
            } else {
                layout_confirm.visibility = View.GONE
            }
        }
    }

    private fun getSingleDataList(): List<TestBean> {
        val list:ArrayList<TestBean> = ArrayList()
        for (i in 0..20) {
            list.add(TestBean("code$i","name$i"))
        }
        return list
    }
}