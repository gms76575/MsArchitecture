package com.gengms.architecture.binding

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class MsBindingFragment : Fragment() {
    protected var mAnimationLoaded = false
    private var mBinding: ViewDataBinding? = null
    protected abstract val dataBindingConfig: MsBindingConfig

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val dataBindingConfig = dataBindingConfig
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            inflater,
            dataBindingConfig.layout,
            container,
            false
        )
        binding.lifecycleOwner = this
        val variables: SparseArray<*> = dataBindingConfig.getVariables()
        val length = variables.size()
        for (i in 0..length) {
            binding.setVariable(variables.keyAt(i), variables.valueAt(i))
        }
        mBinding = binding
        return binding.root
    }

    override fun onCreateAnimation(
        transit: Int,
        enter: Boolean,
        nextAnim: Int
    ): Animation? {
        //TODO 错开动画转场与 UI 刷新的时机，避免掉帧卡顿的现象
        HANDLER.postDelayed({
            if (!mAnimationLoaded) {
                mAnimationLoaded = true
                loadInitData()
            }
        }, 280)
        return super.onCreateAnimation(transit, enter, nextAnim)
    }

    protected fun loadInitData() {}

    companion object {
        private val HANDLER = Handler(Looper.getMainLooper())
    }
}