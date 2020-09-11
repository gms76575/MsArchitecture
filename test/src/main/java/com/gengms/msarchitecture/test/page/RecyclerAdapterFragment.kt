package com.gengms.msarchitecture.test.page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.gengms.common.utils.toastShort
import com.gengms.msarchitecture.R
import com.gengms.recyclerview.base.MsRecyclerAdapter
import com.gengms.recyclerview.base.MsViewHolder
import com.gengms.recyclerview.decoration.MsDividerItemDecoration
import kotlinx.android.synthetic.main.fragment_item_decoration.*

class RecyclerAdapterFragment: Fragment() {
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
        val adapter = TestRecyclerAdapter()
        recycler.adapter = adapter
        adapter.setOnItemClickListener { viewHolder, position ->
            val textView = viewHolder.itemView as TextView
            toastShort(requireContext(), textView.text.toString())
        }
    }
}
class TestRecyclerAdapter: MsRecyclerAdapter() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MsViewHolder {
        val textView = TextView(parent.context)
        textView.setPadding(24, 15, 24, 15)
        return MsViewHolder(textView)
    }

    override fun getItemCount(): Int = 20

    override fun onBindViewHolder(holder: MsViewHolder, position: Int) {
        val textView = holder.itemView as TextView
        textView.text = "Test item $position"
    }

}