package com.gengms.msarchitecture.test.page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gengms.msarchitecture.R
import com.gengms.recyclerview.decoration.MsDividerItemDecoration
import kotlinx.android.synthetic.main.fragment_item_decoration.*

class ItemDecorationFragment: Fragment() {
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
        recycler.adapter = TestAdapter()
    }
}
class TestViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

}
class TestAdapter: RecyclerView.Adapter<TestViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = TestViewHolder(TextView(parent.context))

    override fun getItemCount(): Int = 20

    override fun onBindViewHolder(holder: TestViewHolder, position: Int) {
        val textView = holder.itemView as TextView
        textView.text = "Test item $position"
    }

}