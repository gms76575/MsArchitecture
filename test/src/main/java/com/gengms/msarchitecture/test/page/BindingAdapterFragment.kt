package com.gengms.msarchitecture.test.page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.gengms.binding.MsBindingViewHolder
import com.gengms.binding.MsDataBindingAdapter
import com.gengms.common.utils.toastShort
import com.gengms.msarchitecture.R
import com.gengms.msarchitecture.databinding.AdapterDatabindingBinding
import com.gengms.recyclerview.base.MsRecyclerAdapter
import com.gengms.recyclerview.base.MsViewHolder
import com.gengms.recyclerview.decoration.MsDividerItemDecoration
import kotlinx.android.synthetic.main.fragment_item_decoration.*

class BindingAdapterFragment: Fragment() {
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
        val adapter = TestBindingAdapter(getDataList())
        recycler.adapter = adapter
        adapter.setOnItemClickListener { viewHolder, _ ->
            val textView = viewHolder.itemView as TextView
            toastShort(requireContext(), textView.text.toString())
        }
    }

    private fun getDataList(): List<String> = arrayListOf("111","222","333","444","555")
}
class TestBindingAdapter(dataList : List<String>): MsDataBindingAdapter<String, AdapterDatabindingBinding>(dataList) {
    override fun getLayoutResId(viewType: Int) = R.layout.adapter_databinding

    override fun onBindItem(binding: AdapterDatabindingBinding?, data: String) {
        binding?.text = data
    }


}