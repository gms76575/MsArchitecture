package com.gengms.binding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.gengms.recyclerview.base.MsRecyclerAdapter
import com.gengms.recyclerview.base.MsViewHolder

open class MsBindingViewHolder(binding: ViewDataBinding): MsViewHolder(binding.root)

abstract class BaseDataBindingAdapter<D,B : ViewDataBinding>(private val dataList : List<D>): MsRecyclerAdapter() {
    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int): MsBindingViewHolder {
        val binding : B = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            this.getLayoutResId(viewType),parent,false
        )
        return MsBindingViewHolder(binding)
    }

    fun onBindViewHolder(holder: MsBindingViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val binding: B? = DataBindingUtil.getBinding(holder.itemView)
        this.onBindItem(binding, dataList[position])
        binding?.executePendingBindings()
    }

    @LayoutRes
    protected abstract fun getLayoutResId(viewType: Int): Int

    protected abstract fun onBindItem(
        binding: B?,
        data: D
    )
}